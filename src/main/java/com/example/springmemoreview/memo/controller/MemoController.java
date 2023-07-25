package com.example.springmemoreview.memo.controller;

import com.example.springmemoreview.memo.dto.MemoRequestDto;
import com.example.springmemoreview.memo.dto.MemoResponseDto;
import com.example.springmemoreview.memo.service.MemoService;
import com.example.springmemoreview.security.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/memos")
public class MemoController {

    private final MemoService memoService;

    @PostMapping("")
    public ResponseEntity<MemoResponseDto> createMemo(@RequestBody MemoRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        MemoResponseDto result = memoService.createMemo(requestDto, userDetails.getUser());
        return ResponseEntity.status(201).body(result);
    }
}
