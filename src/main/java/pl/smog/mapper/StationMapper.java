package pl.smog.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.smog.dto.AirQualityIndexDto;
import pl.smog.dto.StationDto;
import pl.smog.entity.Station;

@Mapper(componentModel = "spring")
public interface StationMapper {

    @Mappings({
            @Mapping(target="idStation", source="stationDto.id"),
            @Mapping(target="stationName", source="stationDto.stationName"),
            @Mapping(target="stationLocation.addressStreet", source="stationDto.addressStreet"),
    })
    Station stationDtoToStation(StationDto stationDto);

    @Mappings({
            @Mapping(target="stationLocation.cityName", source="cityDto.name"),
            @Mapping(target="stationLocation.communeName", source="communeDto.communeName"),
            @Mapping(target="stationLocation.districtName", source="communeDto.districtName"),
            @Mapping(target="stationLocation.provinceName", source="communeDto.provinceName")
    })
    Station.StationLocation stationDtoToStationLocation(StationDto.CityDto cityDto, StationDto.CommuneDto communeDto);

    @Mappings({
            @Mapping(target="stationEmissions.IMP10", source="pm10IndexLevel.indexLevelName"),
            @Mapping(target="stationEmissions.IPM25", source="pm25IndexLevel.indexLevelName"),
            @Mapping(target="stationEmissions.ICO", source="coIndexLevel.indexLevelName"),
            @Mapping(target="stationEmissions.IC6H6", source="c6H6IndexLevel.indexLevelName"),
            @Mapping(target="stationEmissions.IO3", source="o3IndexLevel.indexLevelName"),
            @Mapping(target="stationEmissions.INO2", source="no2IndexLevel.indexLevelName"),
            @Mapping(target="stationEmissions.ISO2", source="so2IndexLevel.indexLevelName"),
            @Mapping(target="stationEmissions.IJP", source="stIndexLevel.indexLevelName")
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
