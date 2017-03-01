package com.nicefish.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.nicefish.service.RoleService;

@Controller
public class RoleController {
	private RoleService roleService;

	public RoleService getRoleService() {
		return roleService;
	}
	
	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
}
