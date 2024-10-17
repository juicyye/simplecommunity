package hello.community.global.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.community.application.common.controller.resp.ResponseDto;
import hello.community.application.common.service.CustomResponseUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class FormAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        CustomResponseUtil.fail(response,"권한이 없습니다.", HttpStatus.FORBIDDEN);

    }
}
