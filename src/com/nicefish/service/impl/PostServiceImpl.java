package com.nicefish.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicefish.dao.POPostMapper;
import com.nicefish.po.POPost;
import com.nicefish.po.POSysParam;
import com.nicefish.po.POUser;
import com.nicefish.service.PostService;
import com.nicefish.service.SysParamService;
import com.nicefish.service.UserService;
import com.nicefish.utils.WebUtil;
import com.nicefish.vo.VONewPost;

@Service("postService")
public class PostServiceImpl implements PostService {
	@Autowired
	private SysParamService sysParamService;
	
	@Autowired
	private UserService userSerivce;
	
	@Autowired
	private POPostMapper postMapper;

	@Override
	public POPost getPostById(String id) {
		return postMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<POPost> getAllPosts() {
		return postMapper.findAll();
	}
	
	@Override
	public List<POPost> getPostListByPage(String currentPage) {
		POSysParam poSysParam=sysParamService.findByParamKey("POST_PAGE_NUM");
		String pageSize=poSysParam.getParamValue();
		
		String[] pageParams=WebUtil.parseStartLimit(currentPage,pageSize);
		String start=pageParams[0];
		String limit=pageParams[1];
		
		return postMapper.selectByPage(start,limit);
	}
	
	@Override
	public String newPost(VONewPost voNewPost) throws IllegalAccessException, InvocationTargetException {
		POPost poPost=new POPost();
		BeanUtils.copyProperties(poPost, voNewPost);		
		POUser poUser=userSerivce.findById(voNewPost.getUserId());
		BeanUtils.copyProperties(poPost, poUser);
		
		String postId=UUID.randomUUID().toString();
		poPost.setPostId(postId);
		postMapper.insertSelective(poPost);
		return postId;
	}
	
	@Override
	public int deleteById(String postId) {
		return postMapper.deleteByPrimaryKey(postId);
	}

	@Override
	public List<POPost> getPostByTitle(String key) {
		Map<String,Object> map=new HashMap<String,Object>();
		if(key!=null&&!key.equals(null)){
			map.put("key", key);
		}
		return postMapper.findByTitle(map);
	}

	@Override
	public String getTotalPages() {
		int totalCount=postMapper.selectCount();
		POSysParam poSysParam=sysParamService.findByParamKey("POST_PAGE_NUM");
		String pageSize=poSysParam.getParamValue();
		return WebUtil.calcPages(totalCount+"", pageSize);
	}
	
}
