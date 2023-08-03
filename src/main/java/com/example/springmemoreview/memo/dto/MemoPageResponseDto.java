package com.example.springmemoreview.memo.dto;

import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class MemoPageResponseDto {
    private Page<SimpleMemoResponseDto> memoPage;

    public MemoPageResponseDto(Page<SimpleMemoResponseDto> simpleMemoResponseDtoPage) {
        this.memoPage = simpleMemoResponseDtoPage;
    }
}
