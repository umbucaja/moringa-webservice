package br.com.moringa.webservice.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.moringa.webservice.domain.object.CityDomain;
import br.com.moringa.webservice.domain.object.LitersPerPersonDomain;
import br.com.moringa.webservice.entity.City;
import br.com.moringa.webservice.entity.WaterSource;
import br.com.moringa.webservice.entity.WaterSourceMeasurement;
import br.com.moringa.webservice.repository.CityRepository;

@Service
public class CityService {

    @Autowired
    CityRepository cityRepository;
    
    public List<City> findAll(){
    	return cityRepository.findAll();
    }
    
    public City findById(Long id){
    	return cityRepository.findOne(id);
    }
    
    public Set<WaterSource> findWaterSourcesByCityId(Long id){
    	City city = cityRepository.findById(id);
    	return city.getWaterSources();
    }
    
    public List<City> findByWaterSourcesId(Long id){
    	return cityRepository.findByWaterSourcesId(id);
    }
    
    public LitersPerPersonDomain findLitersPerPerson(Long id){
    	
    	City city = findById(id);
    	Map<Long,List> mapCities = new HashMap<>();
    	List<CityDomain> cityDomainList = new ArrayList<>();
    	LitersPerPersonDomain liters = new LitersPerPersonDomain();
    	
    	if(city != null){
    		    		
    		float amountOfLiters = 0;
    		float amountOfPopulation = 0;
    		
    		liters.setCity(city.getName());
    		
        	for (WaterSource waterSource : city.getWaterSources()) {
        		
        		//Sum amountOfLiters of each water source;
        		Hibernate.initialize(waterSource);
        		
        		List <WaterSourceMeasurement> wsm = waterSource.getWaterSourceMeasurements();
        		
        		if(wsm != null && !wsm.isEmpty()){
        			
            		Collections.sort(wsm, (o1, o2) -> o1.getDate().compareTo(o2.getDate()));
            		amountOfLiters =+ wsm.get(wsm.size()-1).getValue();
            		
            		List<City> cities = findByWaterSourcesId(waterSource.getId());
            		List<CityDomain> tempList = CityDomain.toCityDomain(cities);
            		
            		for (CityDomain cityDomain : tempList) {
            			if(!cityDomainList.contains(cityDomain)){
            				cityDomainList.add(cityDomain);
            				amountOfPopulation =+ cityDomain.getPopulation();
            			}
    				}
        		}
    		}
        	
        	//Calc the return
        	if(amountOfLiters != 0){
        		liters.setLiters(amountOfLiters/amountOfPopulation);
        	}else{
        		liters.setLiters(0);
        	}
        	
        	
    	}
    	
		return liters;
    }
    
	
}
