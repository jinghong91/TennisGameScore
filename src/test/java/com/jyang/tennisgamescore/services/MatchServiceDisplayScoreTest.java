package com.jyang.tennisgamescore.services;

import com.jyang.tennisgamescore.model.NormalGame;
import com.jyang.tennisgamescore.model.Player;
import com.jyang.tennisgamescore.model.ScoreTable;
import com.jyang.tennisgamescore.model.TieBreak;
import com.jyang.tennisgamescore.services.impl.MatchServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatchServiceDisplayScoreTest {
    private final MatchServiceImpl matchService = new MatchServiceImpl();


    @BeforeEach
    public void setProperties() {
        ReflectionTestUtils.setField(matchService, "tieBreakScoreMsg", "TB Score");
        ReflectionTestUtils.setField(matchService, "gameScoreMsg", "Game Score");
        ReflectionTestUtils.setField(matchService, "setScoreMsg", "Set Score");
        ReflectionTestUtils.setField(matchService, "normalGameFormatter", "%-10s|%-10s|");
        ReflectionTestUtils.setField(matchService, "tieBreakFormatter", "%-10s|%-10s|%-10s|");
        ReflectionTestUtils.setField(matchService, "playerFormatter", "|%-10s|");
    }

    @Test
    public void displayScore_normalGame() {
        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");

        player1.getGameList().add(new NormalGame());
        player2.getGameList().add(new NormalGame());

        ScoreTable result = matchService.generateScoreTable(player1, player2);
        String expectedTitle = "|          |Game Score|Set Score |";
        String expectedScore1 = "|Player1   |0         |0         |";
        String expectedScore2 = "|Player2   |0         |0         |";
        assertEquals(expectedTitle, result.getTitle());
        assertEquals(expectedScore1, result.getPlayer1Score());
        assertEquals(expectedScore2, result.getPlayer2Score());
    }

    @Test
    public void displayScore_tieBreak() {
        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");

        player1.getGameList().add(new TieBreak());
        player2.getGameList().add(new TieBreak());

        ScoreTable result = matchService.generateScoreTable(player1, player2);
        String expectedTitle = "|          |Game Score|Set Score |TB Score  |";
        String expectedScore1 = "|Player1   |0         |0         |6         |";
        String expectedScore2 = "|Player2   |0         |0         |6         |";
        assertEquals(expectedTitle, result.getTitle());
        assertEquals(expectedScore1, result.getPlayer1Score());
        assertEquals(expectedScore2, result.getPlayer2Score());
    }
}
