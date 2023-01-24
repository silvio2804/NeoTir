package com.example.neoproject.map.dtos.postoletto;

import com.example.neoproject.map.dtos.neonato.NeonatoSlimDto;
import com.example.neoproject.map.dtos.sensore.SensoreEcgDto;
import com.example.neoproject.map.dtos.sensore.SensoreTempDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PostilettoAll {

    @JsonProperty("neonatoes")
    private List<NeonatoSlimDto> neonatoes;
    @JsonProperty("sensoreecgs")
    private List<SensoreEcgDto> sensoreecgs;
    @JsonProperty("sensoretemps")
    private List<SensoreTempDto> sensoretemps;
}
