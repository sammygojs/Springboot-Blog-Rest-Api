package online.sumitakoliya.springboot.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.sumitakoliya.springboot.blog.payload.PostDto;
import online.sumitakoliya.springboot.blog.repository.PostRepository;
import online.sumitakoliya.springboot.blog.service.PostService;
import online.sumitakoliya.springboot.blog.entity.Post;

@Service
public class PostServiceImpl implements PostService{

	private PostRepository postRepository;
		
	@Autowired
	public PostServiceImpl(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@Override
	public PostDto createPost(PostDto postDto) {
		//convert DTO to entity
		Post post = new Post();
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());
		
		Post newPost = postRepository.save(post);
		
		//convert entity to DTO
		PostDto postResponse = new PostDto();
		postResponse.setId(newPost.getId());
		postResponse.setTitle(newPost.getTitle());
		postResponse.setDescription(newPost.getDescription());
		postResponse.setContent(newPost.getContent());
		
		return postResponse;
	}

}
