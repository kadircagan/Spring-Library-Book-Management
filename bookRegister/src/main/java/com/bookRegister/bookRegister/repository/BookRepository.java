package com.bookRegister.bookRegister.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookRegister.bookRegister.entity.Book;


@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
