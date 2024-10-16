package hello.community.application.comment.domain;

import hello.community.application.post.domain.Post;
import hello.community.application.user.domain.User;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Comment {
    private Long id;
    private String content;
    private CommentStatus status;
    private String UID;
    private Post post;
    private User user;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<Comment> child = new ArrayList<>();

}
