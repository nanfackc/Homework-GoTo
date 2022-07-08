package com.homeworkgoto.cardsgame.data.repository;

import com.homeworkgoto.cardsgame.data.domain.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<Game, Integer> {
}
