package com.example.springmemoreview.memo.service;

import com.example.springmemoreview.common.dto.PageDto;
import com.example.springmemoreview.memo.dto.MemoPageResponseDto;
import com.example.springmemoreview.memo.dto.MemoRequestDto;
import com.example.springmemoreview.memo.dto.MemoResponseDto;
import com.example.springmemoreview.memo.entity.Memo;
import com.example.springmemoreview.memo.repository.MemoRepository;
import com.example.springmemoreview.user.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemoServiceTest {

    @Autowired
    private MemoRepository memoRepository;

    @Autowired
    private MemoService memoService;

    @Test
    @DisplayName("서비스(성공) - 메모 작성하기")
    void memoServiceCreateMemoTest() {
        // given
        MemoRequestDto requestDto = MemoRequestDto.builder()
                .title("new title")
                .content("new content")
                .build();
        User user = new User("nickname", "password");

        MemoService memoService = new MemoServiceImpl(memoRepository);

        // when
        MemoResponseDto savedMemo = memoService.createMemo(requestDto, user);

        // then
        assert savedMemo.getTitle().equals(requestDto.getTitle());
    }

    @Test
    @DisplayName("서비스 - 메모 리스트 조회하기")
    void memoServiceGetListTest() {
        // given
        User user = new User("nickname", "password");
        Memo memo1 = Memo.builder().title("title1").content("content1").user(user).build();
        Memo memo2 = Memo.builder().title("title2").content("content2").user(user).build();
        Memo memo3 = Memo.builder().title("title3").content("content3").user(user).build();
        memoRepository.save(memo1);
        memoRepository.save(memo2);
        memoRepository.save(memo3);

        // when
        PageDto pageDto = PageDto.builder().currentPage(1).size(10).build();
        MemoPageResponseDto memoList = memoService.getMemoPage(pageDto);

        // then
        assert memoList.getMemoPage().getSize() == 10;
    }

    @Test
    @DisplayName("서비스 - 메모 수정하기")
    void memoServiceUpdateMemoTest() {
        // given
        MemoRequestDto requestDto = MemoRequestDto.builder()
                .title("new title")
                .content("new content")
                .build();
        User user = new User("nickname", "password");
        Memo savedMemo = memoRepository.save(new Memo(requestDto, user));

        MemoService memoService = new MemoServiceImpl(memoRepository);
        // when


        // then


    }

    @Test
    @DisplayName("서비스 (성공) - 키워드로 검색하기")
    void selectMemosContainKeywordTest() {
        // given - 기존 db에 있는 메모
/*        var memo1 = Memo.builder().title("title1").content("green").build();
        var memo2 = Memo.builder().title("title2").content("green").build();
        var memo3 = Memo.builder().title("title3").content("banana2").build();
        memoRepository.save(memo1);
        memoRepository.save(memo2);
        memoRepository.save(memo3);*/

        // when
        var pageDto = PageDto.builder().currentPage(1).size(5).build();
        var results = memoService.selectMemosContainKeyword("green", pageDto);

        // then
        assert results.getMemoPage().getContent().size() == 2;
    }

}