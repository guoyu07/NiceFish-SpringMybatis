package com.nicefish.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nicefish.po.POUser;
import com.nicefish.service.CommentService;
import com.nicefish.utils.SessionConsts;
import com.nicefish.vo.VONewComment;

@Controller
@RequestMapping("/comment")
public class CommentController extends BaseController {
	@Autowired
	private CommentService commentService;

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
	public Map<String, Object> newComment(@RequestBody VONewComment voNewComment,HttpSession session) throws IllegalAccessException,
			InvocationTargetException {
		//校验用户是否已经登录
		if(null==session.getAttribute(SessionConsts.UserInfo)){
			return this.ajaxFailureResponse("请先登录");
		}
		
		POUser poUser=(POUser) session.getAttribute(SessionConsts.UserInfo);
		voNewComment.setUserId(poUser.getUserId());
		
		commentService.newComment(voNewComment);
		return this.ajaxSuccessResponse();
	}

	@RequestMapping(value = "/delComment/{commentId}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> delComment(@PathVariable("commentId") String commentId) {
		commentService.delCommentById(commentId);
		return this.ajaxSuccessResponse();
	}
}
