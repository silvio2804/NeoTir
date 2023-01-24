package com.example.neoproject.map.dtos.postoletto;

import com.example.neoproject.model.Observationecg;
import com.example.neoproject.model.Observationtemp;
import com.example.neoproject.model.Sensoreecg;
import com.example.neoproject.model.Sensoretemp;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data //paziente e stato dei singoli device
public class PostoLettoGetDto {

    @JsonProperty("observationecgList")
    private List<Sensoreecg> observationecgList;
    @JsonProperty("sensoretemps")
    private List<Sensoretemp> sensoretemps;
}
