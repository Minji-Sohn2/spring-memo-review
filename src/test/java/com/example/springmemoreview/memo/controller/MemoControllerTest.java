package com.example.springmemoreview.memo.controller;

import com.example.springmemoreview.memo.dto.MemoRequestDto;
import com.example.springmemoreview.memo.service.MemoService;
import com.example.springmemoreview.mvc.MockSpringSecurityFilter;
import com.example.springmemoreview.security.user.UserDetailsImpl;
import com.example.springmemoreview.user.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.security.Principal;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*@WebMvcTest(
        controllers = {MemoController.class, AuthController.class},
        excludeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ASSIGNABLE_TYPE,
                        classes = WebSecurityConfig.class
                )
        }
)*/
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Rollback(value = false)
class MemoControllerTest {

    private MockMvc mvc;

    private Principal mockPrincipal;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    MemoService memoService;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity(new MockSpringSecurityFilter()))
                .build();
    }

    private void mockUserSetup() {
        // Mock 테스트 유져 생성
        String nickname = "new-user";
        String password = "pass";
        User testUser = new User(nickname, password);
        UserDetailsImpl testUserDetails = new UserDetailsImpl(testUser);
        mockPrincipal = new UsernamePasswordAuthenticationToken(testUserDetails, null, testUserDetails.getAuthorities());
    }

    @Test
    @DisplayName("컨트롤러 - 메모 작성하기")
    void createMemoTest() throws Exception {
        // given
        this.mockUserSetup();
        MemoRequestDto requestDto = MemoRequestDto.builder()
                .title("new title")
                .content("new content")
                .build();
        String memoInfo = objectMapper.writeValueAsString(requestDto);
        // memoInfo : "{"title":"new title","content":"new content"}"

        // when - then
        mvc.perform(post("/api/memos")
                        //.contentType(MediaType.APPLICATION_JSON)
                        .content(memoInfo)
                        //.accept(MediaType.APPLICATION_JSON)
                        .principal(mockPrincipal)
                )
                .andExpect(status().isCreated())
                .andDo(print());    // 415 나옴

    }

    @Test
    @DisplayName("컨트롤러 - 메모 불러오기")
    void getMemosTest() throws Exception {
        mvc.perform(get("/api/memos")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("컨트롤러 - 메모 1개 불러오기")
    void getOneMemoTest() throws Exception {
        // given


        // when - then
        mvc.perform(get("/api/memos/{memoId}", "1")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

}