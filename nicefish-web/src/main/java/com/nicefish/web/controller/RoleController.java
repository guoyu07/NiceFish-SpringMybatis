package com.nicefish.web.controller;

import com.nicefish.auth.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class RoleController extends BaseController{
	
	@Autowired
	private RoleService roleService;

}
