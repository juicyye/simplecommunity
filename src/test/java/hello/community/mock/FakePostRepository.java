package hello.community.mock;

import hello.community.application.post.domain.Post;
import hello.community.application.post.domain.PostStatus;
import hello.community.application.post.service.port.PostRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class FakePostRepository implements PostRepository {
    private List<Post> data = new ArrayList<>();
    private AtomicLong autoGeneratedId = new AtomicLong(0);

    @Override
    public Post save(Post post) {
        if (post.getId() == null || post.getId() == 0) {
            Post newPost = Post.builder()
                    .id(autoGeneratedId.getAndIncrement())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .views(post.getViews())
                    .status(post.getStatus())
                    .UID(post.getUID())
                    .createdAt(post.getCreatedAt())
                    .updatedAt(post.getUpdatedAt())
                    .build();
            data.add(newPost);
            return newPost;
        } else{
            data.removeIf(item -> Objects.equals(item.getId(), post.getId()));
            data.add(post);
            return post;
        }
    }

    @Override
    public Optional<Post> findByUIDAndStatus(String uid, PostStatus status) {
        return data.stream().filter(item -> Objects.equals(item.getId(), uid) && Objects.equals(item.getStatus(), status)).findFirst();
    }

    @Override
    public void deleteAll() {
        data.clear();
    }
}
