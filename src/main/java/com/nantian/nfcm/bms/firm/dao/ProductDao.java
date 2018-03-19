package com.nantian.nfcm.bms.firm.dao;

import com.nantian.nfcm.bms.firm.entity.FirmInfo;
import com.nantian.nfcm.bms.firm.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDao extends JpaRepository<ProductInfo, String>, JpaSpecificationExecutor<ProductInfo> {
    public List<ProductInfo> findByFirmInfo(FirmInfo firmInfo);
}
