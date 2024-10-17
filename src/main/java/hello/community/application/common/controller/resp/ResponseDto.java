package hello.community.application.common.controller.resp;

import lombok.Getter;

@Getter
public class ResponseDto<T> {
    private final Integer code; // 성공 1, 실패 -1
    private final String message;
    private final T data;

    public ResponseDto(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
