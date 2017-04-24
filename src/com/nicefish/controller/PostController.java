package com.nicefish.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nicefish.model.Post;
import com.nicefish.service.PostService;
import com.nicefish.utils.BaseEncode;
import com.nicefish.utils.PagerUtil;

@Controller
@RequestMapping("/posts")
public class PostController extends BaseController{
	//后面会抽到数据库系统配置表里面去
	private final static int pageSize=20;

	private final static ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private PostService postService;

	@RequestMapping(value = "/getPosts/{pageIndex}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getPosts(@PathVariable int pageIndex) throws Exception{
		int totalRecords=postService.selectCount();
		int startRow=PagerUtil.calcStartRow(pageIndex,pageSize);
		
		List<Post> list = postService.selectByPage(startRow,pageSize);
		int totalPages=PagerUtil.calcPages(totalRecords, pageSize);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalRecords", totalRecords);
		map.put("totalPages", totalPages);
		map.put("pageSize", pageSize);
		map.put("currentPage", pageIndex);
		map.put("list", list);
		return map;
    }
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ResponseBody
	public Post getPostById(@PathVariable("id")String id) {
        return postService.findById(id);
    }
	
	@RequestMapping(value = "/newPost", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> newPost(@RequestBody Post post) {
        return this.ajaxSuccessResponse();
    }
	
	@RequestMapping(value = "/findbykey", method = RequestMethod.GET)
	@ResponseBody
	public String findPostByKey(HttpSession session,String key) throws Exception {
        List<Post> list = postService.findByTitle(BaseEncode.encoding(key));
        return objectMapper.writeValueAsString(list);
    }
}
