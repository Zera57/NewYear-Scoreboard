package com.Zera57.Application.Player.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "player")
@NoArgsConstructor
@Data
public class Player {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "player_sequence"
    )
    @SequenceGenerator(
            name = "player_sequence",
            sequenceName = "player_sequence",
            allocationSize = 1
    )
    @JsonIgnore
    private Long id;
    private String nickname;
    private String name;
    private Integer score = 0;

    public Player(String nickname, String name) {
        this.nickname = nickname;
        this.name = name;
    }
}
