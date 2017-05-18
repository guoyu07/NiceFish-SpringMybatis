package com.nicefish.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nicefish.po.POUser;
import com.nicefish.service.UserService;
import com.nicefish.utils.SessionConsts;
import com.nicefish.vo.VOUserLogin;

@Controller
@RequestMapping("/common")
public class CommonController extends BaseController{
	
}
