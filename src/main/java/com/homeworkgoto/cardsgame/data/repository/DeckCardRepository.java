package com.homeworkgoto.cardsgame.data.repository;

import com.homeworkgoto.cardsgame.data.domain.DeckCard;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeckCardRepository extends CrudRepository<DeckCard, Integer> {

    List<DeckCard> findByGameId(Integer gameId);

    List<DeckCard> findByGameIdAndDealtToPlayerIsNull(Integer gameId);

    @Query("select c from DeckCard c where c.dealtToPlayer =:playerId")
    public  List<DeckCard> findCardDeckByPlayer(@Param("playerId") Integer playerId);

}
