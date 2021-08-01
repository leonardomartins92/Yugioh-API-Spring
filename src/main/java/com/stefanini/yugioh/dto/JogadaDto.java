package com.stefanini.yugioh.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JogadaDto {

    private Long id;

    @JsonProperty("cardId")
    private Long cartaId;

    @JsonProperty("playerId")
    private Long jogadorId;

    private Long partidaId;
}