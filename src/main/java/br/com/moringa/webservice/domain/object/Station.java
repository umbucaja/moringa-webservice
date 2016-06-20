package br.com.moringa.webservice.domain.object;

import br.com.moringa.webservice.entity.WaterSourceMeasurement;

import java.util.List;

/**
 * Created by Thiago Almeida on 20/06/2016.
 */
public class Station {
    String name;
    List<WaterSourceMeasurement> values;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<WaterSourceMeasurement> getValues() {
        return values;
    }

    public void setValues(List<WaterSourceMeasurement> values) {
        this.values = values;
    }
}
