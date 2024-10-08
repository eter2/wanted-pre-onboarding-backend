package eter2.spring_project.controller;

import eter2.spring_project.dto.PostRequestDTO;
import eter2.spring_project.dto.PostResponseDTO;
import eter2.spring_project.dto.PostDetailDTO;
import eter2.spring_project.entity.Post;
import eter2.spring_project.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 공고 생성
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

    // 공고 수정
    @PutMapping(value = "/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody Post postDetails) {
        return postService.updatePost(id, postDetails);
    }

    // 공고 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.ok().build();
    }

    // 공고 리스트 불러오기
    @GetMapping
    public List<PostResponseDTO> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return posts.stream()
                .map(PostResponseDTO::new)
                .collect(Collectors.toList());
    }

    // 공고 상세 정보 불러오기
    @GetMapping("/{id}")
    public ResponseEntity<PostDetailDTO> getPostById(@PathVariable Long id) {
        PostDetailDTO post = postService.getPostById(id);
        return ResponseEntity.ok(post);
    }

    // 공고 검색
    @GetMapping(value = "/search", produces = "application/json; charset=UTF-8")
    public ResponseEntity<List<PostResponseDTO>> searchPosts(@RequestParam("search") String search) {
        List<PostResponseDTO> posts = postService.searchPosts(search);
        return ResponseEntity.ok(posts);
    }
}
