package br.com.moringa.webservice.domain.object;

import java.sql.Date;

import br.com.moringa.webservice.entity.WaterSourceMeasurement;

public class WaterSourceMeasurementDomain {
   
	private Long id;
    private float value;
    private Date date;
    
	private WaterSourceMeasurementDomain() {
	}
	
	public WaterSourceMeasurement toWaterSourceMeasurement(WaterSourceMeasurementDomain domain){
		WaterSourceMeasurement ws = new WaterSourceMeasurement();
		return ws;
	}
	
	public WaterSourceMeasurementDomain toWaterSourceDomain(WaterSourceMeasurement ws){
		WaterSourceMeasurementDomain domain = new WaterSourceMeasurementDomain();
		return domain;
	}
}
