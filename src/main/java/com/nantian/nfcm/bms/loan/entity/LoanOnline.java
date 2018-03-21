package com.nantian.nfcm.bms.loan.entity;

import javax.persistence.*;

@Entity
@Table(name = "loan_online")
public class LoanOnline {
    private Long id;
    private Long loanId;
    private String processName;
    private String processStatus;
    private String processUser;

    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "loan_id")
    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    @Basic
    @Column(name = "process_name")
    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    @Basic
    @Column(name = "process_status")
    public String getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }

    @Basic
    @Column(name = "process_user")
    public String getProcessUser() {
        return processUser;
    }

    public void setProcessUser(String processUser) {
        this.processUser = processUser;
    }
}
