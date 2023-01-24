package com.example.neoproject.map.dtos.dashboard;

import com.example.neoproject.map.dtos.postoletto.PostoLettoGetDto;
import com.example.neoproject.map.dtos.postoletto.PostolettoDto;
import com.example.neoproject.map.dtos.widget.WidgetDto;
import com.example.neoproject.model.Widget;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class DashboardGetDto {

    @JsonProperty("idpostoletto")
    private PostolettoDto idpostoletto;
    @JsonProperty("widgets")
    private List <WidgetDto> widgets;

}
