package hello.community.global.security.jwt;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.community.application.user.controller.req.UserLoginReqDto;
import hello.community.application.user.domain.Role;
import hello.community.application.user.domain.User;
import hello.community.application.user.service.port.UserRepository;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(value = "/sql/delete-all-data.sql")
class JwtAuthenticationFilterTest {
    @Autowired
    private ObjectMapper om;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void init(){
        userRepository.save(
                User.builder().email("123@naver.com").password(passwordEncoder.encode("password")).nickname("닉네임").createdAt(
                        LocalDateTime.of(2024, 10, 17, 13, 19)).role(Role.ROLE_USER).build());
    }

    @Test
    @DisplayName("정상적으로 로그인을 진행하면 JWT 토큰을 발급한다")
    void successAuthentication() throws Exception {
        // given
        UserLoginReqDto userLoginReqDto = new UserLoginReqDto("123@naver.com", "password");
        String requestBody = om.writeValueAsString(userLoginReqDto);

        // when
        ResultActions resultActions = mvc.perform(
                        MockMvcRequestBuilders.post("/api/login").content(requestBody).contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
        String header = resultActions.andReturn().getResponse().getHeader(JwtVO.HEADER);

        // then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        assertTrue(header.startsWith(JwtVO.TOKEN_PREFIX));
    }

    @Test
    @DisplayName("로그인 정보가 틀리면 오류를 반환한다")
    void unSuccessAuthentication() throws Exception {
        // given
        UserLoginReqDto userLoginReqDto = new UserLoginReqDto("1234@naver.com", "password");
        String requestBody = om.writeValueAsString(userLoginReqDto);

        // when
        ResultActions resultActions = mvc.perform(
                        MockMvcRequestBuilders.post("/api/login").content(requestBody).contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        // then
        resultActions.andExpect(MockMvcResultMatchers.status().isUnauthorized()).andExpect(jsonPath("$.code").value(-1));
    }

}