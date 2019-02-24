package pl.smog.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "station")
public class Station {

    @Id
    private ObjectId id;
    private int idStation;
    private String stationName;
    private float gegrLat;
    private float gegrLon;
    private StationLocation stationLocation;
    private StationEmissions stationEmissions;


    @Data
    public static class StationLocation {
        private String cityName;
        private String communeName;
        private String districtName;
        private String provinceName;
        private String addressStreet;
    }

    @Data
    public static class StationEmissions {

        private float pm10;
        private String ipm10 = "b/d";

        private float pm25;
        private String ipm25 = "b/d";

        private float co;
        private String ico = "b/d";

        private float c6h6;
        private String ic6h6 = "b/d";

        private float o3;
        private String io3 = "b/d";

        private float no2;
        private String ino2 = "b/d";

        private float so2;
        private String iso2 = "b/d";

        private String ijp = "b/d";
    }

}
