package hello.community.application.user.infrastructure;

import hello.community.application.user.domain.Role;
import hello.community.application.user.domain.User;
import hello.community.application.user.domain.UserStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Entity
@NoArgsConstructor
@Table(name = "user_tb")
public class UserEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public static UserEntity fromModel(User user) {
        return UserEntity.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .status(user.getStatus())
                .nickname(user.getNickname())
                .lastLoginAt(user.getLastLoginAt())
                .UID(user.getUID())
                .deletedAt(user.getDeletedAt())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    public User toModel() {
        return User.builder()
                .id(id)
                .email(email)
                .password(password)
                .role(role)
                .status(status)
                .nickname(nickname)
                .lastLoginAt(lastLoginAt)
                .UID(UID)
                .deletedAt(deletedAt)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();

    }
}
