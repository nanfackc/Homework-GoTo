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
    public boolean deleteGame(Integer gameId) {
        gameRepository.deleteById(gameId);
        return true;
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
