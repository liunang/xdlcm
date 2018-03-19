package com.nantian.nfcm.bms.firm.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "firm_info")
public class FirmInfo implements Serializable {
    private String firmNum;
    private String firmName;
    private String contact;
    private String telephone;
    private String mobilePhone;
    private String fax;
    private String email;
    private String address;
    private String firmDate;
    private String remark;

    public FirmInfo() {
    }

    public FirmInfo(String firmNum) {
        this.firmNum = firmNum;
    }

    @Id
    @Column(name = "firm_num")
    public String getFirmNum() {
        return firmNum;
    }

    public void setFirmNum(String firmNum) {
        this.firmNum = firmNum;
    }

    @Basic
    @Column(name = "firm_name")
    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    @Basic
    @Column(name = "contact")
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Basic
    @Column(name = "telephone")
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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
    @Column(name = "fax")
    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
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
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "firm_date")
    public String getFirmDate() {
        return firmDate;
    }

    public void setFirmDate(String firmDate) {
        this.firmDate = firmDate;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
