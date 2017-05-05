package com.nicefish.service;

import com.nicefish.po.POUser;

import java.util.List;

public interface UserService {
	public int insert(POUser user);

	public POUser findById(String id);

	public POUser findByCode(String code);

	public POUser findByUserName(String userName);
	
	public POUser findByNickName(String nickName);

	public POUser findByEmail(String email);

	public List<POUser> findAll();

	public int delete(String id);

	public int update(POUser user);
}
