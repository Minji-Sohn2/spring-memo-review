package com.example.springmemoreview.memo.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class MemoListResponseDto {
    private List<SimpleMemoResponseDto> memoList;
    public MemoListResponseDto(List<SimpleMemoResponseDto> simpleMemoResponseDtoList) {
        this.memoList = simpleMemoResponseDtoList;
    }
}
