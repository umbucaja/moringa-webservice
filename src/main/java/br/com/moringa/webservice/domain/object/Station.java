package br.com.moringa.webservice.domain.object;

import java.util.List;

/**
 * Created by Thiago Almeida on 20/06/2016.
 */
public class Station {
    String name;
    List<WaterSourceMeasurementDomain> values;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<WaterSourceMeasurementDomain> getValues() {
        return values;
    }

    public void setValues(List<WaterSourceMeasurementDomain> values) {
        this.values = values;
    }
}
