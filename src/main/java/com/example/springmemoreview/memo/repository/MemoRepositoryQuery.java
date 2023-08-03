package com.example.springmemoreview.memo.repository;

import com.example.springmemoreview.memo.dto.MemoSearchCond;
import com.example.springmemoreview.memo.entity.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemoRepositoryQuery {

    Page<Memo> searchByKeyword(MemoSearchCond cond, Pageable pageable);
}
