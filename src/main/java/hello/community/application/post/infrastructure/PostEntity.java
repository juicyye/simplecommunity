package hello.community.application.post.infrastructure;

import hello.community.application.post.domain.Post;
import hello.community.application.post.domain.PostStatus;
import hello.community.application.user.infrastructure.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "post_tb")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private Long views;
    private PostStatus status;
    private String UID;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    public static PostEntity fromModel(Post post) {
        return PostEntity.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .views(post.getViews())
                .status(post.getStatus())
                .UID(post.getUID())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .userEntity(UserEntity.fromModel(post.getUser()))
                .build();
    }

    public Post toModel() {
        return Post.builder()
                .id(id)
                .title(title)
                .content(content)
                .views(views)
                .status(status)
                .UID(UID)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .user(userEntity.toModel())
                .build();
    }
}
