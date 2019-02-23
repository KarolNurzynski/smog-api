package pl.smog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.smog.entity.Emission;

public interface EmissionRepository extends MongoRepository<Emission, Long> {
}
