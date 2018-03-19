package com.nantian.nfcm.bms.firm.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "firm_user_info")
public class FirmUserInfo implements Serializable {
    private String firmUsrId;
    private FirmInfo firmInfo;
    private String firmUsername;
    private String mobilePhone;
    private String email;
    private String firmPwd;
    private String registerTime;

    @Id
    @Column(name = "firm_usr_id")
    public String getFirmUsrId() {
        return firmUsrId;
    }

    public void setFirmUsrId(String firmUsrId) {
        this.firmUsrId = firmUsrId;
    }

    @ManyToOne(targetEntity = FirmInfo.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "firm_num")
    public FirmInfo getFirmInfo() {
        return firmInfo;
    }

    public void setFirmInfo(FirmInfo firmInfo) {
        this.firmInfo = firmInfo;
    }

    @Basic
    @Column(name = "firm_username")
    public String getFirmUsername() {
        return firmUsername;
    }

    public void setFirmUsername(String firmUsername) {
        this.firmUsername = firmUsername;
    }

    @Basic
    @Column(name = "mobile_phone")
    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "firm_pwd")
    public String getFirmPwd() {
        return firmPwd;
    }

    public void setFirmPwd(String firmPwd) {
        this.firmPwd = firmPwd;
    }

    @Basic
    @Column(name = "register_time")
    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }
}
