package com.nantian.nfcm.bms.firm.dao;

import com.nantian.nfcm.bms.firm.entity.FirmInfo;
import com.nantian.nfcm.bms.firm.entity.FirmUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface FirmUserDao extends JpaRepository<FirmUserInfo, String>, JpaSpecificationExecutor<FirmUserInfo> {
    public List<FirmUserInfo> findByFirmUsernameOrMobilePhoneOrEmail(String firmUsername, String mobilePhone, String email);
}
