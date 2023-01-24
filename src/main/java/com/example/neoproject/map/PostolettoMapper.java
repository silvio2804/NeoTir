package com.example.neoproject.map;

import com.example.neoproject.map.dtos.postoletto.PostilettoAll;
import com.example.neoproject.model.Postoletto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface PostolettoMapper {
    PostilettoAll postolettoToPostilettoAllDto(Postoletto postoletto);
    List<PostilettoAll> postilettoAllToPostilettoAllDto(List<Postoletto> postolettos);
}
