package pl.smog.service;

import org.springframework.stereotype.Service;
import pl.smog.entity.Emission;
import pl.smog.entity.Station;

import java.util.List;

@Service
public interface ISmogService {

    List<Station> getAllStations();
    Station getStationById(Long id);
    Emission getEmissionsById(Long id);
    void seedDataFromApi();

}
