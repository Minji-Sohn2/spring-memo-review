package com.example.springmemoreview.auth.controller;

import com.example.springmemoreview.mvc.MockSpringSecurityFilter;
import com.example.springmemoreview.security.user.UserDetailsImpl;
import com.example.springmemoreview.user.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.xml.validation.Validator;
import java.security.Principal;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Rollback(value = false)
class AuthControllerTest {

    private MockMvc mvc;

    private Principal mockPrincipal;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private Validator validator;

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

    /*@Test
    @DisplayName("회원가입 테스트")
    void loginControllerTest() {
        // given
        SignupRequestDto requestDto = SignupRequestDto.builder()
                .nickname("nickname")
                .password("password")
                .confirmPassword("password")
                .build();

        // when
        Set<ConstraintViolation<SignupRequestDto>> validate = validator.validate(requestDto);
    }*/
}