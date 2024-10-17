package hello.community.application.common.infrastructure;

import hello.community.application.common.service.port.LocalDateTimeHolder;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class SystemLocalDateTimeHolder implements LocalDateTimeHolder {
    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}
