package com.nicefish.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicefish.dao.PermissionMapper;
import com.nicefish.service.PermissionService;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {
	private PermissionMapper permissionMapper;

	public PermissionMapper getPermissionMapper() {
		return permissionMapper;
	}
	@Autowired
	public void setPermissionMapper(PermissionMapper permissionMapper) {
		this.permissionMapper = permissionMapper;
	}
}
