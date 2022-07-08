package com.homeworkgoto.cardsgame.mapper;

import com.homeworkgoto.cardsgame.data.domain.DeckCard;
import com.homeworkgoto.cardsgame.model.CardDto;
import com.homeworkgoto.cardsgame.model.Rank;
import com.homeworkgoto.cardsgame.model.Suit;
import org.springframework.stereotype.Component;

@Component
public class CardMapper {

    public CardDto toCardDto(DeckCard deckCard) {
        return CardDto.builder().cardPosition(deckCard.getCardPosition())
                .rank(Rank.valueOf(deckCard.getRank()))
                .suit(Suit.valueOf(deckCard.getSuit())).build();
    }

    public DeckCard toCard(CardDto cardDto) {
        DeckCard deckCard = new DeckCard();
        deckCard.setSuit(cardDto.getSuit().getSuitValue());
        deckCard.setRank(cardDto.getRank().getRankValue());
        deckCard.setCardPosition(cardDto.getCardPosition());
        return deckCard;
    }
}
