package com.homeworkgoto.cardsgame.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
@EqualsAndHashCode
public class GameDto {
    private int id;
    private String name;
}
