package hello.community.application.notification.infrastructure;

import hello.community.application.notification.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationJpaRepository extends JpaRepository<Notification, Long> {
}
