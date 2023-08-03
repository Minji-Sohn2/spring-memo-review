package com.example.springmemoreview.memo.controller;

import com.example.springmemoreview.common.dto.ApiResponseDto;
import com.example.springmemoreview.common.dto.PageDto;
import com.example.springmemoreview.memo.dto.MemoPageResponseDto;
import com.example.springmemoreview.memo.dto.MemoRequestDto;
import com.example.springmemoreview.memo.dto.MemoResponseDto;
import com.example.springmemoreview.memo.service.MemoService;
import com.example.springmemoreview.security.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

    // 전체 게시글 목록 조회
    @GetMapping("")
    public ResponseEntity<MemoPageResponseDto> getMemoPage(@RequestBody PageDto pageDto) {
        MemoPageResponseDto result = memoService.getMemoPage(pageDto);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{memoId}")
    public ResponseEntity<MemoResponseDto> getOneMemo(@PathVariable Long memoId) {
        MemoResponseDto result = memoService.getOneMemo(memoId);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/{memoId}")
    public ResponseEntity<?> updateMemo(
            @PathVariable Long memoId,
            @RequestBody MemoRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        MemoResponseDto result = memoService.updateMemo(memoId, requestDto, userDetails.getUser());
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{memoId}")
    public ResponseEntity<?> deleteMemo(
            @PathVariable Long memoId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        memoService.deleteMemo(memoId, userDetails.getUser());
        return ResponseEntity.ok().body(new ApiResponseDto("메모 삭제 성공", HttpStatus.OK.value()));
    }

    @GetMapping("/search")
    public MemoPageResponseDto selectMemosContainKeyword(
            @RequestParam("keyword") String keyword,
            @RequestBody PageDto pageDto
    ) {
        return memoService.selectMemosContainKeyword(keyword, pageDto);
    }
}
