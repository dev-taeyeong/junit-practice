package com.taeyeong.bank.dto.user;

import com.taeyeong.bank.domain.user.User;
import com.taeyeong.bank.domain.user.UserEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserResponseDto {

    @Getter
    @Setter
    public static class JoinResponseDto {

        private Long id;
        private String username;
        private String fullName;

        public JoinResponseDto(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.fullName = user.getFullName();
        }
    }
}
