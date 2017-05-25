package com.nicefish.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nicefish.vo.Response;

@RestController
@RequestMapping("/rest")
public class IndexController {

	@GetMapping
	private String index() {
		return "欢迎来到NiceFish";
	}
	
	@GetMapping("/test")
	private Response testResponse() {
		Response res = new Response();
		res.success();
		return res;
	}
}
