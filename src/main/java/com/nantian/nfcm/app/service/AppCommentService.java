package com.nantian.nfcm.app.service;

import com.nantian.nfcm.app.vo.AppCommentBean;
import com.nantian.nfcm.bms.comment.entity.CommentInfo;
import com.nantian.nfcm.util.ServiceException;

public interface AppCommentService
{
	/**
     * 添加留言信息
     *
     * @param commentInfo
     * @return CommentInfo
     * @throws ServiceException
     */
    public CommentInfo addCommentInfo(AppCommentBean appCommentBean) throws ServiceException;

    
}
