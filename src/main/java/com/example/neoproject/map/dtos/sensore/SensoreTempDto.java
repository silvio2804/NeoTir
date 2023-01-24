package com.example.neoproject.map.dtos.sensore;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SensoreTempDto {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("modello")
    private String modello;
}
