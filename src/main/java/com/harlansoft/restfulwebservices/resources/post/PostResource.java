package com.harlansoft.restfulwebservices.resources.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.harlansoft.restfulwebservices.dao.post.PostService;
import com.harlansoft.restfulwebservices.model.post.Post;

@RestController
public class PostResource {

	@Autowired
	private PostService postService;
	
	@GetMapping("/users/{id}/posts")
	public List<Post> retrieveAllPostsByUser(@PathVariable Integer userId) {
		List<Post> posts = postService.findByUserId(userId);
		return posts;
	}
	
}
