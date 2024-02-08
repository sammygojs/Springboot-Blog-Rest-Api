package online.sumitakoliya.springboot.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import online.sumitakoliya.springboot.blog.payload.PostDto;
import online.sumitakoliya.springboot.blog.service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	private PostService postService;

	@Autowired
	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	//create blog post rest api
	@PostMapping
	public ResponseEntity<PostDto>createPost(@RequestBody PostDto postDto){
		return new ResponseEntity<>(postService.createPost(postDto),HttpStatus.CREATED);
	}
	
	//get all posts rest api
	@GetMapping
	public List<PostDto>getAllPosts(){
		return postService.getAllPosts();
	}
	
	//get post by id
	@GetMapping("/{id}")
	public ResponseEntity<PostDto>getPostById(@PathVariable(name="id") long id){
		return ResponseEntity.ok(postService.getPostById(id));
//				ResponseEntity.ok(postService.getPostById(id),HttpStatus.ACCEPTED);
	}
	
	
}
