package com.Zera57.Application.Player;

import com.Zera57.Application.Player.Models.ChangePointsRq;
import com.Zera57.Application.Player.Models.Player;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public Player getPlayer(@RequestParam String name, @RequestParam String nickname) {
        return playerService.getPlayer(name, nickname);
    }

    @GetMapping("/all")
    public Collection<Player> getAll() {
        return playerService.getAll();
    }

    @PostMapping("/addPoint")
    public Player addPoint(@RequestBody ChangePointsRq rq) {
        return playerService.addPoint(rq.getName(), rq.getNickname(), rq.getPoints());
    }

    @PostMapping("/removePoint")
    public Player removePoint(@RequestBody ChangePointsRq rq) {
        return playerService.removePoint(rq.getName(), rq.getNickname(), rq.getPoints());
    }

    @GetMapping("/random")
    public Player getRandomPlayer() {
        return playerService.getRandomPlayer();
    }
}
