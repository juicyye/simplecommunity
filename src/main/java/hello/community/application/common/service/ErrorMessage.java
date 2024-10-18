package hello.community.application.common.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorMessage {
    USER_NOT_FOUND("유저를 찾을 수 없습니다."),
    POST_NOT_FOUND("게시글을 찾을 수 없습니다.")

    ;

    private String description;
}
