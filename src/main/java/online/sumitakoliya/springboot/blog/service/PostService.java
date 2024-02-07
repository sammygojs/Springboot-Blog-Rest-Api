package online.sumitakoliya.springboot.blog.service;

import online.sumitakoliya.springboot.blog.payload.PostDto;

public interface PostService {
	PostDto createPost(PostDto postDto);
}
