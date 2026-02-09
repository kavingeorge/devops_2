package com.example.demo.repository;

import com.example.demo.domain.Game;
import com.example.demo.domain.GameKey;
import com.example.demo.domain.ApprovalStatus;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepo extends JpaRepository<Game, GameKey> {
    void deleteByGameIdAndReleaseDateAndUserId(int gameId, Date releaseDate, int userId);
    Game findByGameIdAndReleaseDateAndUserId(int gameId, Date releaseDate, int userId);
    List<Game> findByGameIdAndUserId(int gameId, int userId);
    List<Game> findAllByUserId(int userId);
    List<Game> findByApprovalStatus(ApprovalStatus status);
    List<Game> findByApprovalStatusAndReleaseDateBefore(ApprovalStatus status, Date currentDate);
    List<Game> findByTitleContainingIgnoreCaseOrGenreContainingIgnoreCase(String title, String genre);
}
