package com.nantian.nfcm.bms.firm.service;

import com.nantian.nfcm.bms.firm.vo.TagBean;
import com.nantian.nfcm.util.ServiceException;
import com.nantian.nfcm.util.vo.GridData;

public interface TagService {
    /**
     *  根据条件分页查询标签信息
     * @param page
     * @param size
     * @param tagBean
     * @return GridData<TagBean>
     * @throws ServiceException
     */
    public GridData<TagBean> findByCondition(int page,int size, TagBean tagBean) throws ServiceException;
    
    /**
     * 根据ID查询标签信息
     *
     * @param tagNum
     * @return ProductBean
     * @throws ServiceException
     */
    public TagBean findById(String tagNum) throws ServiceException;

}
