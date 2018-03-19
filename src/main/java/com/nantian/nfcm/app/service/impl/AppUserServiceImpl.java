package com.nantian.nfcm.app.service.impl;

import com.nantian.nfcm.app.service.AppUserService;
import com.nantian.nfcm.app.vo.AppProductBean;
import com.nantian.nfcm.app.vo.AppTagBean;
import com.nantian.nfcm.app.vo.AppUserBean;
import com.nantian.nfcm.bms.auth.dao.UserInfoDao;
import com.nantian.nfcm.bms.auth.entity.UserInfo;
import com.nantian.nfcm.bms.firm.dao.FirmDao;
import com.nantian.nfcm.bms.firm.dao.FirmUserDao;
import com.nantian.nfcm.bms.firm.dao.ProductDao;
import com.nantian.nfcm.bms.firm.dao.TagDao;
import com.nantian.nfcm.bms.firm.entity.FirmInfo;
import com.nantian.nfcm.bms.firm.entity.FirmUserInfo;
import com.nantian.nfcm.bms.firm.entity.ProductInfo;
import com.nantian.nfcm.bms.firm.entity.TagInfo;
import com.nantian.nfcm.util.BaseConst;
import com.nantian.nfcm.util.BaseUtil;
import com.nantian.nfcm.util.ServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppUserServiceImpl implements AppUserService {
    private UserInfoDao userInfoDao;
    private FirmUserDao firmUserDao;
    private FirmDao firmDao;
    private ProductDao productDao;
    private TagDao tagDao;

    @Autowired
    public AppUserServiceImpl(UserInfoDao userInfoDao, FirmUserDao firmUserDao,
                              FirmDao firmDao, ProductDao productDao, TagDao tagDao) {
        this.userInfoDao = userInfoDao;
        this.firmUserDao = firmUserDao;
        this.firmDao = firmDao;
        this.productDao = productDao;
        this.tagDao = tagDao;
    }

    public AppUserBean confirmSysUser(AppUserBean appUserBean) throws ServiceException {
        String userName = appUserBean.getUserName();
        if (userName != null && !userName.equals("")) {
            UserInfo userInfo = userInfoDao.findOne(userName);
            if (userInfo != null) {
                String pwd = userInfo.getPwd();
                if (pwd != null && pwd.equals(BaseUtil.getMD5Encode(userName + appUserBean.getPwd()))) {
                    List<FirmInfo> firmInfos = firmDao.findAll();
                    appUserBean.setFirmInfos(firmInfos);
                } else {
                    throw new ServiceException("密码错误");
                }
            } else {
                throw new ServiceException("用户不存在");
            }
        } else {
            throw new ServiceException("用户名错误");
        }
        return appUserBean;
    }

    @Transactional
    public AppUserBean confirmFirmUser(AppUserBean appUserBean) throws ServiceException {
        String userName = appUserBean.getUserName();
        if (userName != null && !userName.equals("")) {
            List<FirmUserInfo> firmUserInfos = firmUserDao.findByFirmUsernameOrMobilePhoneOrEmail(userName, userName, userName);
            if (firmUserInfos != null && firmUserInfos.size() == 1) {
                FirmUserInfo firmUserInfo = firmUserInfos.get(0);
                String firmUsername = firmUserInfo.getFirmUsername();
                String pwd = firmUserInfo.getFirmPwd();
                if (pwd != null && pwd.equals(BaseUtil.getMD5Encode(firmUsername + appUserBean.getPwd()))) {
                    FirmInfo firmInfo = firmUserInfo.getFirmInfo();
                    List<ProductInfo> productInfos = productDao.findByFirmInfo(firmInfo);
                    List<AppProductBean> appProductBeans = new ArrayList<>();
                    for (ProductInfo productInfo : productInfos) {
                        AppProductBean appProductBean = new AppProductBean();
                        BeanUtils.copyProperties(productInfo, appProductBean);
                        appProductBean.setFirmNum(firmInfo.getFirmNum());
                        appProductBeans.add(appProductBean);
                    }
                    List<TagInfo> tagInfos = tagDao.findByFirmInfoAndTagState(firmInfo, BaseConst.TAGINIT);
                    List<AppTagBean> appTagBeans = new ArrayList<>();
                    for (TagInfo tagInfo : tagInfos) {
                        AppTagBean appTagBean = new AppTagBean();
                        BeanUtils.copyProperties(tagInfo, appTagBean);
                        appTagBeans.add(appTagBean);
                    }
                    appUserBean.setAppProductBeana(appProductBeans);
                    appUserBean.setAppTagBeans(appTagBeans);
                } else {
                    throw new ServiceException("密码错误");
                }
            } else {
                throw new ServiceException("用户信息存在异常");
            }
        }
        return appUserBean;
    }
}
