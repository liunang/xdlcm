package com.nantian.nfcm.bms.loan.dao;

import com.nantian.nfcm.bms.loan.entity.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LoanApplicationDao extends JpaRepository<LoanApplication, Long>, JpaSpecificationExecutor<LoanApplication> {
}
