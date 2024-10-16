package hello.community.application.user.service.port;

import hello.community.application.user.domain.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findByEmail(String email);

    Optional<User> findByUID(String UID);
    List<User> findAll();
}
