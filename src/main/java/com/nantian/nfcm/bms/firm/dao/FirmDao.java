package com.nantian.nfcm.bms.firm.dao;

import com.nantian.nfcm.bms.firm.entity.FirmInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FirmDao extends JpaRepository<FirmInfo,String>,JpaSpecificationExecutor<FirmInfo> {
}
