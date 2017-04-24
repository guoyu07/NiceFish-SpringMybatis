package com.nicefish.service;

import java.util.List;

import com.nicefish.po.POUser;

public interface UserService {
	public int insert(POUser user);

	public POUser findById(String id);

	public POUser findByCode(String code);

	public POUser findByUserName(String userName);

	public POUser findByEmail(String email);

	public List<POUser> findAll();

	public int delete(String id);

	public int update(POUser user);

}
