package com.nantian.nfcm.bms.firm.service;

import com.nantian.nfcm.bms.firm.entity.FirmInfo;
import com.nantian.nfcm.util.ServiceException;

import java.util.List;

import org.springframework.data.domain.Page;

public interface FirmService {
    /**
     * 添加厂商信息
     *
     * @param firmInfo
     * @return FirmInfo
     * @throws ServiceException
     */
    public FirmInfo addFirmInfo(FirmInfo firmInfo) throws ServiceException;

    /**
     * 删除厂商信息
     *
     * @param firmInfo
     * @throws ServiceException
     */
    public void delFirmInfo(FirmInfo firmInfo) throws ServiceException;

    /**
     * 修改厂商信息
     *
     * @param firmInfo
     * @return FirmInfo
     * @throws ServiceException
     */
    public FirmInfo updateFirmInfo(FirmInfo firmInfo) throws ServiceException;

    /**
     * 通过ID厂商编号查询厂商信息
     *
     * @param firmNum
     * @return FirmInfo
     * @throws ServiceException
     */
    public FirmInfo findById(String firmNum) throws ServiceException;

    /**
     * 通过条件分页查询厂商信息列表
     *
     * @param page
     * @param size
     * @param firmInfo
     * @return Page<FirmInfo>
     * @throws ServiceException
     */
    public Page<FirmInfo> findByCondition(int page, int size, FirmInfo firmInfo) throws ServiceException;
    
    public List queryFirmOptions()throws ServiceException;
}
