package hello.community.application.common.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.community.application.common.controller.resp.ResponseDto;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@Slf4j
public class CustomResponseUtil {
    public static void fail(HttpServletResponse response, String message, HttpStatus status) {
        try {
            ObjectMapper om = new ObjectMapper();
            ResponseDto<Object> responseDto = new ResponseDto<>(-1, message, null);
            String responseBody = om.writeValueAsString(responseDto);
            response.setStatus(status.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(responseBody);
        } catch (Exception e) {
            log.error("서버 파싱 에러",e);
        }


    }
}
