package com.homeworkgoto.cardsgame.data.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class DeckCard {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private Integer gameId;
  private Integer rank;
  private Integer suit;
  private Integer cardIndex;
  private Integer dealtToPlayer;
}
