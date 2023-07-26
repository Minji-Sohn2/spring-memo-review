package com.example.springmemoreview.memo.dto;

import com.example.springmemoreview.comment.dto.CommentResponseDto;
import com.example.springmemoreview.memo.entity.Memo;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class MemoResponseDto {

    private Long id;
    private String title;
    private String content;
    private String nickname;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<CommentResponseDto> commentList;

    public MemoResponseDto(Memo memo) {
        this.id = memo.getId();
        this.title = memo.getTitle();
        this.content = memo.getContent();
        this.nickname = memo.getUser().getNickname();
        this.createdAt = memo.getCreatedAt();
        this.modifiedAt = memo.getModifiedAt();
        this.commentList = memo.getCommentList()
                .stream()
                .map(CommentResponseDto::new)
                .sorted(Comparator.comparing(CommentResponseDto::getModifiedAt).reversed())
                .collect(Collectors.toList());
    }
}
