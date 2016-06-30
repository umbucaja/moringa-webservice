package br.com.moringa.webservice.domain.object;

import java.util.List;

/**
 * Created by Thiago Almeida on 20/06/2016.
 */
public class Station {
    String name;
    List<RainFallMeasurementDomain> wsmDomainList;


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
