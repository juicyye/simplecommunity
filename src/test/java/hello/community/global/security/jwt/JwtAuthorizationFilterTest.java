package hello.community.global.security.jwt;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import hello.community.application.user.domain.Role;
import hello.community.application.user.domain.User;
import hello.community.application.user.service.port.UserRepository;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@SpringBootTest
class JwtAuthorizationFilterTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProcess jwtProcess;

    @BeforeEach
    void init(){
        userRepository.save(
                User.builder().email("123@naver.com").password(passwordEncoder.encode("password")).nickname("닉네임").createdAt(
                        LocalDateTime.of(2024, 10, 17, 13, 19)).role(Role.ROLE_USER).build());
    }

    @Test
    @DisplayName("권한이 user인 토큰이 권한에 맞는 URL로 이동할 시 성공적으로 갈 수 있다")
    void successAuthorization() throws Exception {
        // given
        String accessToken = jwtProcess.createAccessToken("123@naver.com", "ROLE_USER");

        // when
        mvc.perform(post("/api/s/adfa").header(JwtVO.HEADER, JwtVO.TOKEN_PREFIX + accessToken))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        // then
    }

    @Test
    @DisplayName("권한이 user인 토큰이 admin 권한으로 가면 forbidden")
    void ForbiddenAuthorization() throws Exception {
        // given
        String accessToken = jwtProcess.createAccessToken("123@naver.com", "ROLE_USER");

        // when
        mvc.perform(post("/admin/adfa").header(JwtVO.HEADER, JwtVO.TOKEN_PREFIX + accessToken))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isForbidden());

        // then
    }

    @Nested
    class roleAdmin{

        @Test
        @DisplayName("admin 권한으로 admin권한 사이트로 갈 수 있다")
        void AdminToAdminAuthorization() throws Exception {
            // given
            String accessToken = jwtProcess.createAccessToken("123@naver.com", "ROLE_ADMIN");

            // when
            mvc.perform(post("/admin/adfa").header(JwtVO.HEADER, JwtVO.TOKEN_PREFIX + accessToken))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isNotFound());

            // then
        }

        @Test
        @DisplayName("admin 권한으로 user권한 사이트로 갈 수 있다")
        void AdminAllOfAuthorization() throws Exception {
            // given
            String accessToken = jwtProcess.createAccessToken("123@naver.com", "ROLE_ADMIN");

            // when
            mvc.perform(post("/s/adfa").header(JwtVO.HEADER, JwtVO.TOKEN_PREFIX + accessToken))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isNotFound());

            // then
        }
    }

}