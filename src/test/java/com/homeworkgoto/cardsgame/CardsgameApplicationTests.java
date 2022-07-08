package com.homeworkgoto.cardsgame;

import com.homeworkgoto.cardsgame.controller.DeckCardController;
import com.homeworkgoto.cardsgame.controller.GameController;
import com.homeworkgoto.cardsgame.controller.PlayerController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CardsgameApplicationTests {

	@Autowired
	DeckCardController deckCardController;

	@Autowired
	GameController gameController;

	@Autowired
	PlayerController playerController;

	@Test
	void contextLoads() {
		Assertions.assertThat(deckCardController).isNotNull();
		Assertions.assertThat(gameController).isNotNull();
		Assertions.assertThat(playerController).isNotNull();
	}

}
