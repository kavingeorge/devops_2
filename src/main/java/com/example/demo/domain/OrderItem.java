package com.example.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="order_items")
@IdClass(OrderItemKey.class)
public class OrderItem implements Serializable {
    @Id
    private int orderId;
    @Id
    private int gameId;
    private int quantity;
    private float price;

    public OrderItem(int orderId, int gameId, int quantity, float price) {
        this.orderId = orderId;
        this.gameId = gameId;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderItem() {}

    // Getters and setters
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    public int getGameId() { return gameId; }
    public void setGameId(int gameId) { this.gameId = gameId; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public float getPrice() { return price; }
    public void setPrice(float price) { this.price = price; }
}
