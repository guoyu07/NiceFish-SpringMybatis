package com.nicefish.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import com.nicefish.enums.CommentStatusEnum;

@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long parentId;
	
	private Long userId;
	
	private String username;
	
	private String nickname;
	
	@Column(length = 1024)
	private String content;
	
	private String ip;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	
	private Long postId;
	
	@Enumerated(EnumType.STRING)
	private CommentStatusEnum status;

	public Comment() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public CommentStatusEnum getStatus() {
		return status;
	}

	public void setStatus(CommentStatusEnum status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", parentId=" + parentId + ", userId=" + userId + ", username=" + username
				+ ", nickname=" + nickname + ", content=" + content + ", ip=" + ip + ", createTime=" + createTime
				+ ", postId=" + postId + ", status=" + status + "]";
	}

}
