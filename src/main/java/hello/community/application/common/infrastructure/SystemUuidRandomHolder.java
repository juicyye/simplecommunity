package hello.community.application.common.infrastructure;

import hello.community.application.common.service.port.UuidRandomHolder;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class SystemUuidRandomHolder implements UuidRandomHolder {
    @Override
    public String getUID() {
        return UUID.randomUUID().toString();
    }
}
