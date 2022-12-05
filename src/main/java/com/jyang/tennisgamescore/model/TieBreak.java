package com.jyang.tennisgamescore.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
public class TieBreak implements Game {
    private List<TieBreakScore> scoreList = new ArrayList<>();

    public TieBreak() {
        scoreList.add(new TieBreakScore(0, 6));
    }

    @Override
    public TieBreakScore getCurrentScore() {
        return scoreList.get(scoreList.size() - 1);
    }

}
