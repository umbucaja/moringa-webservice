package br.com.moringa.webservice.domain.object;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import br.com.moringa.webservice.entity.RainFallMeasurement;

public class RainFallMeasurementDomain {

    private Long id;
    private Date date;
    private String unit;
    private float value;
	
	public RainFallMeasurementDomain (){
		
	}
	
	public RainFallMeasurementDomain (RainFallMeasurement rain){
		this.id = rain.getId();
		this.date = rain.getDate();
		this.unit = rain.getUnit();
		this.value = rain.getValue();
	}
    
	public static List<RainFallMeasurementDomain> toRainFallMeasurementDomain(List<RainFallMeasurement> rains){
		
		List<RainFallMeasurementDomain> domains = new ArrayList<>();
		
		for (RainFallMeasurement rfm : rains) {
			RainFallMeasurementDomain domain = new RainFallMeasurementDomain(rfm);
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}
	
	
	
	
}
