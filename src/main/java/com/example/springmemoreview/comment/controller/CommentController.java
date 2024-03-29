package com.example.springmemoreview.comment.controller;

import com.example.springmemoreview.comment.dto.CommentRequestDto;
import com.example.springmemoreview.comment.dto.CommentResponseDto;
import com.example.springmemoreview.comment.service.CommentService;
import com.example.springmemoreview.common.dto.ApiResponseDto;
import com.example.springmemoreview.security.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("")
    public ResponseEntity<?> createComment(
            @RequestParam("memo") Long memoId,
            @RequestBody CommentRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        CommentResponseDto result = commentService.createComment(memoId, requestDto, userDetails.getUser());
        return ResponseEntity.status(201).body(result);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<?> updateComment(
            @PathVariable Long commentId,
            @RequestBody CommentRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        CommentResponseDto result = commentService.updateComment(commentId, requestDto, userDetails.getUser());
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<ApiResponseDto> deleteComment(
            @PathVariable Long commentId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        commentService.deleteComment(commentId,userDetails.getUser());
        return ResponseEntity.ok().body(new ApiResponseDto("댓글 삭제 성공", HttpStatus.OK.value()));
    }
}
