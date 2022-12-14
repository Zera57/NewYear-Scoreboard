package com.Zera57.Application.Player;

import com.Zera57.Application.Account.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    public static final String PLAYER_NOT_FOUND = "Player not found";
    AccountService accountService;
    PlayerRepository playerRepository;

    @Override
    public Player getPlayer(String name) {
        return playerRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException(PLAYER_NOT_FOUND));
    }

    @Override
    public Collection<Player> getAll() {
        return playerRepository.findAll();
    }

    @Override
    public Player addPlayer(Player player) {
        playerRepository.save(player);
        return getPlayer(player.getName());
    }

    @Override
    public Player addPoint(String name) {
        playerRepository.addPoint(name);
        return getPlayer(name);
    }

    @Override
    public Player removePoint(String name) {
        playerRepository.removePoint(name);
        return getPlayer(name);
    }
}
