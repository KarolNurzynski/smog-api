package pl.smog.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "emission")
public class Emission {

    @Id
    public ObjectId id;
    public int idStation;
    public List<SensorEmissions> sensorEmissions = new ArrayList<>();

    public void addSensorEmissions(SensorEmissions sensorEmissionsElement) {
        sensorEmissions.add(sensorEmissionsElement);
    }

    @Data
    public class SensorEmissions {
        public String substanceCodename;
        public List<Measure> values = new ArrayList<>();

        public void addMeasure(Measure measureElement) {
            values.add(measureElement);
        }
    }

    @Data
    public class Measure {
        public LocalDateTime date;
        public float value;
    }

}


