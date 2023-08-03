package com.example.springmemoreview.memo.repository;

import com.example.springmemoreview.memo.dto.MemoSearchCond;
import com.example.springmemoreview.memo.entity.Memo;

import java.util.List;

public interface MemoRepositoryQuery {

    List<Memo> searchByKeyword(MemoSearchCond cond);
}
