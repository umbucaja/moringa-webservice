package br.com.moringa.webservice.domain.object;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.moringa.webservice.entity.WaterSource;
import br.com.moringa.webservice.entity.WaterSourceMeasurement;

public class WaterSourceDomain {

    private Long id;
    private String name;
    private String measurementUnit;
    private float capacity;
    private String type;
    private CityDomain city;
    private Set<WaterSourceMeasurementDomain> measurementList;
	
	public WaterSourceDomain(){}
    
	public WaterSourceDomain(WaterSource ws) {
		this.id = ws.getId();
		this.name = ws.getName();
		this.measurementUnit = ws.getMeasurementUnit();
		this.capacity = ws.getCapacity();
		this.type = ws.getType();
	}
	
	public static Set<WaterSourceDomain> toWaterSource(Set<WaterSource> waterSources){
		Set<WaterSourceDomain> domains = new HashSet<WaterSourceDomain>();
		
		for (WaterSource ws : waterSources) {
			WaterSourceDomain domain = new WaterSourceDomain(ws);
			domains.add(domain);
		}
		
		return domains;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMeasurementUnit() {
		return measurementUnit;
	}

	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;
	}

	public float getCapacity() {
		return capacity;
	}

	public void setCapacity(float capacity) {
		this.capacity = capacity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public CityDomain getCity() {
		return city;
	}

	public void setCity(CityDomain city) {
		this.city = city;
	}
	
	public static Set<WaterSourceMeasurement> parseMeasurementList(Set<WaterSourceMeasurementDomain> domainList){
		
		Set<WaterSourceMeasurement> entity = new HashSet<WaterSourceMeasurement>();
		for (WaterSourceMeasurementDomain wsmDomain : domainList) {
			WaterSourceMeasurement ws = new WaterSourceMeasurement();
			ws.setValue(wsmDomain.getValue());
			ws.setDate(wsmDomain.getDate());
			entity.add(ws);
		}
		
		return entity;
	}
	
    public Set<WaterSourceMeasurementDomain> getMeasurementList() {
    	if(measurementList == null){
    		measurementList = new HashSet<WaterSourceMeasurementDomain>();
    	}
		return measurementList;
	}

	public void setMeasurementList(
			Set<WaterSourceMeasurementDomain> measurementList) {
		this.measurementList = measurementList;
	}
	


	
}
