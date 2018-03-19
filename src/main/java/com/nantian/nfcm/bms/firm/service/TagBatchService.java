package com.nantian.nfcm.bms.firm.service;

import com.nantian.nfcm.bms.firm.vo.TagBatchBean;
import com.nantian.nfcm.util.ServiceException;
import com.nantian.nfcm.util.vo.GridData;

public interface TagBatchService {
    public void addTagBatch() throws ServiceException;
    
    /**
     *  根据条件分页查询标签批次信息
     * @param page
     * @param size
     * @param tagBathcBean
     * @return GridData<TagBatchBean>
     * @throws ServiceException
     */
    public GridData<TagBatchBean> findByCondition(int page,int size, TagBatchBean tagBatchBean) throws ServiceException;
    
    /**
     * 根据ID查询标签批次信息
     *
     * @param batchId
     * @return ProductBean
     * @throws ServiceException
     */
    public TagBatchBean findById(Long batchId) throws ServiceException;
}
