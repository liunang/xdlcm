package com.nantian.nfcm.bms.firm.service.impl;

import com.nantian.nfcm.bms.firm.dao.FirmUserDao;
import com.nantian.nfcm.bms.firm.entity.FirmInfo;
import com.nantian.nfcm.bms.firm.entity.FirmUserInfo;
import com.nantian.nfcm.bms.firm.service.FirmUserService;
import com.nantian.nfcm.bms.firm.vo.FirmUserBean;
import com.nantian.nfcm.util.BaseUtil;
import com.nantian.nfcm.util.ServiceException;
import com.nantian.nfcm.util.vo.GridData;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class FirmUserServiceImpl implements FirmUserService {
    private FirmUserDao firmUserDao;

    @Autowired
    public FirmUserServiceImpl(FirmUserDao firmUserDao) {
        this.firmUserDao = firmUserDao;
    }

    public FirmUserBean addFirmUser(FirmUserBean firmUserBean) throws ServiceException {
        String firmUserId = firmUserBean.getFirmUsrId();
        FirmUserInfo firmUserInfo = firmUserDao.findOne(firmUserId);
        if(firmUserInfo!=null)
        {
        	throw new ServiceException("厂商用户编号已经存在[新增失败]");
        }
        else
        {
        	String firmUserName = firmUserBean.getFirmUsername();
            String mobilePhone = firmUserBean.getMobilePhone();
            String email = firmUserBean.getEmail();
            List<FirmUserInfo> firmUserInfos = firmUserDao.
                    findByFirmUsernameOrMobilePhoneOrEmail(firmUserName, mobilePhone, email);
            if (firmUserInfos != null && firmUserInfos.size() > 0) {
                throw new ServiceException("厂商用户名或手机号或电子邮箱重复[新增失败]");
                /*for(FirmUserInfo firmUserInfo:firmUserInfos){
                    String firmUsernameRet = firmUserInfo.getFirmUsername();
                    if(firmUsernameRet!=null && !firmUsernameRet.equals("")){
                        throw new ServiceException("用户名["+firmUsernameRet+"]重复，添加失败");
                    }
                }*/
            } else {
                FirmUserInfo firmUserInfoTemp = vo2po(firmUserBean);
                String pwd = firmUserBean.getFirmPwd();
                if (pwd != null && pwd.length() > 0) {
                	firmUserInfoTemp.setFirmPwd(BaseUtil.getMD5Encode(firmUserName + pwd));
                }
                return po2vo(firmUserDao.save(firmUserInfoTemp));
            }
        }
    	
    }

    public void delFirmUser(FirmUserBean firmUserBean) throws ServiceException {
        String firmUserId = firmUserBean.getFirmUsrId();
        FirmUserInfo firmUserInfo = firmUserDao.findOne(firmUserId);
        if (firmUserInfo == null) {
            throw new ServiceException("厂商用户不存在[删除失败]");
        } else {
            firmUserDao.delete(firmUserInfo);
        }
    }

    public FirmUserBean updateFirmUser(FirmUserBean firmUserBean) throws ServiceException {
        String firmUserId = firmUserBean.getFirmUsrId();
        FirmUserInfo firmUserInfo = firmUserDao.findOne(firmUserId);
        if (firmUserInfo == null) {
            throw new ServiceException("厂商用户不存在[更新失败]");
        }
        else {
            firmUserInfo = vo2po(firmUserBean);
            firmUserDao.save(firmUserInfo);
        }
        return po2vo(firmUserInfo);
    }

    public FirmUserBean findByid(String firmUserId) throws ServiceException {
        FirmUserInfo firmUserInfo = firmUserDao.findOne(firmUserId);
        if (firmUserInfo == null) {
            return null;
        }
        return po2vo(firmUserInfo);
    }

    @Transactional
    public GridData<FirmUserBean> findByCondition(int page, int size, FirmUserBean firmUserBean) throws ServiceException {
        Pageable pageable = new PageRequest(page, size);
        Specification<FirmUserInfo> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (firmUserBean.getFirmNum() != null && !firmUserBean.getFirmNum().equals("")) {
                Predicate firmNum = criteriaBuilder.equal(root.get("firmInfo").get("firmNum").as(String.class),firmUserBean.getFirmNum() );
                predicates.add(firmNum);
            }
            /* 关联查询，备用
            if (firmUserBean.getFirmNum() != null && !firmUserBean.getFirmNum().equals("")) {
                Join<FirmUserInfo,FirmInfo> firmNumJoin = root.join(root.getModel().getSingularAttribute("firmInfo",FirmInfo.class), JoinType.LEFT);
                Predicate firmNum = criteriaBuilder.equal(firmNumJoin.get("firmNum").as(String.class),firmUserBean.getFirmNum());
                predicates.add(firmNum);
            }*/
            if (firmUserBean.getFirmUsername() != null && !firmUserBean.getFirmUsername().equals("")) {
                Predicate firmUsername = criteriaBuilder.like(root.get("firmUsername").as(String.class), "%" + firmUserBean.getFirmUsername() + "%");
                predicates.add(firmUsername);
            }
            if (firmUserBean.getMobilePhone() != null && !firmUserBean.getMobilePhone().equals("")) {
                Predicate mobilePhone = criteriaBuilder.like(root.get("mobilePhone").as(String.class), firmUserBean.getMobilePhone() + "%");
                predicates.add(mobilePhone);
            }
            if (firmUserBean.getEmail() != null && !firmUserBean.getEmail().equals("")) {
                Predicate email = criteriaBuilder.like(root.get("email").as(String.class), firmUserBean.getEmail() + "%");
                predicates.add(email);
            }
            query.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
            return query.getRestriction();
        };
        Page<FirmUserInfo> firmUserInfoPage = firmUserDao.findAll(specification, pageable);
        List<FirmUserInfo> firmUserInfos = firmUserInfoPage.getContent();
        List<FirmUserBean> firmUserBeans = new ArrayList<>();
        for (FirmUserInfo firmUserInfo : firmUserInfos) {
            FirmUserBean firmUserBeanRet = po2vo(firmUserInfo);
            firmUserBeans.add(firmUserBeanRet);
        }
        GridData<FirmUserBean> gridData = new GridData<>();
        gridData.setData(firmUserBeans);
        gridData.setNumber(firmUserInfoPage.getTotalElements());
        gridData.setPage(firmUserInfoPage.getNumber());
        gridData.setTotalPage(firmUserInfoPage.getTotalPages());
        return gridData;
    }

    //TODO
    public FirmUserBean confirmFirmUser(FirmUserBean firmUserBean) throws ServiceException {
        return null;
    }

    private FirmUserInfo vo2po(FirmUserBean firmUserBean) {
        FirmUserInfo firmUserInfo = new FirmUserInfo();
        BeanUtils.copyProperties(firmUserBean, firmUserInfo);
        String firmNum = firmUserBean.getFirmNum();
        if (firmNum != null && !firmNum.equals("")) {
            FirmInfo firmInfo = new FirmInfo();
            firmInfo.setFirmNum(firmNum);
            firmUserInfo.setFirmInfo(firmInfo);
        }
        return firmUserInfo;
    }

    private FirmUserBean po2vo(FirmUserInfo firmUserInfo) {
        FirmUserBean firmUserBean = new FirmUserBean();
        BeanUtils.copyProperties(firmUserInfo, firmUserBean);
        FirmInfo firmInfo = firmUserInfo.getFirmInfo();
        if (firmInfo != null) {
            String firmNum = firmInfo.getFirmNum();
            String firmName = firmInfo.getFirmName();
            firmUserBean.setFirmNum(firmNum);
            firmUserBean.setFirmName(firmName);
        }
        return firmUserBean;
    }
    
    public FirmUserBean resetUserPwd(FirmUserBean firmUserBean) throws ServiceException {
        String firmUserId = firmUserBean.getFirmUsrId();
        FirmUserInfo firmUserInfo = (FirmUserInfo) firmUserDao.findOne(firmUserId);
        if (firmUserInfo == null) {
            throw new ServiceException("用户已被其他用户删除");
        } else {
            String pwd = BaseUtil.getMD5Encode(firmUserBean.getFirmUsername() + "e10adc3949ba59abbe56e057f20f883e");
            firmUserInfo.setFirmPwd(pwd);
            firmUserDao.save(firmUserInfo);
            return po2vo(firmUserInfo);
        }
    }
}
