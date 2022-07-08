package com.homeworkgoto.cardsgame.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class CardDescriptor {
    private Rank rank;
    private Suit suit;
}
