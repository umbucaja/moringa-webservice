package br.com.moringa.webservice.domain.object;

import java.util.ArrayList;
import java.util.List;

import br.com.moringa.webservice.entity.City;

public class CityDomain {
	
    private Long id;
    private String name;
    private String state;
    private long population;
    
	public CityDomain (){
		
	}
	
	public CityDomain (City city){
		this.id = city.getId();
		this.name = city.getName();
		this.population = city.getPopulation();
		this.state = city.getState();
	}
    
	public static List<CityDomain> toCityDomain(List<City> cities){
		
		List<CityDomain> domains = new ArrayList<>();
		
		for (City city : cities) {
			CityDomain domain = new CityDomain(city);
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public long getPopulation() {
		return population;
	}
	public void setPopulation(long population) {
		this.population = population;
	}
    
    
    
}
