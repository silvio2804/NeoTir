package com.example.neoproject.map.dtos.widget;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WidgetDto {

    @JsonProperty("dimensione")
    private Double dimensione;
    @JsonProperty("posizione")
    private Double posizione;
    @JsonProperty("testo")
    private String testo;
}
