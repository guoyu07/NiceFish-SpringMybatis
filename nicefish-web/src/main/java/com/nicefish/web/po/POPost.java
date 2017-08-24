package com.nicefish.web.po;

import java.io.Serializable;
import java.util.Date;

public class POPost implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 780466231377828632L;

	private String postId;

	private String postTitle;

	private String originalURL;

	private Date postTime;

	private Integer postType;

	private Date lastModifyTime;

	private Integer readTimes;

	private Integer likedTimes;

	private Integer commentTimes;

	private String userId;

	private String userName;

	private String nickName;
	
	private Integer enableComment;

	private String content;

	// 状态：默认4 1、已删除 2、已归档，已归档的内容禁止评论，文章不可删除 3、草稿 4、已发布 5、精华-->精华文章不可删除 6、已推至首页
	private Integer status;

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId == null ? null : postId.trim();
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle == null ? null : postTitle.trim();
	}

	public Date getPostTime() {
		return postTime;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}

	public Integer getPostType() {
		return postType;
	}

	public void setPostType(Integer postType) {
		this.postType = postType;
	}

	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public String getOriginalURL() {
		return originalURL;
	}

	public void setOriginalURL(String originalURL) {
		this.originalURL = originalURL;
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

	public Integer getLikedTimes() {
		return likedTimes;
	}

	public void setLikedTimes(Integer likedTimes) {
		this.likedTimes = likedTimes;
	}

	public Integer getCommentTimes() {
		return commentTimes;
	}

	public void setCommentTimes(Integer commentTimes) {
		this.commentTimes = commentTimes;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public Integer getEnableComment() {
		return enableComment;
	}

	public void setEnableComment(Integer enableComment) {
		this.enableComment = enableComment;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName == null ? null : nickName.trim();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}