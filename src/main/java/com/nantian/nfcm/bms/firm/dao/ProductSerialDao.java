package com.nantian.nfcm.bms.firm.dao;

import com.nantian.nfcm.bms.firm.entity.ProductSerial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductSerialDao extends JpaRepository<ProductSerial, String>, JpaSpecificationExecutor<ProductSerial> {
}
