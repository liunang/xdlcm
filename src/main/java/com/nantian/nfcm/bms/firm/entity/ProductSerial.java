package com.nantian.nfcm.bms.firm.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product_serial")
public class ProductSerial implements Serializable {
    private String productSerialNum;
    private String productIndex;
    private String serialState;
    private String serialInitTime;
    private String tagNum;
    private FirmInfo firmInfo;
    private ProductInfo productInfo;

    @Id
    @Column(name = "product_serial_num")
    public String getProductSerialNum() {
        return productSerialNum;
    }

    public void setProductSerialNum(String productSerialNum) {
        this.productSerialNum = productSerialNum;
    }

    @Basic
    @Column(name = "product_index")
    public String getProductIndex() {
        return productIndex;
    }

    public void setProductIndex(String productIndex) {
        this.productIndex = productIndex;
    }

    @Basic
    @Column(name = "serial_state")
    public String getSerialState() {
        return serialState;
    }

    public void setSerialState(String serialState) {
        this.serialState = serialState;
    }

    @Basic
    @Column(name = "serial_init_time")
    public String getSerialInitTime() {
        return serialInitTime;
    }

    public void setSerialInitTime(String serialInitTime) {
        this.serialInitTime = serialInitTime;
    }

    @Basic
    @Column(name = "tag_num")
    public String getTagNum() {
        return tagNum;
    }

    public void setTagNum(String tagNum) {
        this.tagNum = tagNum;
    }

    @ManyToOne(targetEntity = FirmInfo.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "firm_num")
    public FirmInfo getFirmInfo() {
        return firmInfo;
    }

    public void setFirmInfo(FirmInfo firmInfo) {
        this.firmInfo = firmInfo;
    }

    @ManyToOne(targetEntity = ProductInfo.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_num")
    public ProductInfo getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }
}
