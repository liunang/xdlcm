package com.nantian.nfcm.ca.service;

import com.nantian.nfcm.ca.vo.KeyBean;
import com.nantian.nfcm.util.ServiceException;
import com.nantian.nfcm.util.vo.GridData;

public interface KeyInfoService {
    
    /**
     *  根据条件分页查询密钥信息
     * @param page
     * @param size
     * @param keyBean
     * @return GridData<TagBatchBean>
     * @throws ServiceException
     */
    public GridData<KeyBean> findByCondition(int page,int size,KeyBean keyBean) throws ServiceException;
}
