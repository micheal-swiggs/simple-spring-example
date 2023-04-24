package com.carasent.library.web.dto;

import com.carasent.library.model.Book;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class PageMapper {

    public static PageResponse<BookResponse> mapToResponse(Page<Book> pageBook){
        List<BookResponse> content = pageBook.getContent().stream()
                .map(BookMapper::mapToResponse)
                .collect(Collectors.toList());
        return new PageResponse<>(pageBook.getNumber(),
                                  pageBook.getTotalElements(),
                                  pageBook.getTotalPages(),
                                  pageBook.getSize(),
                                  content);
    }
}
