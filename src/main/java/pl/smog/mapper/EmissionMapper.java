package pl.smog.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;
import pl.smog.dto.AirQualityIndexDto;
import pl.smog.dto.MeasuredDataDto;
import pl.smog.dto.SensorDto;
import pl.smog.dto.StationDto;
import pl.smog.entity.Emission;
import pl.smog.entity.Station;

@Component
@Mapper(componentModel = "spring")
public interface EmissionMapper {

    @Mapping(target="substanceCodename", source="parameter.paramCode",
            qualifiedByName = "toLowerCase")
    Emission.SensorEmissions sensorDtoToSensorEmissions(SensorDto.Parameter parameter);

    @Mappings({
            @Mapping(target="date", source="measure.date"),
            @Mapping(target="value", source="measure.value")
    })
    Emission.Measure measuredDataDtoToMeasure(MeasuredDataDto.Measure measure);

    @Named("toLowerCase")
    default String toLowerCase(String str) {
        return str.toLowerCase();
    }



}
