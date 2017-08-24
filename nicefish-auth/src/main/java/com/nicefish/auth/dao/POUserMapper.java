package com.nicefish.auth.dao;

import com.nicefish.auth.po.POUser;
import com.nicefish.web.utils.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Param;

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

    POUser getByUser(@Param("user") POUser user);
}