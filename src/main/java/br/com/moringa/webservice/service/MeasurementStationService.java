package br.com.moringa.webservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.moringa.webservice.domain.object.RainFallMeasurementDomain;
import br.com.moringa.webservice.domain.object.Station;
import br.com.moringa.webservice.entity.MeasurementStation;
import br.com.moringa.webservice.entity.RainFallMeasurement;
import br.com.moringa.webservice.repository.MeasurementStationRepository;
import br.com.moringa.webservice.repository.RainFallMeasurementRepository;

@Service
public class MeasurementStationService {

    @Autowired
    MeasurementStationRepository msRepository;
    
    @Autowired
    RainFallMeasurementRepository rfmRepository;
    
    public List<Station> findAll(){
    	
    	List<MeasurementStation> ms = msRepository.findAll();
        List<Station> domainList = Station.toDomainList(ms); 
        
        return domainList;
    }
    
    public List<RainFallMeasurementDomain> findMeasurementsByStationId(Long id){
    	
    	List<RainFallMeasurement> rf = rfmRepository.findByMeasurementStationId(id);
        List<RainFallMeasurementDomain> domainList = RainFallMeasurementDomain.toDomainList(rf); 
        
        return domainList;
    }
	
}
