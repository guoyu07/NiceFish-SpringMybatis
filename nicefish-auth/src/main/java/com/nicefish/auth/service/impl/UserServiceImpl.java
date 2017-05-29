package com.nicefish.auth.service.impl;

import com.nicefish.auth.dao.POUserMapper;
import com.nicefish.auth.po.POUser;
import com.nicefish.auth.service.UserService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
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

	@Override
	public void ensureUser(POUser user) throws Exception {
		POUser poUser = this.userMapper.getByUser(user);
		if(poUser != null){
			BeanUtils.copyProperties(user,poUser);
		}
	}

	public int insert(POUser user) {
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
