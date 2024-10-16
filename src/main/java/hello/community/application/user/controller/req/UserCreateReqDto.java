package hello.community.application.user.controller.req;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserCreateReqDto {
    @NotEmpty
    @Email
    private String email;
    @Pattern(regexp = "^[가-힣ㄱ-ㅎa-zA-Z]{2,20}$")
    private String password;
    @Pattern(regexp = "^[가-힣ㄱ-ㅎa-zA-Z]{2,20}$")
    private String nickname;
}
