package br.com.moringa.webservice.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.moringa.webservice.domain.object.LitersPerPersonDomain;
import br.com.moringa.webservice.entity.City;
import br.com.moringa.webservice.entity.WaterSource;
import br.com.moringa.webservice.service.CityService;
import br.com.moringa.webservice.service.WaterSourceService;

/**
 * Created by Luiz Corrêa on 17/06/2016.
 */

@RestController
@RequestMapping("/cities")
public class CityCtrl {

    @Autowired
    CityService cityService;
    @Autowired
    WaterSourceService wsService;

    @RequestMapping(value="",method = RequestMethod.GET)
    public ResponseEntity<List<City>> listCities(@RequestParam(value="name", required=false) String name) {
        List<City> response;

        if (name != null) {
            response = cityService.findByName(name);
        }
        else {
            response = cityService.findAll();
        }

        HttpStatus status;

        if(response == null || response.isEmpty()){
            status = HttpStatus.NOT_FOUND;
        }else{
            status = HttpStatus.OK;
        }

        return new ResponseEntity<List<City>>(response,status);
    }

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public ResponseEntity<City> findById(@PathVariable("id") Long id) {

        City city = cityService.findById(id);
        HttpStatus status;

        if(city == null){
            status = HttpStatus.NOT_FOUND;
        }else{
            status = HttpStatus.OK;
        }

        return new ResponseEntity<City>(city,status);
    }

    @RequestMapping(value="/{id}/watersources",method = RequestMethod.GET)
    public ResponseEntity<Set<WaterSource>> findWaterSources(@PathVariable("id") Long id) {

        Set<WaterSource> response = cityService.findWaterSourcesByCityId(id);
        HttpStatus status;

        if(response == null || response.isEmpty()){
            status = HttpStatus.NOT_FOUND;
        }else{
            status = HttpStatus.OK;
        }

        return new ResponseEntity<Set<WaterSource>>(response,status);
    }

    @RequestMapping(value="/{id}/liters",method = RequestMethod.GET)
    public ResponseEntity<LitersPerPersonDomain> findLitersPerPerson(@PathVariable("id") Long id) {

        LitersPerPersonDomain response = cityService.findLitersPerPerson(id);
        HttpStatus status;

        if(response == null){
            status = HttpStatus.NOT_FOUND;
        }else{
            status = HttpStatus.OK;
        }

        return new ResponseEntity<LitersPerPersonDomain>(response,status);
    }


}
