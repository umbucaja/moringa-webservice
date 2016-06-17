package br.com.moringa.webservice.restAPI;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.moringa.webservice.entity.WaterSourceMeasurement;
import br.com.moringa.webservice.repository.WaterSourceMeasurementRepository;

/**
 * Created by Luiz Corrêa on 17/06/2016.
 */

@RestController
@RequestMapping("/measurements")
public class WaterSourceMeasurementCtrl {

    @Autowired
    WaterSourceMeasurementRepository wsmRepository;

    @RequestMapping(value="",method = RequestMethod.GET)
    public ResponseEntity<List<WaterSourceMeasurement>> findAll() {
        return new ResponseEntity<List<WaterSourceMeasurement>>(wsmRepository.findAll(), HttpStatus.OK);
    }

}
