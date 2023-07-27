package com.example.springmemoreview.auth.service;

import com.example.springmemoreview.auth.dto.SigninRequestDto;
import com.example.springmemoreview.auth.dto.SignupRequestDto;
import com.example.springmemoreview.security.jwt.JwtUtil;
import com.example.springmemoreview.user.entity.User;
import com.example.springmemoreview.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public void signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String nickname = requestDto.getNickname();
        String password = passwordEncoder.encode(requestDto.getPassword());
        String confirmPassword = requestDto.getConfirmPassword();

        if(!passwordEncoder.matches(confirmPassword, password)) {
            throw new IllegalArgumentException("비밀번호를 다시 확인해주세요.");
        }

        if(userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }

        if(userRepository.findByNickname(nickname).isPresent()) {
            throw new IllegalArgumentException("이미 사용중인 닉네임입니다.");
        }

        User user = new User(username, nickname, password);
        userRepository.save(user);
    }

    @Override
    public void signin(SigninRequestDto requestDto, HttpServletResponse response) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 사용자입니다.")
        );

        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }

        String token = jwtUtil.createToken(user.getUsername());
        jwtUtil.addJwtToCookie(token, response);
    }
}