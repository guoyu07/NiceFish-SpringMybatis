package com.nicefish.dao;

import com.nicefish.po.POPost;
import com.nicefish.utils.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface POPostMapper extends BaseMapper<POPost, String>{
    
	List<POPost> findAll();
	
	List<POPost> findByTitle(Map<String, Object> map);

	List<POPost> selectByPage(@Param("beginRow")int beginRow,@Param("pageSize")int pageSize);

	int selectCount();
}