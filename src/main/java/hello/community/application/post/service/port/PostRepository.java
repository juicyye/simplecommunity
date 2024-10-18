package hello.community.application.post.service.port;

import hello.community.application.post.domain.Post;
import hello.community.application.post.domain.PostStatus;
import java.util.Optional;

public interface PostRepository {

    Post save(Post post);
    Optional<Post> findByUIDAndStatus(String uid, PostStatus status);

    void deleteAll();

}
