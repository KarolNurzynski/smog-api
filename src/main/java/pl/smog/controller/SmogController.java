package pl.smog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.smog.entity.Emission;
import pl.smog.entity.Station;
import pl.smog.service.ISmogService;
import java.util.List;

@Controller
@RequestMapping(value = "/api")
public class SmogController {

    @Autowired
    private ISmogService iSmogService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Station> getAll() {

        return iSmogService.getAllStations();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Station get(@PathVariable("id") int id) {

        return iSmogService.getStationById(id);
    }

    @RequestMapping(value = "/emissions/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<Emission> getEmissions(@PathVariable("id") int id) {

        return iSmogService.getEmissionsByStationId(id);
    }

    @RequestMapping(value = "/seedDataFromApi", method = RequestMethod.GET)
    @ResponseBody
    public void seedDataFromApi() {
        iSmogService.seedDataFromApi();
    }

}
