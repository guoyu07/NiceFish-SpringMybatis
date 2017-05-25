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

import com.nicefish.enums.CommonStatusEnum;
import com.nicefish.enums.PostStatusEnum;
import com.nicefish.enums.PostTypeEnum;

@Entity
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	
	@Column(columnDefinition = "text")
	private String content;
	
	private String originalUrl;
	
	@Enumerated(EnumType.STRING)
	private PostTypeEnum type;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastModifyTime;
	
	private Integer readTimes;
	
	private Integer likeTimes;
	
	private Long userId;
	
	@Enumerated(EnumType.STRING)
	private CommonStatusEnum enableComment;
	
	@Enumerated(EnumType.STRING)
	private PostStatusEnum status;

	public Post() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOriginalUrl() {
		return originalUrl;
	}

	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}

	public PostTypeEnum getType() {
		return type;
	}

	public void setType(PostTypeEnum type) {
		this.type = type;
	}

	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public Integer getReadTimes() {
		return readTimes;
	}

	public void setReadTimes(Integer readTimes) {
		this.readTimes = readTimes;
	}

	public Integer getLikeTimes() {
		return likeTimes;
	}

	public void setLikeTimes(Integer likeTimes) {
		this.likeTimes = likeTimes;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public CommonStatusEnum getEnableComment() {
		return enableComment;
	}

	public void setEnableComment(CommonStatusEnum enableComment) {
		this.enableComment = enableComment;
	}

	public PostStatusEnum getStatus() {
		return status;
	}

	public void setStatus(PostStatusEnum status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", createTime=" + createTime + ", content=" + content
				+ ", originalUrl=" + originalUrl + ", type=" + type + ", lastModifyTime=" + lastModifyTime + ", readTimes="
				+ readTimes + ", likeTimes=" + likeTimes + ", userId=" + userId + ", enableComment=" + enableComment + ", status="
				+ status + "]";
	}
	
}
