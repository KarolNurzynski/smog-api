package pl.smog.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AirQualityIndexDto {
    
    private int id;
    private StIndexLevel stIndexLevel;
    private So2IndexLevel so2IndexLevel;
    private No2IndexLevel no2IndexLevel;
    private CoIndexLevel coIndexLevel;
    private C6H6IndexLevel c6h6IndexLevel;
    private Pm10IndexLevel pm10IndexLevel;
    private Pm25IndexLevel pm25IndexLevel;
    private O3IndexLevel o3IndexLevel;

    @Data
    public static class StIndexLevel {
        private int id;
        private String indexLevelName;
    }

    @Data
    public static class No2IndexLevel {
        private int id;
        private String indexLevelName;
    }

    @Data
    public static class O3IndexLevel {
        private int id;
        private String indexLevelName;
    }

    @Data
    public static class So2IndexLevel {
        private int id;
        private String indexLevelName;
    }

    @Data
    public static class CoIndexLevel {
        private int id;
        private String indexLevelName;
    }

    @Data
    public static class C6H6IndexLevel {
        private int id;
        private String indexLevelName;
    }

    @Data
    public static class Pm10IndexLevel {
        private int id;
        private String indexLevelName;
    }

    @Data
    public static class Pm25IndexLevel {
        private int id;
        private String indexLevelName;
    }


}
