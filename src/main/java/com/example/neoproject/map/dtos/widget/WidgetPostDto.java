package com.example.neoproject.map.dtos.widget;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WidgetPostDto {

    @JsonProperty("dimensione")
    private Double dimensione;
    @JsonProperty("posizione")
    private Double posizione;
    @JsonProperty("testo")
    private String testo;

}
