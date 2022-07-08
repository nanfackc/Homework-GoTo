package com.homeworkgoto.cardsgame.model;

public enum Suit {
    DIAMONDS(1),
    CLUBS(2),
    HEARTS(3),
    SPADES(4);

    private final int suitValue;

    Suit(final int suitValue) {
        this.suitValue = suitValue;
    }

    public int getSuitValue() {
        return this.suitValue;
    }

    public static Suit valueOf(int rankValue) {
        for (Suit e : values()) {
            if (e.getSuitValue() == rankValue) {
                return e;
            }
        }
        return null;
    }
}
