package com.example.springmemoreview.memo.repository;

import com.example.springmemoreview.memo.dto.MemoSearchCond;
import com.example.springmemoreview.memo.entity.Memo;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.example.springmemoreview.memo.entity.QMemo.memo;

@Component
@RequiredArgsConstructor
public class MemoRepositoryQueryImpl implements MemoRepositoryQuery {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Memo> searchByKeyword(MemoSearchCond cond) {
        return jpaQueryFactory.selectFrom(memo)
                .where(containsKeyword(cond.getKeyword()))
                .fetch();
    }

    // QueryDsl의 where 절은 null값을 무시 -> 검색값이 없을 경우에만 null
    private BooleanExpression containsKeyword(String keyword) {
        // memo의 content에 keyword가 존재한다면
        return StringUtils.hasText(keyword) ? memo.content.contains(keyword) : null;
    }
}
