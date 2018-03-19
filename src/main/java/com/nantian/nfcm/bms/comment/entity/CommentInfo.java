package com.nantian.nfcm.bms.comment.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comment_info")
public class CommentInfo implements Serializable {
	private Long id;
	private String telephone;
	private String commentContent;
	private String position;
	
	public CommentInfo()
	{
		
	}
	
	public CommentInfo(Long id,String telephone,String commentContent,String position)
	{
		this.id = id;
		this.telephone = telephone;
		this.commentContent = commentContent;
		this.position = position;
	}
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Basic
	@Column(name = "telephone")
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	@Basic
	@Column(name = "commentContent")
	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	
	@Basic
	@Column(name = "position")
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
}
