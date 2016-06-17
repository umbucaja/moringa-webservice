package br.com.moringa.webservice.restAPI;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.moringa.webservice.entity.City;
import br.com.moringa.webservice.repository.CityRepository;
import br.com.moringa.webservice.service.CityService;

/**
 * Created by Luiz Corrêa on 17/06/2016.
 */

@RestController
@RequestMapping("/cities")
public class CityCtrl {

    @Autowired
    CityService cityService;
    
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

}
