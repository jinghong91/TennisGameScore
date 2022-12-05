package com.jyang.tennisgamescore.services;

import com.jyang.tennisgamescore.model.Player;
import com.jyang.tennisgamescore.model.Point;
import com.jyang.tennisgamescore.model.ScoreTable;

public interface MatchService {
    boolean isMatchFinished(Player player1, Player player2);

    Point updateScore(Player winner, Player loser);

    ScoreTable generateScoreTable(Player player1, Player player2);

    void displayScore(ScoreTable scoreTable);

}
