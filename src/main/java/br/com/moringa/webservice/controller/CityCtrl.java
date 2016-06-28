package br.com.moringa.webservice.controller;

import java.util.Date;
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

import br.com.moringa.webservice.domain.object.CityDomain;
import br.com.moringa.webservice.domain.object.LitersPerPersonDomain;
import br.com.moringa.webservice.domain.object.WaterSourceDomain;
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
    public ResponseEntity<List<CityDomain>> listCities(@RequestParam(value="name", required=false) String name) {
        List<CityDomain> response;

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

        return new ResponseEntity<List<CityDomain>>(response,status);
    }

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public ResponseEntity<CityDomain> findById(@PathVariable("id") Long id) {

    	CityDomain city = cityService.findOne(id);
        HttpStatus status;

        if(city == null){
            status = HttpStatus.NOT_FOUND;
        }else{
            status = HttpStatus.OK;
        }

        return new ResponseEntity<CityDomain>(city,status);
    }

    @RequestMapping(value="/{id}/watersources",method = RequestMethod.GET)
    public ResponseEntity<Set<WaterSourceDomain>> findWaterSources(@PathVariable("id") Long id) {

        Set<WaterSourceDomain> response = cityService.findWaterSourcesByCityId(id);
        HttpStatus status;

        if(response == null || response.isEmpty()){
            status = HttpStatus.NOT_FOUND;
        }else{
            status = HttpStatus.OK;
        }

        return new ResponseEntity<Set<WaterSourceDomain>>(response,status);
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

    @RequestMapping(value="/{id}/water",method = RequestMethod.GET)
    public ResponseEntity<Date> findDateEndOfWater(@PathVariable("id") Long id) {

        Date response = cityService.getEndOfWater(id);
        HttpStatus status;

        if(response == null){
            status = HttpStatus.NOT_FOUND;
        }else{
            status = HttpStatus.OK;
        }

        return new ResponseEntity<Date>(response,status);
    }

    @RequestMapping(value="/{id}/reservoir/info",method = RequestMethod.GET)
    public ResponseEntity<List<Object>> waterSourceInfo(@PathVariable("id") Long id) {

        List<Object> response = cityService.getInfoWaterSources(id);
        HttpStatus status;

        if(response == null){
            status = HttpStatus.NOT_FOUND;
        }else{
            status = HttpStatus.OK;
        }

        return new ResponseEntity<List<Object>>(response,status);
    }


}
