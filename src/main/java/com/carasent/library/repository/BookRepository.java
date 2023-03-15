package com.carasent.library.repository;

import com.carasent.library.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookRepository extends PagingAndSortingRepository<Book, Integer>, CrudRepository<Book, Integer> {
}