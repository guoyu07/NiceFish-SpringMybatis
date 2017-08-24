package com.nicefish.web.controller;

import com.nicefish.auth.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PermissionController extends BaseController{
	
	@Autowired
	private PermissionService permissionService;

}
