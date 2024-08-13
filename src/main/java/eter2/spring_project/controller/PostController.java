package eter2.spring_project.controller;

import eter2.spring_project.dto.PostRequestDTO;
import eter2.spring_project.dto.PostResponseDTO;
import eter2.spring_project.entity.Post;
import eter2.spring_project.dto.PostRequestDTO;
import eter2.spring_project.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping(produces = "application/json; charset=UTF-8")
    public ResponseEntity<?> createPost(@RequestBody PostRequestDTO postDTO) {
        try {
            Post createdPost = postService.createPost(postDTO);
            return ResponseEntity.ok(createdPost);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            String errorMessage = "An error occurred while creating the post: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @PutMapping(value = "/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody Post postDetails) {
        return postService.updatePost(id, postDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<PostResponseDTO> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return posts.stream()
                .map(PostResponseDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public Post getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

//    @GetMapping(produces = "application/json; charset=UTF-8")
//    public ResponseEntity<List<PostResponseDTO>> searchPosts(@RequestParam("search") String search) {
//        List<PostResponseDTO> posts = postService.searchPosts(search);
//        return ResponseEntity.ok(posts);
//    }


    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    @GetMapping(value = "/search", produces = "application/json; charset=UTF-8")
    public ResponseEntity<List<PostResponseDTO>> searchPosts(@RequestParam("search") String search) {
        logger.info("Received search parameter: {}", search);  // 로그 추가
        List<PostResponseDTO> posts = postService.searchPosts(search);
        return ResponseEntity.ok(posts);
    }
}
