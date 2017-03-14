package com.nicefish.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.nicefish.service.RoleService;

@Controller
public class RoleController extends BaseController{
	
	@Autowired
	private RoleService roleService;

}
