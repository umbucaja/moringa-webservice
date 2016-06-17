package br.com.moringa.webservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.moringa.webservice.entity.WaterSource;
import br.com.moringa.webservice.repository.WaterSourceRepository;

@Service
public class WaterSourceService {

    @Autowired
    WaterSourceRepository WaterSourceRepository;
    
    public List<WaterSource> findAll(){
    	
    	return WaterSourceRepository.findAll();
    }
	
}
