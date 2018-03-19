package com.nantian.nfcm.bms.firm.service.impl;

import com.nantian.nfcm.bms.firm.dao.TagDao;
import com.nantian.nfcm.bms.firm.entity.FirmInfo;
import com.nantian.nfcm.bms.firm.entity.ProductInfo;
import com.nantian.nfcm.bms.firm.entity.TagInfo;
import com.nantian.nfcm.bms.firm.service.TagService;
import com.nantian.nfcm.bms.firm.vo.TagBean;
import com.nantian.nfcm.util.ServiceException;
import com.nantian.nfcm.util.vo.GridData;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service
public class TagServiceImpl implements TagService{
	private TagDao tagDao;
	
	@Autowired
	public TagServiceImpl(TagDao tagDao)
	{
		this.tagDao= tagDao;
	}

    public GridData<TagBean> findByCondition(int page, int size, TagBean tagBean) throws ServiceException {
    	Pageable pageable = new PageRequest(page, size);
        Specification<TagInfo> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (tagBean.getFirmNum() != null && !tagBean.getFirmNum().equals("")) {
                Predicate firmNum = criteriaBuilder.equal(root.get("firmInfo").get("firmNum").as(String.class), tagBean.getFirmNum());
                predicates.add(firmNum);
            }
            //标签序列号
            if (tagBean.getTagNum() != null && !tagBean.getTagNum().equals("")) {
                Predicate tagNum = criteriaBuilder.equal(root.get("tagNum").as(String.class), tagBean.getTagNum());
                predicates.add(tagNum);
            }
            //标签品牌
            if (tagBean.getTagSp() != null && !tagBean.getTagSp().equals("")) {
                Predicate tagSp = criteriaBuilder.equal(root.get("tagSp").as(String.class), tagBean.getTagSp());
                predicates.add(tagSp);
            }
          //标签批次ID
            if (tagBean.getBatchId() != null && !tagBean.getBatchId().equals("")) {
                Predicate batchId = criteriaBuilder.equal(root.get("batchId").as(String.class), tagBean.getBatchId());
                predicates.add(batchId);
            }
            /*if (tagBean.getProductName() != null && !tagBean.getProductName().equals("")) {
                Predicate productName = criteriaBuilder.like(root.get("productName").as(String.class), "%" + tagBean.getProductName() + "%");
                predicates.add(productName);
            }*/
            query.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
            return query.getRestriction();
        };
        Page<TagInfo> tagInfoPage = tagDao.findAll(specification, pageable);
        List<TagInfo> tagInfos = tagInfoPage.getContent();
        List<TagBean> tagBeans = new ArrayList<>();
        for (TagInfo tagInfo : tagInfos) {
            TagBean tagBeanRet = po2vo(tagInfo);
            tagBeans.add(tagBeanRet);
        }
        GridData<TagBean> gridData = new GridData<>();
        gridData.setData(tagBeans);
        gridData.setNumber(tagInfoPage.getTotalElements());
        gridData.setPage(tagInfoPage.getNumber());
        gridData.setTotalPage(tagInfoPage.getTotalPages());
        return gridData;
    }

	@Override
	public TagBean findById(String tagNum) throws ServiceException {
		 TagInfo tagInfo = tagDao.findOne(tagNum);
	        if (tagInfo == null) {
	            return null;
	        }
	        return po2vo(tagInfo);
	}
	
	private TagInfo vo2po(TagBean tagBean) {
		TagInfo tagInfo = new TagInfo();
        BeanUtils.copyProperties(tagBean, tagInfo);
        String firmNum = tagBean.getFirmNum();
        FirmInfo firmInfo = new FirmInfo(firmNum);
        tagInfo.setFirmInfo(firmInfo);
        String productNum = tagBean.getProductNum();
        ProductInfo productInfo = new ProductInfo(productNum);
        tagInfo.setProductInfo(productInfo);
        return tagInfo;
    }

    private TagBean po2vo(TagInfo tagInfo) {
    	TagBean tagBean = new TagBean();
        BeanUtils.copyProperties(tagInfo, tagBean);
        FirmInfo firmInfo = tagInfo.getFirmInfo();
        if (firmInfo != null) {
        	tagBean.setFirmNum(firmInfo.getFirmNum());
        	tagBean.setFirmName(firmInfo.getFirmName());
        }
        ProductInfo productInfo = tagInfo.getProductInfo();
        if(productInfo !=null)
        {
        	tagBean.setProductNum(productInfo.getProductNum());
        	tagBean.setProductName(productInfo.getProductName());
        }
        return tagBean;
    }
}
