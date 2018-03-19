package com.nantian.nfcm.app.vo;

import com.nantian.nfcm.bms.firm.entity.FirmInfo;
import com.nantian.nfcm.bms.firm.entity.ProductInfo;
import com.nantian.nfcm.bms.firm.entity.TagInfo;

import java.util.List;

public class AppUserBean {
    private String userName;
    private String pwd;
    private String mobilePhone;
    private String iemiNum;
    private String mac;
    private List<FirmInfo> firmInfos;
    private List<AppProductBean> appProductBeana;
    private List<AppTagBean> appTagBeans;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getIemiNum() {
        return iemiNum;
    }

    public void setIemiNum(String iemiNum) {
        this.iemiNum = iemiNum;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public List<FirmInfo> getFirmInfos() {
        return firmInfos;
    }

    public void setFirmInfos(List<FirmInfo> firmInfos) {
        this.firmInfos = firmInfos;
    }

    public List<AppProductBean> getAppProductBeana() {
        return appProductBeana;
    }

    public void setAppProductBeana(List<AppProductBean> appProductBeana) {
        this.appProductBeana = appProductBeana;
    }

    public List<AppTagBean> getAppTagBeans() {
        return appTagBeans;
    }

    public void setAppTagBeans(List<AppTagBean> appTagBeans) {
        this.appTagBeans = appTagBeans;
    }
}
