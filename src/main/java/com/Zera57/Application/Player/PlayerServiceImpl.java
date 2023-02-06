package com.Zera57.Application.Player;

import com.Zera57.Application.Account.AccountService;
import com.Zera57.Application.Player.Models.Player;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    public static final String PLAYER_NOT_FOUND = "Player not found ";
    AccountService accountService;
    PlayerRepository playerRepository;

    @Override
    public Player getPlayer(String name, String nickname) {
        return playerRepository.findByName(name, nickname)
                .orElseThrow(() -> new RuntimeException(PLAYER_NOT_FOUND + name));
    }

    @Override
    public List<Player> getAll() {
        return playerRepository.findAll().stream()
                .sorted(Comparator.comparing(Player::getName))
                .sorted(Comparator.comparingInt(Player::getScore).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Player addPlayer(Player player) {
        playerRepository.save(player);
        return getPlayer(player.getName(), player.getNickname());
    }

    @Override
    public Player addPoint(String name, String nickname, int points) {
        playerRepository.addPoint(name, nickname, points);
        return getPlayer(name, nickname);
    }

    @Override
    public Player removePoint(String name, String nickname, int points) {
        addPoint(name, nickname, -points);
        return getPlayer(name, nickname);
    }

    @Override
    public Player getRandomPlayer() {
        List<Player> listPlayers = getAll();
        Random random = new Random(new Date().getTime());
        return listPlayers.get(random.nextInt(listPlayers.size()));
    }
}
