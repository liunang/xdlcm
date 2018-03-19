package com.nantian.nfcm.ca.service.impl;

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

import com.nantian.nfcm.ca.dao.KeyInfoDao;
import com.nantian.nfcm.ca.entity.KeyInfo;
import com.nantian.nfcm.ca.service.KeyInfoService;
import com.nantian.nfcm.ca.vo.KeyBean;
import com.nantian.nfcm.util.ServiceException;
import com.nantian.nfcm.util.vo.GridData;

@Service
public class KeyInfoServiceImpl implements KeyInfoService {

private KeyInfoDao keyInfoDao;
	
	@Autowired
	public KeyInfoServiceImpl(KeyInfoDao keyInfoDao)
	{
		this.keyInfoDao= keyInfoDao;
	}

	@Override
	public GridData<KeyBean> findByCondition(int page, int size, KeyBean keyBean)
			throws ServiceException {
		Pageable pageable = new PageRequest(page, size);
        Specification<KeyInfo> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (keyBean.getBatchId() != null && !keyBean.getBatchId().equals("")) {
                Predicate batchId = criteriaBuilder.equal(root.get("batchId").as(String.class), keyBean.getBatchId());
                predicates.add(batchId);
            }
            /*if (keyBean.getStartDate() != null && !keyBean.getStartDate().equals("")&&
            		keyBean.getEndDate() != null && !keyBean.getEndDate().equals("")) {
            	//Expression<String> test =  root.get("keyInfo").get("batchId").as(String.class);
                Predicate startDate = criteriaBuilder.between(root.get("keyInitTime").as(String.class), keyBean.getStartDate(), keyBean.getEndDate());
                predicates.add(startDate);
            }*/
           /* if (tagBatchBean.getBatchOperator() != null && !tagBatchBean.getBatchOperator().equals("")) {
                Predicate batchOperator = criteriaBuilder.equal(root.get("batchOperator").as(String.class), tagBatchBean.getBatchOperator());
                predicates.add(batchOperator);
            }*/
           
            query.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
            return query.getRestriction();
        };
        Page<KeyInfo> keyInfoPage = keyInfoDao.findAll(specification, pageable);
        List<KeyInfo> keyInfos = keyInfoPage.getContent();
        List<KeyBean> keyBeans = new ArrayList<>();
        for (KeyInfo keyInfo: keyInfos) {
        	KeyBean keyBeanRet = po2vo(keyInfo);
        	keyBeans.add(keyBeanRet);
        }
        GridData<KeyBean> gridData = new GridData<>();
        gridData.setData(keyBeans);
        gridData.setNumber(keyInfoPage.getTotalElements());
        gridData.setPage(keyInfoPage.getNumber());
        gridData.setTotalPage(keyInfoPage.getTotalPages());
        return gridData;
	}
	
	private KeyInfo vo2po(KeyBean keyBean) {
		KeyInfo keyInfo = new KeyInfo();
        BeanUtils.copyProperties(keyBean, keyInfo);
        return keyInfo;
    }

    private KeyBean po2vo(KeyInfo keyInfo) {
    	KeyBean keyBean = new KeyBean();
        BeanUtils.copyProperties(keyInfo, keyBean);
        return keyBean;
    }

}
