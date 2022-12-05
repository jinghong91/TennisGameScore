package com.jyang.tennisgamescore.utils;

import com.jyang.tennisgamescore.model.NormalGameScore;
import com.jyang.tennisgamescore.model.Point;
import com.jyang.tennisgamescore.model.TieBreakScore;
import com.jyang.tennisgamescore.model.enums.GameScore;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreCalculatorTest {

    private final ScoreCalculator scoreCalculator = new ScoreCalculator();

    @Test
    public void nextNormalScore_0_0_return_15_0() {
        NormalGameScore winnerScore = new NormalGameScore(GameScore.ZERO, 0);
        NormalGameScore loserScore = new NormalGameScore(GameScore.ZERO, 0);
        Point expectedResult = new Point().setGameFinished(false).setLoserScore(loserScore)
                .setWinnerScore(new NormalGameScore(GameScore.FIFTEEN, 0));
        Point result = scoreCalculator.nextNormalScore(winnerScore, loserScore);
        assertEquals(expectedResult, result);
    }

    @Test
    public void nextNormalScore_40_0_return_0_0() {
        NormalGameScore winnerScore = new NormalGameScore(GameScore.FORTY, 0);
        NormalGameScore loserScore = new NormalGameScore(GameScore.ZERO, 0);
        Point expectedResult = new Point().setGameFinished(true).setLoserScore(new NormalGameScore(GameScore.ZERO, 0))
                .setWinnerScore(new NormalGameScore(GameScore.ZERO, 1));
        Point result = scoreCalculator.nextNormalScore(winnerScore, loserScore);
        assertEquals(expectedResult, result);
    }

    @Test
    public void nextNormalScore_30_40_return_40_40() {
        NormalGameScore winnerScore = new NormalGameScore(GameScore.THIRTY, 0);
        NormalGameScore loserScore = new NormalGameScore(GameScore.FORTY, 0);
        Point expectedResult = new Point().setGameFinished(false).setLoserScore(loserScore)
                .setWinnerScore(new NormalGameScore(GameScore.FORTY, 0));
        Point result = scoreCalculator.nextNormalScore(winnerScore, loserScore);
        assertEquals(expectedResult, result);
    }

    @Test
    public void nextNormalScore_40_40_return_ADV_40() {
        NormalGameScore winnerScore = new NormalGameScore(GameScore.FORTY, 0);
        NormalGameScore loserScore = new NormalGameScore(GameScore.FORTY, 0);
        Point expectedResult = new Point().setGameFinished(false).setLoserScore(new NormalGameScore(GameScore.FORTY, 0))
                .setWinnerScore(new NormalGameScore(GameScore.ADV, 0));
        Point result = scoreCalculator.nextNormalScore(winnerScore, loserScore);
        assertEquals(expectedResult, result);
    }

    @Test
    public void nextNormalScore_DEUCE_DEUCE_return_ADV_40() {
        NormalGameScore winnerScore = new NormalGameScore(GameScore.DEUCE, 0);
        NormalGameScore loserScore = new NormalGameScore(GameScore.DEUCE, 0);
        Point expectedResult = new Point().setGameFinished(false).setLoserScore(new NormalGameScore(GameScore.FORTY, 0))
                .setWinnerScore(new NormalGameScore(GameScore.ADV, 0));
        Point result = scoreCalculator.nextNormalScore(winnerScore, loserScore);
        assertEquals(expectedResult, result);
    }

    @Test
    public void nextNormalScore_40_ADV_return_DEUCE_DEUCE() {
        NormalGameScore winnerScore = new NormalGameScore(GameScore.FORTY, 0);
        NormalGameScore loserScore = new NormalGameScore(GameScore.ADV, 0);
        Point expectedResult = new Point().setGameFinished(false).setLoserScore(new NormalGameScore(GameScore.DEUCE, 0))
                .setWinnerScore(new NormalGameScore(GameScore.DEUCE, 0));
        Point result = scoreCalculator.nextNormalScore(winnerScore, loserScore);
        assertEquals(expectedResult, result);
    }

    @Test
    public void nextNormalScore_ADV_40_return_0_0() {
        NormalGameScore winnerScore = new NormalGameScore(GameScore.ADV, 0);
        NormalGameScore loserScore = new NormalGameScore(GameScore.FORTY, 0);
        Point expectedResult = new Point().setGameFinished(true).setLoserScore(new NormalGameScore(GameScore.ZERO, 0))
                .setWinnerScore(new NormalGameScore(GameScore.ZERO, 1));
        Point result = scoreCalculator.nextNormalScore(winnerScore, loserScore);
        assertEquals(expectedResult, result);
    }

    @Test
    public void nextTieBreakScore_0_0_return_1_0() {
        TieBreakScore winnerScore = new TieBreakScore(0, 6);
        TieBreakScore loserScore = new TieBreakScore(0, 6);
        Point expectedResult = new Point().setGameFinished(false).setLoserScore(loserScore)
                .setWinnerScore(new TieBreakScore(1, 6));
        Point result = scoreCalculator.nextTieBreakScore(winnerScore, loserScore);
        assertEquals(expectedResult, result);
    }

    @Test
    public void nextTieBreakScore_6_0_return_0_0() {
        TieBreakScore winnerScore = new TieBreakScore(6, 6);
        TieBreakScore loserScore = new TieBreakScore(0, 6);
        Point expectedResult = new Point().setGameFinished(true).setLoserScore(new TieBreakScore(0, 6))
                .setWinnerScore(new TieBreakScore(0, 7));
        Point result = scoreCalculator.nextTieBreakScore(winnerScore, loserScore);
        assertEquals(expectedResult, result);
    }

    @Test
    public void nextTieBreakScore_6_5_return_0_0() {
        TieBreakScore winnerScore = new TieBreakScore(6, 6);
        TieBreakScore loserScore = new TieBreakScore(5, 6);
        Point expectedResult = new Point().setGameFinished(true).setLoserScore(new TieBreakScore(0, 6))
                .setWinnerScore(new TieBreakScore(0, 7));
        Point result = scoreCalculator.nextTieBreakScore(winnerScore, loserScore);
        assertEquals(expectedResult, result);
    }

    @Test
    public void nextTieBreakScore_6_6_return_7_6() {
        TieBreakScore winnerScore = new TieBreakScore(6, 6);
        TieBreakScore loserScore = new TieBreakScore(6, 6);
        Point expectedResult = new Point().setGameFinished(false).setLoserScore(loserScore)
                .setWinnerScore(new TieBreakScore(7, 7));
        Point result = scoreCalculator.nextTieBreakScore(winnerScore, loserScore);
        assertEquals(expectedResult, result);
    }

    @Test
    public void nextTieBreakScore_7_6_return_8_6() {
        TieBreakScore winnerScore = new TieBreakScore(6, 6);
        TieBreakScore loserScore = new TieBreakScore(5, 6);
        Point expectedResult = new Point().setGameFinished(true).setLoserScore(new TieBreakScore(0, 6))
                .setWinnerScore(new TieBreakScore(0, 7));
        Point result = scoreCalculator.nextTieBreakScore(winnerScore, loserScore);
        assertEquals(expectedResult, result);
    }

}
