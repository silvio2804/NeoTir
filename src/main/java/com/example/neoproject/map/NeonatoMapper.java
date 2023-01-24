package com.example.neoproject.map;

import com.example.neoproject.map.dtos.neonato.NeonatoGetDto;
import com.example.neoproject.map.dtos.neonato.NeonatoPostDto;
import com.example.neoproject.map.dtos.neonato.NeonatoSlimDto;
import com.example.neoproject.model.Neonato;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface NeonatoMapper {

    Neonato neonatoPostDtoToNeonato(NeonatoPostDto neonatoPostDto);
    NeonatoGetDto neonatoGetDtoToNeonato(Neonato neonato);
    NeonatoSlimDto neonatoToNeonatoSlimDto(Neonato neonato);
}
