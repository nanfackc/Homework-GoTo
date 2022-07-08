package com.homeworkgoto.cardsgame.controller;

import com.homeworkgoto.cardsgame.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.assertj.core.api.Assertions;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class IntegrationTests {

	public static final int GAME_ID = 1;
	@Autowired
	DeckCardController deckCardController;

	@Autowired
	GameController gameController;

	@Autowired
	PlayerController playerController;

	@Test
	public void testCreateGame() {
		CreateGameRequest createGameRequest = new CreateGameRequest();
		createGameRequest.setName("Demo Game");
		GameDto gameDto = gameController.createGame(createGameRequest);
		List<GameDto> gameDtoList = gameController.getGames();
		Assertions.assertThat(gameDtoList.contains(gameDto)).isEqualTo(true);
	}

	@Test
	public void testDeleteGame() {
		CreateGameRequest createGameRequest = new CreateGameRequest();
		createGameRequest.setName("Demo Game");
		GameDto gameDto = gameController.createGame(createGameRequest);
		gameController.deleteGame(gameDto.getId());
		List<GameDto> gameDtoList = gameController.getGames();
		Assertions.assertThat(gameDtoList.contains(gameDto)).isEqualTo(false);
	}

	@Test
	public void testAddDecktoGame() {
		ResponseEntity responseEntity = deckCardController.createDeck(GAME_ID);
		Assertions.assertThat( responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	public void testAddPlayertoGame() {
		playerController.addPlayer(playerRequest());
		List<PlayerDto> playerDtos = playerController.getPlayers(GAME_ID);
		Assertions.assertThat(playerDtos.size()).isEqualTo(1);
		Assertions.assertThat(playerDtos.get(0).getName()).isEqualTo("Charly");
	}

	@Test
	public void testShuffleCards() {
		deckCardController.createDeck(GAME_ID);
		List<CardDescriptor> cards = deckCardController.showUndealtCards(GAME_ID);
		deckCardController.shuffleCards(GAME_ID);
		List<CardDescriptor> shuffleCards = deckCardController.showUndealtCards(GAME_ID);
		int permutations = compareList(cards, shuffleCards).stream()
				.filter(integer -> integer == 0)
				.collect(Collectors.toList())
				.size();
		Assertions.assertThat(permutations > 10).isEqualTo(true);
	}

	private List<Integer> compareList(List<CardDescriptor> list1, List<CardDescriptor> list2) {
		int minLen = Math.min(list1.size(), list2.size());
		return IntStream.range(0, minLen)
				.map(i -> {
					return list1.get(i).equals(list2.get(i)) ? 1 : 0;
				})
				.boxed().collect(Collectors.toList());
	}


	@Test
	public void testDealCards() {
		deckCardController.createDeck(GAME_ID);
		PlayerDto playerDto = playerController.addPlayer(playerRequest());
		deckCardController.dealCards(GAME_ID, playerDto.getId());
		Assertions.assertThat(deckCardController.showUndealtCards(GAME_ID).size()).isEqualTo(51);
		Assertions.assertThat(playerController.showPlayerHands(playerDto.getId()).size()).isEqualTo(1);
	}

	private CreatePlayerRequest playerRequest() {
		CreatePlayerRequest createPlayerRequest = new CreatePlayerRequest();
		createPlayerRequest.setGameId(GAME_ID);
		createPlayerRequest.setName("Charly");
		return createPlayerRequest;
	}
}
