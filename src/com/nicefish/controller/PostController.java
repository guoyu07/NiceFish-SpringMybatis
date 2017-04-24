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
import com.nicefish.po.POPost;
import com.nicefish.service.PostService;
import com.nicefish.utils.BaseEncode;

@Controller
@RequestMapping("/posts")
public class PostController extends BaseController{
	private final static ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private PostService postService;

	@RequestMapping(value = "/getPostListByPage/{currentPage}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getPostListByPage(@PathVariable int currentPage) throws Exception{
		List<POPost> poPostList=postService.selectPostsByPage(currentPage+"");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("data", poPostList);
		return resultMap;
    }
	
	@RequestMapping(value = "/getTotalPages", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getTotalPages() throws Exception{
		String totalCount=postService.getTotalPages();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("totalCount", totalCount);
		return resultMap;
    }
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ResponseBody
	public POPost getPostById(@PathVariable("id")String id) {
        return postService.findById(id);
    }
	
	@RequestMapping(value = "/newPost", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> newPost(@RequestBody POPost post) {
        return this.ajaxSuccessResponse();
    }
	
	@RequestMapping(value = "/findbykey", method = RequestMethod.GET)
	@ResponseBody
	public String findPostByKey(HttpSession session,String key) throws Exception {
        List<POPost> list = postService.findByTitle(BaseEncode.encoding(key));
        return objectMapper.writeValueAsString(list);
    }
}
