package com.example.neoproject.map;

import com.example.neoproject.map.dtos.observation.ObservationPostDto;
import com.example.neoproject.model.Observationtemp;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface ObservationTempMapper {

    Observationtemp observationtempPostDtoToObservationtemp(ObservationPostDto observationPostDto);
    List<Observationtemp> observatiotempListPostDtoToObservationtemp(List <ObservationPostDto> observationPostDtoList);
}
