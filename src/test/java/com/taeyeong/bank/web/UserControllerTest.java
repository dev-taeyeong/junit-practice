package com.taeyeong.bank.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taeyeong.bank.config.dummy.DummyObject;
import com.taeyeong.bank.domain.user.UserRepository;
import com.taeyeong.bank.dto.user.UserRequestDto.JoinRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class UserControllerTest extends DummyObject {

    @Autowired private MockMvc mvc;
    @Autowired private ObjectMapper om;
    @Autowired private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        dataSetting();
    }

    @Test
    void join_test() throws Exception {
        // given
        JoinRequestDto joinRequestDto = new JoinRequestDto();
        joinRequestDto.setUsername("taeyeong");
        joinRequestDto.setPassword("1234");
        joinRequestDto.setEmail("taeyeong@gmail.com");
        joinRequestDto.setFullName("정태영");

        String requestBody = om.writeValueAsString(joinRequestDto);

        // when
        ResultActions resultActions = mvc.perform(
            post("/api/join")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
        );
        String responseBody = resultActions.andReturn()
            .getResponse()
            .getContentAsString();

        // then
        resultActions.andExpect(status().isCreated());
    }

    @Test
    void join_fail_test() throws Exception {
        // given
        JoinRequestDto joinRequestDto = new JoinRequestDto();
        joinRequestDto.setUsername("test1");
        joinRequestDto.setPassword("1234");
        joinRequestDto.setEmail("test1@gmail.com");
        joinRequestDto.setFullName("테스트");

        String requestBody = om.writeValueAsString(joinRequestDto);

        // when
        ResultActions resultActions = mvc.perform(
            post("/api/join")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
        );
        String responseBody = resultActions.andReturn()
            .getResponse()
            .getContentAsString();

        // then
        resultActions.andExpect(status().isBadRequest());
    }

    private void dataSetting() {
        userRepository.save(newUser("test1", "테스트"));
    }
}
