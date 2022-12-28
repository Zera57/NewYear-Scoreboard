package com.Zera57.Application.Player;

import com.Zera57.Application.Player.Models.Player;

import java.util.Collection;

public interface PlayerService {

    public Player getPlayer(String name);
    Collection<Player> getAll();
    public Player addPlayer(Player player);
    public Player addPoint(String name, int points);
    public Player removePoint(String name);

}
