package com.nicefish.dao;

import java.util.List;

import com.nicefish.model.User;
import com.nicefish.util.base.BaseMapper;

public interface UserMapper extends BaseMapper<User, String>{
    
	List<User> findAll();
	
	User findByCode(String code);
}