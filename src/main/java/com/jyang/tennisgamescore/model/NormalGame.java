package com.jyang.tennisgamescore.model;

import com.jyang.tennisgamescore.model.enums.GameScore;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
public class NormalGame implements Game {
    private List<NormalGameScore> scoreList = new ArrayList<>();

    public NormalGame() {
        scoreList.add(new NormalGameScore(GameScore.ZERO, 0));
    }

    public NormalGame(int setScore) {
        scoreList.add(new NormalGameScore(GameScore.ZERO, setScore));
    }

    @Override
    public NormalGameScore getCurrentScore() {
        return scoreList.get(scoreList.size() - 1);
    }
}
