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
import org.springframework.transaction.annotation.Transactional;

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

    public MemoResponseDto getOneMemo(Long memoId) {
        Memo memo = findMemo(memoId);
        return new MemoResponseDto(memo);
    }

    @Transactional
    public MemoResponseDto updateMemo(Long memoId, MemoRequestDto requestDto, User user) throws ReflectiveOperationException {
        Memo memo = findMemo(memoId);

        if(checkMemoUser(memo, user)) {
            if(requestDto.getTitle() != null) {
                memo.changeTitle(requestDto.getTitle());
            }
            if(requestDto.getContent() != null) {
                memo.changeContent(requestDto.getContent());
            }
        } else {
            throw new ReflectiveOperationException("작성자만 수정할 수 있습니다.");
        }
        return new MemoResponseDto(memo);
    }

    public Memo findMemo(Long memoId) {
        return memoRepository.findById(memoId).orElseThrow(
                () -> new NullPointerException("존재하지 않는 게시물입니다.")
        );
    }

    public boolean checkMemoUser(Memo memo, User user) {
        if(memo.getUser().getId() == user.getId()){
            return true;
        } else {
            return false;
        }
    }
}
