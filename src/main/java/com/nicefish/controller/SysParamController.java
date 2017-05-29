package com.nicefish.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nicefish.po.POSysParam;
import com.nicefish.service.SysParamService;

@Controller
@RequestMapping("/sysparam")
public class SysParamController extends BaseController {
	@Autowired
	private SysParamService sysParamService;

	@RequestMapping(value = "/getAllSysParams", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllSysParams() throws Exception {
		List<POSysParam> sysParams = sysParamService.findAllParams();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("data", sysParams);
		return resultMap;
	}
}
