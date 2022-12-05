package com.jyang.tennisgamescore.services;

import com.jyang.tennisgamescore.model.*;
import com.jyang.tennisgamescore.model.enums.GameScore;
import com.jyang.tennisgamescore.services.impl.MatchServiceImpl;
import com.jyang.tennisgamescore.utils.ScoreCalculator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MatchServiceTest {
    @Mock
    private ScoreCalculator scoreCalculator;
    @InjectMocks
    private MatchServiceImpl matchService;

    @Test
    public void isMatchFinished_1_0_return_false() {
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");

        List<NormalGameScore> player1ScoreList = new ArrayList<>();
        List<NormalGameScore> player2ScoreList = new ArrayList<>();
        player1ScoreList.add(new NormalGameScore(GameScore.ZERO, 0));
        player2ScoreList.add(new NormalGameScore(GameScore.ZERO, 0));
        player1ScoreList.add(new NormalGameScore(GameScore.FIFTEEN, 0));
        player2ScoreList.add(new NormalGameScore(GameScore.ZERO, 0));
        player1ScoreList.add(new NormalGameScore(GameScore.FORTY, 0));
        player2ScoreList.add(new NormalGameScore(GameScore.ZERO, 0));
        player1ScoreList.add(new NormalGameScore(GameScore.ZERO, 1));
        player2ScoreList.add(new NormalGameScore(GameScore.ZERO, 0));

        player1.getGameList().add(new NormalGame().setScoreList(player1ScoreList));
        player2.getGameList().add(new NormalGame().setScoreList(player2ScoreList));

        assertFalse(matchService.isMatchFinished(player1, player2));
    }

    @Test
    public void isMatchFinished_6_0_return_true() {
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");

        for (int i = 0; i < 4; i++) {
            player1.getGameList().add(new NormalGame());
            player2.getGameList().add(new NormalGame());
        }
        List<NormalGameScore> player1ScoreList = new ArrayList<>();
        List<NormalGameScore> player2ScoreList = new ArrayList<>();
        player1ScoreList.add(new NormalGameScore(GameScore.ZERO, 5));
        player2ScoreList.add(new NormalGameScore(GameScore.ZERO, 0));
        player1ScoreList.add(new NormalGameScore(GameScore.FIFTEEN, 5));
        player2ScoreList.add(new NormalGameScore(GameScore.ZERO, 0));
        player1ScoreList.add(new NormalGameScore(GameScore.FORTY, 5));
        player2ScoreList.add(new NormalGameScore(GameScore.ZERO, 0));
        player1ScoreList.add(new NormalGameScore(GameScore.ZERO, 6));
        player2ScoreList.add(new NormalGameScore(GameScore.ZERO, 0));

        player1.getGameList().add(new NormalGame().setScoreList(player1ScoreList));
        player2.getGameList().add(new NormalGame().setScoreList(player2ScoreList));
        assertTrue(matchService.isMatchFinished(player1, player2));
    }

    @Test
    public void isMatchFinished_0_6_return_true() {
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");

        for (int i = 0; i < 4; i++) {
            player1.getGameList().add(new NormalGame());
            player2.getGameList().add(new NormalGame());
        }
        List<NormalGameScore> player1ScoreList = new ArrayList<>();
        List<NormalGameScore> player2ScoreList = new ArrayList<>();
        player1ScoreList.add(new NormalGameScore(GameScore.ZERO, 0));
        player2ScoreList.add(new NormalGameScore(GameScore.ZERO, 5));
        player1ScoreList.add(new NormalGameScore(GameScore.ZERO, 0));
        player2ScoreList.add(new NormalGameScore(GameScore.FIFTEEN, 5));
        player1ScoreList.add(new NormalGameScore(GameScore.ZERO, 0));
        player2ScoreList.add(new NormalGameScore(GameScore.FORTY, 5));
        player1ScoreList.add(new NormalGameScore(GameScore.ZERO, 0));
        player2ScoreList.add(new NormalGameScore(GameScore.ZERO, 6));

        player1.getGameList().add(new NormalGame().setScoreList(player1ScoreList));
        player2.getGameList().add(new NormalGame().setScoreList(player2ScoreList));
        assertTrue(matchService.isMatchFinished(player1, player2));
    }

    @Test
    public void isMatchFinished_7_5_return_true() {
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");

        for (int i = 0; i < 11; i++) {
            player1.getGameList().add(new NormalGame());
            player2.getGameList().add(new NormalGame());
        }

        List<NormalGameScore> player1ScoreList = new ArrayList<>();
        List<NormalGameScore> player2ScoreList = new ArrayList<>();
        player1ScoreList.add(new NormalGameScore(GameScore.ZERO, 6));
        player2ScoreList.add(new NormalGameScore(GameScore.ZERO, 5));
        player1ScoreList.add(new NormalGameScore(GameScore.FIFTEEN, 6));
        player2ScoreList.add(new NormalGameScore(GameScore.ZERO, 5));
        player1ScoreList.add(new NormalGameScore(GameScore.FORTY, 6));
        player2ScoreList.add(new NormalGameScore(GameScore.ZERO, 5));
        player1ScoreList.add(new NormalGameScore(GameScore.ZERO, 7));
        player2ScoreList.add(new NormalGameScore(GameScore.ZERO, 5));

        player1.getGameList().add(new NormalGame().setScoreList(player1ScoreList));
        player2.getGameList().add(new NormalGame().setScoreList(player2ScoreList));
        assertTrue(matchService.isMatchFinished(player1, player2));
    }

    @Test
    public void isMatchFinished_6_6_return_false() {
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");

        for (int i = 0; i < 11; i++) {
            player1.getGameList().add(new NormalGame());
            player2.getGameList().add(new NormalGame());
        }

        List<NormalGameScore> player1ScoreList = new ArrayList<>();
        List<NormalGameScore> player2ScoreList = new ArrayList<>();
        player1ScoreList.add(new NormalGameScore(GameScore.ZERO, 5));
        player2ScoreList.add(new NormalGameScore(GameScore.ZERO, 6));
        player1ScoreList.add(new NormalGameScore(GameScore.FIFTEEN, 5));
        player2ScoreList.add(new NormalGameScore(GameScore.ZERO, 6));
        player1ScoreList.add(new NormalGameScore(GameScore.FORTY, 5));
        player2ScoreList.add(new NormalGameScore(GameScore.ZERO, 6));
        player1ScoreList.add(new NormalGameScore(GameScore.ZERO, 6));
        player2ScoreList.add(new NormalGameScore(GameScore.ZERO, 6));

        player1.getGameList().add(new NormalGame().setScoreList(player1ScoreList));
        player2.getGameList().add(new NormalGame().setScoreList(player2ScoreList));

        assertFalse(matchService.isMatchFinished(player1, player2));
    }

    @Test
    public void isMatchFinished_6_5_return_false() {
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");

        for (int i = 0; i < 10; i++) {
            player1.getGameList().add(new NormalGame());
            player2.getGameList().add(new NormalGame());
        }

        List<NormalGameScore> player1ScoreList = new ArrayList<>();
        List<NormalGameScore> player2ScoreList = new ArrayList<>();
        player1ScoreList.add(new NormalGameScore(GameScore.ZERO, 5));
        player2ScoreList.add(new NormalGameScore(GameScore.ZERO, 5));
        player1ScoreList.add(new NormalGameScore(GameScore.FIFTEEN, 5));
        player2ScoreList.add(new NormalGameScore(GameScore.ZERO, 5));
        player1ScoreList.add(new NormalGameScore(GameScore.FORTY, 5));
        player2ScoreList.add(new NormalGameScore(GameScore.ZERO, 5));
        player1ScoreList.add(new NormalGameScore(GameScore.ZERO, 6));
        player2ScoreList.add(new NormalGameScore(GameScore.ZERO, 5));

        player1.getGameList().add(new NormalGame().setScoreList(player1ScoreList));
        player2.getGameList().add(new NormalGame().setScoreList(player2ScoreList));
        assertFalse(matchService.isMatchFinished(player1, player2));
    }

    @Test
    public void isMatchFinished_tie_break_return_true() {
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");

        for (int i = 0; i < 12; i++) {
            player1.getGameList().add(new NormalGame());
            player2.getGameList().add(new NormalGame());
        }

        player1.getGameList().add(new TieBreak());
        player2.getGameList().add(new TieBreak());
        assertTrue(matchService.isMatchFinished(player1, player2));
    }

    @Test
    public void updateScore_normalGame() {
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");

        player1.getGameList().add(new NormalGame());
        player2.getGameList().add(new NormalGame());

        when(scoreCalculator.nextNormalScore(any(NormalGameScore.class), any(NormalGameScore.class))).thenReturn(new Point());
        matchService.updateScore(player1, player2);

        verify(scoreCalculator, times(1)).nextNormalScore(any(NormalGameScore.class), any(NormalGameScore.class));
    }

    @Test
    public void updateScore_tieBreak() {
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");

        for (int i = 0; i < 12; i++) {
            player1.getGameList().add(new NormalGame());
            player2.getGameList().add(new NormalGame());
        }

        player1.getGameList().add(new TieBreak());
        player2.getGameList().add(new TieBreak());

        when(scoreCalculator.nextTieBreakScore(any(TieBreakScore.class), any(TieBreakScore.class))).thenReturn(new Point());
        matchService.updateScore(player1, player2);

        verify(scoreCalculator, times(1)).nextTieBreakScore(any(TieBreakScore.class), any(TieBreakScore.class));
    }


}
