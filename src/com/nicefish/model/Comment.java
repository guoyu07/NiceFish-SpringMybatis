package com.nicefish.model;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable{
    
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 6195619727960332246L;

	//评论主键id
	private String commentId;

	//
    private String pId;

    //用户id
    private String userId;

    //用户登录时的账号
    private String userName;

    //中文昵称
    private String nickName;

    //对于从网络上抓取到的评论，此字段表示评论者名字。
    private String commentName;

    //评论者的IP地址
    private String commentIp;

    //评论时间
    private Date commentTime;

    //帖子id
    private String postId;

    //评论状态：0：已删除；1：已发布；2:优质评论；
    private Integer status;

    //评论内容
    private String content;

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId == null ? null : commentId.trim();
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId == null ? null : pId.trim();
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getCommentName() {
        return commentName;
    }

    public void setCommentName(String commentName) {
        this.commentName = commentName == null ? null : commentName.trim();
    }

    public String getCommentIp() {
        return commentIp;
    }

    public void setCommentIp(String commentIp) {
        this.commentIp = commentIp == null ? null : commentIp.trim();
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId == null ? null : postId.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}