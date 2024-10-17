package hello.community.application.user.controller.resp;

import hello.community.application.user.domain.User;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class UserCreateRespDto implements Serializable {
    private String nickname;
    private LocalDateTime createdAt;

    public static UserCreateRespDto from(User user) {
        return UserCreateRespDto.builder()
                .nickname(user.getNickname())
                .createdAt(user.getCreatedAt())
                .build();
    }



}
