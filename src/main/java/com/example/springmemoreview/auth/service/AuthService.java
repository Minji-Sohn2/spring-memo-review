package com.example.springmemoreview.auth.service;

import com.example.springmemoreview.auth.dto.SigninRequestDto;
import com.example.springmemoreview.auth.dto.SignupRequestDto;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {
    /**
     * 회원가입
     *
     * @param requestDto 회원가입할 user 정보
     */
    void signup(SignupRequestDto requestDto);

    /**
     * 로그인
     *
     * @param requestDto user의 로그인 정보
     * @param response   token 담을 응답
     */
    void signin(SigninRequestDto requestDto, HttpServletResponse response);
}
