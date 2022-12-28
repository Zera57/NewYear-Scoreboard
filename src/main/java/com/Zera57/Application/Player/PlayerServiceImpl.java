package com.Zera57.Application.Player;

import com.Zera57.Application.Account.AccountService;
import com.Zera57.Application.Player.Models.Player;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    public static final String PLAYER_NOT_FOUND = "Player not found ";
    AccountService accountService;
    PlayerRepository playerRepository;

    @Override
    public Player getPlayer(String name) {
        return playerRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException(PLAYER_NOT_FOUND + name));
    }

    @Override
    public Collection<Player> getAll() {
        return playerRepository.findAll().stream()
                .sorted(Comparator.comparing(Player::getName))
                .sorted(Comparator.comparingInt(Player::getScore).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Player addPlayer(Player player) {
        playerRepository.save(player);
        return getPlayer(player.getName());
    }

    @Override
    public Player addPoint(String name, int points) {
        playerRepository.addPoint(name, points);
        return getPlayer(name);
    }

    @Override
    public Player removePoint(String name) {
        playerRepository.removePoint(name);
        return getPlayer(name);
    }
}
