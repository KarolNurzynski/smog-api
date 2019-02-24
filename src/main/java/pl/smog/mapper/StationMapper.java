package pl.smog.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;
import pl.smog.dto.AirQualityIndexDto;
import pl.smog.dto.StationDto;
import pl.smog.entity.Station;

@Component
@Mapper(componentModel = "spring")
public interface StationMapper {

    @Mappings({
            @Mapping(target="idStation", source="stationDto.id"),
            @Mapping(target="id", ignore = true)
    })
    Station stationDtoToStation(StationDto stationDto);


    @Mapping(target="cityName", source="cityDto.name")
    Station.StationLocation stationDtoToStationLocation(StationDto.CityDto cityDto, StationDto.CommuneDto communeDto,
                                                        StationDto stationDto);

    @Mappings({
            @Mapping(target="ipm10", source="pm10IndexLevel.indexLevelName"),
            @Mapping(target="ipm25", source="pm25IndexLevel.indexLevelName"),
            @Mapping(target="ico", source="coIndexLevel.indexLevelName"),
            @Mapping(target="ic6h6", source="c6H6IndexLevel.indexLevelName"),
            @Mapping(target="io3", source="o3IndexLevel.indexLevelName"),
            @Mapping(target="ino2", source="no2IndexLevel.indexLevelName"),
            @Mapping(target="iso2", source="so2IndexLevel.indexLevelName"),
            @Mapping(target="ijp", source="stIndexLevel.indexLevelName")
    })
    Station.StationEmissions airQualityIndexDtoToStationEmissions(AirQualityIndexDto.Pm10IndexLevel pm10IndexLevel,
                                                                  AirQualityIndexDto.Pm25IndexLevel pm25IndexLevel,
                                                                  AirQualityIndexDto.CoIndexLevel coIndexLevel,
                                                                  AirQualityIndexDto.C6H6IndexLevel c6H6IndexLevel,
                                                                  AirQualityIndexDto.O3IndexLevel o3IndexLevel,
                                                                  AirQualityIndexDto.No2IndexLevel no2IndexLevel,
                                                                  AirQualityIndexDto.So2IndexLevel so2IndexLevel,
                                                                  AirQualityIndexDto.StIndexLevel stIndexLevel);


}
