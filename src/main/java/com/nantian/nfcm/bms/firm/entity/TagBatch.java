package com.nantian.nfcm.bms.firm.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tag_batch")
public class TagBatch implements Serializable{
    private Long batchId;
    private String batchTime;
    private String batchSum;
    private String batchOperator;
    private FirmInfo firmInfo;

    @Id
    @Column(name = "batch_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    @Basic
    @Column(name = "batch_time")
    public String getBatchTime() {
        return batchTime;
    }

    public void setBatchTime(String batchTime) {
        this.batchTime = batchTime;
    }

    @Basic
    @Column(name = "batch_sum")
    public String getBatchSum() {
        return batchSum;
    }

    public void setBatchSum(String batchSum) {
        this.batchSum = batchSum;
    }

    @Basic
    @Column(name = "batch_operator")
    public String getBatchOperator() {
        return batchOperator;
    }

    public void setBatchOperator(String batchOperator) {
        this.batchOperator = batchOperator;
    }

    @ManyToOne(targetEntity = FirmInfo.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "firm_num")
    public FirmInfo getFirmInfo() {
        return firmInfo;
    }

    public void setFirmInfo(FirmInfo firmInfo) {
        this.firmInfo = firmInfo;
    }
}
