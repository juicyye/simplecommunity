package hello.community.mock;

import hello.community.application.common.service.LocalDateTimeHolder;
import java.time.LocalDateTime;
import org.springframework.data.annotation.LastModifiedDate;

public class FakeLocalDateTimeHolder implements LocalDateTimeHolder {
    private LocalDateTime localDateTime;

    public FakeLocalDateTimeHolder(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public LocalDateTime now() {
        return localDateTime;
    }
}
