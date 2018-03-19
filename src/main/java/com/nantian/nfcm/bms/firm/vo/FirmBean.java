package com.nantian.nfcm.bms.firm.vo;

import javax.persistence.*;
import java.io.Serializable;

public class FirmBean implements Serializable {
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
	public String getFirmNum() {
		return firmNum;
	}
	public void setFirmNum(String firmNum) {
		this.firmNum = firmNum;
	}
	public String getFirmName() {
		return firmName;
	}
	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFirmDate() {
		return firmDate;
	}
	public void setFirmDate(String firmDate) {
		this.firmDate = firmDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

   
}
