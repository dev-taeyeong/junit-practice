package com.taeyeong.bank.web;

import com.taeyeong.bank.dto.ResponseDto;
import com.taeyeong.bank.dto.user.UserResponseDto.JoinResponseDto;
import com.taeyeong.bank.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.HashMap;
import java.util.Map;

import static com.taeyeong.bank.dto.user.UserRequestDto.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<?> join(
        @RequestBody @Valid JoinRequestDto joinRequestDto,
        BindingResult bindingResult
    ) {

        JoinResponseDto joinResponseDto = userService.signUp(joinRequestDto);
        return new ResponseEntity<>(new ResponseDto<>(1, "회원가입 성공", joinResponseDto), HttpStatus.CREATED);
    }
}
