package com.example.neoproject.map.dtos.reparto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RepartoPostDto {

    @JsonProperty("nomeReparto")
    private String nome;
    @JsonProperty("ospedale")
    private String ospedale;
    @JsonProperty("livello")
    private Integer livello;
}
