package com.homeworkgoto.cardsgame.service;

import com.homeworkgoto.cardsgame.model.GameDto;
import com.homeworkgoto.cardsgame.model.PlayerDto;

import java.util.List;
import java.util.Optional;

public interface GameService {

    GameDto createGame(GameDto gameDto);

    PlayerDto addPlayer(PlayerDto playerDto);

    void removePlayer(int playerId);

    List<PlayerDto> getGamePlayers(int gameId);

    List<GameDto> getGames();

    Optional<GameDto> getGameById(int id);

}