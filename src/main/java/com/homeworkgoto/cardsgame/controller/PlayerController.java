package com.homeworkgoto.cardsgame.controller;

import com.homeworkgoto.cardsgame.exception.GenericException;
import com.homeworkgoto.cardsgame.model.CardDescriptor;
import com.homeworkgoto.cardsgame.model.CreatePlayerRequest;
import com.homeworkgoto.cardsgame.model.PlayerDto;
import com.homeworkgoto.cardsgame.service.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "PlayerController", description = "Manages operations on Players of cards")
@RestController
@RequestMapping("/v1/player")
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @Operation(description = "Add Player to the Game Deck")
    @PostMapping
    public PlayerDto addPlayer(@RequestBody CreatePlayerRequest playerRequest) {
        if(Strings.isEmpty(playerRequest.getName())) {
            throw new GenericException("PlayerName name is required");
        } else if(playerRequest.getGameId() == null) {
            throw new GenericException("GameId is required");
        }
        return playerService.addPlayer(PlayerDto.builder()
                .gameId(playerRequest.getGameId())
                .name(playerRequest.getName())
                .build());
    }

    @Operation(description = "List of all players based on gameId")
    @GetMapping(value="/{gameId}")
    public List<PlayerDto> getPlayers(@PathVariable Integer gameId)  {
        return playerService.getGamePlayers(gameId);
    }


    @Operation( description = "Remove player based on playerId")
    @DeleteMapping(value="/{playerId}")
    public ResponseEntity<?> removePlayer(@PathVariable Integer playerId) {
        playerService.removePlayer(playerId);
        return ResponseEntity.ok().build();
    }

    @Operation( description = "Show player cards")
    @GetMapping(value="/{playerId}/showhands")
    public List<CardDescriptor> showPlayerHands(@PathVariable Integer playerId) {
        return playerService.showPlayerHands(playerId);
    }

}
