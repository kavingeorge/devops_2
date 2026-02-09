package com.example.demo.domain;

import java.io.Serializable;
import java.util.Objects;

public class OrderItemKey implements Serializable {
    private int orderId;
    private int gameId;

    public OrderItemKey() {}

    public OrderItemKey(int orderId, int gameId) {
        this.orderId = orderId;
        this.gameId = gameId;
    }

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    public int getGameId() { return gameId; }
    public void setGameId(int gameId) { this.gameId = gameId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemKey that = (OrderItemKey) o;
        return orderId == that.orderId && gameId == that.gameId;
    }

    @Override
    public int hashCode() { return Objects.hash(orderId, gameId); }
}
