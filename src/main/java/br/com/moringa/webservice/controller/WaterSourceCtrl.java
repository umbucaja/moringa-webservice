package br.com.moringa.webservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.moringa.webservice.entity.WaterSource;
import br.com.moringa.webservice.service.WaterSourceService;

/**
 * Created by Luiz Corrêa on 17/06/2016.
 */

@RestController
@RequestMapping("/watersources")
public class WaterSourceCtrl {

    @Autowired
    WaterSourceService waterSourceService;


    @RequestMapping(value="",method = RequestMethod.GET)
    public ResponseEntity<List<WaterSource>> findAll() {
    	List<WaterSource> response = waterSourceService.findAll();
    	HttpStatus status;
    	
    	if(response == null || response.isEmpty()){
    		status = HttpStatus.NOT_FOUND;
    	}else{
    		status = HttpStatus.OK;
    	}
    	
        return new ResponseEntity<List<WaterSource>>(response,status);
    }

}
