package com.jyang.tennisgamescore.utils;

import com.jyang.tennisgamescore.model.NormalGameScore;
import com.jyang.tennisgamescore.model.Point;
import com.jyang.tennisgamescore.model.TieBreakScore;
import com.jyang.tennisgamescore.model.enums.GameScore;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class ScoreCalculator {
    private final Map<Integer, GameScore> scoreMap = new HashMap<>();

    public ScoreCalculator() {
        Stream.of(GameScore.values()).forEach(s -> scoreMap.put(s.getOrder(), s));
    }

    public Point nextNormalScore(NormalGameScore winnerScore, NormalGameScore loserScore) {
        GameScore winnerGameScore = winnerScore.getGameScore();
        GameScore loserGameScore = loserScore.getGameScore();
        int winnerSetScore = winnerScore.getSetScore();
        int loserSetScore = loserScore.getSetScore();
        if (winnerGameScore == GameScore.ADV
                || (winnerGameScore == GameScore.FORTY && loserGameScore.getOrder() < GameScore.FORTY.getOrder())) {
            return new Point().setGameFinished(true)
                    .setWinnerScore(new NormalGameScore(GameScore.ZERO, winnerSetScore + 1))
                    .setLoserScore(new NormalGameScore(GameScore.ZERO, loserSetScore));
        } else {
            Point point = new Point().setGameFinished(false);
            NormalGameScore newWinnerScore = new NormalGameScore();
            newWinnerScore.setSetScore(winnerSetScore);
            NormalGameScore newLoserScore = new NormalGameScore();
            newLoserScore.setSetScore(loserSetScore);

            if ((winnerGameScore == GameScore.FORTY && loserGameScore == GameScore.FORTY)
                    || (winnerGameScore == GameScore.DEUCE && loserGameScore == GameScore.DEUCE)) {
                newWinnerScore.setGameScore(GameScore.ADV);
                newLoserScore.setGameScore(GameScore.FORTY);
            } else if (loserGameScore == GameScore.ADV) {
                newWinnerScore.setGameScore(GameScore.DEUCE);
                newLoserScore.setGameScore(GameScore.DEUCE);
            } else {
                newWinnerScore.setGameScore(scoreMap.get(winnerGameScore.getOrder() + 1));
                newLoserScore.setGameScore(loserGameScore);
            }
            point.setWinnerScore(newWinnerScore);
            point.setLoserScore(newLoserScore);
            return point;
        }
    }

    public Point nextTieBreakScore(TieBreakScore winnerScore, TieBreakScore loserScore) {
        int winnerGameScore = winnerScore.getGameScore();
        int loserGameScore = loserScore.getGameScore();
        int winnerSetScore = winnerScore.getSetScore();
        int loserSetScore = loserScore.getSetScore();
        if (winnerGameScore > 5 && winnerGameScore > loserGameScore) {
            return new Point().setGameFinished(true)
                    .setWinnerScore(new TieBreakScore(0, winnerSetScore + 1))
                    .setLoserScore(new TieBreakScore(0, loserSetScore));
        } else {
            return new Point().setGameFinished(false)
                    .setWinnerScore(new TieBreakScore(winnerGameScore + 1, winnerSetScore))
                    .setLoserScore(new TieBreakScore(loserGameScore, loserSetScore));
        }
    }
}
