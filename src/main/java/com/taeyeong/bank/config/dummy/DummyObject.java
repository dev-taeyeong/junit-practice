package com.taeyeong.bank.config.dummy;

import com.taeyeong.bank.domain.user.User;
import com.taeyeong.bank.domain.user.UserEnum;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

public class DummyObject {
    protected User newUser(String username, String fullName) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode("1234");
        return User.builder()
            .username(username)
            .password(encodedPassword)
            .email("taeyeong@gmail.com")
            .fullName(fullName)
            .role(UserEnum.CUSTOMER)
            .build();
    }

    protected User newMockUser(Long id, String username, String fullName) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode("1234");
        return User.builder()
            .id(id)
            .username(username)
            .password(encodedPassword)
            .email("taeyeong@gmail.com")
            .fullName(fullName)
            .role(UserEnum.CUSTOMER)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();
    }
}
