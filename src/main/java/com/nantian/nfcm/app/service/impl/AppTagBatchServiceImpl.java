package com.nantian.nfcm.app.service.impl;

import com.nantian.nfcm.app.service.AppTagBatchService;
import com.nantian.nfcm.app.vo.AppTagBatch;
import com.nantian.nfcm.app.vo.AppTagBean;
import com.nantian.nfcm.bms.firm.dao.FirmDao;
import com.nantian.nfcm.bms.firm.dao.ProductDao;
import com.nantian.nfcm.bms.firm.dao.TagBatchDao;
import com.nantian.nfcm.bms.firm.dao.TagDao;
import com.nantian.nfcm.bms.firm.entity.FirmInfo;
import com.nantian.nfcm.bms.firm.entity.ProductInfo;
import com.nantian.nfcm.bms.firm.entity.TagBatch;
import com.nantian.nfcm.bms.firm.entity.TagInfo;
import com.nantian.nfcm.ca.dao.KeyInfoDao;
import com.nantian.nfcm.ca.entity.KeyInfo;
import com.nantian.nfcm.util.*;
import com.nantian.nfcm.util.dao.ParamDao;
import com.nantian.nfcm.util.entity.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class AppTagBatchServiceImpl implements AppTagBatchService {
    private FirmDao firmDao;
    private ProductDao productDao;
    private TagBatchDao tagBatchDao;
    private TagDao tagDao;
    private KeyInfoDao keyInfoDao;
    private ParamDao paramDao;

    @Autowired
    public AppTagBatchServiceImpl(FirmDao firmDao,ProductDao productDao, TagBatchDao tagBatchDao,
                                  TagDao tagDao, KeyInfoDao keyInfoDao, ParamDao paramDao) {
        this.firmDao = firmDao;
        this.productDao = productDao;
        this.tagBatchDao = tagBatchDao;
        this.tagDao = tagDao;
        this.keyInfoDao = keyInfoDao;
        this.paramDao = paramDao;
    }

    public void tagBatchInit(AppTagBatch appTagBatch) throws ServiceException {
        Param param = paramDao.findOne("RSA");
        if(param == null){
            throw new ServiceException("服务器安全密钥未导入，请联系管理员");
        }
        String paramValue = param.getParamValue();
        if (paramValue==null||paramValue.equals("")){
            throw new ServiceException("服务器安全密钥缺失，请联系管理员");
        }

        String firmNum = appTagBatch.getFirmNum();
        if (firmNum != null && !firmNum.equals("")) {
            FirmInfo firmInfo = firmDao.findOne(firmNum);
            if (firmInfo == null) {
                throw new ServiceException("厂商信息不存在");
            }
            List<TagInfo> tagInfos = appTagBatch.getTagInfos();
            if (tagInfos == null || tagInfos.size() == 0) {
                throw new ServiceException("标签批次信息为空值");
            }
            String currentTime = DateUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss");
            TagBatch tagBatch = new TagBatch();
            tagBatch.setBatchTime(currentTime);
            tagBatch.setBatchSum(tagInfos.size() + "");
            tagBatch.setBatchOperator(appTagBatch.getUserName());
            tagBatch.setFirmInfo(firmInfo);
            tagBatch = tagBatchDao.save(tagBatch);

            for (TagInfo tagInfo : tagInfos) {
                String tagNum = tagInfo.getTagNum();
                TagInfo tagInfoRet = tagDao.findOne(tagNum);
                if (tagInfoRet != null) {
                    throw new ServiceException("标签[" + tagNum + "]已被使用");
                }
                tagInfo.setBatchId(tagBatch.getBatchId());
                tagInfo.setTagState(BaseConst.TAGINIT);
                tagInfo.setTagInitTime(currentTime);
                tagInfo.setFirmInfo(firmInfo);

                KeyInfo keyInfo = new KeyInfo();
                keyInfo.setTagNum(tagNum);
                keyInfo.setBatchId(tagBatch.getBatchId());
                String keyValue = BaseUtil.getMD5Encode(tagNum);
                keyInfo.setKeyValue(keyValue);
                String keyCiphertext = CipherUtil.encryptByPrivateKey(keyValue.getBytes(),paramValue);
                if(keyCiphertext == null){
                    throw new ServiceException("标签["+ tagNum +"]加密失败");
                }
                keyInfo.setKeyCiphertext(keyCiphertext);
                keyInfo.setKeyInitTime(currentTime);
                keyInfo.setKeyType("AES");
                keyInfoDao.save(keyInfo);

                tagInfo.setTagCiphertext(keyCiphertext);
                tagInfo.setTagKey(keyValue);
                tagDao.save(tagInfo);
            }
        } else {
            throw new ServiceException("厂商信息为空值");
        }
    }

    public String tagInfoActive(AppTagBatch appTagBatch) throws ServiceException {
        String currentTime = DateUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss");
        String errInfo="";
        List<TagInfo> tagInfos = appTagBatch.getTagInfos();
        for (TagInfo tagInfo : tagInfos) {
            String tagNum = tagInfo.getTagNum();
            TagInfo tagInfoRet = tagDao.findOne(tagNum);
            if(tagInfoRet!=null){
                String tagState = tagInfoRet.getTagState();
                if(BaseConst.TAGINIT.equals(tagState)){
                    tagInfoRet.setTagState(BaseConst.TAGACTIVE);
                    tagInfoRet.setTagAllocateTime(currentTime);
                    String productNum =appTagBatch.getProductNum();
                    ProductInfo productInfo = productDao.findOne(productNum);
                    if(productInfo!=null){
                        tagInfoRet.setProductInfo(productInfo);
                        String productSerial = tagInfo.getProductSerial();
                        tagInfoRet.setProductSerial(tagInfoRet.getFirmInfo().getFirmNum()+productNum+productSerial);
                        tagDao.save(tagInfoRet);
                    }
                    else {
                        errInfo+="["+tagNum+"]所选择产品["+productNum+"]不存在|";
                    }
                }
                else
                {
                    errInfo+="["+tagNum+"]状态错误|";
                }
            }
            else{
                errInfo+="["+tagNum+"]不存在|";
            }
        }
        return errInfo;
    }

    public String tagInfoCheck(AppTagBean appTagBean) throws ServiceException {
        StringBuilder str = new StringBuilder();
        String tagNum = appTagBean.getTagNum();
        if(tagNum!=null&&!"".equals(tagNum))
        {
        	TagInfo tagInfo = tagDao.findOne(tagNum);
        	if(tagInfo!=null)
        	{
        		String tagState = tagInfo.getTagState();
                if(BaseConst.TAGACTIVE.equals(tagState)){
                    String tagKey = tagInfo.getTagKey();
                    if(tagKey!=null && !tagKey.equals("")){
                        String cipherText = appTagBean.getTagCiphertext();
                        if(cipherText == null || cipherText.equals("")){
                            throw new ServiceException("["+tagNum+"]验证异常");
                        }
                        String decode = CipherUtil.decryptByAES(cipherText,tagKey);
                        if(!decode.equals(tagNum)){
                            throw new ServiceException("["+tagNum+"]验证异常");
                        }
                    }
                    else{
                        throw new ServiceException("["+tagNum+"]验证异常");
                    }
                    str.append("标签编号:"+tagNum+"|");
                    str.append("茶叶厂家:"+tagInfo.getFirmInfo().getFirmName()+"|");
                    str.append("茶叶名称:"+tagInfo.getProductInfo().getProductName()+"|");
                    str.append("茶叶生产日期:"+tagInfo.getProductInfo().getProductionDate()+"|");
                    str.append("茶叶QS:"+tagInfo.getProductInfo().getQsNum()+"|");
                }
                else {
                    throw new ServiceException("["+tagNum+"]存在异常");
                }
        	}
        	else {
        		throw new ServiceException("["+tagNum+"]标签不存在");
        	}
        }
        else
        {
        	throw new ServiceException("["+tagNum+"]为空");
        }
        
        return str.toString();
    }
    
    @Override
	public String getImageUrlByTag(AppTagBean appTagBean) throws ServiceException {
		StringBuilder str = new StringBuilder();
        String tagNum = appTagBean.getTagNum();
        if(tagNum!=null&&!"".equals(tagNum))
        {
	        TagInfo tagInfo = tagDao.findOne(tagNum);
	        if(tagInfo!=null)
        	{
		        String tagState = tagInfo.getTagState();
		        if(BaseConst.TAGACTIVE.equals(tagState)){
		        	String productPicture = tagInfo.getProductInfo().getPictureUrl();
		        	if(productPicture!=null&&!"".equals(productPicture))
		        	{
		        		String[] productPictures =productPicture.split("#");
		            	for(int i=0;i<productPictures.length;i++)
		            	{
		            		String productNum = tagInfo.getProductInfo().getProductNum();
		            		File tempFile = new File(productPictures[i]);
		            		str.append(tempFile.getName()+":"+productNum+"/"+tempFile.getName()+"|");
		            	}
		        	}
		        }
		        else {
		            throw new ServiceException("["+tagNum+"]存在异常");
		        }
        	}
	        else {
        		throw new ServiceException("["+tagNum+"]标签不存在");
        	}
        }
        else
        {
        	throw new ServiceException("["+tagNum+"]为空");
        }
        return str.toString();
	}
    
    @Override
   	public String getMovieUrlByTag(AppTagBean appTagBean) throws ServiceException {
   		StringBuilder str = new StringBuilder();
           String tagNum = appTagBean.getTagNum();
           if(tagNum!=null&&!"".equals(tagNum))
           {
	           TagInfo tagInfo = tagDao.findOne(tagNum);
	           if(tagInfo!=null)
	        	{
		           String tagState = tagInfo.getTagState();
		           if(BaseConst.TAGACTIVE.equals(tagState)){
		           	String productMovie = tagInfo.getProductInfo().getMovieUrl();
		           	if(productMovie!=null&&!"".equals(productMovie))
		           	{
		           		String[] productPictures =productMovie.split("#");
		               	for(int i=0;i<productPictures.length;i++)
		               	{
		               		String productNum = tagInfo.getProductInfo().getProductNum();
		               		File tempFile = new File(productPictures[i]);
		               		//str.append(tempFile.getName()+":appDownload/down?fileName="+tempFile.getName()+"&productNum="+productNum+"|");
		               		str.append(tempFile.getName()+":"+productNum+"/"+tempFile.getName()+"|");
		               	}
		           	}
		           	
		           }
		           else {
		               throw new ServiceException("["+tagNum+"]存在异常");
		           }
	        	}
	           	else {
	        		throw new ServiceException("["+tagNum+"]标签不存在");
	        	}
           }
           else
           {
           	throw new ServiceException("["+tagNum+"]为空");
           }
           return str.toString();
   	}
}
