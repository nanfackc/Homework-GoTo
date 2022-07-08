package com.homeworkgoto.cardsgame.service;

import com.homeworkgoto.cardsgame.model.CardDto;

import java.util.List;

public interface DeckFactory {
    List<CardDto> createDeck();
}
