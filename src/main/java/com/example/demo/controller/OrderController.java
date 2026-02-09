package com.example.demo.controller;

import com.example.demo.domain.Order;
import com.example.demo.domain.OrderItem;
import com.example.demo.domain.Game;
import com.example.demo.domain.User;
import com.example.demo.domain.UserRole;
import com.example.demo.service.OrderService;
import com.example.demo.service.GameService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.ArrayList;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private GameService gameService;

    @GetMapping("/cart")
    public String viewCart(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";
        // For simplicity, cart is session-based; in real app, use database
        return "cart";
    }

    @PostMapping("/add-to-cart/{gameId}")
    public String addToCart(@PathVariable("gameId") int gameId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        List<Integer> cart = (List<Integer>) session.getAttribute("cart");
        if (cart == null) cart = new ArrayList<>();
        cart.add(gameId);
        session.setAttribute("cart", cart);
        return "redirect:/games";
    }

    @PostMapping("/checkout")
    public String checkout(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        List<Integer> cart = (List<Integer>) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) return "redirect:/cart";

        // Mock order creation
        int orderId = (int) (Math.random() * 10000); // Generate random order ID
        List<OrderItem> items = new ArrayList<>();
        for (int gameId : cart) {
            Game game = gameService.getApprovedGames().stream().filter(g -> g.getGameId() == gameId).findFirst().orElse(null);
            if (game != null) {
                OrderItem item = new OrderItem(orderId, gameId, 1, game.getPrice());
                items.add(item);
            }
        }
        orderService.createOrder(orderId, user.getUserId(), items);
        session.removeAttribute("cart");
        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String viewOrders(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        List<Order> orders = orderService.getOrdersByUserId(user.getUserId());
        model.addAttribute("orders", orders);
        return "orders";
    }

    @GetMapping("/order/{id}")
    public String viewOrder(@PathVariable("id") int id, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        Order order = orderService.getOrderById(id);
        if (order == null || order.getUserId() != user.getUserId()) return "redirect:/orders";
        model.addAttribute("order", order);
        return "order-detail";
    }
}
