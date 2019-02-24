package com.harlansoft.restfulwebservices.dao.post;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.harlansoft.restfulwebservices.model.post.Post;

@Service
public class PostService {
	
	private static List<Post> posts = new ArrayList<>();
	private static int postCount;
	static {
		posts.add(Post.builder().id(1).title("prova").message("messaggio").build());
		postCount = 1;
	}
	
	public List<Post> findAll() {
		return posts;
	}
	
	public Post findOne(Integer id) {
		return posts.stream()
			.filter(post -> post.getId().equals(id))
			.findAny()
			.orElse(null);
	}
	
	public List<Post> findByUserId(Integer userId) {
		return posts.stream()
				.filter(post -> post.getAuthor().getId().equals(userId))
				.collect(Collectors.toCollection(() -> new ArrayList<>()));
	}
	
	public Post save(Post post) {
		if (post.getId() == null) {
			post.setId(++postCount);
		}
		
		posts.add(post);
		
		return post;
	}
	
	public Post deleteById(Integer id) {
		Post post = findOne(id);
		return post != null ? posts.remove(posts.indexOf(post)) : null;
	}

}
