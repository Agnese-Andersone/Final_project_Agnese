package com.company.service;

import com.company.model.Book;
import com.company.model.User;
import com.company.repository.BookRepository;
import com.company.service.validator.BookValidator;
import com.company.service.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookService {

    private final BookRepository bookRepository;
    private final BookValidator bookValidator;
    private final UserValidator userValidator;

    @Autowired
    public BookService(BookRepository bookRepository,
                       BookValidator bookValidator,
                       UserValidator userValidator) {

        this.bookRepository = bookRepository;
        this.bookValidator = bookValidator;
        this.userValidator = userValidator;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findByStatus("ACTIVE");
    }

    public List<Book> getAllBooksByYear(String year) {
        return bookRepository.findByYearAndStatus("ACTIVE", year);
    }

    public List<Book> getAllBooksByInAnnotation(String keyword) {
        return bookRepository.findByAnnotationLikeAndStatus("ACTIVE","%" + keyword + "%");
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Book book) {
        bookValidator.checkIfBookExists(book.getId());
        return bookRepository.save(book);
    }

    public List<Book> filterBook(Book book) {
        book.setStatus("ACTIVE");
        Example<Book> bookExample = Example.of(book);
        return bookRepository.findAll(bookExample);
    }
    public void addBookToUser(Long userId, Long bookId) {
        Book book = bookValidator.checkIfBookExists(bookId);
        User user = userValidator.checkIfUserExists(userId);
        userValidator.checkIfUserDoesNotHaveMoreThenFiveBooks(user);
        book.setUser(user);
        bookRepository.save(book);
    }

    public void removeBookFromUser(Long userId, Long bookId){
        Book book = bookValidator.checkIfBookExists(bookId);
        User user = userValidator.checkIfUserExists(userId);
        userValidator.checkIfUserHasBook(user, bookId);
        book.setUser(null);
        bookRepository.save(book);
    }
    public void softDeleteBook(Long bookId){
        Book book = bookValidator.checkIfBookExists(bookId);
        book.setStatus("DELETED");
        bookRepository.save(book);
    }
}
