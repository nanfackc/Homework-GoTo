package com.homeworkgoto.cardsgame.controller;


import com.homeworkgoto.cardsgame.model.CardDescriptor;
import com.homeworkgoto.cardsgame.service.DeckCardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "CardDeckController", description = "Manages operations on Deck of cards")
@RestController
@RequestMapping("/v1/deckcard")
public class DeckCardController {

    @Autowired
    DeckCardService deckCardService;

    @Operation( description = "Add a deck based on GameId")
    @PostMapping(value="/{gameId}/deck")
    public ResponseEntity<?> createDeck(@PathVariable Integer gameId) {
        deckCardService.addDeck(gameId);
        return ResponseEntity.ok().build();
    }

    @Operation( description = "shuffle a game deck based on GameId")
    @PostMapping(value="/{gameId}/shuffleCards")
    public ResponseEntity<?> shuffleCards(@PathVariable Integer gameId) {
        deckCardService.shuffleCards(gameId);
        return ResponseEntity.ok().build();
    }

    @Operation( description = "DealCards to a PlayerId")
    @PostMapping(value="/{gameId}/dealCards/{playerId}")
    public ResponseEntity<?> dealCards(@PathVariable Integer gameId, @PathVariable Integer playerId) {
        deckCardService.dealCards(gameId, playerId);
        return ResponseEntity.ok().build();
    }

    @Operation( description = "Get the count of how many cards per suit are left undealt in the game deck")
    @PostMapping(value="/{gameId}/undealtCardsCount")
    public ResponseEntity<?> undealtCardsCount(@PathVariable Integer gameId) {
        deckCardService.getUndealtCardsCound(gameId);
        return ResponseEntity.ok().body(deckCardService.getUndealtCardsCound(gameId));
    }

    @Operation( description = "Show the all the Undealt cards")
    @PostMapping(value="/{gameId}/showUndealtCard")
    public List<CardDescriptor> showUndealtCards(@PathVariable Integer gameId) {
        return deckCardService.showUndealtCard(gameId);
    }
}

