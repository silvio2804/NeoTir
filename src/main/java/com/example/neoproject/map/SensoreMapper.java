package com.example.neoproject.map;

import com.example.neoproject.map.dtos.sensore.SensoreEcgDto;
import com.example.neoproject.map.dtos.sensore.SensoreEcgPostDto;
import com.example.neoproject.map.dtos.sensore.SensoreTempDto;
import com.example.neoproject.map.dtos.sensore.SensoreTempPostDto;
import com.example.neoproject.model.Sensoreecg;
import com.example.neoproject.model.Sensoretemp;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface SensoreMapper {

    Sensoreecg sensoreecgPostDtoToSensoreecg(SensoreEcgPostDto sensoreEcgPostDto);
    Sensoretemp sensoretempPostDtoToSensoretemp(SensoreTempPostDto sensoreTempPostDto);

    SensoreTempDto sensoreTempToSensoreTempDto (Sensoretemp sensoretemp);
    SensoreEcgDto sensoreEcgToSensoreEcgDto (Sensoreecg sensoreecg);
}
