package br.com.moringa.webservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.moringa.webservice.domain.object.WaterSourceDomain;
import br.com.moringa.webservice.domain.object.WaterSourceMeasurementDomain;
import br.com.moringa.webservice.entity.WaterSource;
import br.com.moringa.webservice.entity.WaterSourceMeasurement;
import br.com.moringa.webservice.repository.WaterSourceMeasurementRepository;
import br.com.moringa.webservice.repository.WaterSourceRepository;

@Service
public class WaterSourceMeasurementService {

    @Autowired
    WaterSourceMeasurementRepository wsmRepository;
    
    @Autowired
    WaterSourceRepository wsRepository;
    
    public List<WaterSourceMeasurement> findAll(){
    	
    	return wsmRepository.findAll();
    }
	
    public void addMeasurements (List<WaterSourceDomain> wsListDomain){
    	
    	for (WaterSourceDomain waterSourceDomain : wsListDomain) {
    		
    		WaterSource ws = wsRepository.findByName(waterSourceDomain.getName());
    		
    		for(WaterSourceMeasurementDomain domain: waterSourceDomain.getMeasurementList()){
    			
    			WaterSourceMeasurement wsm = domain.toWaterSourceMeasurement();
    			wsm.setWaterSource(ws);
    			
    			wsmRepository.save(wsm);
    		}
		}
    }
    
}
