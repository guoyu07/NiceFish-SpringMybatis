package com.nicefish.po;

import java.io.Serializable;
import java.util.Date;

public class POPost implements Serializable{
   
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 780466231377828632L;

	//文章列表主键id
	private String postId;

	//文章标题
    private String postTitle;

    //发布时间
    private Date postTime;

    //文章的类型，0原创1翻译
    private Integer postType;

    //最后修改时间
    private Date lastModifyTime;

    //浏览
    private Integer readTimes;

    //喜欢
    private Integer likedTimes;

    //收藏
    private Integer collectTimes;

    //用户id
    private String userId;

    //用户登录时的账号
    private String userName;

    //是否可评论，0不可1可
    private Integer enableComment;

    /**
     * 状态：默认4
     * 	1、已删除
     *	2、已归档，已归档的内容禁止评论，文章不可删除
     *	3、草稿
     *	4、已发布
     *	5、精华-->精华文章不可删除
     *	6、已推至首页
     */
    private Integer status;

    //中文昵称
    private String nickName;

    //文章内容
    private String postContent;

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

    public Integer getCollectTimes() {
        return collectTimes;
    }

    public void setCollectTimes(Integer collectTimes) {
        this.collectTimes = collectTimes;
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

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent == null ? null : postContent.trim();
    }
}