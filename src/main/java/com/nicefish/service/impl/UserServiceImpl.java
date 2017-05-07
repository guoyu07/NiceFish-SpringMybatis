package com.nicefish.service.impl;

import com.nicefish.dao.POUserMapper;
import com.nicefish.po.POUser;
import com.nicefish.service.UserService;
import com.nicefish.utils.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private POUserMapper userMapper;

	public POUser findById(String id) {
		return userMapper.selectByPrimaryKey(id);
	}

	public List<POUser> findAll() {
		return userMapper.findAll();
	}

	public int delete(String id) {
		return userMapper.deleteByPrimaryKey(id);
	}

	public POUser findByCode(String code) {
		return userMapper.findByCode(code);
	}

	public int update(POUser user) {
		return userMapper.updateByPrimaryKeySelective(user);
	}

	public int insert(POUser user) {
		user.setPassword(PasswordHelper.encryptPassword(user));
		return userMapper.insertSelective(user);
	}
	
	public POUser findByUserName(String userName) {
		return userMapper.findByUserName(userName);
	}
	
	public POUser findByNickName(String nickName) {
		return userMapper.findByNickName(nickName);
	}

	public POUser findByEmail(String email) {
		return userMapper.findByEmail(email);
	}
}
