package com.nantian.nfcm.bms.firm.service;

import com.nantian.nfcm.bms.firm.entity.ProductSerial;
import com.nantian.nfcm.bms.firm.vo.ProductSerialBean;
import com.nantian.nfcm.util.ServiceException;
import com.nantian.nfcm.util.vo.GridData;

public interface ProductSerialService {
    /**
     * 新增产品序列信息
     *
     * @param productSerial
     * @throws ServiceException
     */
    public void addProductSerial(ProductSerial productSerial) throws ServiceException;

    /**
     * 根据ID查询产品序列信息
     *
     * @param productSerialNum
     * @return ProductSerial
     * @throws ServiceException
     */
    public ProductSerial findById(String productSerialNum) throws ServiceException;

    /**
     * 根据条件分页查询产品序列信息
     *
     * @param page
     * @param size
     * @param productSerialBean
     * @return GridData<ProductSerialBean>
     * @throws ServiceException
     */
    public GridData<ProductSerialBean> findByCondition(int page, int size, ProductSerialBean productSerialBean) throws ServiceException;
}
