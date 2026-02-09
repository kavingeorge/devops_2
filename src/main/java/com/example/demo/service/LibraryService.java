package com.example.demo.service;

import com.example.demo.domain.Library;
import com.example.demo.domain.LibraryKey;
import com.example.demo.repository.LibraryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class LibraryService {
    @Autowired
    private LibraryRepo libraryRepo;

    public void addToLibrary(int userId, int gameId, String downloadLink) {
        Library library = new Library(userId, gameId, Date.valueOf(LocalDate.now()), downloadLink);
        libraryRepo.save(library);
    }

    public List<Library> getUserLibrary(int userId) {
        return libraryRepo.findByUserId(userId);
    }

    public void removeFromLibrary(int userId, int gameId) {
        LibraryKey key = new LibraryKey(userId, gameId);
        libraryRepo.deleteById(key);
    }
}
