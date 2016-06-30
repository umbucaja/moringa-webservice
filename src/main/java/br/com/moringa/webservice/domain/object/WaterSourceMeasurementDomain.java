package br.com.moringa.webservice.domain.object;



import java.util.Date;

import br.com.moringa.webservice.entity.WaterSource;
import br.com.moringa.webservice.entity.WaterSourceMeasurement;

public class WaterSourceMeasurementDomain {
   
	private Long id;
    private float value;
    private Date date;
    private WaterSourceDomain waterSource;
    
	public WaterSourceMeasurementDomain() {
	}
	
	public WaterSourceMeasurement toWaterSourceMeasurement(){
		WaterSourceMeasurement wsm = new WaterSourceMeasurement();
		WaterSource ws = this.waterSource.toWaterSource();

		wsm.setId(this.id);
		wsm.setValue(this.value);
		wsm.setDate(this.date);
		wsm.setWaterSource(ws);	
		
		return wsm;
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
	
	public WaterSourceDomain getWaterSource() {
		return waterSource;
	}

	public void setWaterSource(WaterSourceDomain waterSource) {
		this.waterSource = waterSource;
	}
	
}
