package com.nantian.nfcm.bms.firm.vo;

public class TagBean {
	private String tagNum;
    private Long batchId;
    private String tagSn;
    private String tagSp;
    private String tagState;
    private String tagInitTime;
    private String tagAllocateTime;
    private String tagKey;
    private String tagCiphertext;
    private String tagMac;
    private String productSerial;
    private String firmNum;
    private String firmName;
    private String productNum;
    private String productName;

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

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

	public String getTagSn() {
		return tagSn;
	}

	public void setTagSn(String tagSn) {
		this.tagSn = tagSn;
	}

	public String getTagSp() {
		return tagSp;
	}

	public void setTagSp(String tagSp) {
		this.tagSp = tagSp;
	}

	public String getTagState() {
		return tagState;
	}

	public void setTagState(String tagState) {
		this.tagState = tagState;
	}

	public String getTagInitTime() {
		return tagInitTime;
	}

	public void setTagInitTime(String tagInitTime) {
		this.tagInitTime = tagInitTime;
	}

	public String getTagAllocateTime() {
		return tagAllocateTime;
	}

	public void setTagAllocateTime(String tagAllocateTime) {
		this.tagAllocateTime = tagAllocateTime;
	}

	public String getTagKey() {
		return tagKey;
	}

	public void setTagKey(String tagKey) {
		this.tagKey = tagKey;
	}

	public String getTagCiphertext() {
		return tagCiphertext;
	}

	public void setTagCiphertext(String tagCiphertext) {
		this.tagCiphertext = tagCiphertext;
	}

	public String getTagMac() {
		return tagMac;
	}

	public void setTagMac(String tagMac) {
		this.tagMac = tagMac;
	}

	public String getProductSerial() {
		return productSerial;
	}

	public void setProductSerial(String productSerial) {
		this.productSerial = productSerial;
	}
    
    
}
