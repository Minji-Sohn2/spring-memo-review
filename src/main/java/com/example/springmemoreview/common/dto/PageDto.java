package com.example.springmemoreview.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageDto {
    private Integer currentPage;   // 현재 페이지
    private Integer size;     // 페이지 크기

    public Pageable toPageable() {
        return PageRequest.of(currentPage - 1, size, Sort.by(Sort.Direction.DESC, "modifiedAt"));
    }
}
