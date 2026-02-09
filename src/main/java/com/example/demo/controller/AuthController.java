package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.service.UserService;
import com.example.demo.domain.User;
import com.example.demo.domain.UserRole;
import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/log")
    public String login(@ModelAttribute("user") User user, HttpSession session) {
        User authenticatedUser = userService.authenticate(user.getEmail(), user.getPassword());
        if (authenticatedUser != null) {
            session.setAttribute("userId", authenticatedUser.getUserId());
            session.setAttribute("user", authenticatedUser);
            return "redirect:/home";
        } else {
            return "redirect:/login?error";
        }
    }

    @GetMapping("/home")
    public String home(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            if (user.getRole() == UserRole.ADMIN) {
                return "admin-home";
            } else if (user.getRole() == UserRole.SELLER) {
                return "seller-home";
            } else {
                return "user-home";
            }
        }
        return "redirect:/login";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/profile")
    public String profile(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
            return "profile";
        }
        return "redirect:/login";
    }
}
