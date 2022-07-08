package com.homeworkgoto.cardsgame;

import com.homeworkgoto.cardsgame.data.domain.DeckCard;
import com.homeworkgoto.cardsgame.model.CardDto;
import com.homeworkgoto.cardsgame.model.Rank;
import com.homeworkgoto.cardsgame.model.Suit;

import java.util.ArrayList;
import java.util.List;

public class TestConfig {

    protected List<CardDto> createDeck() {
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

    protected DeckCard createCardDeck(int cardIndex, int rankValue, int suiteValue) {
        DeckCard deckCard = new DeckCard();
        deckCard.setGameId(1);
        deckCard.setCardPosition(cardIndex);
        deckCard.setRank(rankValue);
        deckCard.setSuit(suiteValue);
        return deckCard;
    }
}
