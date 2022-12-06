package com.jyang.tennisgamescore.model;

public interface Game {
    Score getCurrentScore();

    void addScore(Score score);
}
