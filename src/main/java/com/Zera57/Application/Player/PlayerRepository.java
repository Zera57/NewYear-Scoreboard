package com.Zera57.Application.Player;

import com.Zera57.Application.Player.Models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Transactional
    @Query("select p from Player p where p.name = ?1 and p.nickname = ?2")
    Optional<Player> findByName(String name, String nickName);

    @Transactional
    @Modifying
    @Query("update Player p set p.score = p.score + ?3 where p.name = ?1 and p.nickname = ?2")
    void addPoint(String name, String nickname, int points);
}
