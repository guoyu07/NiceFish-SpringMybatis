package com.nicefish.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicefish.dao.UserMapper;
import com.nicefish.model.User;
import com.nicefish.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public User findById(String id) {
		return userMapper.selectByPrimaryKey(id);
	}


	@Override
	public List<User> findAll() {
		return userMapper.findAll();
	}


	@Override
	public int delete(String id) {
		return userMapper.deleteByPrimaryKey(id);
	}


	@Override
	public boolean createUser(User user) {
		//校验邮箱
		//数据入库
		return false;
	}
}
