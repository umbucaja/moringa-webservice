package br.com.moringa.webservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.moringa.webservice.entity.RainFallMeasurement;
import br.com.moringa.webservice.repository.RainFallMeasurementRepository;

@Service
public class RainFallMeasurementService {

    @Autowired
    RainFallMeasurementRepository RainFallMeasurementRepository;
    
    public List<RainFallMeasurement> findAll(){
    	
    	return RainFallMeasurementRepository.findAll();
    }
	
}
