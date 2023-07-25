package com.example.springmemoreview.memo.repository;

import com.example.springmemoreview.memo.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoRepository extends JpaRepository<Memo, Long> {
}
