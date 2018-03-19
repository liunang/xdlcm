package com.nantian.nfcm.bms.firm.service.impl;

import com.nantian.nfcm.bms.firm.dao.ProductDao;
import com.nantian.nfcm.bms.firm.entity.FirmInfo;
import com.nantian.nfcm.bms.firm.entity.ProductInfo;
import com.nantian.nfcm.bms.firm.service.ProductService;
import com.nantian.nfcm.bms.firm.vo.ProductBean;
import com.nantian.nfcm.util.DataUtil;
import com.nantian.nfcm.util.ServiceException;
import com.nantian.nfcm.util.vo.FileInfoBean;
import com.nantian.nfcm.util.vo.GridData;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductDao productDao;
  
    @Autowired
    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Transactional
    public ProductBean addProductInfo(ProductBean productBean) throws ServiceException {
        String productNum = productBean.getProductNum();
        ProductInfo productInfo = productDao.findOne(productNum);
        if (productInfo != null) {
            throw new ServiceException("产品信息已存在[添加失败]");
        } else {
            productInfo = vo2po(productBean);
            productDao.save(productInfo);
        }
        return po2vo(productInfo);
    }

    @Transactional
    public void delProductInfo(ProductBean productBean,String fileSavePath) throws ServiceException {
        String productNum = productBean.getProductNum();
        ProductInfo productInfo = productDao.findOne(productNum);
        if (productInfo == null) {
            throw new ServiceException("产品信息不存在[删除失败]");
        } else {
        	//删除产品图片和视频的保存目录
        	String productFileSavePath = fileSavePath+"/"+productNum;
        	File productFilePath =new File(productFileSavePath);
        	DataUtil.deleteFileOrFolder(productFilePath);
            productDao.delete(productInfo);
        }
    }

    @Transactional
    public ProductBean updateProductInfo(ProductBean productBean) throws ServiceException {
        String productNum = productBean.getProductNum();
        ProductInfo productInfo = productDao.findOne(productNum);
        if (productInfo == null) {
            throw new ServiceException("产品信息不存在[更新失败]");
        } else {
        	//防止更新文字信息时覆盖图片和视频路径信息
        	productBean.setPictureUrl(productInfo.getPictureUrl());
        	productBean.setMovieUrl(productInfo.getMovieUrl());
            productInfo = vo2po(productBean);
            productDao.save(productInfo);
        }
        return po2vo(productInfo);
    }

    @Transactional
    public ProductBean findById(String productNum) throws ServiceException {
        ProductInfo productInfo = productDao.findOne(productNum);
        if (productInfo == null) {
            return null;
        }
        return po2vo(productInfo);
    }

    @Transactional
    public GridData<ProductBean> findByCondition(int page, int size, ProductBean productBean) throws ServiceException {
        Pageable pageable = new PageRequest(page, size);
        Specification<ProductInfo> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (productBean.getFirmNum() != null && !productBean.getFirmNum().equals("")) {
                Predicate firmNum = criteriaBuilder.equal(root.get("firmInfo").get("firmNum").as(String.class), productBean.getFirmNum());
                predicates.add(firmNum);
            }
            if (productBean.getProductName() != null && !productBean.getProductName().equals("")) {
                Predicate productName = criteriaBuilder.like(root.get("productName").as(String.class), "%" + productBean.getProductName() + "%");
                predicates.add(productName);
            }
            query.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
            return query.getRestriction();
        };
        Page<ProductInfo> productInfoPage = productDao.findAll(specification, pageable);
        List<ProductInfo> productInfos = productInfoPage.getContent();
        List<ProductBean> productBeans = new ArrayList<>();
        for (ProductInfo productInfo : productInfos) {
            ProductBean productBeanRet = po2vo(productInfo);
            productBeans.add(productBeanRet);
        }
        GridData<ProductBean> gridData = new GridData<>();
        gridData.setData(productBeans);
        gridData.setNumber(productInfoPage.getTotalElements());
        gridData.setPage(productInfoPage.getNumber());
        gridData.setTotalPage(productInfoPage.getTotalPages());
        return gridData;
    }

    private ProductInfo vo2po(ProductBean productBean) {
        ProductInfo productInfo = new ProductInfo();
        BeanUtils.copyProperties(productBean, productInfo);
        String firmNum = productBean.getFirmNum();
        FirmInfo firmInfo = new FirmInfo(firmNum);
        productInfo.setFirmInfo(firmInfo);
        return productInfo;
    }

    private ProductBean po2vo(ProductInfo productInfo) {
        ProductBean productBean = new ProductBean();
        BeanUtils.copyProperties(productInfo, productBean);
        FirmInfo firmInfo = productInfo.getFirmInfo();
        if (firmInfo != null) {
            productBean.setFirmNum(firmInfo.getFirmNum());
            productBean.setFirmName(firmInfo.getFirmName());
        }
        return productBean;
    }

	@Override
	public ProductBean updatePicture(String productNum, String pictureUrl) throws ServiceException {
		 //String productNum = productBean.getProductNum();
	        ProductInfo productInfo = productDao.findOne(productNum);
	        if (productInfo == null) {
	            throw new ServiceException("产品信息不存在[图片更新失败]");
	        } else {
	           // productInfo = vo2po(productBean);
	        	String picUrl = productInfo.getPictureUrl();
	        	if(picUrl!=null&&!"".equals(picUrl))
	        	{
	        		//上传的图片信息合并上数据库中已有的图片信息
	        		pictureUrl+=picUrl;
	        	}
	        	productInfo.setPictureUrl(pictureUrl);
	            productDao.save(productInfo);
	        }
	        return po2vo(productInfo);
	}

	@Override
	public List<FileInfoBean> findImageById(String productNum) throws ServiceException {
		List<FileInfoBean> imageFiles = new ArrayList<>();
		ProductInfo productInfo = productDao.findOne(productNum);
		if (productInfo == null) {
			return null;
		} else {
			String pictureUrl = productInfo.getPictureUrl();

			if (pictureUrl != null && !"".equals(pictureUrl)) {
				String[] pictureNames = pictureUrl.split("#");
				for (int i = 0; i < pictureNames.length; i++) {
					FileInfoBean imageFile = new FileInfoBean();
					
					//imageFile.setFileUrl(pictureUrls[i]);
					//imageFile.setFileSize(fileSize);
					imageFile.setFileName(pictureNames[i]);
					imageFiles.add(imageFile);
				}
			}
		}
		return imageFiles;
	}
	
	@Override
	public ProductBean updateMovie(String productNum, String movieUrl) throws ServiceException {
		 //String productNum = productBean.getProductNum();
	        ProductInfo productInfo = productDao.findOne(productNum);
	        if (productInfo == null) {
	            throw new ServiceException("产品信息不存在[视频更新失败]");
	        } else {
	           // productInfo = vo2po(productBean);
	        	String movUrl = productInfo.getMovieUrl();
	        	if(movUrl!=null&&!"".equals(movUrl))
	        	{
	        		movieUrl+=movUrl;
	        	}
	        	productInfo.setMovieUrl(movieUrl);
	            productDao.save(productInfo);
	        }
	        return po2vo(productInfo);
	}

	@Override
	public List<FileInfoBean> findMovieById(String productNum) throws ServiceException {
		List<FileInfoBean> imageFiles = new ArrayList<>();
		ProductInfo productInfo = productDao.findOne(productNum);
		if (productInfo == null) {
			return null;
		} else {
			String movieUrl = productInfo.getMovieUrl();

			if (movieUrl != null && !"".equals(movieUrl)) {
				String[] movieNames = movieUrl.split("#");
				for (int i = 0; i < movieNames.length; i++) {
					FileInfoBean imageFile = new FileInfoBean();
					
					//imageFile.setFileUrl(movieUrls[i]);
					//imageFile.setFileSize(fileSize);
					imageFile.setFileName(movieNames[i]);
					imageFiles.add(imageFile);
				}
			}
		}
		return imageFiles;
	}

	@Override
	public FileInfoBean deleteImage(String fileName,String productNum,String savePath) throws ServiceException {
		ProductInfo productInfo = productDao.findOne(productNum);
		FileInfoBean fileInfoBean =new FileInfoBean();
		if(productInfo!=null)
		{
			String pictureUrl = productInfo.getPictureUrl();
			String[] pictureUrls = pictureUrl.split("#");
			String updatePictureUrl="";
			for(int i=0;i<pictureUrls.length;i++)
			{
				if(!pictureUrls[i].equals(fileName))
				{
					updatePictureUrl+=pictureUrls[i]+"#";
				}
			}
			productInfo.setPictureUrl(updatePictureUrl);
			productDao.save(productInfo);
			
			String path = savePath+"/"+fileName;
			File deleteImageFile = new File(path);
			deleteImageFile.delete();
			
			fileInfoBean.setFileName(fileName);
			
		}
		else {
			throw new ServiceException("产品信息不存在[图片删除失败]");
		}
		return fileInfoBean;
	}
	
	@Override
	public FileInfoBean deleteMovie(String fileName,String productNum,String savePath) throws ServiceException {
		ProductInfo productInfo = productDao.findOne(productNum);
		FileInfoBean fileInfoBean =new FileInfoBean();
		if(productInfo!=null)
		{
			String movieUrl = productInfo.getMovieUrl();
			String[] movieUrls = movieUrl.split("#");
			String updateMovieUrl="";
			for(int i=0;i<movieUrls.length;i++)
			{
				if(!movieUrls[i].equals(fileName))
				{
					updateMovieUrl+=movieUrls[i]+"#";
				}
			}
			productInfo.setMovieUrl(updateMovieUrl);
			productDao.save(productInfo);
			
			String path = savePath+"/"+fileName;
			File deleteMovieFile = new File(path);
			deleteMovieFile.delete();
			
			fileInfoBean.setFileName(fileName);
			
		}
		else {
			throw new ServiceException("产品信息不存在[视频删除失败]");
		}
		return fileInfoBean;
	}

	
}
