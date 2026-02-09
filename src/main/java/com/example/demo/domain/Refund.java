package com.example.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Date;

@Entity
@Table(name="refunds")
public class Refund {
    @Id
    private int refundId;
    private int orderId;
    private int userId;
    private String reason;
    private RefundStatus status; // PENDING, APPROVED, REJECTED
    private Date requestDate;
    private float refundAmount;

    public Refund(int refundId, int orderId, int userId, String reason, RefundStatus status, Date requestDate, float refundAmount) {
        this.refundId = refundId;
        this.orderId = orderId;
        this.userId = userId;
        this.reason = reason;
        this.status = status;
        this.requestDate = requestDate;
        this.refundAmount = refundAmount;
    }

    public Refund() {}

    // Getters and setters
    public int getRefundId() { return refundId; }
    public void setRefundId(int refundId) { this.refundId = refundId; }
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    public RefundStatus getStatus() { return status; }
    public void setStatus(RefundStatus status) { this.status = status; }
    public Date getRequestDate() { return requestDate; }
    public void setRequestDate(Date requestDate) { this.requestDate = requestDate; }
    public float getRefundAmount() { return refundAmount; }
    public void setRefundAmount(float refundAmount) { this.refundAmount = refundAmount; }
}
