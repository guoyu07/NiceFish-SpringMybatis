package com.nicefish.dao;

import com.nicefish.po.POUser;
import com.nicefish.utils.BaseMapper;

import java.util.List;
import java.util.Set;

public interface POUserMapper extends BaseMapper<POUser, String>{
    
	List<POUser> findAll();
	
	POUser findByCode(String code);
	
	POUser findByUserName(String userName);
	
	POUser findByNickName(String nickName);
	
	POUser findByEmail(String email);
	
	POUser selectByUserName(String userName);

    Set<String> findRoles(String username);
}