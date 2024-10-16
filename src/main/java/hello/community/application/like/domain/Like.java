package hello.community.application.like.domain;

import hello.community.application.comment.domain.Comment;
import hello.community.application.post.domain.Post;
import hello.community.application.user.domain.User;
import java.time.LocalDateTime;
import java.util.List;

public class Like {
    private Long id;
    private User user;
    private Post post;
    private Comment comment;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
