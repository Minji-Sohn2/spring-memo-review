package com.example.springmemoreview.memo.service;

import com.example.springmemoreview.memo.dto.MemoRequestDto;
import com.example.springmemoreview.memo.dto.MemoResponseDto;
import com.example.springmemoreview.memo.entity.Memo;
import com.example.springmemoreview.memo.repository.MemoRepository;
import com.example.springmemoreview.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    public MemoResponseDto createMemo(MemoRequestDto requestDto, User user) {

        Memo memo = new Memo(requestDto, user);
        memoRepository.save(memo);

        return new MemoResponseDto(memo);
    }
}
