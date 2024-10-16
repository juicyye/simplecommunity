package hello.community.application.user.domain;

import static org.assertj.core.api.Assertions.assertThat;

import hello.community.application.user.controller.req.UserCreateReqDto;
import hello.community.mock.FakeLocalDateTimeHolder;
import hello.community.mock.FakeUuidRandomHolder;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {
    FakeUuidRandomHolder fakeUuidRandomHolder = new FakeUuidRandomHolder("랜덤");
    FakeLocalDateTimeHolder fakeLocalDateTimeHolder = new FakeLocalDateTimeHolder(
            LocalDateTime.of(2024, 10, 16, 19, 41, 30));

    @Test
    @DisplayName("create로 유저가 제대로 생성되는지 확인한다")
    void create() throws Exception {
        // given
        UserCreateReqDto userCreateReqDto = createUserDto("이메일", "비밀번호", "닉네임");

        // when
        User user = User.create(userCreateReqDto, "인코딩비밀번호", fakeUuidRandomHolder, fakeLocalDateTimeHolder);

        // then
        assertThat(user)
                .isNotNull()
                .extracting("email", "password", "nickname", "UID")
                .containsExactly("이메일", "인코딩비밀번호", "닉네임", "랜덤");
    }

    private static UserCreateReqDto createUserDto(String email, String password, String nickname) {
        return new UserCreateReqDto(email, password, nickname);
    }

}