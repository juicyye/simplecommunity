package hello.community.application.user.service.port;

public interface LocalPasswordEncoder {
    String encode(String password);
    boolean matches(CharSequence rawPassword, String encodedPassword);
}
