package br.com.moringa.webservice.entity;


import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class City implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3672661699892394073L;

	@Id
    @GeneratedValue(generator = "id", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "id")
    private Long id;

    private String name;
    private String state;
    private long population;
    
    @JsonBackReference
    @ManyToMany(targetEntity = WaterSource.class)
    private Set<WaterSource> waterSources;

    public City() {

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

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Set<WaterSource> getWaterSources() {
        return this.waterSources;
    }

    public void setWaterSources(Set<WaterSource> waterSources) {
        this.waterSources = waterSources;
    }

    public long getPopulation() {
        return this.population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }
}
