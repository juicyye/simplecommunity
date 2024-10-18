package hello.community.application.post.infrastructure;

import hello.community.application.post.domain.Post;
import hello.community.application.post.domain.PostStatus;
import hello.community.application.post.service.port.PostRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {
    private final PostJpaRepository postJpaRepository;
    @Override
    public Post save(Post post) {
        return postJpaRepository.save(PostEntity.fromModel(post)).toModel();
    }

    @Override
    public Optional<Post> findByUIDAndStatus(String uid, PostStatus status) {
        return postJpaRepository.findByUIDAndStatus(uid, status).map(PostEntity::toModel);
    }

    @Override
    public void deleteAll() {
        postJpaRepository.deleteAll();
    }
}
