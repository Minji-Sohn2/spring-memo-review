package com.example.springmemoreview.comment.service;

import com.example.springmemoreview.comment.dto.CommentRequestDto;
import com.example.springmemoreview.comment.dto.CommentResponseDto;
import com.example.springmemoreview.comment.entity.Comment;
import com.example.springmemoreview.comment.repository.CommentRepository;
import com.example.springmemoreview.memo.entity.Memo;
import com.example.springmemoreview.memo.repository.MemoRepository;
import com.example.springmemoreview.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemoRepository memoRepository;

    public CommentResponseDto createComment(Long memoId, CommentRequestDto requestDto, User user) {
        Memo memo = findMemo(memoId);

        Comment comment = new Comment(requestDto, memo, user);
        commentRepository.save(comment);

        return new CommentResponseDto(comment);
    }

    public Memo findMemo(Long memoId) {
        return memoRepository.findById(memoId).orElseThrow(
                () -> new NullPointerException("존재하지 않는 게시물입니다.")
        );
    }
}
