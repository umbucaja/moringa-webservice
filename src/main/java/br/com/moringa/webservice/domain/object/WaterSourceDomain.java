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
    private List<WaterSourceMeasurementDomain> waterSourceMeasurements;
	
	public WaterSourceDomain(){}
    
	public WaterSourceDomain(WaterSource ws) {
		this.id = ws.getId();
		this.name = ws.getName();
		this.measurementUnit = ws.getMeasurementUnit();
		this.capacity = ws.getCapacity();
		this.type = ws.getType();
		this.city = new CityDomain(ws.getCity());
		this.waterSourceMeasurements = WaterSourceMeasurementDomain.toWaterSourceMeasurementDomain(ws.getWaterSourceMeasurements());
		
	}
	
	public static Set<WaterSourceDomain> toWaterSourceDomainSet(Set<WaterSource> waterSources){
		Set<WaterSourceDomain> domains = new HashSet<WaterSourceDomain>();
		
		for (WaterSource ws : waterSources) {
			WaterSourceDomain domain = new WaterSourceDomain(ws);
			domains.add(domain);
		}
		
		return domains;
	}
	
	public WaterSource toWaterSource(){
		
		WaterSource ws = new WaterSource(); 
		
		ws.setId(this.id);
		ws.setName(this.name);
		ws.setMeasurementUnit(this.measurementUnit);
		ws.setCapacity(this.capacity);
		ws.setType(this.type);
		
		return ws;
	}
	
	public static List<WaterSourceMeasurement> parseMeasurementList(List<WaterSourceMeasurementDomain> domainList){
		
		List<WaterSourceMeasurement> entity = new ArrayList<WaterSourceMeasurement>();
		for (WaterSourceMeasurementDomain wsmDomain : domainList) {
			WaterSourceMeasurement ws = new WaterSourceMeasurement();
			ws.setValue(wsmDomain.getValue());
			ws.setDate(wsmDomain.getDate());
			ws.setId(wsmDomain.getId());
//			ws.setWaterSource(wsmDomain.getWaterSource().toWaterSource());
			entity.add(ws);
		}
		
		return entity;
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
	
    public List<WaterSourceMeasurementDomain> getWaterSourceMeasurements() {
    	if(waterSourceMeasurements == null){
    		waterSourceMeasurements = new ArrayList<WaterSourceMeasurementDomain>();
    	}
		return waterSourceMeasurements;
	}

	public void setWaterSourceMeasurements(
			List<WaterSourceMeasurementDomain> measurementList) {
		this.waterSourceMeasurements = measurementList;
	}
	


	
}
