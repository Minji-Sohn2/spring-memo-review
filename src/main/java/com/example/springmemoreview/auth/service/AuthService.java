package com.example.springmemoreview.auth.service;

import com.example.springmemoreview.auth.dto.SignupRequestDto;
import com.example.springmemoreview.user.entity.User;
import com.example.springmemoreview.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String nickname = requestDto.getNickname();
        String password = passwordEncoder.encode(requestDto.getPassword());

        if(userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }

        if(userRepository.findByNickname(nickname).isPresent()) {
            throw new IllegalArgumentException("이미 사용중인 닉네임입니다.");
        }

        User user = new User(username, nickname, password);
        userRepository.save(user);
    }


}
