package com.dormify.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PaginationUtils {

    public static Pageable getPageable(PaginationRequest request) {
        return PageRequest.of(request.getPage(), request.getSize(), request.getDirection(), request.getSortField());
    }

    public static <E, D> PagingResult<D> createPagingResult(Page<E> page, Function<E, D> mapper) {
        var list = page.stream().map(mapper).toList();

        return new PagingResult(
                list,
                page.getTotalPages(),
                page.getTotalElements(),
                page.getSize(),
                page.getNumber(),
                page.isEmpty()
        );
    }

    public static <E, D> PagingResult<D> createPagingResult(
            Page<E> page,
            List<D> dtoList
    ) {
        return new PagingResult<>(
                dtoList,
                page.getTotalPages(),
                page.getTotalElements(),
                page.getSize(),
                page.getNumber(),
                page.isEmpty()
        );
    }
}
