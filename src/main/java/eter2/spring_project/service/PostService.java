package eter2.spring_project.service;

import eter2.spring_project.dto.PostRequestDTO;
import eter2.spring_project.dto.PostResponseDTO;
import eter2.spring_project.dto.PostDetailDTO;
import eter2.spring_project.entity.Company;
import eter2.spring_project.entity.Post;
import eter2.spring_project.repository.CompanyRepository;
import eter2.spring_project.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private static final Logger logger = LoggerFactory.getLogger(PostService.class);

    @Autowired
    private PostRepository postRepository;
    private final CompanyRepository companyRepository;

    @Autowired
    public PostService(PostRepository postRepository, CompanyRepository companyRepository) {
        this.postRepository = postRepository;
        this.companyRepository = companyRepository;
    }

    @Transactional
    public Post createPost(PostRequestDTO postRequestDTO) {
        Company company = companyRepository.findById(postRequestDTO.getCompanyId())
                .orElseThrow(() -> new IllegalArgumentException("company ID not found: " + postRequestDTO.getCompanyId()));

        Post post = new Post();
        post.setCompany(company);
        post.setPosition(postRequestDTO.getPosition());
        post.setCompensation(postRequestDTO.getCompensation());
        post.setDescription(postRequestDTO.getDescription());
        post.setSkills(postRequestDTO.getSkills());

        return postRepository.save(post);
    }

    public Post updatePost(Long id, Post postDetails) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));

        if (postDetails.getPosition() != null) {
            post.setPosition(postDetails.getPosition());
        }
        if (postDetails.getCompensation() != null) {
            post.setCompensation(postDetails.getCompensation());
        }
        if (postDetails.getDescription() != null) {
            post.setDescription(postDetails.getDescription());
        }
        if (postDetails.getSkills() != null) {
            post.setSkills(postDetails.getSkills());
        }

        return postRepository.save(post);
    }

    public  void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Transactional
    public PostDetailDTO getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        PostDetailDTO dto = new PostDetailDTO(post);
        List<Long> others = postRepository.findAllByCompanyId(post.getCompany().getId()).stream()
                .map(Post::getId)
                .filter(pId -> !pId.equals(id))
                .collect(Collectors.toList());
        dto.setOthers(others);

        return dto;
    }

    public List<PostResponseDTO> searchPosts(String search) {
        List<Post> posts = postRepository.searchPosts(search);
        return posts.stream()
                .map(PostResponseDTO::new)
                .collect(Collectors.toList());
    }
}
