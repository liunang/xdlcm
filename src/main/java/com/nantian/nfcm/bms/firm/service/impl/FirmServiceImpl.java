package com.nantian.nfcm.bms.firm.service.impl;

import com.nantian.nfcm.bms.firm.dao.FirmDao;
import com.nantian.nfcm.bms.firm.entity.FirmInfo;
import com.nantian.nfcm.bms.firm.service.FirmService;
import com.nantian.nfcm.util.ServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class FirmServiceImpl implements FirmService {
    private FirmDao firmDao;

    @Autowired
    public FirmServiceImpl(FirmDao firmDao) {
        this.firmDao = firmDao;
    }

    @Transactional
    public FirmInfo addFirmInfo(FirmInfo firmInfo) throws ServiceException {
        String firmNum = firmInfo.getFirmNum();
        FirmInfo firm = firmDao.findOne(firmNum);
        if (firm != null) {
            throw new ServiceException("厂商信息已存在[添加失败]");
        } else {
            return firmDao.save(firmInfo);
        }
    }

    @Transactional
    public void delFirmInfo(FirmInfo firmInfo) throws ServiceException {
        String firmNum = firmInfo.getFirmNum();
        FirmInfo firm = firmDao.findOne(firmNum);
        if (firm == null) {
            throw new ServiceException("厂商信息不存在[删除失败]");
        } else {
            firmDao.delete(firm);
        }
    }

    @Transactional
    public FirmInfo updateFirmInfo(FirmInfo firmInfo) throws ServiceException {
        String firmNum = firmInfo.getFirmNum();
        FirmInfo firm = firmDao.findOne(firmNum);
        if (firm == null) {
            throw new ServiceException("厂商信息不存在[修改失败]");
        } else {
            BeanUtils.copyProperties(firmInfo, firm);
            return firmDao.save(firm);
        }
    }

    public FirmInfo findById(String firmNum) throws ServiceException {
        return firmDao.findOne(firmNum);
    }

    public Page<FirmInfo> findByCondition(int page, int size, FirmInfo firmInfo) throws ServiceException {
        Pageable pageable = new PageRequest(page, size);
        Specification<FirmInfo> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (firmInfo.getFirmNum() != null && !firmInfo.getFirmNum().equals("")) {
                Predicate firmNum = criteriaBuilder.equal(root.get("firmNum").as(String.class), firmInfo.getFirmNum());
                predicates.add(firmNum);
            }
            if (firmInfo.getContact() != null && !firmInfo.getContact().equals("")) {
                Predicate contact = criteriaBuilder.like(root.get("contact").as(String.class), "%" + firmInfo.getContact() + "%");
                predicates.add(contact);
            }
            if (firmInfo.getFirmName() != null && !firmInfo.getFirmName().equals("")) {
                Predicate firmName = criteriaBuilder.like(root.get("firmName").as(String.class), "%" + firmInfo.getFirmName() + "%");
                predicates.add(firmName);
            }
            if (firmInfo.getMobilePhone() != null && !firmInfo.getMobilePhone().equals("")) {
                Predicate mobilePhone = criteriaBuilder.equal(root.get("mobilePhone").as(String.class), firmInfo.getMobilePhone());
                predicates.add(mobilePhone);
            }

            query.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
            return query.getRestriction();
        };
        return firmDao.findAll(specification, pageable);
    }

	@Override
	public List queryFirmOptions() throws ServiceException {
		List<FirmInfo> firmList = firmDao.findAll();
		return firmList;
	}
}
