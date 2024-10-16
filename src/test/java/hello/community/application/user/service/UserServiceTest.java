package hello.community.application.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import hello.community.application.user.controller.req.UserCreateReqDto;
import hello.community.application.user.domain.User;
import hello.community.mock.FakeInstantHolder;
import hello.community.mock.FakeLocalDateTimeHolder;
import hello.community.mock.FakePasswordEncoder;
import hello.community.mock.FakeUserRepository;
import hello.community.mock.FakeUuidRandomHolder;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserServiceTest {

    private UserService userService;
    LocalDateTime localDateTime = LocalDateTime.of(2024, 10, 16, 19, 41, 30);

    @BeforeEach
    void setUp() {
        this.userService = new UserService(
                new FakeUserRepository(),
                new FakeLocalDateTimeHolder(localDateTime),
                new FakeInstantHolder(123L),
                new FakePasswordEncoder("fixPassword"),
                new FakeUuidRandomHolder("랜덤")
        );
    }

    @Test
    @DisplayName("create를 호출하면 유저가 저장된다")
    void create() throws Exception {
        // given
        UserCreateReqDto userCreateReqDto = new UserCreateReqDto("이메일", "패스워드", "닉네임");

        // when
        User user = userService.create(userCreateReqDto);

        // then
        assertThat(user).isNotNull()
                .extracting("id", "nickname", "UID", "password", "createdAt", "updatedAt")
                .containsExactly(0L,"닉네임","랜덤","fixPassword",localDateTime,localDateTime);
    }
}