package com.example.springmemoreview.memo.repository;

import com.example.springmemoreview.memo.entity.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = Memo.class, idClass = Long.class)
public interface MemoRepository extends JpaRepository<Memo, Long>
        , MemoRepositoryQuery {
    Page<Memo> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
