package com.jyang.tennisgamescore.model;

import com.jyang.tennisgamescore.model.enums.GameScore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class NormalGameScore extends Score {
    private GameScore gameScore;

    public NormalGameScore(GameScore gameScore, int setScore) {
        super(setScore);
        this.gameScore = gameScore;
    }
}
