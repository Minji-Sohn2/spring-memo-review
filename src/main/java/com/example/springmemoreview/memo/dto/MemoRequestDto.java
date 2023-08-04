package com.example.springmemoreview.memo.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemoRequestDto {
    private String title;
    private String content;
}
