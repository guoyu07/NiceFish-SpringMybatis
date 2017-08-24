package com.nicefish.auth.service;

import com.nicefish.auth.po.POUser;

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

	void ensureUser(POUser user) throws Exception;
}
