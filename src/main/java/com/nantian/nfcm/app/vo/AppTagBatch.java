package com.nantian.nfcm.app.vo;

import com.nantian.nfcm.bms.firm.entity.TagInfo;

import java.util.List;

public class AppTagBatch {
    private String firmNum;
    private String userName;
    private String productNum;
    private String tagNum;
    private String tagCiphertext;
    private List<TagInfo> tagInfos;

    public String getFirmNum() {
        return firmNum;
    }

    public void setFirmNum(String firmNum) {
        this.firmNum = firmNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public String getTagNum() {
        return tagNum;
    }

    public void setTagNum(String tagNum) {
        this.tagNum = tagNum;
    }

    public String getTagCiphertext() {
        return tagCiphertext;
    }

    public void setTagCiphertext(String tagCiphertext) {
        this.tagCiphertext = tagCiphertext;
    }

    public List<TagInfo> getTagInfos() {
        return tagInfos;
    }

    public void setTagInfos(List<TagInfo> tagInfos) {
        this.tagInfos = tagInfos;
    }
}
