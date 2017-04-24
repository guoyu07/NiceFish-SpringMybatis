package com.nicefish.dao;

import java.util.List;

import com.nicefish.po.POComment;
import com.nicefish.utils.BaseMapper;

public interface POCommentMapper extends BaseMapper<POComment, String>{
    
	List<POComment> findByPostId(String postId);
}