package com.taeyeong.bank.temp;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

public class RegexTest {

    @Test
    public void 한글만된다_test() {
        String value = "가나";
        boolean result = Pattern.matches("^[가-힣]+$", value);
        System.out.println("테스트: " + result);
    }

    @Test
    public void 한글은안된다_test() {
        String value = "asdf# 1";
        boolean result = Pattern.matches("^[^ㄱ-ㅎ가-힣]*$", value);
        System.out.println("테스트: " + result);
    }

    @Test
    public void 영어만된다_test() {
        String value = "absadf";
        boolean result = Pattern.matches("^[a-zA-Z]+$", value);
        System.out.println("테스트: " + result);
    }

    @Test
    public void 영어는안된다_test() {
        String value = "ㅁㄴ어라ㅣ 12 ,3";
        boolean result = Pattern.matches("^[^a-zA-Z]*$", value);
        System.out.println("테스트: " + result);
    }

    @Test
    public void 영어와숫자만된다_test() {
        String value = "absadf123";
        boolean result = Pattern.matches("^[a-zA-Z0-9]+$", value);
        System.out.println("테스트: " + result);
    }

    @Test
    public void 영어만되고_길이는최소2최대4이다_test() {
        String value = "aasa";
        boolean result = Pattern.matches("^[a-zA-Z]{2,4}$", value);
        System.out.println("테스트: " + result);
    }

    @Test
    public void user_username_test() {
        String username = "taeyeong123";
        boolean result = Pattern.matches("^[a-zA-z0-9]{2,20}$", username);
        System.out.println("테스트: " + result);
    }

    @Test
    public void user_fullName_test() {
        String fullName = "정태영";
        boolean result = Pattern.matches("^[a-zA-Z가-힣]{1,20}", fullName);
        System.out.println("테스트: " + result);
    }

    @Test
    public void user_email_test() {
        String email = "taeyeongaa@naver.com";
        boolean result = Pattern.matches("^[a-zA-Z0-9]{2,10}@[a-zA-Z0-9]{2,6}\\.[a-zA-Z]{2,3}$", email);
        System.out.println("테스트: " + result);
    }
}
