package com.example.neoproject.map.dtos.sensore;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SensoreEcgPostDto {

    @JsonProperty("modello")
    private String modello;
    @JsonProperty("idpostoletto")
    private Integer idletto;

}
