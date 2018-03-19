package com.nantian.nfcm.app.service;

import com.nantian.nfcm.app.vo.AppUserBean;
import com.nantian.nfcm.util.ServiceException;

public interface AppUserService {

    /**
     * 系统用户登录
     *
     * @param appUserBean
     * @return AppUserBean
     * @throws ServiceException
     */
    public AppUserBean confirmSysUser(AppUserBean appUserBean) throws ServiceException;

    /**
     * 厂商用户登录
     * @param appUserBean
     * @return AppUserBean
     * @throws ServiceException
     */
    public AppUserBean confirmFirmUser(AppUserBean appUserBean) throws ServiceException;
}
