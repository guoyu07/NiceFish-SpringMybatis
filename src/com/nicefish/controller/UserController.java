package com.nicefish.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.nicefish.model.User;
import com.nicefish.service.UserService;
import com.nicefish.util.base.UUIDUtil;
import com.nicefish.util.code.MailUtils;
import com.nicefish.util.page.Result;

/**
 * 请注意此类仅为测试样例
 * @author Administrator
 *
 */

@Controller
@RequestMapping("/users")
public class UserController extends BaseController{
	//后面会抽到数据库系统配置表里面去
	private final static int pageSize=20;
		
	@Autowired
	private UserService userService;
	
	ObjectMapper objectMapper=new ObjectMapper();

	@RequestMapping(value = "", method = RequestMethod.GET)
    public String testlist() {
        return "/index";
    }
	
	@RequestMapping(value = "/listpage/{pageIndex}", method = RequestMethod.GET)
	@ResponseBody
	public String listPage(@PathVariable int pageIndex,Model model) throws Exception{
		//开启PageHelper
		Page<?> page = PageHelper.startPage(pageIndex,pageSize,true);
		//获得查询结果列表数据
		List<User> list = userService.findAll();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", page.getTotal());//总数
		map.put("list", list);
		//返回一个json数组
		return objectMapper.writeValueAsString(map);
    }
	
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ResponseBody
	public User userFindOne(@PathVariable("id")String id) {
        return userService.findById(id);
    }
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public Result<String> userAdd(User user) throws AddressException, MessagingException{
		Result<String> result = new Result<String>();
		user.setCode(UUIDUtil.generate()+UUIDUtil.generate());
		int flag = userService.insert(user);
		if(flag > 0){
			MailUtils.sendMail(user.getEmail(),user.getCode());
			result.setStatus(flag);
			result.setMsg("注册成功,请查看邮件！");
			return result;
		}
		result.setStatus(-1);
		result.setMsg("注册失败！");
		return result;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Result<String> userAddTest(Model model) throws AddressException, MessagingException {
		Result<String> result = new Result<String>();
		User user = new User();
		user.setUserId(UUIDUtil.generate());
		user.setCode(UUIDUtil.generate()+UUIDUtil.generate());
		user.setRealName("邮箱测试");
		user.setNickName("Email");
		user.seteName("sendEmail");
		user.setUserName("123456");
		user.setPassword("123456");
		user.setQq("1334996110");
		user.setEmail("17691008810@163.com");
		user.setCellPhone("18368921130");
		user.setUserDesc("发送邮件测试");
		user.setType(1);
		int flag = userService.insert(user);
		MailUtils.sendMail(user.getEmail(),user.getCode());
		result.setStatus(flag);
		result.setMsg("succesful");
		return result;
	}
	
	
	
	@RequestMapping(value="{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Result<String> delete(@PathVariable("id")String id){
		Result<String> result = new Result<String>();
		int flag = userService.delete(id);
		result.setStatus(flag);
		result.setMsg("succesful");
		return result;
	}
}
