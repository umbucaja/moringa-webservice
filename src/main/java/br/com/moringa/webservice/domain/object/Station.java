package br.com.moringa.webservice.domain.object;

import java.util.ArrayList;
import java.util.List;

import br.com.moringa.webservice.entity.MeasurementStation;

/**
 * Created by Thiago Almeida on 20/06/2016.
 */
public class Station {
	
	Long id;
    String name;
    float latitude;
    float longitude;
    
    List<RainFallMeasurementDomain> wsmDomainList;


	public static List<Station> toDomainList(List<MeasurementStation> stations){
		
		List<Station> domains = new ArrayList<>();
		
		for (MeasurementStation station : stations) {
			Station domain = new Station(station);
			domains.add(domain);
		}
		
		return domains;
	}
	
	public Station(){}
	
	public Station (MeasurementStation ms){
		this.id = ms.getId();
		this.name = ms.getName();
		this.latitude = ms.getLatitude();
		this.longitude = ms.getLongitude();
	}
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RainFallMeasurementDomain> getWsmDomainList() {
        return wsmDomainList;
    }

    public void setWsmDomainList(List<RainFallMeasurementDomain> values) {
        this.wsmDomainList = values;
    }
}
