package com.nantian.nfcm.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nantian.nfcm.app.service.AppCommentService;
import com.nantian.nfcm.app.vo.AppCommentBean;
import com.nantian.nfcm.bms.comment.dao.CommentDao;
import com.nantian.nfcm.bms.comment.entity.CommentInfo;
import com.nantian.nfcm.util.ServiceException;

@Service
public class AppCommentServiceImpl implements AppCommentService
{
	private CommentDao commentDao;
	@Autowired
    public AppCommentServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

	public CommentInfo addCommentInfo(AppCommentBean appCommentBean) throws ServiceException {
		CommentInfo commentInfo = new CommentInfo();
		commentInfo.setTelephone(appCommentBean.getTelephone());
		commentInfo.setCommentContent(appCommentBean.getCommentContent());
		commentInfo.setPosition(appCommentBean.getPosition());
		
		return commentDao.save(commentInfo);
	}
	
}
