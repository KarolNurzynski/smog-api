package pl.smog.service;

import org.springframework.stereotype.Service;
import pl.smog.entity.Emission;
import pl.smog.entity.Station;

import java.util.List;

@Service
public interface ISmogService {

    List<Station> getAllStations();
    Station getStationById(int id);
    List<Emission> getEmissionsByStationId(int id);
    void seedDataFromApi();

}
