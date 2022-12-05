package com.jyang.tennisgamescore.model.enums;

import lombok.Getter;

@Getter
public enum GameScore {
    ZERO("0", 0), FIFTEEN("15", 1), THIRTY("30", 2),
    FORTY("40", 3),
    DEUCE("DEUCE", 4), ADV("ADV", 5), WIN("0", 6);

    private final String value;
    private final int order;

    GameScore(String value, int order) {
        this.value = value;
        this.order = order;
    }
}
