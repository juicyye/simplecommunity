package hello.community.mock;

import hello.community.application.user.service.port.LocalPasswordEncoder;

public class FakePasswordEncoder implements LocalPasswordEncoder {
    private String fixedEncodedPassword;

    // 고정된 인코딩된 비밀번호를 설정할 수 있는 생성자
    public FakePasswordEncoder(String fixedEncodedPassword) {
        this.fixedEncodedPassword = fixedEncodedPassword;
    }

    @Override
    public String encode(String password) {
        // 비밀번호를 인코딩할 필요 없이 고정된 값을 반환
        return fixedEncodedPassword;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        // 원시 비밀번호가 고정된 인코딩된 비밀번호와 일치하는지 확인
        return fixedEncodedPassword.equals(encodedPassword);
    }
}
