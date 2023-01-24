package com.example.neoproject.map.dtos.neonato;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class NeonatoGetDto {

    @JsonProperty
    private Integer id;
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("cognome")
    private String cognome;
    @JsonProperty("dataNa")
    private LocalDate dataNa;
    @JsonProperty("peso")
    private Double peso;
    @JsonProperty("etaGestazionale")
    private Integer etaGestazionale;
}
