package com.example.demo.domain;

import java.io.Serializable;
import java.util.Objects;

public class LibraryKey implements Serializable {
    private int userId;
    private int gameId;

    public LibraryKey() {}

    public LibraryKey(int userId, int gameId) {
        this.userId = userId;
        this.gameId = gameId;
    }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public int getGameId() { return gameId; }
    public void setGameId(int gameId) { this.gameId = gameId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LibraryKey that = (LibraryKey) o;
        return userId == that.userId && gameId == that.gameId;
    }

    @Override
    public int hashCode() { return Objects.hash(userId, gameId); }
}
