package com.homeworkgoto.cardsgame.data.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
  private Integer cardPosition;
  private Integer dealtToPlayer;
}
