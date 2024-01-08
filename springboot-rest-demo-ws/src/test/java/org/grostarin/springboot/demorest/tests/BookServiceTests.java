package org.grostarin.springboot.demorest.tests;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.grostarin.springboot.demorest.domain.BannedBook;
import org.grostarin.springboot.demorest.domain.Book;
import org.grostarin.springboot.demorest.exceptions.BannedBookException;
import org.grostarin.springboot.demorest.exceptions.BookIdMismatchException;
import org.grostarin.springboot.demorest.services.BannedBookServices;
import org.grostarin.springboot.demorest.services.BookServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

@SpringBootTest
public class BookServiceTests {
    @Autowired
    private BookServices bookService;
    @Autowired
    private BannedBookServices bannedBookServices;
    
    @Test
    public void testCreationNoAttributes() {
        Book toCreate = new Book();
        assertThatExceptionOfType(DataIntegrityViolationException.class).isThrownBy( () -> bookService.create(toCreate));
    }

    @Test
    public void catchErrorBookMismatch() {
        Book toCreate = new Book("title", "author");
        toCreate.setId(1);
        bookService.create(toCreate);
        assertThatExceptionOfType(BookIdMismatchException.class).isThrownBy( () -> bookService.updateBook(toCreate, 2));
        }

    @Test
    public void catchErrorOnAddBannedBook() {
        BannedBook bannedBook = new BannedBook("title", "author");
        bannedBookServices.addBannedBook(bannedBook);
        Book toCreate = new Book("title", "author");
        assertThatExceptionOfType(BannedBookException.class).isThrownBy( () -> bookService.create(toCreate));
    }
}
