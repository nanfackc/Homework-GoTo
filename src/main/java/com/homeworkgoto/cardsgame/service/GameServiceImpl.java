package com.homeworkgoto.cardsgame.service;

import com.homeworkgoto.cardsgame.data.domain.Game;
import com.homeworkgoto.cardsgame.data.domain.GamePlayer;
import com.homeworkgoto.cardsgame.data.repository.GamePlayerRepository;
import com.homeworkgoto.cardsgame.data.repository.GameRepository;
import com.homeworkgoto.cardsgame.model.GameDto;
import com.homeworkgoto.cardsgame.model.PlayerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final GamePlayerRepository gamePlayerRepository;

    @Override
    public GameDto createGame(GameDto gameDto) {
        Game game = new Game();
        game.setName(gameDto.getName());

        game = gameRepository.save(game);
        return GameDto.builder().id(game.getId())
                .name(game.getName()).build();
    }

    @Override
    public PlayerDto addPlayer(PlayerDto playerDto) {

        GamePlayer gamePlayer = new GamePlayer();
        gamePlayer.setGameId(playerDto.getGameId());
        gamePlayer.setName(playerDto.getName());

        gamePlayer = gamePlayerRepository.save(gamePlayer);

        return PlayerDto.builder().gameId(gamePlayer.getGameId())
                .id(gamePlayer.getId())
                .name(gamePlayer.getName()).build();
    }

    @Override
    public void removePlayer(int playerId) {
        gamePlayerRepository.deleteById(playerId);
    }

    @Override
    public List<PlayerDto> getGamePlayers(int gameId) {
        return gamePlayerRepository.findByGameId(gameId).stream()
                .map(gamePlayer -> {
                    return PlayerDto.builder()
                        .gameId(gameId)
                        .name(gamePlayer.getName())
                        .id(gamePlayer.getId())
                        .build();

        }).collect(Collectors.toList());
    }

    @Override
    public List<GameDto> getGames() {
        return StreamSupport.stream(gameRepository.findAll().spliterator(), false).map(game -> {
            return GameDto.builder().id(game.getId())
           .name(game.getName())
           .build();
        }).collect(Collectors.toList());
    }

    @Override
    public Optional<GameDto> getGameById(int id) {
        return  gameRepository.findById(id).map(game -> {
            return GameDto.builder().id(game.getId())
                    .name(game.getName())
                    .build();
        });
    }
}
