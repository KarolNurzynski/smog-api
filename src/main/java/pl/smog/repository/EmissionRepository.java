package pl.smog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.smog.entity.Emission;

import java.util.List;

public interface EmissionRepository extends MongoRepository<Emission, Integer> {

    List<Emission> findAllByIdStation(int idStation);

}
