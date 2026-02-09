package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.domain.Library;
import com.example.demo.domain.LibraryKey;
import java.util.List;

@Repository
public interface LibraryRepo extends JpaRepository<Library, LibraryKey> {
    List<Library> findByUserId(int userId);
}
