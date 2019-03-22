package pl.smog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.smog.entity.TestEntity;

import java.util.List;

public interface TestRepository extends MongoRepository<TestEntity, Long> {

    public List<TestEntity> findAllById(Long id);


}
