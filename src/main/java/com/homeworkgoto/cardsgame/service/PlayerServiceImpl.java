package com.homeworkgoto.cardsgame.service;

import com.homeworkgoto.cardsgame.data.domain.GamePlayer;
import com.homeworkgoto.cardsgame.data.repository.DeckCardRepository;
import com.homeworkgoto.cardsgame.data.repository.GamePlayerRepository;
import com.homeworkgoto.cardsgame.model.CardDescriptor;
import com.homeworkgoto.cardsgame.model.PlayerDto;
import com.homeworkgoto.cardsgame.model.Rank;
import com.homeworkgoto.cardsgame.model.Suit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class PlayerServiceImpl implements PlayerService {

    private final DeckCardRepository deckCardRepository;
    private final GamePlayerRepository gamePlayerRepository;

    @Override
    public List<CardDescriptor> showPlayerHands(Integer playerId) {
        return deckCardRepository.findCardDeckByPlayer(playerId).stream().map(deckCard -> {
            return CardDescriptor.builder()
                    .rank(Rank.valueOf(deckCard.getRank()))
                    .suit(Suit.valueOf(deckCard.getSuit()))
                    .build();
        }).collect(Collectors.toList());
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
}
