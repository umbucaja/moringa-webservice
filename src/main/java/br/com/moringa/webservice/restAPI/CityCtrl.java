package br.com.moringa.webservice.restAPI;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<WaterSource>> findById(@PathVariable("id") Long id) {
    	
    	List<WaterSource> response = wsService.findByCityId(id);
    	HttpStatus status;
    	
    	if(response == null || response.isEmpty()){
    		status = HttpStatus.NOT_FOUND;
    	}else{
    		status = HttpStatus.OK;
    	}
    	
        return new ResponseEntity<List<WaterSource>>(response,status);
    }
    
    @RequestMapping(value="/{id}/watersources",method = RequestMethod.GET)
    public ResponseEntity<City> findWaterSources(@PathVariable("id") Long id) {
    	
    	City response = cityService.findById(id);
    	HttpStatus status;
    	
    	if(response == null){
    		status = HttpStatus.NOT_FOUND;
    	}else{
    		status = HttpStatus.OK;
    	}
    	
        return new ResponseEntity<City>(response,status);
    }

}
