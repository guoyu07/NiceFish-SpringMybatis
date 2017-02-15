package com.nicefish.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nicefish.model.Comment;
import com.nicefish.service.CommentService;

@Controller
@RequestMapping("/comments")
public class CommentController {
	
	private final static ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private CommentService commentService;

	@RequestMapping(value = "{postid}", method = RequestMethod.GET)
	@ResponseBody
	public String postFindKey(@PathVariable("postid")String postid) throws Exception {
        List<Comment> list = commentService.findByPostId(postid);
        return objectMapper.writeValueAsString(list);
    }
}
