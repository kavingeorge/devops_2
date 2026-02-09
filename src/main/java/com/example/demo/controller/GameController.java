package com.example.demo.controller;

import com.example.demo.domain.Game;
import com.example.demo.domain.ApprovalStatus;
import com.example.demo.domain.User;
import com.example.demo.domain.UserRole;
import com.example.demo.service.GameService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.sql.Date;
import java.util.List;

@Controller
public class GameController {
    @Autowired
    private GameService gameService;

    @GetMapping("/games")
    public String gameList(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        if (user.getRole() == UserRole.SELLER) {
            List<Game> games = gameService.getAllGamesByUserId(user.getUserId());
            model.addAttribute("games", games);
            return "seller-games";
        } else if (user.getRole() == UserRole.ADMIN) {
            List<Game> pendingGames = gameService.getPendingGames();
            model.addAttribute("pendingGames", pendingGames);
            return "admin-approvals";
        } else {
            List<Game> approvedGames = gameService.getApprovedGames();
            model.addAttribute("games", approvedGames);
            return "catalog";
        }
    }

    @GetMapping("/add-game")
    public String addGameForm(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole() != UserRole.SELLER) return "redirect:/home";
        return "add-game";
    }

    @PostMapping("/add-game")
    public String addGame(@ModelAttribute("game") Game game, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole() != UserRole.SELLER) return "redirect:/home";

        game.setUserId(user.getUserId());
        game.setApprovalStatus(ApprovalStatus.PENDING);
        gameService.saveOrUpdateGame(game.getGameId(), game.getTitle(), game.getDescription(), game.getGenre(), game.getPrice(), game.getReleaseDate(), game.getDeveloperId(), user.getUserId(), ApprovalStatus.PENDING, game.getDownloadLink());
        return "redirect:/games";
    }

    @GetMapping("/edit-game/{id}/{releaseDate}")
    public String editGameForm(@PathVariable("id") int id, @PathVariable("releaseDate") Date releaseDate, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole() != UserRole.SELLER) return "redirect:/home";

        Game game = gameService.getGameByIdAndReleaseDate(id, releaseDate, user.getUserId());
        model.addAttribute("game", game);
        return "edit-game";
    }

    @PostMapping("/edit-game/{id}/{releaseDate}")
    public String updateGame(@PathVariable("id") int id, @PathVariable("releaseDate") Date releaseDate, @ModelAttribute("game") Game updatedGame, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole() != UserRole.SELLER) return "redirect:/home";

        gameService.updateGame(id, releaseDate, updatedGame, user.getUserId());
        return "redirect:/games";
    }

    @GetMapping("/delete-game/{id}/{releaseDate}")
    public String deleteGame(@PathVariable("id") int id, @PathVariable("releaseDate") Date releaseDate, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole() != UserRole.SELLER) return "redirect:/home";

        gameService.deleteGameByIdAndReleaseDate(id, releaseDate, user.getUserId());
        return "redirect:/games";
    }

    @PostMapping("/approve-game/{id}/{releaseDate}")
    public String approveGame(@PathVariable("id") int id, @PathVariable("releaseDate") Date releaseDate, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole() != UserRole.ADMIN) return "redirect:/home";

        gameService.approveGame(id, releaseDate, user.getUserId());
        return "redirect:/games";
    }

    @PostMapping("/reject-game/{id}/{releaseDate}")
    public String rejectGame(@PathVariable("id") int id, @PathVariable("releaseDate") Date releaseDate, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole() != UserRole.ADMIN) return "redirect:/home";

        gameService.rejectGame(id, releaseDate, user.getUserId());
        return "redirect:/games";
    }

    @GetMapping("/search")
    public String searchGames(@RequestParam("query") String query, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        List<Game> results = gameService.searchGames(query);
        model.addAttribute("games", results);
        return "catalog";
    }
}
