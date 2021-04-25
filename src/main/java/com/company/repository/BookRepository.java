
package com.company.repository;

import com.company.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByYearAndStatus(String status,String year);
    List<Book> findByAnnotationLikeAndStatus(String status, String keyword);

    List<Book> findByStatus(String status);

    Book findByStatusAndId(String status, Long id);


}