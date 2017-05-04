package com.nicefish.controller;

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

import com.nicefish.po.POPost;
import com.nicefish.po.POUser;
import com.nicefish.service.PostService;
import com.nicefish.utils.SessionConsts;
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
	public String getTotalPages() throws Exception{
		String totalCount=postService.getTotalPages();
		return totalCount;
    }
	
	@RequestMapping(value = "/getTotalItemsNum", method = RequestMethod.GET)
	@ResponseBody
	public String getTotalItemsNum() throws Exception{
		String totalCount=postService.getTotalItemsNum();
		return totalCount;
    }
	
	@RequestMapping(value = "/getPagerParam", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getPagerParam() throws Exception{
		return postService.getPagerParam();
    }
	
	@RequestMapping(value = "{postId}", method = RequestMethod.GET)
	@ResponseBody
	public POPost getPostById(@PathVariable("postId")String postId) {
        return postService.getPostById(postId);
    }
	
	@RequestMapping(value = "/newPost", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> newPost(@RequestBody VONewPost voNewPost,HttpSession session) throws Exception {
		//校验用户是否已经登录
		if(null==session.getAttribute(SessionConsts.UserInfo)){
			return this.ajaxFailureResponse("请先登录");
		}
		
		POUser poUser=(POUser) session.getAttribute(SessionConsts.UserInfo);
		voNewPost.setUserId(poUser.getUserId());
		
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
