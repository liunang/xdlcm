package com.nantian.nfcm.bms.firm.dao;

import com.nantian.nfcm.bms.firm.entity.FirmInfo;
import com.nantian.nfcm.bms.firm.entity.TagInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface TagDao extends JpaRepository<TagInfo,String>,JpaSpecificationExecutor<TagInfo> {
    public List<TagInfo> findByFirmInfoAndTagState(FirmInfo firmInfo,String tagState);
}
