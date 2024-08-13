package eter2.spring_project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import eter2.spring_project.dto.ApplicationRequestDTO;
import eter2.spring_project.entity.Application;
import eter2.spring_project.entity.User;
import eter2.spring_project.entity.Company;
import eter2.spring_project.entity.Post;
import eter2.spring_project.service.ApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ApplicationController.class)
public class ApplicationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ApplicationService applicationService;

    @Autowired
    private ObjectMapper objectMapper;

    private ApplicationRequestDTO requestDTO;
    private Application application;
    private User testUser;
    private Company testCompany;
    private Post testPost;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setId(1L);
        testUser.setUsername("사용자1");

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

        requestDTO = new ApplicationRequestDTO();
        requestDTO.setPostId(1L);
        requestDTO.setUserId(1L);

        application = new Application();
        application.setId(1L);
        application.setUser(testUser);
        application.setPost(testPost);
    }

    // 사용자 공고 지원 테스트
    @Test
    void applyTest() throws Exception {
        when(applicationService.apply(any(ApplicationRequestDTO.class))).thenReturn(application);

        mockMvc.perform(post("/api/apply")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.user").exists())
                .andExpect(jsonPath("$.user.id").value(1))
                .andExpect(jsonPath("$.post").exists())
                .andExpect(jsonPath("$.post.id").value(1));
    }
}