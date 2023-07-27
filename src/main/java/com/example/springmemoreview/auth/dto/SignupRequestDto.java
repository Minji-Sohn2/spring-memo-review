package com.example.springmemoreview.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class SignupRequestDto {

    //@NotBlank(message = "닉네임에는 빈 칸이 들어갈 수 없습니다.")
    //@Pattern(regexp = "^[a-zA-Z0-9]{3,}$", message = "닉네임 형식에 맞게 입력해주세요.")
    private String nickname;

    //@NotBlank(message = "비밀번호에는 빈 칸이 들어갈 수 없습니다.")
    //@Pattern(regexp = "^[a-zA-Z0-9]{4,}$", message = "비밀번호 형식에 맞게 입력해주세요.")
    private String password;

    //@NotBlank(message = "비밀번호에는 빈 칸이 들어갈 수 없습니다.")
    //@Pattern(regexp = "^[a-zA-Z0-9]{4,}$", message = "비밀번호 형식에 맞게 입력해주세요.")
    private String confirmPassword;
}
