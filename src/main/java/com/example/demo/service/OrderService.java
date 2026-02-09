package com.example.demo.service;

import com.example.demo.domain.Order;
import com.example.demo.domain.OrderItem;
import com.example.demo.domain.OrderStatus;
import com.example.demo.domain.Library;
import com.example.demo.domain.Game;
import com.example.demo.repository.OrderRepo;
import com.example.demo.repository.GameRepo;
import com.example.demo.repository.LibraryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private GameRepo gameRepo;
    @Autowired
    private LibraryRepo libraryRepo;

    @Transactional
    public void createOrder(int orderId, int userId, List<OrderItem> items) {
        float total = 0;
        for (OrderItem item : items) {
            total += item.getPrice() * item.getQuantity();
        }
        Order order = new Order(orderId, Date.valueOf(LocalDate.now()), total, OrderStatus.PENDING, userId);
        orderRepo.save(order);
        // Mock payment: assume success
        order.setStatus(OrderStatus.COMPLETED);
        orderRepo.save(order);
        // Add to library
        for (OrderItem item : items) {
            Game game = gameRepo.findByGameIdAndReleaseDateAndUserId(item.getGameId(), Date.valueOf(LocalDate.now()), userId); // Approximate
            if (game != null) {
                Library library = new Library(userId, item.getGameId(), Date.valueOf(LocalDate.now()), game.getDownloadLink());
                libraryRepo.save(library);
            }
        }
    }

    public List<Order> getOrdersByUserId(int userId) {
        return orderRepo.findByUserId(userId);
    }

    public Order getOrderById(int orderId) {
        return orderRepo.findById(orderId).orElse(null);
    }
}
