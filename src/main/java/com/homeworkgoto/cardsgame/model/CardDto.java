package com.homeworkgoto.cardsgame.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
@EqualsAndHashCode
public class CardDto implements Comparable<CardDto> {

    private final Rank rank;
    private final Suit suit;
    private final int cardPosition;


    @Override
    public int compareTo(final CardDto o) {
        final int rankComparison = Integer.compare(this.rank.getRankValue(), o.rank.getRankValue());
        return rankComparison != 0 ? rankComparison : Integer.compare(this.suit.getSuitValue(), o.suit.getSuitValue());
    }
}
