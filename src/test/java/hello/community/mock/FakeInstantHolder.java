package hello.community.mock;

import hello.community.application.common.service.port.LocalInstantHolder;

public class FakeInstantHolder implements LocalInstantHolder {
    private Long instant;

    public FakeInstantHolder(Long instant) {
        this.instant = instant;
    }

    @Override
    public Long getInstant() {
        return instant;
    }
}
