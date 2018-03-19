package com.nantian.nfcm.bms.firm.dao;

import com.nantian.nfcm.bms.firm.entity.TagBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TagBatchDao extends JpaRepository<TagBatch,Long>,JpaSpecificationExecutor<TagBatch> {
}
