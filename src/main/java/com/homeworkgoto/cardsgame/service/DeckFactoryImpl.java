package com.homeworkgoto.cardsgame.service;

import com.homeworkgoto.cardsgame.model.CardDto;
import com.homeworkgoto.cardsgame.model.Rank;
import com.homeworkgoto.cardsgame.model.Suit;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeckFactoryImpl implements DeckFactory {

    @Override
    public List<CardDto> createDeck() {
        final List<CardDto> cards = new ArrayList<>();
        int cardIndex = 1;
        for(final Suit suit : Suit.values()) {
            for(final Rank rank : Rank.values()) {
                cards.add(CardDto.builder()
                        .rank(rank)
                        .suit(suit)
                        .cardPosition(cardIndex++)
                        .build());
            }
        }
        return cards;
    }
}
