package br.com.moringa.webservice.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.moringa.webservice.entity.WaterSource;
import br.com.moringa.webservice.entity.WaterSourceMeasurement;
import br.com.moringa.webservice.service.WaterSourceMeasurementService;
import br.com.moringa.webservice.service.WaterSourceService;
import br.com.moringa.webservice.util.Parser;
import br.com.moringa.webservice.util.Util;

/**
 * Created by Luiz Corrêa on 17/06/2016.
 */

@RestController
@RequestMapping("/watersources")
public class WaterSourceCtrl {

    @Autowired
    WaterSourceService waterSourceService;

    @Autowired
    WaterSourceMeasurementService waterSourceMeasurementService;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<WaterSource>> findAll() {
        List<WaterSource> response = waterSourceService.findAll();
        HttpStatus status;

        if (response == null || response.isEmpty()) {
            status = HttpStatus.NOT_FOUND;
        } else {
            status = HttpStatus.OK;
        }

        return new ResponseEntity<List<WaterSource>>(response, status);
    }


    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<WaterSource> findById(@PathVariable("id") Long id) {
        WaterSource response = waterSourceService.findById(id);
        HttpStatus status;

        if (response == null) {
            status = HttpStatus.NOT_FOUND;
        } else {
            status = HttpStatus.OK;
        }

        return new ResponseEntity<WaterSource>(response, status);
    }

    @RequestMapping(value = "{id}/measurements", method = RequestMethod.GET)
    public ResponseEntity<List<WaterSourceMeasurement>> listWatersourceMeasurements(
            @PathVariable("id") Long id,
            @RequestParam(value = "startDate", required = false) String startDateString,
            @RequestParam(value = "endDate", required = false) String endDateString, @RequestParam(value = "lastMeasurements", required = false) Integer lastMeasurements) {

        Date start = null;
        Date end = null;

        try {
            start = startDateString != null ? Parser.stringToDate("dd/MM/yyyy", startDateString) : null;
            end = endDateString != null ? Parser.stringToDate("dd/MM/yyyy", endDateString) : null;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        List<WaterSourceMeasurement> measurements = new ArrayList<WaterSourceMeasurement>();

        if (start == null) {
            if (end == null) {
                measurements.addAll(waterSourceMeasurementService.findByWaterSourceId(id));
            } else {
                measurements.addAll(waterSourceMeasurementService.findByDateBeforeAndWaterSourceId(end, id));
            }
        } else {
            if (end == null) {
                measurements.addAll(waterSourceMeasurementService.findByDateAfterAndWaterSourceId(start, id));
            } else {
                measurements.addAll(waterSourceMeasurementService.findByDateBetweenAndWaterSourceId(start, end, id));
            }
        }

        HttpStatus status;

        if (measurements.isEmpty()) {
            status = HttpStatus.NO_CONTENT;
        } else {
            if (lastMeasurements != null) {
                Collections.sort(measurements, (o1, o2) -> o1.getDate().compareTo(o2.getDate()));
                List<WaterSourceMeasurement> lastTen = measurements.stream().collect(Util.lastN(lastMeasurements));
                measurements = lastTen;
            }
            status = HttpStatus.OK;
        }

        return new ResponseEntity<List<WaterSourceMeasurement>>(measurements, status);
    }

    @RequestMapping(value = "{id}/measurements/last", method = RequestMethod.GET)
    public ResponseEntity<WaterSourceMeasurement> lastWatersourceMeasurement(
            @PathVariable("id") Long id) {

        WaterSourceMeasurement measurement = waterSourceMeasurementService.findLastByWaterSourceId(id);

        HttpStatus status;

        if (measurement == null) {
            status = HttpStatus.NOT_FOUND;
        } else {
            status = HttpStatus.OK;
        }

        return new ResponseEntity<WaterSourceMeasurement>(measurement, status);
    }

}
