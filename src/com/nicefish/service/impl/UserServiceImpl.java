package com.nicefish.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicefish.dao.POUserMapper;
import com.nicefish.po.POUser;
import com.nicefish.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private POUserMapper userMapper;

	@Override
	public POUser findById(String id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<POUser> findAll() {
		return userMapper.findAll();
	}

	@Override
	public int delete(String id) {
		return userMapper.deleteByPrimaryKey(id);
	}

	@Override
	public POUser findByCode(String code) {
		return userMapper.findByCode(code);
	}

	@Override
	public int update(POUser user) {
		return userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public int insert(POUser user) {
		return userMapper.insertSelective(user);
	}
	
	@Override
	public POUser findByUserName(String userName) {
		return userMapper.findByUserName(userName);
	}

	@Override
	public POUser findByEmail(String email) {
		return userMapper.findByEmail(email);
	}
}
