package com.nicefish.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.nicefish.service.PermissionService;

@Controller
public class PermissionController {
	
	@Autowired
	private PermissionService permissionService;

}
