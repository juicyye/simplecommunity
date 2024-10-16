package hello.community.application.post.domain;

import hello.community.application.user.domain.User;
import java.time.LocalDateTime;

public class Post {
    private Long id;
    private String title;
    private String content;
    private Long views;
    private PostStatus status;
    private String UID;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private User user;
}
