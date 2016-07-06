package br.com.moringa.webservice.service;

import java.util.Collections;
import java.util.List;

import br.com.moringa.webservice.util.Util;
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
    
    public List<RainFallMeasurementDomain> findMeasurementsByStationId(Long id,Integer last){

    	List<RainFallMeasurement> rf = rfmRepository.findByMeasurementStationId(id);
        Collections.sort(rf, (o1, o2) -> o1.getDate().compareTo(o2.getDate()));
        List<RainFallMeasurement> lastTen = rf.stream().collect(Util.lastN(last));
        List<RainFallMeasurementDomain> domainList = RainFallMeasurementDomain.toDomainList(lastTen);
        return domainList;
    }
	
}
