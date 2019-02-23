package pl.smog.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Id;

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
    public class StationLocation {
        private String cityName;
        private String communeName;
        private String districtName;
        private String provinceName;
        private String addressStreet;
    }

    @Data
    public class StationEmissions {

        private float pm10;
        @Column(name = "ipm10")
        private static final String IMP10 = "b/d";

        private float pm25;
        @Column(name = "ipm25")
        private static final String IPM25 = "b/d";

        private float co;
        @Column(name = "ico")
        private static final String ICO = "b/d";

        private float c6h6;
        @Column(name = "ic6h6")
        private static final String IC6H6 = "b/d";

        private float o3;
        @Column(name = "io3")
        private static final String IO3 = "b/d";

        private float no2;
        @Column(name = "ino2")
        private static final String INO2 = "b/d";

        private float so2;
        @Column(name = "iso2")
        private static final String ISO2 = "b/d";

        @Column(name = "ijp")
        private static final String IJP = "b/d";
    }

}
