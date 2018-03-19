package com.nantian.nfcm.app.service;

import com.nantian.nfcm.app.vo.AppTagBatch;
import com.nantian.nfcm.app.vo.AppTagBean;
import com.nantian.nfcm.bms.firm.vo.TagBean;
import com.nantian.nfcm.util.ServiceException;

public interface AppTagBatchService {
    /**
     * 初始化标签批次信息
     * @param appTagBatch
     * @throws ServiceException
     */
    public void tagBatchInit(AppTagBatch appTagBatch) throws ServiceException;

    /**
     * 标签激活
     * @param appTagBatch
     * @throws ServiceException
     * @return String
     */
    public String tagInfoActive(AppTagBatch appTagBatch) throws ServiceException;

    /**
     * 标签防伪
     * @param appTagBean
     * @return String
     * @throws ServiceException
     */
    public String tagInfoCheck(AppTagBean appTagBean) throws ServiceException;
    
    /**
     * 根据标签信息查询产品图片URL信息
     *
     * @param appTagBean
     * @return String
     * @throws ServiceException
     */
    public String getImageUrlByTag(AppTagBean appTagBean) throws ServiceException;
    /**
     * 根据标签信息查询产品视频URL信息
     *
     * @param appTagBean
     * @return String
     * @throws ServiceException
     */
    public String getMovieUrlByTag(AppTagBean appTagBean) throws ServiceException;
}
