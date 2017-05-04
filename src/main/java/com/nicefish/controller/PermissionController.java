package com.nicefish.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.nicefish.service.PermissionService;

@Controller
public class PermissionController extends BaseController{
	
	@Autowired
	private PermissionService permissionService;

}
