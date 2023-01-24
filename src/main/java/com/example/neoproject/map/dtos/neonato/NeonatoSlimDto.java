package com.example.neoproject.map.dtos.neonato;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NeonatoSlimDto {
    @JsonProperty
    private Integer id;
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("cognome")
    private String cognome;
}
