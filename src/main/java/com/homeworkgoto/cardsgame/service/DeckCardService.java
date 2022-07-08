package com.homeworkgoto.cardsgame.service;

import com.homeworkgoto.cardsgame.model.Suit;

import java.util.Map;

public interface DeckCardService {

    void addDeck(Integer gameId);

    void shuffleCards(Integer gameId);

    void dealCards(Integer gameId, Integer playerId);

    Map<Suit, Integer> getUndealtCardsCound(Integer gameId);
}
