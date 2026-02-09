package com.example.demo.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class GameKey implements Serializable {
    private int gameId;
    private Date releaseDate;

    public GameKey() {}

    public GameKey(int gameId, Date releaseDate) {
        this.gameId = gameId;
        this.releaseDate = releaseDate;
    }

    public int getGameId() { return gameId; }
    public void setGameId(int gameId) { this.gameId = gameId; }
    public Date getReleaseDate() { return releaseDate; }
    public void setReleaseDate(Date releaseDate) { this.releaseDate = releaseDate; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameKey that = (GameKey) o;
        return gameId == that.gameId && Objects.equals(releaseDate, that.releaseDate);
    }

    @Override
    public int hashCode() { return Objects.hash(gameId, releaseDate); }
}
