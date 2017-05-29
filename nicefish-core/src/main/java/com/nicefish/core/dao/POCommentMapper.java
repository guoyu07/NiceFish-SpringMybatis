package com.nicefish.core.dao;

import com.nicefish.common.utils.BaseMapper;
import com.nicefish.core.po.POComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface POCommentMapper extends BaseMapper<POComment, String> {

	List<POComment> findByPostId(String postId);

	List<POComment> findByPostIdAndPage(@Param("postId") String postId, @Param("beginRow") int start, @Param("pageSize") int limit);

	int selectCount(@Param("postId") String postId);
}