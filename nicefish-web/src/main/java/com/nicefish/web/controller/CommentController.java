package com.nicefish.web.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import com.nicefish.auth.po.POUser;
import com.nicefish.web.service.CommentService;
import com.nicefish.web.service.PostService;
import com.nicefish.web.vo.VONewComment;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.nicefish.web.utils.SessionConsts;

@RestController
@RequestMapping("/comment")
public class CommentController extends BaseController {
	@Autowired
	private CommentService commentService;

	@Autowired
	private PostService postService;

	@RequestMapping(value = "/{postId}/{pageIndex}", method = RequestMethod.GET)
	@ResponseBody
	public Object getCommentByPostIdAndPage(@PathVariable("postId") String postId,@PathVariable("pageIndex") String pageIndex)
			throws Exception {
		return commentService.getCommentByPostIdAndPage(postId,pageIndex);
	}

	@RequestMapping(value = "/getPagerParam/{postId}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getPagerParam(@PathVariable("postId") String postId) throws Exception{
		return commentService.getPagerParam(postId);
    }

	@RequestMapping(value = "/newComment", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> newComment(@RequestBody VONewComment voNewComment) throws IllegalAccessException,
			InvocationTargetException {
		Session session=SecurityUtils.getSubject().getSession(false);
		if(session!=null){
			POUser poUser=(POUser)session.getAttribute(SessionConsts.UserInfo);
			if(poUser!=null){
				voNewComment.setUserId(poUser.getUserId());
			}
		}

		commentService.newComment(voNewComment);
		//文章对应的评论数加一
		postService.commentTimesPlusOne(voNewComment.getPostId());
		return this.ajaxSuccessResponse();
	}

	@RequestMapping(value = "/delComment/{commentId}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> delComment(@PathVariable("commentId") String commentId) {
		commentService.delCommentById(commentId);
		return this.ajaxSuccessResponse();
	}
}
