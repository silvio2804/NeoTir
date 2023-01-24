package com.example.neoproject.map.dtos.postoletto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PostolettoDto {
    @JsonProperty("id")
    private Integer id;
}
