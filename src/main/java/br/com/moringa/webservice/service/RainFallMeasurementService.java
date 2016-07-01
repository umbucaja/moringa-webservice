package br.com.moringa.webservice.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.moringa.webservice.domain.object.RainFallMeasurementDomain;
import br.com.moringa.webservice.domain.object.Station;
import br.com.moringa.webservice.entity.MeasurementStation;
import br.com.moringa.webservice.entity.RainFallMeasurement;
import br.com.moringa.webservice.repository.MeasurementStationRepository;
import br.com.moringa.webservice.repository.RainFallMeasurementRepository;

@Service
public class RainFallMeasurementService {

    @Autowired
    RainFallMeasurementRepository rfmRepository;
    @Autowired
    MeasurementStationRepository msRepository;
    
    private static final Logger LOG = LoggerFactory.getLogger(RainFallMeasurementService.class);
    
    public List<RainFallMeasurementDomain> findAll(){
    	
        List<RainFallMeasurement> rains = rfmRepository.findAll();
        List<RainFallMeasurementDomain> domainList = RainFallMeasurementDomain.toRainFallMeasurementDomain(rains);
    	
    	return domainList;
    }
    
    public void addRainFalls (List<Station> stationList){

        for (Station station : stationList) {

        	if(station.getName().contains("/")){
        		String[] temp = station.getName().split("/");
        		station.setName(temp[temp.length - 1]);
        	}
        			
        			
        	MeasurementStation ms = msRepository.findByName(station.getName());
        	
        	if(null == ms){
        		LOG.info("********************************");
        		LOG.info("Station :" + station.getName());
        		LOG.info("List of Measurements :" + station.getWsmDomainList().toString());
        		LOG.info("********************************");
        	}
        	
            for(RainFallMeasurementDomain domain: station.getWsmDomainList()){

            	RainFallMeasurement rfm = domain.toRainFallMeasurement();
            	rfm.setMeasurementStation(ms);
            	
                try {
                	rfmRepository.save(rfm);
                } catch (DataIntegrityViolationException dive) {
                    // TODO: handle exception
                }
            }
        }
    }
	
}
