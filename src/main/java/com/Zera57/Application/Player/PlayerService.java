package com.Zera57.Application.Player;

import com.Zera57.Application.Player.Models.Player;
import java.util.List;

public interface PlayerService {

    public Player getPlayer(String name, String nickname);
    List<Player> getAll();
    public Player addPlayer(Player player);
    public Player addPoint(String name, String nickname, int points);
    public Player removePoint(String name, String nickname, int points);
    Player getRandomPlayer();
}
