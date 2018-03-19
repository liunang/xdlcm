package com.nantian.nfcm.bms.firm.service.impl;

import com.nantian.nfcm.bms.firm.dao.ProductSerialDao;
import com.nantian.nfcm.bms.firm.entity.ProductSerial;
import com.nantian.nfcm.bms.firm.service.ProductSerialService;
import com.nantian.nfcm.bms.firm.vo.ProductSerialBean;
import com.nantian.nfcm.util.ServiceException;
import com.nantian.nfcm.util.vo.GridData;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductSerialServiceImpl implements ProductSerialService {
    private ProductSerialDao productSerialDao;

    @Autowired
    public ProductSerialServiceImpl(ProductSerialDao productSerialDao) {
        this.productSerialDao = productSerialDao;
    }

    public void addProductSerial(ProductSerial productSerial) throws ServiceException {
        String productSerialNum = productSerial.getProductSerialNum();
        ProductSerial productSerialRet = productSerialDao.findOne(productSerialNum);
        if (productSerialRet != null) {
            throw new ServiceException("产品序列已存在[添加失败]");
        } else {
            productSerialDao.save(productSerial);
        }
    }

    public ProductSerial findById(String productSerialNum) throws ServiceException {
        return productSerialDao.findOne(productSerialNum);
    }

    public GridData<ProductSerialBean> findByCondition(int page, int size, ProductSerialBean productSerialBean) throws ServiceException {
        Pageable pageable = new PageRequest(page, size);
        Specification<ProductSerial> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (productSerialBean.getFirmNum() != null && !productSerialBean.getFirmNum().equals("")) {
                Predicate firmNum = criteriaBuilder.equal(root.get("firmInfo").get("firmNum").as(String.class), productSerialBean.getFirmNum());
                predicates.add(firmNum);
            }
            if (productSerialBean.getProductNum() != null && !productSerialBean.getProductNum().equals("")) {
                Predicate productNum = criteriaBuilder.like(root.get("productInfo").get("productNum").as(String.class), "%" + productSerialBean.getProductName() + "%");
                predicates.add(productNum);
            }

            query.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
            return query.getRestriction();
        };
        Page<ProductSerial> productSerialPage = productSerialDao.findAll(specification, pageable);
        List<ProductSerial> productSerials = productSerialPage.getContent();
        List<ProductSerialBean> productSerialBeans = new ArrayList<>();
        for (ProductSerial productSerial : productSerials) {
            ProductSerialBean productSerialBeanRet = po2vo(productSerial);
            productSerialBeans.add(productSerialBeanRet);
        }
        GridData<ProductSerialBean> gridData = new GridData<>();
        gridData.setData(productSerialBeans);
        gridData.setNumber(productSerialPage.getTotalElements());
        gridData.setPage(productSerialPage.getNumber());
        gridData.setTotalPage(productSerialPage.getTotalPages());
        return gridData;
    }

    //TODO
    private ProductSerial vo2po (ProductSerialBean productSerialBean) {
        ProductSerial productSerial = new ProductSerial();
        return productSerial;
    }

    //TODO
    private ProductSerialBean po2vo (ProductSerial productSerial) {
        ProductSerialBean productSerialBean = new ProductSerialBean();
        BeanUtils.copyProperties(productSerial,productSerialBean);
        if(productSerial.getFirmInfo()!=null){
            productSerialBean.setFirmNum(productSerial.getFirmInfo().getFirmNum());
            productSerialBean.setFirmName(productSerial.getFirmInfo().getFirmName());
        }
        if (productSerial.getProductInfo()!=null){
            productSerialBean.setProductNum(productSerial.getProductInfo().getProductNum());
            productSerialBean.setProductName(productSerial.getProductInfo().getProductName());
        }
        return productSerialBean;
    }
}
