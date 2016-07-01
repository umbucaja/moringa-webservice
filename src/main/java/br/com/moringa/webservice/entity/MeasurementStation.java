package br.com.moringa.webservice.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class MeasurementStation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8318101548748473306L;

	@Id
    @GeneratedValue(generator = "id", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "id")
	private Long id;
	
	@JsonManagedReference
    @OneToMany(targetEntity = RainFallMeasurement.class, mappedBy = "measurementStation",cascade = {CascadeType.PERSIST})
    private List<RainFallMeasurement> rainFallMeasurements;

    @ManyToOne(targetEntity = City.class)
    private City city;
    
    private float latitude;
    private String name;
    private float longitude;

    public MeasurementStation() {

    }
    
    public City getCity() {
        return this.city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<RainFallMeasurement> getRainFallMeasurements() {
        return this.rainFallMeasurements;
    }

    public void setRainFallMeasurements(List<RainFallMeasurement> rainFallMeasurements) {
        this.rainFallMeasurements = rainFallMeasurements;
    }

    public float getLatitude() {
        return this.latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getLongitude() {
        return this.longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
}
