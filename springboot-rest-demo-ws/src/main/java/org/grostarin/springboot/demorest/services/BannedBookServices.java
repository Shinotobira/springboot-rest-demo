package org.grostarin.springboot.demorest.services;

import org.grostarin.springboot.demorest.domain.BannedBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.grostarin.springboot.demorest.repositories.BannedBookRepository;

@Service
public class BannedBookServices {
    @Autowired
    private BannedBookRepository bannedBookRepository;
    public boolean isBanned(String title, String author) {
        return bannedBookRepository.existsByTitleAndAuthor(title, author);
    }

    public BannedBook addBannedBook(BannedBook book) {
        return bannedBookRepository.save(book);
    }
}
