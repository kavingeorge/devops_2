package com.example.demo.service;

import com.example.demo.domain.Game;
import com.example.demo.domain.GameKey;
import com.example.demo.domain.ApprovalStatus;
import com.example.demo.repository.GameRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class GameService {
    @Autowired
    private GameRepo gameRepo;

    @Transactional
    public void saveOrUpdateGame(int gameId, String title, String description, String genre, float price, Date releaseDate, int developerId, int userId, ApprovalStatus approvalStatus, String downloadLink) {
        Game existingGame = gameRepo.findByGameIdAndReleaseDateAndUserId(gameId, releaseDate, userId);
        if (existingGame != null) {
            existingGame.setTitle(title);
            existingGame.setDescription(description);
            existingGame.setGenre(genre);
            existingGame.setPrice(price);
            existingGame.setDeveloperId(developerId);
            existingGame.setApprovalStatus(approvalStatus);
            existingGame.setDownloadLink(downloadLink);
            gameRepo.save(existingGame);
        } else {
            Game newGame = new Game(gameId, releaseDate, title, description, genre, price, developerId, userId, approvalStatus, downloadLink);
            gameRepo.save(newGame);
        }
    }

    public void deleteGameByIdAndReleaseDate(int gameId, Date releaseDate, int userId) {
        Game existingGame = gameRepo.findByGameIdAndReleaseDateAndUserId(gameId, releaseDate, userId);
        if (existingGame != null) {
            gameRepo.delete(existingGame);
        } else {
            throw new RuntimeException("Game with gameId " + gameId + " and releaseDate " + releaseDate + " not found for user " + userId);
        }
    }

    public Game getGameByIdAndReleaseDate(int gameId, Date releaseDate, int userId) {
        return gameRepo.findByGameIdAndReleaseDateAndUserId(gameId, releaseDate, userId);
    }

    @Transactional
    public void updateGame(int gameId, Date releaseDate, Game updatedGame, int userId) {
        Game existingGame = gameRepo.findByGameIdAndReleaseDateAndUserId(gameId, releaseDate, userId);
        if (existingGame != null) {
            existingGame.setTitle(updatedGame.getTitle());
            existingGame.setDescription(updatedGame.getDescription());
            existingGame.setGenre(updatedGame.getGenre());
            existingGame.setPrice(updatedGame.getPrice());
            existingGame.setDeveloperId(updatedGame.getDeveloperId());
            existingGame.setApprovalStatus(updatedGame.getApprovalStatus());
            existingGame.setDownloadLink(updatedGame.getDownloadLink());
            gameRepo.save(existingGame);
        } else {
            throw new RuntimeException("Game with gameId " + gameId + " and releaseDate " + releaseDate + " not found for user " + userId);
        }
    }

    public List<Game> getAllGamesByUserId(int userId) {
        return gameRepo.findAllByUserId(userId);
    }

    public List<Game> getApprovedGames() {
        Date currentDate = Date.valueOf(LocalDate.now());
        return gameRepo.findByApprovalStatusAndReleaseDateBefore(ApprovalStatus.APPROVED, currentDate);
    }

    public List<Game> searchGames(String query) {
        return gameRepo.findByTitleContainingIgnoreCaseOrGenreContainingIgnoreCase(query, query);
    }

    public List<Game> getPendingGames() {
        return gameRepo.findByApprovalStatus(ApprovalStatus.PENDING);
    }

    public void approveGame(int gameId, Date releaseDate, int userId) {
        Game game = gameRepo.findByGameIdAndReleaseDateAndUserId(gameId, releaseDate, userId);
        if (game != null) {
            game.setApprovalStatus(ApprovalStatus.APPROVED);
            gameRepo.save(game);
        }
    }

    public void rejectGame(int gameId, Date releaseDate, int userId) {
        Game game = gameRepo.findByGameIdAndReleaseDateAndUserId(gameId, releaseDate, userId);
        if (game != null) {
            game.setApprovalStatus(ApprovalStatus.REJECTED);
            gameRepo.save(game);
        }
    }
}
