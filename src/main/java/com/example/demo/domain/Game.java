package com.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "games")
@IdClass(GameKey.class)
public class Game implements Serializable {
    @Id
    private int gameId;
    @Id
    private Date releaseDate;
    private String title;
    private String description;
    private String genre;
    private float price;
    private int developerId;
    @Column(name = "userId")
    private int userId; // seller who added the game
    private ApprovalStatus approvalStatus; // PENDING, APPROVED, REJECTED
    private String downloadLink;

    public Game() {}

    public Game(int gameId, Date releaseDate, String title, String description, String genre, float price, int developerId, int userId, ApprovalStatus approvalStatus, String downloadLink) {
        this.gameId = gameId;
        this.releaseDate = releaseDate;
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.price = price;
        this.developerId = developerId;
        this.userId = userId;
        this.approvalStatus = approvalStatus;
        this.downloadLink = downloadLink;
    }

    // Getters and setters
    public int getGameId() { return gameId; }
    public void setGameId(int gameId) { this.gameId = gameId; }
    public Date getReleaseDate() { return releaseDate; }
    public void setReleaseDate(Date releaseDate) { this.releaseDate = releaseDate; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    public float getPrice() { return price; }
    public void setPrice(float price) { this.price = price; }
    public int getDeveloperId() { return developerId; }
    public void setDeveloperId(int developerId) { this.developerId = developerId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public ApprovalStatus getApprovalStatus() { return approvalStatus; }
    public void setApprovalStatus(ApprovalStatus approvalStatus) { this.approvalStatus = approvalStatus; }
    public String getDownloadLink() { return downloadLink; }
    public void setDownloadLink(String downloadLink) { this.downloadLink = downloadLink; }
}
