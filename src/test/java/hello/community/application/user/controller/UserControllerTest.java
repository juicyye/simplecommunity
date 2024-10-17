package hello.community.application.user.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.community.application.user.controller.req.UserCreateReqDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper om;


    @Test
    @DisplayName("userCreate를 통해 유저를 저장시킬 수 있다")
    void createUser() throws Exception {
        // given
        UserCreateReqDto userCreateReqDto = getUserCreateReqDto("email@naver.com", "password", "nickname");
        String requestBody = om.writeValueAsString(userCreateReqDto);

        // when
        ResultActions resultActions = mockMvc.perform(
                post("/api/signup").contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andDo(print());

        // then
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("responseBody = " + responseBody);
        resultActions.andExpect(status().isCreated()).andExpect(jsonPath("$.data.nickname").value(userCreateReqDto.getNickname()));
    }

    private static UserCreateReqDto getUserCreateReqDto(String email, String password, String nickname) {
        return new UserCreateReqDto(email, password, nickname);
    }

}