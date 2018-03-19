package com.nantian.nfcm.bms.firm.vo;

public class TagBatchBean {
	private Long batchId;
    private String batchTime;
    private String batchSum;
    private String batchOperator;
    private String firmNum;
    private String firmName;

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

	public Long getBatchId() {
		return batchId;
	}

	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}

	public String getBatchTime() {
		return batchTime;
	}

	public void setBatchTime(String batchTime) {
		this.batchTime = batchTime;
	}

	public String getBatchSum() {
		return batchSum;
	}

	public void setBatchSum(String batchSum) {
		this.batchSum = batchSum;
	}

	public String getBatchOperator() {
		return batchOperator;
	}

	public void setBatchOperator(String batchOperator) {
		this.batchOperator = batchOperator;
	}
    
}
