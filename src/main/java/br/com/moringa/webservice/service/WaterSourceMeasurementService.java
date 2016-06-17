package br.com.moringa.webservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.moringa.webservice.entity.WaterSourceMeasurement;
import br.com.moringa.webservice.repository.WaterSourceMeasurementRepository;

@Service
public class WaterSourceMeasurementService {

    @Autowired
    WaterSourceMeasurementRepository WaterSourceMeasurementRepository;
    
    public List<WaterSourceMeasurement> findAll(){
    	
    	return WaterSourceMeasurementRepository.findAll();
    }
	
}
