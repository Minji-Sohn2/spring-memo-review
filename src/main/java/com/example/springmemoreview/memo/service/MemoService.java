package com.example.springmemoreview.memo.service;

import com.example.springmemoreview.memo.dto.MemoListResponseDto;
import com.example.springmemoreview.memo.dto.MemoRequestDto;
import com.example.springmemoreview.memo.dto.MemoResponseDto;
import com.example.springmemoreview.memo.entity.Memo;
import com.example.springmemoreview.user.entity.User;

public interface MemoService {
    /**
     * 메모 작성
     *
     * @param requestDto 작성할 메모 내용
     * @param user       요청자
     * @return 작성된 메모
     */
    MemoResponseDto createMemo(MemoRequestDto requestDto, User user);

    /**
     * 메모 목록 조회(간단한 정보)
     *
     * @return 메모 목록
     */
    MemoListResponseDto getMemoList();

    /**
     * 메모 1개 조회
     *
     * @param memoId 조회할 메모 id
     * @return 조회된 메모
     */
    MemoResponseDto getOneMemo(Long memoId);

    /**
     * 메모 수정
     *
     * @param memoId     수정할 메모 id
     * @param requestDto 수정할 메모 내용
     * @param user       요청자
     * @return 수정된 메모
     */
    MemoResponseDto updateMemo(Long memoId, MemoRequestDto requestDto, User user);

    /**
     * 메모 삭제
     *
     * @param memoId 삭제할 메모 id
     * @param user   요청자
     */
    void deleteMemo(Long memoId, User user);

    /**
     * repository에서 메모 조회
     *
     * @param memoId 조회할 메모 id
     * @return 조회된 메모
     */
    Memo findMemo(Long memoId);

    /**
     * 메모의 작성자와 요청자 일치 여부 확인
     *
     * @param memo 확인할 메모
     * @param user 요청자
     * @return 일치 여부
     */
    boolean checkMemoUser(Memo memo, User user);
}
