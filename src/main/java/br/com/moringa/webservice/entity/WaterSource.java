package br.com.moringa.webservice.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class WaterSource implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4097126042714711822L;

    @Id
    @GeneratedValue(generator = "id", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "id")
    private Long id;

    private String name;
    private String measurementUnit;
    private float capacity;
    private String type;

    @JsonBackReference
    @ManyToMany(targetEntity = City.class, mappedBy = "waterSources")
    private List<City> cities;

    @JsonManagedReference
    @ManyToOne(targetEntity = City.class)
    private City city;


    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER ,targetEntity = WaterSourceMeasurement.class, mappedBy = "waterSource",cascade = {CascadeType.PERSIST})
    private List<WaterSourceMeasurement> waterSourceMeasurements;

    public WaterSource() {

    }

    public List<City> getCities() {
        return this.cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public City getCity() {
        return this.city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<WaterSourceMeasurement> getWaterSourceMeasurements() {
        return this.waterSourceMeasurements;
    }

    public void setWaterSourceMeasurements(List<WaterSourceMeasurement> waterSourceMeasurements) {
        this.waterSourceMeasurements = waterSourceMeasurements;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMeasurementUnit() {
        return this.measurementUnit;
    }

    public void setMeasurementUnit(String measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    public float getCapacity() {
        return this.capacity;
    }

    public void setCapacity(float capacity) {
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
