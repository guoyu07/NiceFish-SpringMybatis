package com.nicefish.service;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nicefish.entity.Post;
import com.nicefish.enums.CommonStatusEnum;
import com.nicefish.enums.PostStatusEnum;
import com.nicefish.enums.PostTypeEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceTest {

	@Autowired
	private PostService service;
	
	@Test
	public void testListByUserId() {
		System.out.println(service.listByUserId(1L));
	}
	
	@Test
	public void testSave() {
		Post post = new Post();
		post.setContent("aaa");
		post.setCreateTime(new Date());
		post.setEnableComment(CommonStatusEnum.OPEN);
		post.setStatus(PostStatusEnum.PUBLISH);
		post.setTitle("title aa");
		post.setType(PostTypeEnum.ORIGINAL);
		post.setUserId(1L);
		
		service.save(post);
	}
}
