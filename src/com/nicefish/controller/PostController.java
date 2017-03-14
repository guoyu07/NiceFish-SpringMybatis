package com.nicefish.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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
import com.nicefish.model.Post;
import com.nicefish.service.PostService;
import com.nicefish.util.base.BaseEncode;
import com.nicefish.util.page.PageSize;

@Controller
@RequestMapping("/posts")
public class PostController {
	
	private final static ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private PostService postService;

	@RequestMapping(value = "/page/{pageIndex}", method = RequestMethod.GET)
	@ResponseBody
	public String listPage(@PathVariable int pageIndex,Model model) throws Exception{
		Page<?> page = PageHelper.startPage(pageIndex,PageSize.TEN.getSize(),true);
		List<Post> list = postService.findAll();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", page.getTotal());//总数
		map.put("list", list);
		return objectMapper.writeValueAsString(map);
    }
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ResponseBody
	public Post postFindOne(@PathVariable("id")String id) {
        return postService.findById(id);
    }
	
	@RequestMapping(value = "/findbykey", method = RequestMethod.GET)
	@ResponseBody
	public String postFindKey(HttpSession session,String key) throws Exception {
        List<Post> list = postService.findByTitle(BaseEncode.encoding(key));
        return objectMapper.writeValueAsString(list);
    } 
}
