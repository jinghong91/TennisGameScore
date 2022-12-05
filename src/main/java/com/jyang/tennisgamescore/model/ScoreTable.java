package com.jyang.tennisgamescore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ScoreTable {
    private String title;
    private String player1Score;
    private String player2Score;

}
