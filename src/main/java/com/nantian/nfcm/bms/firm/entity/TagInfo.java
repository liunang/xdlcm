package com.nantian.nfcm.bms.firm.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tag_info")
public class TagInfo implements Serializable{
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
    private FirmInfo firmInfo;
    private ProductInfo productInfo;
    private String productSerial;

    @Id
    @Column(name = "tag_num")
    public String getTagNum() {
        return tagNum;
    }

    public void setTagNum(String tagNum) {
        this.tagNum = tagNum;
    }

    @Basic
    @Column(name = "batch_id")
    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    @Basic
    @Column(name = "tag_sn")
    public String getTagSn() {
        return tagSn;
    }

    public void setTagSn(String tagSn) {
        this.tagSn = tagSn;
    }

    @Basic
    @Column(name = "tag_sp")
    public String getTagSp() {
        return tagSp;
    }

    public void setTagSp(String tagSp) {
        this.tagSp = tagSp;
    }

    @Basic
    @Column(name = "tag_state")
    public String getTagState() {
        return tagState;
    }

    public void setTagState(String tagState) {
        this.tagState = tagState;
    }

    @Basic
    @Column(name = "tag_init_time")
    public String getTagInitTime() {
        return tagInitTime;
    }

    public void setTagInitTime(String tagInitTime) {
        this.tagInitTime = tagInitTime;
    }

    @Basic
    @Column(name = "tag_allocate_time")
    public String getTagAllocateTime() {
        return tagAllocateTime;
    }

    public void setTagAllocateTime(String tagAllocateTime) {
        this.tagAllocateTime = tagAllocateTime;
    }

    @Basic
    @Column(name = "tag_key")
    public String getTagKey() {
        return tagKey;
    }

    public void setTagKey(String tagKey) {
        this.tagKey = tagKey;
    }

    @Basic
    @Column(name = "tag_ciphertext")
    public String getTagCiphertext() {
        return tagCiphertext;
    }

    public void setTagCiphertext(String tagCiphertext) {
        this.tagCiphertext = tagCiphertext;
    }

    @Basic
    @Column(name = "tag_mac")
    public String getTagMac() {
        return tagMac;
    }

    public void setTagMac(String tagMac) {
        this.tagMac = tagMac;
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

    @Basic
    @Column(name = "product_serial")
    public String getProductSerial() {
        return productSerial;
    }

    public void setProductSerial(String productSerial) {
        this.productSerial = productSerial;
    }
}
