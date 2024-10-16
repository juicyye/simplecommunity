package hello.community.application.notification.domain;

import hello.community.application.user.domain.User;
import java.time.LocalDateTime;

public class Notification {
    private Long id;
    private String URL;
    private String type;
    private String content;
    private String UID;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private User user;
}
