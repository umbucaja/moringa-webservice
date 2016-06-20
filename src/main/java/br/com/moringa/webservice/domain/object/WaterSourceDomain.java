package br.com.moringa.webservice.domain.object;

import java.util.List;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.com.moringa.webservice.entity.City;
import br.com.moringa.webservice.entity.WaterSource;
import br.com.moringa.webservice.entity.WaterSourceMeasurement;

public class WaterSourceDomain {

    private Long id;
    private String name;
    private String measurementUnit;
    private float capacity;
    private String type;
    private City city;
	
	private WaterSourceDomain() {
	}
	
	public WaterSource toWaterSource(WaterSourceDomain domain){
		WaterSource ws = new WaterSource();
		return ws;
	}
	
	public WaterSourceDomain toWaterSourceDomain(WaterSource ws){
		WaterSourceDomain domain = new WaterSourceDomain();
		return domain;
	}


	
}
