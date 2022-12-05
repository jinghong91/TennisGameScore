package com.jyang.tennisgamescore.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Point {
    private Score winnerScore;
    private Score loserScore;
    private boolean gameFinished;

}
