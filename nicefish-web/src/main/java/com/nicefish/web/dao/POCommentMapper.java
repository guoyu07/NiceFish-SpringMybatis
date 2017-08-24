package com.nicefish.web.dao;

import java.util.List;

import com.nicefish.web.utils.mybatis.BaseMapper;
import com.nicefish.web.po.POComment;
import org.apache.ibatis.annotations.Param;

public interface POCommentMapper extends BaseMapper<POComment, String> {

	List<POComment> findByPostId(String postId);

	List<POComment> findByPostIdAndPage(@Param("postId")String postId, @Param("beginRow")int start, @Param("pageSize")int limit);

	int selectCount(@Param("postId")String postId);
}