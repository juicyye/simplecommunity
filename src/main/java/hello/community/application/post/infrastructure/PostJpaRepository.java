package hello.community.application.post.infrastructure;

import hello.community.application.post.domain.PostStatus;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostJpaRepository extends JpaRepository<PostEntity, Long> {
    @Query("select p from PostEntity p where p.UID = :uid and p.status = :staus")
    Optional<PostEntity> findByUIDAndStatus(@Param("uid") String uid, @Param("status")PostStatus status);
}
