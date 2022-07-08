package com.homeworkgoto.cardsgame.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CreatePlayerRequest {
    private Integer gameId;
    private String name;
}
