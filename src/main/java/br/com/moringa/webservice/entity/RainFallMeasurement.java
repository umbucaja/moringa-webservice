package br.com.moringa.webservice.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table( uniqueConstraints = { @UniqueConstraint( columnNames = { "date", "measurement_station_id" } ) } )
public class RainFallMeasurement implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1026095886203083485L;

	@Id
    @GeneratedValue(generator = "id", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "id")
    private Long id;

    private Date date;
    private String unit;
    private float value;

    @JsonBackReference
    @ManyToOne(targetEntity = MeasurementStation.class)
    private MeasurementStation measurementStation;
    
    public RainFallMeasurement() {

    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getValue() {
        return this.value;
    }

    public void setValue(float value) {
        this.value = value;
    }

	public MeasurementStation getMeasurementStation() {
		return measurementStation;
	}

	public void setMeasurementStation(MeasurementStation measurementStation) {
		this.measurementStation = measurementStation;
	}
}
