package com.homeworkgoto.cardsgame.service;

import com.homeworkgoto.cardsgame.model.CardDescriptor;
import com.homeworkgoto.cardsgame.model.PlayerDto;

import java.util.List;

public interface PlayerService {

    List<CardDescriptor> showPlayerHands(Integer playerId);

    PlayerDto addPlayer(PlayerDto playerDto);

    void removePlayer(int playerId);

    List<PlayerDto> getGamePlayers(int gameId);
}
