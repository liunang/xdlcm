package com.nantian.nfcm.ca.dao;

import com.nantian.nfcm.ca.entity.KeyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface KeyInfoDao extends JpaRepository<KeyInfo, Long>, JpaSpecificationExecutor<KeyInfo> {
}
