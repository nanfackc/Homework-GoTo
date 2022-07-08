package com.homeworkgoto.cardsgame.data.repository;

import com.homeworkgoto.cardsgame.data.domain.Game;
import com.homeworkgoto.cardsgame.data.domain.GamePlayer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GamePlayerRepository extends CrudRepository<GamePlayer, Integer> {

    List<GamePlayer> findByGameId(Integer gameId);

}
