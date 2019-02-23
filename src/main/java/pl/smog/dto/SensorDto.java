package pl.smog.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SensorDto {

    private int id;
    private int stationId;
    private Parameter param;

    @Data
    public class Parameter {
        private String paramName;
        private String paramFormula;
        private String paramCode;
        private int idParam;
    }

}
