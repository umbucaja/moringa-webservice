package br.com.moringa.webservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.moringa.webservice.domain.object.RainFallMeasurementDomain;
import br.com.moringa.webservice.domain.object.Station;
import br.com.moringa.webservice.entity.RainFallMeasurement;
import br.com.moringa.webservice.repository.RainFallMeasurementRepository;

@Service
public class RainFallMeasurementService {

    @Autowired
    RainFallMeasurementRepository rfmRepository;
    
    public List<RainFallMeasurementDomain> findAll(){
    	
        List<RainFallMeasurement> rains = rfmRepository.findAll();
        List<RainFallMeasurementDomain> domainList = RainFallMeasurementDomain.toRainFallMeasurementDomain(rains);
    	
    	return domainList;
    }
    
    public void addRainFalls (List<Station> stationList){

        for (Station station : stationList) {

            for(RainFallMeasurementDomain domain: station.getWsmDomainList()){

            	RainFallMeasurement rfm = domain.toRainFallMeasurement();

                try {
                	rfmRepository.save(rfm);
                } catch (DataIntegrityViolationException dive) {
                    // TODO: handle exception
                }
            }
        }
    }
	
}
