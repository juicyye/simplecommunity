package hello.community.application.common.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.community.application.common.controller.resp.ResponseDto;
import java.net.BindException;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionControllerAdvice {
    private final ObjectMapper om;

    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> bindException(org.springframework.validation.BindException e) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : e.getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return new ResponseEntity<>(new ResponseDto<>(-1, "유효성 검사 실패", errors), HttpStatus.BAD_REQUEST);
    }
}
