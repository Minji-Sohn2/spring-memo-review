package com.example.springmemoreview.memo.dto;

import com.example.springmemoreview.memo.entity.Memo;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SimpleMemoResponseDto {
    private Long id;
    private String title;
    private String nickname;
    private LocalDateTime createdAt;

    public SimpleMemoResponseDto (Memo memo) {
        this.id = memo.getId();
        this.title = memo.getTitle();
        this.nickname = memo.getUser().getNickname();
        this.createdAt = memo.getCreatedAt();
    }
}
