package com.taeyeong.bank.dto.user;

import com.taeyeong.bank.domain.user.User;
import com.taeyeong.bank.domain.user.UserEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRequestDto {

    @Getter
    @Setter
    public static class JoinRequestDto {
        // 유효성 검사

        // 영문, 숫자 가능, 길이 2~20자
        @Pattern(regexp = "^[a-zA-Z0-9]{2,20}$", message = "영문/숫자 2~20자 이내로 작성해주세요.")
        @NotEmpty
        private String username; // null이거나 공백일 수 없음

        // 4~10
        @NotEmpty
        @Size(min = 4, max = 20)
        private String password;

        // 이메일 형식
        @NotEmpty
        @Pattern(regexp = "^[a-zA-Z0-9]{2,10}@[a-zA-Z0-9]{2,6}\\.[a-zA-Z]{2,3}", message = "이메일 형식으로 작성해주세요.")
        private String email;

        // 영어, 한글, 1~20자
        @NotEmpty
        @Pattern(regexp = "^[a-zA-Z가-힣]{1,20}$", message = "한글/영문 1~20자 이내로 작성해주세요.")
        private String fullName;

        public User toEntity(BCryptPasswordEncoder passwordEncoder) {
            return User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .fullName(fullName)
                .role(UserEnum.CUSTOMER)
                .build();
        }
    }
}
