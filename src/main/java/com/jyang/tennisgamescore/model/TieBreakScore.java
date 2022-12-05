package com.jyang.tennisgamescore.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TieBreakScore extends Score {
    private int gameScore;

    public TieBreakScore(int gameScore, int setScore) {
        super(setScore);
        this.gameScore = gameScore;
    }
}
