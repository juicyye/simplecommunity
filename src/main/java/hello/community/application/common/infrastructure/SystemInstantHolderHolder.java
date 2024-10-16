package hello.community.application.common.infrastructure;

import hello.community.application.common.service.LocalInstantHolder;
import java.time.Instant;
import org.springframework.stereotype.Component;

@Component
public class SystemInstantHolderHolder implements LocalInstantHolder {
    @Override
    public Long getInstant() {
        return Instant.now().toEpochMilli();
    }
}
