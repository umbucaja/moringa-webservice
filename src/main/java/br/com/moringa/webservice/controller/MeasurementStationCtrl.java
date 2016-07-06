package br.com.moringa.webservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.moringa.webservice.domain.object.RainFallMeasurementDomain;
import br.com.moringa.webservice.domain.object.Station;
import br.com.moringa.webservice.service.MeasurementStationService;

/**
 * Created by Luiz Corrêa on 17/06/2016.
 */

@RestController
@RequestMapping("/stations")
public class MeasurementStationCtrl {

    @Autowired
    MeasurementStationService msService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Station>> findAll() {

        List<Station> response = msService.findAll();
        HttpStatus status;

        if (response == null || response.isEmpty()) {
            status = HttpStatus.NOT_FOUND;
        } else {
            status = HttpStatus.OK;
        }

        return new ResponseEntity<List<Station>>(response, status);
    }

    @RequestMapping(value = "/{id}/measurements", method = RequestMethod.GET)
    public ResponseEntity<List<RainFallMeasurementDomain>> findStations(@PathVariable("id") Long id, @RequestParam(value = "lastMeasurements", required = false) Integer lastMeasurements) {

        if(lastMeasurements == null) lastMeasurements = 0;

        List<RainFallMeasurementDomain> response = msService.findMeasurementsByStationId(id,lastMeasurements);
        HttpStatus status;

        if (response == null || response.isEmpty()) {
            status = HttpStatus.NOT_FOUND;
        } else {
            status = HttpStatus.OK;
        }

        return new ResponseEntity<List<RainFallMeasurementDomain>>(response, status);
    }


}
