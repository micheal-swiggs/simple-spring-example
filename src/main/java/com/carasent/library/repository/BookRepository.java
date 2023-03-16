package com.carasent.library.repository;

import com.carasent.library.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;

public interface BookRepository extends PagingAndSortingRepository<Book, Integer>, CrudRepository<Book, Integer> {
    Page<Book> findByDueBackByBefore(Date dueBackBy, Pageable pageable);
}