package hello.community.application.post.domain;

import hello.community.application.common.service.port.LocalDateTimeHolder;
import hello.community.application.common.service.port.UuidRandomHolder;
import hello.community.application.post.controller.req.PostCreateReqDto;
import hello.community.application.user.domain.User;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
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

    public static Post create(PostCreateReqDto createDto, UuidRandomHolder uuidRandomHolder,
                              LocalDateTimeHolder localDateTimeHolder, User user) {
        return Post.builder()
                .title(createDto.getTitle())
                .content(createDto.getContent())
                .views(0L)
                .status(PostStatus.ACTIVE)
                .UID(uuidRandomHolder.getUID())
                .createdAt(localDateTimeHolder.now())
                .updatedAt(localDateTimeHolder.now())
                .user(user)
                .build();
    }
}
