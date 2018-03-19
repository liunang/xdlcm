package com.nantian.nfcm.ca.entity;

import javax.persistence.*;

@Entity
@Table(name = "key_info")
public class KeyInfo {
    private Long keyId;
    private String keyValue;
    private String keyType;
    private String tagNum;
    private Long batchId;
    private String keyCiphertext;
    private String keyInitTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "key_id")
    public Long getKeyId() {
        return keyId;
    }

    public void setKeyId(Long keyId) {
        this.keyId = keyId;
    }

    @Basic
    @Column(name = "key_value")
    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    @Basic
    @Column(name = "key_type")
    public String getKeyType() {
        return keyType;
    }

    public void setKeyType(String keyType) {
        this.keyType = keyType;
    }

    @Basic
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
    @Column(name = "key_ciphertext")
    public String getKeyCiphertext() {
        return keyCiphertext;
    }

    public void setKeyCiphertext(String keyCiphertext) {
        this.keyCiphertext = keyCiphertext;
    }

    @Basic
    @Column(name = "key_init_time")
    public String getKeyInitTime() {
        return keyInitTime;
    }

    public void setKeyInitTime(String keyInitTime) {
        this.keyInitTime = keyInitTime;
    }
}
