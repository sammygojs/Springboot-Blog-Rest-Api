package online.sumitakoliya.springboot.blog.service;

import java.util.List;

import online.sumitakoliya.springboot.blog.payload.PostDto;

public interface PostService {
	PostDto createPost(PostDto postDto);
	List<PostDto>getAllPosts();
	PostDto getPostById(long id);
}
