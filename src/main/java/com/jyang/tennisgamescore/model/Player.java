package com.jyang.tennisgamescore.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Player {
    private String name;
    private List<Game> gameList = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public Game getCurrentGame() {
        return gameList.get(gameList.size() - 1);
    }
}
