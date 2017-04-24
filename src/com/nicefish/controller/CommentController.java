package com.nicefish.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nicefish.po.POComment;
import com.nicefish.po.POUser;
import com.nicefish.service.CommentService;
import com.nicefish.utils.SessionConsts;
import com.nicefish.utils.IPUtil;
import com.nicefish.utils.Result;

@Controller
@RequestMapping("/comments")
public class CommentController extends BaseController{
	
	private final static ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private CommentService commentService;

	@RequestMapping(value = "/postid/{postid}", method = RequestMethod.GET)
	@ResponseBody
	public String PostFindByPid(@PathVariable("postid")String postid) throws Exception {
        List<POComment> list = commentService.findByPostId(postid);
        return objectMapper.writeValueAsString(list);
    }
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public Result<String> CommentAdd(POComment comment,HttpServletRequest request,HttpSession session){
		Result<String> result = new Result<String>();
		POUser user = (POUser)session.getAttribute(SessionConsts.UserInfo);
		comment.setCommentId(UUID.randomUUID().toString());
		comment.setUserId(user.getUserId());
		comment.setUserName(user.getUserName());
		comment.setNickName(user.getNickName());
		comment.setCommentIp(IPUtil.getIpAddress(request));
		int flag = commentService.insert(comment);
		if(flag > 0){
			result.setStatus(flag);
			result.setMsg("评论成功！");
			return result;
		}
		result.setStatus(-1);
		result.setMsg("评论失败！");
		return result;
	}
}
