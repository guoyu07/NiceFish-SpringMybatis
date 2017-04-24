package com.nicefish.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicefish.dao.POPermissionMapper;
import com.nicefish.service.PermissionService;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {
	private POPermissionMapper permissionMapper;

	public POPermissionMapper getPermissionMapper() {
		return permissionMapper;
	}
	@Autowired
	public void setPermissionMapper(POPermissionMapper permissionMapper) {
		this.permissionMapper = permissionMapper;
	}
}
