package com.nicefish.auth.service.impl;

import com.nicefish.auth.dao.POPermissionMapper;
import com.nicefish.auth.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

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

	@Override
	public Set<String> findPermissions() {
		return new HashSet<>();
	}
}
