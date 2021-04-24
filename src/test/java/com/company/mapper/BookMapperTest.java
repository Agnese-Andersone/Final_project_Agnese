package com.company.mapper;

import com.company.dto.BookDTO;
import com.company.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
class BookMapperTest {
    private BookMapper bookMapper = new BookMapper();

    @Test
    void fromDTO() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setAnnotation("any");
        bookDTO.setAuthor("the best");
        bookDTO.setId(2L);
        bookDTO.setISBN("ISBV-15");
        bookDTO.setName("Agnese");
        bookDTO.setYear("2010");

        Book book = bookMapper.fromDTO(bookDTO);
        assertEquals("any", book.getAnnotation());
        assertEquals("the best", book.getAuthor());
        assertEquals(2L, book.getId());
        assertEquals("ISBV-15", book.getISBN());
        assertEquals("Agnese", book.getName());
        assertEquals("2010", book.getYear());
    }

    @Test
    void toDTO() {
        Book book = new Book();
        book.setAnnotation("any");
        book.setAuthor("the best");
        book.setId(2L);
        book.setISBN("ISBV-15");
        book.setName("Agnese");
        book.setYear("2010");

        BookDTO bookDTO = bookMapper.toDTO(book);
        assertEquals("any", bookDTO.getAnnotation());
        assertEquals("the best", bookDTO.getAuthor());
        assertEquals(2L, bookDTO.getId());
        assertEquals("ISBV-15", bookDTO.getISBN());
        assertEquals("Agnese", bookDTO.getName());
        assertEquals("2010", bookDTO.getYear());

    }
}