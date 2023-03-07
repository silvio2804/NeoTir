package com.example.neoproject.map.dtos.observation;

import com.example.neoproject.model.ObservationtempId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.time.Instant;

@Data
@ToString
public class ObservationPostDto {

    @JsonProperty("data_rilevazione")
    private Instant data_rilevazione;

    @JsonProperty("temperatura")
    private Integer temperatura;

    @JsonProperty("id")
    private ObservationtempId id;

    @JsonProperty("idSensore")
    private Integer idSensore;

}
