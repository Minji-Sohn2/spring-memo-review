package com.example.springmemoreview.memo.service;

import com.example.springmemoreview.memo.dto.MemoListResponseDto;
import com.example.springmemoreview.memo.dto.MemoRequestDto;
import com.example.springmemoreview.memo.dto.MemoResponseDto;
import com.example.springmemoreview.memo.dto.SimpleMemoResponseDto;
import com.example.springmemoreview.memo.entity.Memo;
import com.example.springmemoreview.memo.repository.MemoRepository;
import com.example.springmemoreview.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    public MemoResponseDto createMemo(MemoRequestDto requestDto, User user) {

        Memo memo = new Memo(requestDto, user);
        memoRepository.save(memo);

        return new MemoResponseDto(memo);
    }

    public MemoListResponseDto getMemoList() {
        List<SimpleMemoResponseDto> simpleMemoResponseDtoList
                = memoRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(SimpleMemoResponseDto::new).toList();
        return new MemoListResponseDto(simpleMemoResponseDtoList);
    }
}
