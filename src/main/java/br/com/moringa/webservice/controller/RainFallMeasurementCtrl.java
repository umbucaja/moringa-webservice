package br.com.moringa.webservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.moringa.webservice.entity.RainFallMeasurement;
import br.com.moringa.webservice.repository.RainFallMeasurementRepository;

/**
 * Created by Luiz Corrêa on 17/06/2016.
 */

@RestController
@RequestMapping("/rains")
public class RainFallMeasurementCtrl {

    @Autowired
    RainFallMeasurementRepository rfmRepository;

    @RequestMapping(value="",method = RequestMethod.GET)
    public ResponseEntity<List<RainFallMeasurement>> findAll() {
        return new ResponseEntity<List<RainFallMeasurement>>(rfmRepository.findAll(), HttpStatus.OK);
    }

}
