package com.homeworkgoto.cardsgame.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@ToString
@EqualsAndHashCode
public class PlayerDto {
    private int id;
    @NotNull
    private int  gameId;
    private String name;
}
