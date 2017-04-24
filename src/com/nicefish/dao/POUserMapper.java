package com.nicefish.dao;

import java.util.List;

import com.nicefish.po.POUser;
import com.nicefish.utils.BaseMapper;

public interface POUserMapper extends BaseMapper<POUser, String>{
    
	List<POUser> findAll();
	
	POUser findByCode(String code);
	
	POUser findByUserName(String userName);
	
	POUser findByEmail(String email);
	
	POUser selectByUserName(String userName);
}