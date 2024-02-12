package online.sumitakoliya.springboot.blog.service;

import java.util.List;

import online.sumitakoliya.springboot.blog.payload.PostDto;
import online.sumitakoliya.springboot.blog.payload.PostResponse;

public interface PostService {
	PostDto createPost(PostDto postDto);
	PostResponse getAllPosts(int pageNo, int pageSize);
	PostDto getPostById(long id);
	PostDto updatePost(PostDto postDto, long id);
	void deletePostById(long id);
}
