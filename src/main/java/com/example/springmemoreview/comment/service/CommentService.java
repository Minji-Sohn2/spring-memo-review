package com.example.springmemoreview.comment.service;

import com.example.springmemoreview.comment.dto.CommentRequestDto;
import com.example.springmemoreview.comment.dto.CommentResponseDto;
import com.example.springmemoreview.comment.entity.Comment;
import com.example.springmemoreview.memo.entity.Memo;
import com.example.springmemoreview.user.entity.User;

public interface CommentService {
    /**
     * 댓글 작성
     *
     * @param memoId     댓글이 달릴 메모 id
     * @param requestDto 댓글 내용
     * @param user       요청자
     * @return 작성된 댓글
     */
    CommentResponseDto createComment(Long memoId, CommentRequestDto requestDto, User user);

    /**
     * 댓글 수정
     *
     * @param commentId  수정할 댓글 id
     * @param requestDto 수정할 댓글 내용
     * @param user       요청자
     * @return 수정된 댓글
     */
    CommentResponseDto updateComment(Long commentId, CommentRequestDto requestDto, User user);

    /**
     * 댓글 삭제
     *
     * @param commentId 삭제할 댓글 id
     * @param user      요청자
     */
    void deleteComment(Long commentId, User user);

    /**
     * repository에서 메모 조회
     *
     * @param memoId 조회할 메모 id
     * @return 조회된 메모
     */
    Memo findMemo(Long memoId);

    /**
     * repository에서 댓글 조회
     *
     * @param commentId 조회할 댓글 id
     * @return 조회된 댓글
     */
    Comment findComment(Long commentId);
}
