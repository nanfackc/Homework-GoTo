package com.homeworkgoto.cardsgame.service;

import com.homeworkgoto.cardsgame.TestConfig;
import com.homeworkgoto.cardsgame.data.domain.DeckCard;
import com.homeworkgoto.cardsgame.data.repository.DeckCardRepository;
import com.homeworkgoto.cardsgame.mapper.CardMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeckCardServiceImplTest extends TestConfig {
    @InjectMocks
    private DeckCardServiceImpl gameDeckFactory;

    @Spy
    private DeckFactory deckFactory = new DeckFactoryImpl();

    @Mock
    private DeckCardRepository deckCardRepository;

    @Spy
    private CardMapper cardMapper = new CardMapper();

    @Test
    void testDealCards() {
        List<DeckCard> cards = deckFactory.createDeck().stream().map(cardDto -> {
            return createCardDeck(cardDto.getCardPosition(), cardDto.getRank().getRankValue(), cardDto.getSuit().getSuitValue());
        }).collect(Collectors.toList());

        when(deckCardRepository.findByGameIdAndDealtToPlayerIsNull(1)).thenReturn(cards);
        gameDeckFactory.dealCards(1,1);
    }
}
