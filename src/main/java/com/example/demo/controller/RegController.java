package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.demo.service.UserService;
import com.example.demo.domain.User;
import com.example.demo.domain.UserRole;
import jakarta.servlet.http.HttpSession;

@Controller
public class RegController {
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/reg")
    public String registerUser(@ModelAttribute("user") User user, HttpSession session) {
        // Default role is USER, but allow selection or set based on some logic
        UserRole role = UserRole.USER; // For now, default to USER; can add form field later
        userService.saveUser(user.getUsername(), user.getEmail(), user.getPassword(), user.getDateofbirth(), user.getCompanyname(), user.getLocation(), role);
        User completeUser = userService.findByEmail(user.getEmail());
        session.setAttribute("user", completeUser);
        session.setAttribute("userId", completeUser.getUserId());
        return "redirect:/home";
    }
}
