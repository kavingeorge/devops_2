package com.example.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name="library")
@IdClass(LibraryKey.class)
public class Library implements Serializable {
    @Id
    private int userId;
    @Id
    private int gameId;
    private Date purchaseDate;
    private String downloadLink;

    public Library(int userId, int gameId, Date purchaseDate, String downloadLink) {
        this.userId = userId;
        this.gameId = gameId;
        this.purchaseDate = purchaseDate;
        this.downloadLink = downloadLink;
    }

    public Library() {}

    // Getters and setters
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public int getGameId() { return gameId; }
    public void setGameId(int gameId) { this.gameId = gameId; }
    public Date getPurchaseDate() { return purchaseDate; }
    public void setPurchaseDate(Date purchaseDate) { this.purchaseDate = purchaseDate; }
    public String getDownloadLink() { return downloadLink; }
    public void setDownloadLink(String downloadLink) { this.downloadLink = downloadLink; }
}
