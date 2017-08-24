package com.nicefish.web.service.impl;

import com.nicefish.auth.po.POUser;
import com.nicefish.auth.service.UserService;
import com.nicefish.web.dao.POCommentMapper;
import com.nicefish.web.po.POComment;
import com.nicefish.web.po.POSysParam;
import com.nicefish.web.service.CommentService;
import com.nicefish.web.service.SysParamService;
import com.nicefish.web.utils.WebUtil;
import com.nicefish.web.vo.VONewComment;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service("commentService")
public class CommentServiceImpl implements CommentService {
	@Autowired
	private SysParamService sysParamService;
	
	@Autowired
	private POCommentMapper commentMapper;

	@Autowired
	private UserService userService;

	public int newComment(VONewComment voNewComment)
			throws IllegalAccessException, InvocationTargetException {
		
		POComment poComment = new POComment();
		BeanUtils.copyProperties(poComment, voNewComment);

		if(StringUtils.isNotBlank(voNewComment.getUserId())){
			POUser poUser = userService.findById(voNewComment.getUserId());
			BeanUtils.copyProperties(poComment, poUser);
		}else{
			if(StringUtils.isNotBlank(voNewComment.getNickName())){
				poComment.setNickName(voNewComment.getNickName());
			}else{
				poComment.setNickName("游客");
			}
		}

		poComment.setCommentId(UUID.randomUUID().toString());
		return commentMapper.insertSelective(poComment);
	}

	public POComment getCommentById(String id) {
		return commentMapper.selectByPrimaryKey(id);
	}
	public int delCommentById(String id) {
		return commentMapper.deleteByPrimaryKey(id);
	}

	public List<POComment> getCommentListByPostId(String postId) {
		return commentMapper.findByPostId(postId);
	}

	public List<POComment> getCommentByPostIdAndPage(String postId,String pageIndex) {
		POSysParam poSysParam=sysParamService.findByParamKey("COMMENT_PAGE_NUM");
		String pageSize=poSysParam.getParamValue();
		
		int[] pageParams=WebUtil.parseStartLimit(pageIndex,pageSize);
		int start=pageParams[0];
		int limit=pageParams[1];
		
		return commentMapper.findByPostIdAndPage(postId,start,limit);
	}

	public Map<String, Object> getPagerParam(String postId) {
		int totalCount=commentMapper.selectCount(postId);
		
		POSysParam poSysParam=sysParamService.findByParamKey("COMMENT_PAGE_NUM");
		String pageSize=poSysParam.getParamValue();
		
		Map<String,Object> result=new HashMap<String,Object>();
		result.put("itemsPerPage", pageSize);
		result.put("totalItems", totalCount);
		return result;
	}
}
