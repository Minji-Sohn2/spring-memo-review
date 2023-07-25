package com.example.springmemoreview.auth.controller;

import com.example.springmemoreview.auth.dto.SigninRequestDto;
import com.example.springmemoreview.auth.dto.SignupRequestDto;
import com.example.springmemoreview.auth.service.AuthService;
import com.example.springmemoreview.common.dto.ApiResponseDto;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Slf4j(topic = "AuthController")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponseDto> signup(@Valid @RequestBody SignupRequestDto requestDto) {
        try {
            authService.signup(requestDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }

        return ResponseEntity.status(201).body(new ApiResponseDto("회원가입 성공", HttpStatus.CREATED.value()));
    }

    @PostMapping("/signin")
    public ResponseEntity<ApiResponseDto> signin(@RequestBody SigninRequestDto requestDto, HttpServletResponse response) {
        try {
            authService.signin(requestDto, response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto("아이디 또는 패스워드를 확인해주세요.", HttpStatus.BAD_REQUEST.value()));
        }

        return ResponseEntity.status(200).body(new ApiResponseDto("로그인 성공", HttpStatus.OK.value()));
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ApiResponseDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ApiResponseDto restApiException = new ApiResponseDto(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(
                // HTTP body
                restApiException,
                // HTTP status code
                HttpStatus.BAD_REQUEST
        );
    }
}
