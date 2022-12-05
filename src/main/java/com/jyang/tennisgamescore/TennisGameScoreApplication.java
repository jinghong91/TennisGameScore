package com.jyang.tennisgamescore;

import com.jyang.tennisgamescore.model.NormalGame;
import com.jyang.tennisgamescore.model.Player;
import com.jyang.tennisgamescore.model.Point;
import com.jyang.tennisgamescore.services.MatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class TennisGameScoreApplication implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(TennisGameScoreApplication.class);

    @Autowired
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        LOG.info("STARTING THE APPLICATION");
        SpringApplication.run(TennisGameScoreApplication.class, args);
        LOG.info("APPLICATION FINISHED");
    }


    @Override
    public void run(String... args) {

        MatchService matchService = applicationContext.getBean(MatchService.class);

        try (Scanner scanner = new Scanner(System.in)) {
            LOG.info("Please enter player1's name:");
            String name1 = scanner.nextLine();
            Player player1 = new Player(name1);
            player1.getGameList().add(new NormalGame());

            LOG.info("Please enter player2's name:");
            String name2 = scanner.nextLine();
            Player player2 = new Player(name2);
            player2.getGameList().add(new NormalGame());

            boolean isMatchFinished = false;
            while (!isMatchFinished) {
                Point point;
                LOG.info("Please enter winner number(1|2):");
                String line = scanner.nextLine();

                if ("1".equals(line)) {
                    point = matchService.updateScore(player1, player2);
                } else if ("2".equals(line)) {
                    point = matchService.updateScore(player2, player1);
                } else if ("Exit".equals(line)) {
                    break;
                } else {
                    LOG.info("Invalid input");
                    continue;
                }

                if (point.isGameFinished()) {
                    isMatchFinished = matchService.isMatchFinished(player1, player2);
                }

                LOG.info("Display score(Y|N):N");
                line = scanner.nextLine();
                if ("Y".equals(line) || "y".equals(line)) {
                    matchService.displayScore(matchService.generateScoreTable(player1, player2));
                }
            }

            matchService.displayScore(matchService.generateScoreTable(player1, player2));
        }
    }
}
