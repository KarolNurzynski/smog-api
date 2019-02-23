package pl.smog.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StationDto {

    private int id;
    private String stationName;
    private String addressStreet;
    private float gegrLat;
    private float gegrLon;
    private CityDto city;

    @Data
    public class CityDto {
        private String name;
        private CommuneDto commune;
    }

    @Data
    public class CommuneDto {
        private String communeName;
        private String districtName;
        private String provinceName;
    }

}
