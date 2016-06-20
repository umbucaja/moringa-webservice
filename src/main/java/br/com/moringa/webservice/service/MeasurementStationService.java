package br.com.moringa.webservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.moringa.webservice.entity.MeasurementStation;
import br.com.moringa.webservice.repository.MeasurementStationRepository;

@Service
public class MeasurementStationService {

    @Autowired
    MeasurementStationRepository measurementStation;
    
    public List<MeasurementStation> findAll(){
    	
    	return measurementStation.findAll();
    }
	
}
