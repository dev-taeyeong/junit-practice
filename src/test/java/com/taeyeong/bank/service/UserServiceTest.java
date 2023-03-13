package com.taeyeong.bank.service;

import com.taeyeong.bank.config.dummy.DummyObject;
import com.taeyeong.bank.domain.user.User;
import com.taeyeong.bank.domain.user.UserRepository;
import com.taeyeong.bank.dto.user.UserResponseDto;
import com.taeyeong.bank.dto.user.UserResponseDto.JoinResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static com.taeyeong.bank.dto.user.UserRequestDto.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

// Spring 관련 Bean들이 하나도 없는 환경
@ExtendWith(MockitoExtension.class)
public class UserServiceTest extends DummyObject {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Spy
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    void 회원가입_test() {
        // given
        JoinRequestDto joinRequestDto = new JoinRequestDto();
        joinRequestDto.setUsername("taeyeong");
        joinRequestDto.setPassword("1234");
        joinRequestDto.setEmail("taeyeong@gmail.com");
        joinRequestDto.setFullName("정태영");

        // stub 1
        when(userRepository.findByUsername(any())).thenReturn(Optional.empty());

        // stub 2
        User user = newMockUser(1L, "taeyeong", "정태영");
        when(userRepository.save(any())).thenReturn(user);

        // when
        JoinResponseDto joinResponseDto = userService.signUp(joinRequestDto);
        System.out.println("테스트: " + joinResponseDto);

        // then
        assertThat(joinResponseDto.getId()).isEqualTo(1L);
        assertThat(joinResponseDto.getUsername()).isEqualTo("taeyeong");
    }
}
