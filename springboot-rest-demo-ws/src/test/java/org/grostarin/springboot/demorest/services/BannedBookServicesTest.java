package org.grostarin.springboot.demorest.services;

import org.grostarin.springboot.demorest.domain.BannedBook;
import org.grostarin.springboot.demorest.domain.Book;
import org.grostarin.springboot.demorest.repositories.BannedBookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BannedBookServicesTest {
    @Autowired
    private BannedBookServices bannedBookServices;
    @Autowired
    private BannedBookRepository bannedBookRepository;
    @Test
    void isBanned() {
        String title="The Banned Book";
        String author="John Author";
        boolean result = false;
        BannedBook toCreate = new BannedBook(title, author);
        bannedBookServices.addBannedBook(toCreate);
        if(bannedBookRepository.existsByTitleAndAuthor(title, author)){
            result = bannedBookServices.isBanned(title, author);
        }
        assertTrue(result);
    }


    @Test
    void addBannedBook() {
        BannedBook toCreate = new BannedBook();
        assertThatExceptionOfType(DataIntegrityViolationException.class).isThrownBy( () -> bannedBookServices.addBannedBook(toCreate));

    }
}