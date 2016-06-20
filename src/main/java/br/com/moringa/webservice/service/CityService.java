package br.com.moringa.webservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.moringa.webservice.entity.City;
import br.com.moringa.webservice.repository.CityRepository;

@Service
public class CityService {

    @Autowired
    CityRepository cityRepository;
    
    public List<City> findAll(){
    	return cityRepository.findAll();
    }
    
    public City findById(Long id){
    	return cityRepository.findById(id);
    }
    
	
}
