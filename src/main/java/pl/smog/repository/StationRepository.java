package pl.smog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.smog.entity.Station;

public interface StationRepository extends MongoRepository<Station, Integer> {

    Station findByIdStation(int idStation);

}
