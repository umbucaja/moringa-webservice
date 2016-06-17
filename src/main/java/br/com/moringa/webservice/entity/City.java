package br.com.moringa.webservice.entity;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

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
    
    @ManyToMany(targetEntity = WaterSource.class)
    private List<WaterSource> waterSources;

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

    public List<WaterSource> getWaterSources() {
        return this.waterSources;
    }

    public void setWaterSources(List<WaterSource> waterSources) {
        this.waterSources = waterSources;
    }

    public long getPopulation() {
        return this.population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }
}
