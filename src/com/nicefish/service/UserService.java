package com.nicefish.service;

import java.util.List;

import com.nicefish.model.User;

public interface UserService {
	
	public int insert(User model);
	
	public User findById(String id);
	
	public List<User> findAll();
	
	public int delete(String id);
	
	public User findByCode(String code);
	
	public int update(User user);

}
