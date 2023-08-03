package com.example.springmemoreview.memo.repository;

import com.example.springmemoreview.memo.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

@RepositoryDefinition(domainClass = Memo.class, idClass = Long.class)
public interface MemoRepository extends JpaRepository<Memo, Long>
        , MemoRepositoryQuery {
    List<Memo> findAllByOrderByCreatedAtDesc();
}
