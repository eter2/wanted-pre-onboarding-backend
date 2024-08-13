package eter2.spring_project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import eter2.spring_project.dto.PostRequestDTO;
import eter2.spring_project.dto.PostResponseDTO;
import eter2.spring_project.dto.PostDetailDTO;
import eter2.spring_project.entity.Post;
import eter2.spring_project.entity.Company;
import eter2.spring_project.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PostController.class)
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @Autowired
    private ObjectMapper objectMapper;

    private Post testPost;
    private PostRequestDTO testPostRequestDTO;
    private PostResponseDTO testPostResponseDTO;
    private PostDetailDTO testPostDetailDTO;
    private Company testCompany;

    @BeforeEach
    void setUp() {
        testCompany = new Company();
        testCompany.setId(1L);
        testCompany.setName("원티드랩");
        testCompany.setCountry("한국");
        testCompany.setRegion("서울");

        testPost = new Post();
        testPost.setId(1L);
        testPost.setCompany(testCompany);
        testPost.setPosition("백엔드 주니어 개발자");
        testPost.setCompensation(1000000);
        testPost.setDescription("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..");
        testPost.setSkills("Python");

        testPostRequestDTO = new PostRequestDTO();
        testPostRequestDTO.setCompanyId(1L);
        testPostRequestDTO.setPosition("백엔드 주니어 개발자");
        testPostRequestDTO.setCompensation(1000000);
        testPostRequestDTO.setDescription("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..");
        testPostRequestDTO.setSkills("Python");

        testPostResponseDTO = new PostResponseDTO(testPost);

        testPostDetailDTO = new PostDetailDTO();
        testPostDetailDTO.setId(1L);
        testPostDetailDTO.setName("원티드랩");
        testPostDetailDTO.setPosition("백엔드 주니어 개발자");
        testPostDetailDTO.setCompensation(1000000);
        testPostDetailDTO.setDescription("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..");
        testPostDetailDTO.setSkills("Python");
    }

    // 공고 작성 테스트
    @Test
    void testCreatePost() throws Exception {
        when(postService.createPost(any(PostRequestDTO.class))).thenReturn(testPost);

        mockMvc.perform(post("/api/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testPostRequestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.company.id").value(1))
                .andExpect(jsonPath("$.position").value("백엔드 주니어 개발자"))
                .andExpect(jsonPath("$.compensation").value(1000000))
                .andExpect(jsonPath("$.description").value("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은.."))
                .andExpect(jsonPath("$.skills").value("Python"));
    }

    // 공고 수정 테스트
    @Test
    void testUpdatePost() throws Exception {
        when(postService.updatePost(anyLong(), any(Post.class))).thenReturn(testPost);

        mockMvc.perform(put("/api/posts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testPost)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.company.id").value(1))
                .andExpect(jsonPath("$.position").value("백엔드 주니어 개발자"))
                .andExpect(jsonPath("$.compensation").value(1000000))
                .andExpect(jsonPath("$.description").value("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은.."))
                .andExpect(jsonPath("$.skills").value("Python"));
    }

    // 공고 삭제 테스트
    @Test
    void testDeletePost() throws Exception {
        doNothing().when(postService).deletePost(anyLong());

        mockMvc.perform(delete("/api/posts/1"))
                .andExpect(status().isOk());
    }

    // 공고 전체 리스트 테스트
    @Test
    void testGetAllPosts() throws Exception {
        List<Post> posts = Arrays.asList(testPost);
        when(postService.getAllPosts()).thenReturn(posts);

        mockMvc.perform(get("/api/posts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("원티드랩"))
                .andExpect(jsonPath("$[0].position").value("백엔드 주니어 개발자"))
                .andExpect(jsonPath("$[0].compensation").value(1000000))
                .andExpect(jsonPath("$[0].skills").value("Python"));
    }

    // 공고 상세 페이지 테스트
    @Test
    void testGetPostById() throws Exception {
        when(postService.getPostById(anyLong())).thenReturn(testPostDetailDTO);

        mockMvc.perform(get("/api/posts/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("원티드랩"))
                .andExpect(jsonPath("$.position").value("백엔드 주니어 개발자"))
                .andExpect(jsonPath("$.compensation").value(1000000))
                .andExpect(jsonPath("$.description").value("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은.."))
                .andExpect(jsonPath("$.skills").value("Python"));
    }

    // 공고 검색 테스트
    @Test
    void testSearchPosts() throws Exception {
        List<PostResponseDTO> posts = Arrays.asList(testPostResponseDTO);
        when(postService.searchPosts(any(String.class))).thenReturn(posts);

        mockMvc.perform(get("/api/posts/search?search=원티드"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("원티드랩"))
                .andExpect(jsonPath("$[0].position").value("백엔드 주니어 개발자"))
                .andExpect(jsonPath("$[0].compensation").value(1000000))
                .andExpect(jsonPath("$[0].skills").value("Python"));
    }
}