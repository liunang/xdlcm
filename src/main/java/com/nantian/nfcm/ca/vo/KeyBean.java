package com.nantian.nfcm.ca.vo;

public class KeyBean {
	private Long keyId;
    private String keyValue;
    private String keyType;
    private String tagNum;
    private Long batchId;
    private String keyCiphertext;
    private String keyInitTime;
    private String startDate;
    private String endDate;
	public Long getKeyId() {
		return keyId;
	}
	public void setKeyId(Long keyId) {
		this.keyId = keyId;
	}
	public String getKeyValue() {
		return keyValue;
	}
	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}
	public String getKeyType() {
		return keyType;
	}
	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}
	public String getTagNum() {
		return tagNum;
	}
	public void setTagNum(String tagNum) {
		this.tagNum = tagNum;
	}
	public Long getBatchId() {
		return batchId;
	}
	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}
	public String getKeyCiphertext() {
		return keyCiphertext;
	}
	public void setKeyCiphertext(String keyCiphertext) {
		this.keyCiphertext = keyCiphertext;
	}
	public String getKeyInitTime() {
		return keyInitTime;
	}
	public void setKeyInitTime(String keyInitTime) {
		this.keyInitTime = keyInitTime;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
    
    
}
