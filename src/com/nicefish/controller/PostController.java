package com.nicefish.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nicefish.po.POPost;
import com.nicefish.service.PostService;
import com.nicefish.vo.VONewPost;

@Controller
@RequestMapping("/post")
public class PostController extends BaseController{
	
	@Autowired
	private PostService postService;

	@RequestMapping(value = "/getPostListByPage/{currentPage}", method = RequestMethod.GET)
	@ResponseBody
	public List<POPost> getPostListByPage(@PathVariable int currentPage) throws Exception{
		List<POPost> poPostList=postService.getPostListByPage(currentPage+"");
		return poPostList;
    }
	
	@RequestMapping(value = "/getTotalPages", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getTotalPages() throws Exception{
		String totalCount=postService.getTotalPages();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("totalCount", totalCount);
		return resultMap;
    }
	
	@RequestMapping(value = "{postId}", method = RequestMethod.GET)
	@ResponseBody
	public POPost getPostById(@PathVariable("postId")String postId) {
        return postService.getPostById(postId);
    }
	
	@RequestMapping(value = "/newPost", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> newPost(@RequestBody VONewPost voNewPost) throws Exception {
		String postId=postService.newPost(voNewPost);
        return this.ajaxSuccessResponse(postId);
    }
	
	@RequestMapping(value = "/delPost/{postId}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> deleteById(@PathVariable("postId")String postId) {
		postService.deleteById(postId);
        return this.ajaxSuccessResponse();
    }
}
