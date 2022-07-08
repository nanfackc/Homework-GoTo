package com.homeworkgoto.cardsgame.controller;

import com.homeworkgoto.cardsgame.exception.GenericException;
import com.homeworkgoto.cardsgame.model.CreateGameRequest;
import com.homeworkgoto.cardsgame.model.GameDto;
import com.homeworkgoto.cardsgame.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "GameController", description = "Manages operations on Game")
@RestController
@RequestMapping("/v1/game")
@Slf4j
public class GameController {

    @Autowired
    GameService gameService;


    @GetMapping(value="/{id}")
    public GameDto getGameById(@PathVariable Integer id)  {
        Optional<GameDto> dept = gameService.getGameById(id);
        return dept.orElseThrow( () -> new GenericException("Game not found"));
    }

    @GetMapping
    public List<GameDto> getGames() {
        return gameService.getGames();
    }

    @Operation( description = "Create a game")
    @PostMapping
    public GameDto createGame(@RequestBody CreateGameRequest createGameRequest) {
        if(Strings.isEmpty(createGameRequest.getName())) {
            throw new GenericException("Game name is required");
        }
        return gameService.createGame(GameDto.builder().name(createGameRequest.getName()).build());
    }

    @Operation( description = "Delete Game by gameId")
    @DeleteMapping(value="/{gameId}")
    public ResponseEntity<?> deleteGame(@PathVariable Integer gameId) {
        gameService.deleteGame(gameId);
        return ResponseEntity.ok().build();
    }
}