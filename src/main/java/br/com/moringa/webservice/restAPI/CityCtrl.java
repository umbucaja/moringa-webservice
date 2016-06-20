package br.com.moringa.webservice.restAPI;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.moringa.webservice.domain.LitersPerPerson;
import br.com.moringa.webservice.entity.City;
import br.com.moringa.webservice.entity.WaterSource;
import br.com.moringa.webservice.entity.WaterSourceMeasurement;
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
    public ResponseEntity<List<City>> findAll() {
    	
    	List<City> response = cityService.findAll();
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
    public ResponseEntity<List<WaterSource>> findWaterSources(@PathVariable("id") Long id) {
    	
    	City city = cityService.findById(id);
    	List<WaterSource> response = null;
    	HttpStatus status;
    	
    	if(city == null || city.getWaterSources() == null || city.getWaterSources().isEmpty()){
    		status = HttpStatus.NOT_FOUND;
    	}else{
    		response = city.getWaterSources();
    		status = HttpStatus.OK;
    	}
    	
        return new ResponseEntity<List<WaterSource>>(response,status);
    }

    @RequestMapping(value="/{id}/liters",method = RequestMethod.GET)
    public ResponseEntity<LitersPerPerson> findLitersPerPerson(@PathVariable("id") Long id) {
    	
    	City city = cityService.findById(id);
    	LitersPerPerson response = new LitersPerPerson();
    	
    	for (WaterSource waterSource : city.getWaterSources()) {
//    		waterSource.get
		}
    	
    	HttpStatus status = HttpStatus.NOT_FOUND;
    	
    	
    	
    	
    	
        return new ResponseEntity<LitersPerPerson>(response,status);
    }
    
    
}
