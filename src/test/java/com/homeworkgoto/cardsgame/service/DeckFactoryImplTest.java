package com.homeworkgoto.cardsgame.service;

import com.homeworkgoto.cardsgame.model.CardDto;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class DeckFactoryImplTest {
    @InjectMocks
    private DeckFactory deckFactory;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateDeck() {
        List<CardDto> cardList = deckFactory.createDeck();
        assertEquals(52, cardList.size());
    }
}
