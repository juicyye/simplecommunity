package hello.community.application.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    ROLE_ADMIN("관리자"), ROLE_USER("유저");

    private String description;
}
