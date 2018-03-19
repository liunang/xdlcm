package com.nantian.nfcm.bms.firm.service.impl;

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

import com.nantian.nfcm.bms.firm.dao.TagBatchDao;
import com.nantian.nfcm.bms.firm.entity.FirmInfo;
import com.nantian.nfcm.bms.firm.entity.TagBatch;
import com.nantian.nfcm.bms.firm.service.TagBatchService;
import com.nantian.nfcm.bms.firm.vo.TagBatchBean;
import com.nantian.nfcm.util.ServiceException;
import com.nantian.nfcm.util.vo.GridData;

@Service
public class TagBatchServiceImpl implements TagBatchService {

private TagBatchDao tagBatchDao;
	
	@Autowired
	public TagBatchServiceImpl(TagBatchDao tagBatchDao)
	{
		this.tagBatchDao= tagBatchDao;
	}
	@Override
	public void addTagBatch() throws ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public GridData<TagBatchBean> findByCondition(int page, int size, TagBatchBean tagBatchBean)
			throws ServiceException {
		Pageable pageable = new PageRequest(page, size);
        Specification<TagBatch> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (tagBatchBean.getFirmNum() != null && !tagBatchBean.getFirmNum().equals("")) {
                Predicate firmNum = criteriaBuilder.equal(root.get("firmInfo").get("firmNum").as(String.class), tagBatchBean.getFirmNum());
                predicates.add(firmNum);
            }
            if (tagBatchBean.getBatchOperator() != null && !tagBatchBean.getBatchOperator().equals("")) {
                Predicate batchOperator = criteriaBuilder.equal(root.get("batchOperator").as(String.class), tagBatchBean.getBatchOperator());
                predicates.add(batchOperator);
            }
           
            query.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
            return query.getRestriction();
        };
        Page<TagBatch> tagBatchPage = tagBatchDao.findAll(specification, pageable);
        List<TagBatch> tagBatchs = tagBatchPage.getContent();
        List<TagBatchBean> tagBatchBeans = new ArrayList<>();
        for (TagBatch tagBatch: tagBatchs) {
            TagBatchBean tagBatchBeanRet = po2vo(tagBatch);
            tagBatchBeans.add(tagBatchBeanRet);
        }
        GridData<TagBatchBean> gridData = new GridData<>();
        gridData.setData(tagBatchBeans);
        gridData.setNumber(tagBatchPage.getTotalElements());
        gridData.setPage(tagBatchPage.getNumber());
        gridData.setTotalPage(tagBatchPage.getTotalPages());
        return gridData;
	}

	@Override
	public TagBatchBean findById(Long batchId) throws ServiceException {
		 TagBatch tagBatch = tagBatchDao.findOne(batchId);
	        if (tagBatch == null) {
	            return null;
	        }
	        return po2vo(tagBatch);
	}
	
	private TagBatch vo2po(TagBatchBean tagBatchBean) {
		TagBatch tagBatch = new TagBatch();
        BeanUtils.copyProperties(tagBatchBean, tagBatch);
        String firmNum = tagBatchBean.getFirmNum();
        FirmInfo firmInfo = new FirmInfo(firmNum);
        tagBatch.setFirmInfo(firmInfo);
        return tagBatch;
    }

    private TagBatchBean po2vo(TagBatch tagBatch) {
    	TagBatchBean tagBatchBean = new TagBatchBean();
        BeanUtils.copyProperties(tagBatch, tagBatchBean);
        FirmInfo firmInfo = tagBatch.getFirmInfo();
        if (firmInfo != null) {
        	tagBatchBean.setFirmNum(firmInfo.getFirmNum());
        	tagBatchBean.setFirmName(firmInfo.getFirmName());
        }
        return tagBatchBean;
    }

}
