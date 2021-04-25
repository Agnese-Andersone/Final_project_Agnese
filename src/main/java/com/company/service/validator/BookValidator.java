package com.company.service.validator;

import com.company.exception.EntityDoesNotExistException;
import com.company.model.Book;
import com.company.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookValidator {

    private final BookRepository bookRepository;

    @Autowired
    public BookValidator(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book checkIfBookExists(Long bookId) {
        Optional<Book> bookFromDBOpt =  Optional.ofNullable(bookRepository.findByStatusAndId("ACTIVE", bookId));
        return bookFromDBOpt.orElseThrow(() ->
                new EntityDoesNotExistException("Book with Id: (" + bookId + ") does not exist!"));
    }
}
