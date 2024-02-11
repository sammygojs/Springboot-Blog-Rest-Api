package online.sumitakoliya.springboot.blog.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import online.sumitakoliya.springboot.blog.payload.PostDto;
import online.sumitakoliya.springboot.blog.repository.PostRepository;
import online.sumitakoliya.springboot.blog.service.PostService;
import online.sumitakoliya.springboot.blog.entity.Post;
import online.sumitakoliya.springboot.blog.exception.ResourceNotFoundException;

@Service
public class PostServiceImpl implements PostService {

	private PostRepository postRepository;

	@Autowired
	public PostServiceImpl(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@Override
	public PostDto createPost(PostDto postDto) {
		// convert DTO to entity
		Post post = mapToEntity(postDto);

		Post newPost = postRepository.save(post);
		
		PostDto postResponse = mapToDto(newPost);
		
		return postResponse;
	}

	@Override
	public List<PostDto> getAllPosts(int pageNo, int pageSize) {
		//create pageable instance
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		
		Page<Post>posts = postRepository.findAll(pageable);
		
		//get content for page object
		List<Post>listOfPosts = posts.getContent();
		
		return listOfPosts.stream().map(post-> mapToDto(post)).collect(Collectors.toList());
	}

	private PostDto mapToDto(Post post) {
		// convert entity to DTO
		PostDto postDto = new PostDto();
		postDto.setId(post.getId());
		postDto.setTitle(post.getTitle());
		postDto.setDescription(post.getDescription());
		postDto.setContent(post.getContent());
		return postDto;
	}
	
	private Post mapToEntity(PostDto postDto) {
		// convert entity to DTO
		Post post = new Post();
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());
		return post;
	}

	@Override
	public PostDto getPostById(long id){
//		Post post;
		Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
		return mapToDto(post);
//				.orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
//		return 
	}

	@Override
	public PostDto updatePost(PostDto postDto, long id) {
		Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());
		Post updatedPost = postRepository.save(post);
		return mapToDto(updatedPost);
	}

	@Override
	public void deletePostById(long id) {
		Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
		postRepository.deleteById(id);
		
	}

}
