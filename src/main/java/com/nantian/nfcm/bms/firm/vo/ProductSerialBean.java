package com.nantian.nfcm.bms.firm.vo;

import com.nantian.nfcm.bms.firm.entity.ProductSerial;

public class ProductSerialBean extends ProductSerial {
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
}
