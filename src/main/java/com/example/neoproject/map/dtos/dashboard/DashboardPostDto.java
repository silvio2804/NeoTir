package com.example.neoproject.map.dtos.dashboard;

import com.example.neoproject.map.dtos.widget.WidgetPostDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class DashboardPostDto {

    @JsonProperty("idpostoletto")
    private Integer idLetto;
    @JsonProperty("widgets")
    private List <WidgetPostDto> widgets;
}
