package com.jyang.tennisgamescore.services.impl;

import com.jyang.tennisgamescore.TennisGameScoreApplication;
import com.jyang.tennisgamescore.model.*;
import com.jyang.tennisgamescore.services.MatchService;
import com.jyang.tennisgamescore.utils.ScoreCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MatchServiceImpl implements MatchService {
    private static final Logger LOG = LoggerFactory.getLogger(MatchServiceImpl.class);
    private static final String LINE_SEPARATOR=System.getProperty("line.separator");
    @Value("${title.tieBreak.score:TB Score}")
    private String tieBreakScoreMsg;

    @Value("${title.set.score:Set Score}")
    private String setScoreMsg;

    @Value("${title.game.score:Game Score}")
    private String gameScoreMsg;

    @Value("${formatter.normal.game: %-10s|%-10s|}")
    private String normalGameFormatter;

    @Value("${formatter.tieBreak:|%-10s|}")
    private String tieBreakFormatter;

    @Value("${formatter.player}")
    private String playerFormatter;

    @Autowired
    private ScoreCalculator scoreCalculator;

    @Override
    public boolean isMatchFinished(Player player1, Player player2) {

        // Tie-break
        if (player1.getGameList().size() == 13) {
            return true;
        }

        // 6 : 6
        if (player1.getCurrentGame().getCurrentScore().getSetScore() == 6 &&
                player2.getCurrentGame().getCurrentScore().getSetScore() == 6) {
            player1.getGameList().add(new TieBreak());
            player2.getGameList().add(new TieBreak());
            return false;
        }
        // 5 : 7
        if (player1.getGameList().size() == 12) {
            return true;
        }
        // 5 : 6
        if (player1.getGameList().size() == 11) {
            return false;
        }

        if (player1.getCurrentGame().getCurrentScore().getSetScore() == 6 ||
                player2.getCurrentGame().getCurrentScore().getSetScore() == 6) {
            return true;
        }
        player1.getGameList().add(new NormalGame(player1.getCurrentGame().getCurrentScore().getSetScore()));
        player2.getGameList().add(new NormalGame(player2.getCurrentGame().getCurrentScore().getSetScore()));
        return false;
    }

    @Override
    public Point updateScore(Player winner, Player loser) {
        Point point;
        if (winner.getCurrentGame() instanceof NormalGame winnerGame) {
            NormalGame loserGame = (NormalGame) loser.getCurrentGame();
            point = scoreCalculator.nextNormalScore(winnerGame.getCurrentScore(),
                    loserGame.getCurrentScore());
            winnerGame.getScoreList().add((NormalGameScore) point.getWinnerScore());
            loserGame.getScoreList().add((NormalGameScore) point.getLoserScore());
        } else {
            TieBreak winnerGame = (TieBreak) winner.getCurrentGame();
            TieBreak loserGame = (TieBreak) loser.getCurrentGame();
            point = scoreCalculator.nextTieBreakScore(winnerGame.getCurrentScore(),
                    loserGame.getCurrentScore());
            winnerGame.getScoreList().add((TieBreakScore) point.getWinnerScore());
            loserGame.getScoreList().add((TieBreakScore) point.getLoserScore());
        }
        return point;
    }

    @Override
    public ScoreTable generateScoreTable(Player player1, Player player2) {
        StringBuilder sbTitle = new StringBuilder();
        StringBuilder sbScore1 = new StringBuilder();
        StringBuilder sbScore2 = new StringBuilder();

        sbTitle.append(String.format(playerFormatter, ""));
        sbScore1.append(String.format(playerFormatter, player1.getName()));
        sbScore2.append(String.format(playerFormatter, player2.getName()));

        for (int i = 0; i < player1.getGameList().size(); i++) {
            Game game1 = player1.getGameList().get(i);
            Game game2 = player2.getGameList().get(i);
            if (game1 instanceof NormalGame) {
                for (int j = 0; j < ((NormalGame) game1).getScoreList().size(); j++) {
                    sbTitle.append(String.format(normalGameFormatter, gameScoreMsg, setScoreMsg));

                    NormalGameScore score1 = ((NormalGame) game1).getScoreList().get(j);
                    sbScore1.append(String.format(normalGameFormatter, score1.getGameScore().getValue(),
                            score1.getSetScore()));

                    NormalGameScore score2 = ((NormalGame) game2).getScoreList().get(j);
                    sbScore2.append(String.format(normalGameFormatter, score2.getGameScore().getValue(),
                            score2.getSetScore()));
                }
            } else {
                for (int j = 0; j < ((TieBreak) game1).getScoreList().size(); j++) {
                    sbTitle.append(String.format(tieBreakFormatter, gameScoreMsg, setScoreMsg,
                            tieBreakScoreMsg));

                    TieBreakScore score1 = ((TieBreak) game1).getScoreList().get(j);
                    sbScore1.append(String.format(tieBreakFormatter, 0, score1.getGameScore(),
                            score1.getSetScore()));

                    TieBreakScore score2 = ((TieBreak) game2).getScoreList().get(j);
                    sbScore2.append(String.format(tieBreakFormatter, 0, score2.getGameScore(),
                            score2.getSetScore()));
                }
            }
        }
        return new ScoreTable(sbTitle.toString(), sbScore1.toString(), sbScore2.toString());
    }

    @Override
    public void displayScore(ScoreTable scoreTable) {
        String result = LINE_SEPARATOR + scoreTable.getTitle() +
                LINE_SEPARATOR +
                scoreTable.getPlayer1Score() +
                LINE_SEPARATOR +
                scoreTable.getPlayer2Score();
        LOG.info(result);
    }
}
