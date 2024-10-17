package hello.community.application.user.controller;

import hello.community.application.common.controller.resp.ResponseDto;
import hello.community.application.user.controller.req.UserCreateReqDto;
import hello.community.application.user.controller.resp.UserCreateRespDto;
import hello.community.application.user.domain.User;
import hello.community.application.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody UserCreateReqDto userCreateReqDto, BindingResult bindingResult) {
        User user = userService.create(userCreateReqDto);
        return new ResponseEntity<>(new ResponseDto<>(1, "회원가입에 성공했습니다.", UserCreateRespDto.from(user)),
                HttpStatus.CREATED);
    }
}
