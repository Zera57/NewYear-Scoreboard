package com.Zera57.Application.Player;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/player")
@AllArgsConstructor
public class PlayerController {
    PlayerService playerService;

    @GetMapping
    public Player getPlayer(@RequestParam String name) {
        return playerService.getPlayer(name);
    }

    @GetMapping("/all")
    public Collection<Player> getAll() {
        return playerService.getAll();
    }

    @PostMapping("/addPoint")
    public Player addPoint(String name) {
        return playerService.addPoint(name);
    }

    @PostMapping("/removePoint")
    public Player removePoint(String name) {
        return playerService.removePoint(name);
    }
}
