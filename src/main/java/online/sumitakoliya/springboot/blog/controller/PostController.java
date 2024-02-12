package online.sumitakoliya.springboot.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import online.sumitakoliya.springboot.blog.payload.PostDto;
import online.sumitakoliya.springboot.blog.payload.PostResponse;
import online.sumitakoliya.springboot.blog.service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	private PostService postService;

	@Autowired
	public PostController(PostService postService) {
		this.postService = postService;
	}

	// create blog post rest api
	@PostMapping
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
		return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
	}

	// get all posts rest api
	@GetMapping
	public PostResponse getAllPosts(
			@RequestParam(value="pageNo", defaultValue="0", required=false)int pageNo,
			@RequestParam(value="pageSize", defaultValue="10", required=false) int pageSize
			) {
		return postService.getAllPosts(pageNo, pageSize);
	}

	// get post by id
	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id) {
		return ResponseEntity.ok(postService.getPostById(id));
//				ResponseEntity.ok(postService.getPostById(id),HttpStatus.ACCEPTED);
	}

	// update post by id rest api
	@PutMapping("/{id}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable(name = "id") long id) {
		PostDto postResponse = postService.updatePost(postDto, id);
		return new ResponseEntity<>(postResponse, HttpStatus.OK);
	}

	// delete post by id rest api
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id) {
		postService.deletePostById(id);
		return new ResponseEntity<>("Post entity deleted successfully", HttpStatus.OK);
	}

}
