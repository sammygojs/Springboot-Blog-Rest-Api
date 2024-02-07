package online.sumitakoliya.springboot.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import online.sumitakoliya.springboot.blog.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	
}
