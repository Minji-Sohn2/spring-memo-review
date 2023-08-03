package com.example.springmemoreview.memo.service;

import com.example.springmemoreview.common.dto.PageDto;
import com.example.springmemoreview.memo.dto.*;
import com.example.springmemoreview.memo.entity.Memo;
import com.example.springmemoreview.memo.repository.MemoRepository;
import com.example.springmemoreview.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.RejectedExecutionException;

@Service
@RequiredArgsConstructor
public class MemoServiceImpl implements MemoService {

    private final MemoRepository memoRepository;

    @Override
    public MemoResponseDto createMemo(MemoRequestDto requestDto, User user) {

        Memo memo = new Memo(requestDto, user);
        memoRepository.save(memo);

        return new MemoResponseDto(memo);
    }

    @Override
    public MemoPageResponseDto getMemoPage(PageDto pageDto) {
        Page<Memo> memoPage = memoRepository.findAllByOrderByCreatedAtDesc(pageDto.toPageable());

        return new MemoPageResponseDto(memoPage.map(SimpleMemoResponseDto::new));
    }

    @Override
    public MemoResponseDto getOneMemo(Long memoId) {
        Memo memo = findMemo(memoId);

        return new MemoResponseDto(memo);
    }

    @Override
    @Transactional
    public MemoResponseDto updateMemo(Long memoId, MemoRequestDto requestDto, User user) {
        Memo memo = findMemo(memoId);

        if (checkMemoUser(memo, user)) {
            if (requestDto.getTitle() != null) {
                memo.changeTitle(requestDto.getTitle());
            }
            if (requestDto.getContent() != null) {
                memo.changeContent(requestDto.getContent());
            }
        } else {
            throw new RejectedExecutionException("작성자만 수정할 수 있습니다.");
        }

        return new MemoResponseDto(memo);
    }

    @Override
    public void deleteMemo(Long memoId, User user) {
        Memo memo = findMemo(memoId);

        if (checkMemoUser(memo, user)) {
            memoRepository.delete(memo);
        } else {
            throw new RejectedExecutionException("작성자만 삭제할 수 있습니다.");
        }
    }

    @Override
    public Memo findMemo(Long memoId) {
        return memoRepository.findById(memoId).orElseThrow(
                () -> new NullPointerException("존재하지 않는 게시물입니다.")
        );
    }

    @Override
    public boolean checkMemoUser(Memo memo, User user) {
        return memo.getUser().getId() == user.getId();
    }

    @Transactional(readOnly = true)
    @Override
    public MemoPageResponseDto selectMemosContainKeyword(String keyword, PageDto pageDto) {
        MemoSearchCond cond = MemoSearchCond.builder().keyword(keyword).build();
        Page<SimpleMemoResponseDto> memoPage = memoRepository.searchByKeyword(cond, pageDto.toPageable()).map(SimpleMemoResponseDto::new);
        return new MemoPageResponseDto(memoPage);
    }
}
