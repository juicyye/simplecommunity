package hello.community.global.security.dto;

import hello.community.application.user.domain.Role;
import hello.community.application.user.domain.User;
import lombok.Data;

@Data
public class LoginUser {
    private Long id;
    private String UID;
    private String password;
    private String nickname;
    private String email;
    private Role role;

    public LoginUser(User user) {
        this.id = user.getId();
        this.UID = user.getUID();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.nickname = user.getNickname();
    }
}
