package hello.community.application.user.domain;

import hello.community.application.common.service.port.LocalDateTimeHolder;
import hello.community.application.common.service.port.UuidRandomHolder;
import hello.community.application.user.controller.req.UserCreateReqDto;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
@EqualsAndHashCode(of = "id")
public class User {
    private Long id;
    private String email;
    private String password;
    private Role role;
    private UserStatus status;
    private String nickname;
    private Long lastLoginAt;
    private String UID;
    private LocalDateTime deletedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static User create(UserCreateReqDto userCreateReqDto, String encodedPassword, UuidRandomHolder uuidRandomHolder,
                              LocalDateTimeHolder localDateTimeHolder) {
        return User.builder()
                .email(userCreateReqDto.getEmail())
                .password(encodedPassword)
                .role(Role.ROLE_USER)
                .status(UserStatus.ACTIVE)
                .nickname(userCreateReqDto.getNickname())
                .UID(uuidRandomHolder.getUID())
                .createdAt(localDateTimeHolder.now())
                .updatedAt(localDateTimeHolder.now())
                .build();
    }
}
