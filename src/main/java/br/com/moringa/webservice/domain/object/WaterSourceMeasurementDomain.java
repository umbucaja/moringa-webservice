package br.com.moringa.webservice.domain.object;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.moringa.webservice.entity.WaterSourceMeasurement;

public class WaterSourceMeasurementDomain {
   
	private Long id;
    private float value;
    private Date date;
//    private WaterSourceDomain waterSource;
    
	public WaterSourceMeasurementDomain() {
	}
	
	public WaterSourceMeasurement toWaterSourceMeasurement(){
		WaterSourceMeasurement wsm = new WaterSourceMeasurement();
//		if(null != waterSource){
//			WaterSource ws = this.waterSource.toWaterSource();
//			wsm.setWaterSource(ws);	
//		}
		wsm.setId(this.id);
		wsm.setValue(this.value);
		wsm.setDate(this.date);
		
		return wsm;
	}
	
	public WaterSourceMeasurementDomain(WaterSourceMeasurement ws){
		this.id = ws.getId();
		this.value = ws.getValue();
		this.date = ws.getDate();
//		if(null != ws.getWaterSource()){
//			WaterSourceDomain wsd = new WaterSourceDomain(ws.getWaterSource());
//			this.waterSource = wsd;
//		}
	}

	public static List<WaterSourceMeasurementDomain> toWaterSourceMeasurementDomain(List<WaterSourceMeasurement> measurements){
		
		List<WaterSourceMeasurementDomain> domains = new ArrayList<>();
		
		for (WaterSourceMeasurement measurement : measurements) {
			WaterSourceMeasurementDomain domain = new WaterSourceMeasurementDomain(measurement);
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
	
//	public WaterSourceDomain getWaterSource() {
//		return waterSource;
//	}
//
//	public void setWaterSource(WaterSourceDomain waterSource) {
//		this.waterSource = waterSource;
//	}
	
}
