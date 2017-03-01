package com.nicefish.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.nicefish.service.PermissionService;

@Controller
public class PermissionController {
	private PermissionService permissionService;

	public PermissionService getPermissionService() {
		return permissionService;
	}
	
	@Autowired
	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}
}
