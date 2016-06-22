package br.com.moringa.webservice.domain.object;



import java.util.Date;

import br.com.moringa.webservice.entity.WaterSourceMeasurement;

public class WaterSourceMeasurementDomain {
   
	private Long id;
    private float value;
    private Date date;
    private Long waterSourceId;
    
	public WaterSourceMeasurementDomain() {
	}
	
	public WaterSourceMeasurement toWaterSourceMeasurement(){
		WaterSourceMeasurement ws = new WaterSourceMeasurement();
		ws.setId(this.id);
		ws.setValue(this.value);
		ws.setDate(this.date);
		return ws;
	}
	
	public WaterSourceMeasurementDomain toWaterSourceDomain(WaterSourceMeasurement ws){
		WaterSourceMeasurementDomain domain = new WaterSourceMeasurementDomain();
		return domain;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public Long getWaterSourceId() {
		return waterSourceId;
	}

	public void setWaterSourceId(Long waterSourceId) {
		this.waterSourceId = waterSourceId;
	}
	
}
