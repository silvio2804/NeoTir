package com.example.neoproject.map.dtos.observation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DateDto {
    @JsonProperty("dataInizio")
    private String dataInizio;
    @JsonProperty("dataFine")
    private String dataFine;
}
