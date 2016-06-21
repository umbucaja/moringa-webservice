package br.com.moringa.webservice.domain.object;

import br.com.moringa.webservice.entity.City;
import br.com.moringa.webservice.entity.WaterSource;

public class WaterSourceDomain {

    private Long id;
    private String name;
    private String measurementUnit;
    private float capacity;
    private String type;
    private City city;
	
	private WaterSourceDomain(WaterSource ws) {
		this.id = ws.getId();
		this.name = ws.getName();
		this.measurementUnit = ws.getMeasurementUnit();
		this.capacity = ws.getCapacity();
		this.type = ws.getType();
		this.city = ws.getCity();
	}
	
	public WaterSource toWaterSource(WaterSourceDomain domain){
		WaterSource ws = new WaterSource();
		return ws;
	}
	


	
}
