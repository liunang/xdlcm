package com.nantian.nfcm.bms.comment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.nantian.nfcm.bms.comment.entity.CommentInfo;

public interface CommentDao extends JpaRepository<CommentInfo, Long>, JpaSpecificationExecutor<CommentInfo> {

}
