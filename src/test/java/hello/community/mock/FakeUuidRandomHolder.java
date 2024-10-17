package hello.community.mock;

import hello.community.application.common.service.port.UuidRandomHolder;

public class FakeUuidRandomHolder implements UuidRandomHolder {
    private String random;

    public FakeUuidRandomHolder(String random) {
        this.random = random;
    }

    @Override
    public String getUID() {
        return random;
    }
}
