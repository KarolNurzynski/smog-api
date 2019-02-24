package pl.smog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.smog.dto.AirQualityIndexDto;
import pl.smog.dto.MeasuredDataDto;
import pl.smog.dto.SensorDto;
import pl.smog.dto.StationDto;
import pl.smog.entity.Emission;
import pl.smog.entity.Station;
import pl.smog.mapper.EmissionMapper;
import pl.smog.mapper.StationMapper;
import pl.smog.repository.EmissionRepository;
import pl.smog.repository.StationRepository;
import java.util.ArrayList;
import java.util.List;

@Service
public class SmogService implements ISmogService {

    @Autowired
    StationRepository stationRepository;

    @Autowired
    EmissionRepository emissionRepository;

    @Autowired
    EmissionMapper emissionMapper;

    @Autowired
    StationMapper stationMapper;

    @Autowired
    RestTemplate restTemplate;

    private static final String GIOS_API = "http://api.gios.gov.pl/pjp-api/rest";
    private static final String FIND_ALL = GIOS_API + "/station/findAll";
    private static final String GET_STATION_AQINDEX = GIOS_API + "/aqindex/getIndex/";
    private static final String GET_STATION_SENSORS = GIOS_API + "/station/sensors/";
    private static final String GET_SENSOR_MEASURES = GIOS_API + "/data/getData/";

    @Override
    public List<Station> getAllStations() {
        return stationRepository.findAll();
    }

    @Override
    public Station getStationById(Long id) {
        return stationRepository.findById(id).orElse(new Station());
    }

    @Override
    public Emission getEmissionsById(Long id) {
        return emissionRepository.findById(id).orElse(new Emission());
    }

    //This method is only for populating smog-api with data from external API
    // TODO: 23.02.19 scheduling method + unit tests + null checks in needed
    @Override
    public void seedDataFromApi() {

        List<Station> stationList = new ArrayList<>();

        //getting list of stations from API
        ResponseEntity<List<StationDto>> allStationsFromApi = restTemplate.exchange(FIND_ALL, HttpMethod.GET,
                                                null, new ParameterizedTypeReference<List<StationDto>>(){});
        for (StationDto stationDto : allStationsFromApi.getBody()) {

            AirQualityIndexDto aqindexFromApi = restTemplate.getForObject(
                    GET_STATION_AQINDEX + stationDto.getId(), AirQualityIndexDto.class);

            //pupulating Station with data from API
            Station station = stationMapper.stationDtoToStation(stationDto);
            Station.StationLocation stationLocation = stationMapper.stationDtoToStationLocation(stationDto.getCity(),
                                                                    stationDto.getCity().getCommune(), stationDto);
            Station.StationEmissions stationEmissions = stationMapper.
                                                airQualityIndexDtoToStationEmissions(aqindexFromApi.getPm10IndexLevel(),
                                                                                     aqindexFromApi.getPm25IndexLevel(),
                                                                                     aqindexFromApi.getCoIndexLevel(),
                                                                                     aqindexFromApi.getC6h6IndexLevel(),
                                                                                     aqindexFromApi.getO3IndexLevel(),
                                                                                     aqindexFromApi.getNo2IndexLevel(),
                                                                                     aqindexFromApi.getSo2IndexLevel(),
                                                                                     aqindexFromApi.getStIndexLevel());
            station.setStationLocation(stationLocation);
            station.setStationEmissions(stationEmissions);
            stationList.add(station);
            stationRepository.saveAll(stationList);

            //pupulating Emission (for each Station) with data from API
            Emission emission = emissionMapper.stationDtoToEmission(stationDto);

            ResponseEntity<List<SensorDto>> stationSensorsFromApi = restTemplate.
                                    exchange(GET_STATION_SENSORS + stationDto.getId(),
                                                HttpMethod.GET, null,
                                                new ParameterizedTypeReference<List<SensorDto>>(){});

            for (SensorDto sensorDto : stationSensorsFromApi.getBody()) {
                Emission.SensorEmissions sensorEmissionsElement = emissionMapper.sensorDtoToSensorEmissions(sensorDto.getParam());
                emission.addSensorEmissions(sensorEmissionsElement);
                List<Emission.Measure> measureList = sensorEmissionsElement.getValues();

                ResponseEntity<List<MeasuredDataDto>> sensorMeasuresFromApi = restTemplate.
                        exchange(GET_SENSOR_MEASURES + sensorDto.getId(),
                                HttpMethod.GET, null,
                                new ParameterizedTypeReference<List<MeasuredDataDto>>(){});

                //adding measures to a sensor
                for (MeasuredDataDto measuredDataDto : sensorMeasuresFromApi.getBody()) {
                    for (MeasuredDataDto.Measure measureDto : measuredDataDto.getValues()) {
                        Emission.Measure measure = emissionMapper.measuredDataDtoToMeasure(measureDto);
                        measureList.add(measure);
                    }
                }
            }
            emissionRepository.save(emission);
        }
    }
}


