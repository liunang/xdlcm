package com.nantian.nfcm.bms.firm.service;

import com.nantian.nfcm.bms.firm.vo.FirmUserBean;
import com.nantian.nfcm.util.ServiceException;
import com.nantian.nfcm.util.vo.GridData;

public interface FirmUserService {
    /**
     * 新增厂商用户信息
     *
     * @param firmUserBean
     * @return FirmUserBean
     * @throws ServiceException
     */
    public FirmUserBean addFirmUser(FirmUserBean firmUserBean) throws ServiceException;

    /**
     * 删除厂商用户信息
     *
     * @param firmUserBean
     * @throws ServiceException
     */
    public void delFirmUser(FirmUserBean firmUserBean) throws ServiceException;

    /**
     * 更新厂商用户信息
     *
     * @param firmUserBean
     * @return FirmUserBean
     * @throws ServiceException
     */
    public FirmUserBean updateFirmUser(FirmUserBean firmUserBean) throws ServiceException;

    /**
     * 根据ID查询厂商用户信息
     *
     * @param firmUserId
     * @return FirmUserBean
     * @throws ServiceException
     */
    public FirmUserBean findByid(String firmUserId) throws ServiceException;

    /**
     * 根据条件分页查询厂商用户信息
     *
     * @param page
     * @param size
     * @param firmUserBean
     * @return GridData
     * @throws ServiceException
     */
    public GridData<FirmUserBean> findByCondition(int page, int size, FirmUserBean firmUserBean) throws ServiceException;

    /**
     * 厂商用户信息确认
     *
     * @param firmUserBean
     * @return FirmUserBean
     * @throws ServiceException
     */
    public FirmUserBean confirmFirmUser(FirmUserBean firmUserBean) throws ServiceException;
    
    
    /**
	 * 重置密码
	 * @param firmUserBean
	 * @return
	 * @throws ServiceException
	 */
	public FirmUserBean resetUserPwd(FirmUserBean firmUserBean) throws ServiceException;
}
