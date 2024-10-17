package hello.community.global.security.jwt;

public interface JwtVO {
    String HEADER = "Authorization";
    String TOKEN_PREFIX = "Bearer ";
    Long ACCESS_EXPIRATION_TIME = 1000 * 60 * 60L;
    Long REFRESH_EXPIRATION_TIME = 1000 * 60 * 60* 24 * 7L;
    String ACCESS_TOKEN = "access";
    String REFRESH_TOKEN = "refresh";
}
