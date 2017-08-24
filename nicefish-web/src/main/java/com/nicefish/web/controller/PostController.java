package com.nicefish.web.controller;

import com.nicefish.auth.po.POUser;
import com.nicefish.web.po.POPost;
import com.nicefish.web.service.PostService;
import com.nicefish.web.utils.SessionConsts;
import com.nicefish.web.vo.VONewPost;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/post")
public class PostController extends BaseController{
	
	@Autowired
	private PostService postService;

	@RequestMapping("/getPostByUserId/{userId}/{currentPage}")
	public Map<String,Object> getPostByUserId(@PathVariable String userId, @PathVariable String currentPage){
		return this.buildResponse(postService.getPostByUserId(userId,currentPage)
				,postService.countByUserId(userId));
	}

	@RequestMapping(value = "/getPostListByPage/{currentPage}", method = RequestMethod.GET)
	public List<POPost> getPostListByPage(@PathVariable int currentPage) throws Exception{
		Session session= SecurityUtils.getSubject().getSession();
		List<POPost> poPostList=postService.getPostListByPage(currentPage+"");
		return poPostList;
    }
	
	@RequestMapping(value = "/getTotalPages", method = RequestMethod.GET)
	public String getTotalPages() throws Exception{
		Session session= SecurityUtils.getSubject().getSession();
		String totalCount=postService.getTotalPages();
		return totalCount;
    }
	
	@RequestMapping(value = "/getTotalItemsNum", method = RequestMethod.GET)
	public String getTotalItemsNum() throws Exception{
		String totalCount=postService.getTotalItemsNum();
		return totalCount;
    }
	
	@RequestMapping(value = "/getPagerParam", method = RequestMethod.GET)
	public Map<String,Object> getPagerParam() throws Exception{
		return postService.getPagerParam();
    }
	
	@RequestMapping(value = "/postdetail/{postId}", method = RequestMethod.GET)
	public POPost getPostById(@PathVariable("postId")String postId) {
		//点击数自动加一
		postService.readTimesPlusOne(postId);
        return postService.getPostById(postId);
    }
	
	@RequestMapping(value = "/newPost", method = RequestMethod.POST)
	public Map<String,Object> newPost(@RequestBody VONewPost voNewPost, HttpSession session) throws Exception {
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
	public Map<String,Object> deleteById(@PathVariable("postId")String postId) {
		postService.deleteById(postId);
        return this.ajaxSuccessResponse();
    }
}
