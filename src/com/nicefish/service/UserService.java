package com.nicefish.service;

import java.util.List;

import com.nicefish.model.User;


public interface UserService {
	
	public boolean createUser(User user);
	
	public User findById(String id);
	
	public List<User> findAll();
	
	public int delete(String id);

}
