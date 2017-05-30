package com.nicefish.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nicefish.po.POComment;
import com.nicefish.utils.BaseMapper;

public interface POCommentMapper extends BaseMapper<POComment, String> {

	List<POComment> findByPostId(String postId);

	List<POComment> findByPostIdAndPage(@Param("postId")String postId, @Param("beginRow")int start, @Param("pageSize")int limit);

	int selectCount(@Param("postId")String postId);
}