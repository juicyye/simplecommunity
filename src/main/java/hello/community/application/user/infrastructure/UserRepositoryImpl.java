package hello.community.application.user.infrastructure;

import hello.community.application.user.domain.User;
import hello.community.application.user.service.port.UserRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository userJpaRepository;

    @Override
    public User save(User user) {
        UserEntity userEntity = userJpaRepository.save(UserEntity.fromModel(user));
        return userEntity.toModel();
    }

    @Override
    public Optional<User> findByUID(String UID) {
        return userJpaRepository.findByUID(UID).stream().map(UserEntity::toModel).findFirst();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userJpaRepository.findByEmail(email).stream().map(UserEntity::toModel).findFirst();
    }

    @Override
    public List<User> findAll() {
        return userJpaRepository.findAll().stream().map(UserEntity::toModel).toList();
    }
}
