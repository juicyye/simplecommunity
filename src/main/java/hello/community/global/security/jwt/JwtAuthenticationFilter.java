package hello.community.global.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.community.application.common.service.CustomResponseUtil;
import hello.community.application.user.controller.req.UserLoginReqDto;
import hello.community.global.security.dto.LoginUser;
import hello.community.global.security.dto.PrincipalDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtProcess jwtProcess;
    private final ObjectMapper om = new ObjectMapper();

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtProcess jwtProcess) {
        super(authenticationManager);
        this.authenticationManager = authenticationManager;
        this.jwtProcess = jwtProcess;
        setFilterProcessesUrl("/api/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            UserLoginReqDto userLoginReqDto = om.readValue(request.getInputStream(), UserLoginReqDto.class);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userLoginReqDto.getEmail(), userLoginReqDto.getPassword());

            return authenticationManager.authenticate(authToken);

        } catch (Exception e) {
            throw new InternalAuthenticationServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        PrincipalDetails principal = (PrincipalDetails) authResult.getPrincipal();
        String accessToken = jwtProcess.createAccessToken(principal.getUsername(),
                principal.getLoginUser().getRole().name());
        response.addHeader(JwtVO.HEADER, JwtVO.TOKEN_PREFIX + accessToken);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        CustomResponseUtil.fail(response,"로그인 실패", HttpStatus.UNAUTHORIZED);
    }
}
