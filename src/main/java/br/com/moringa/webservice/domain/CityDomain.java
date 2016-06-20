package br.com.moringa.webservice.domain;

import java.util.ArrayList;
import java.util.List;

import br.com.moringa.webservice.entity.City;

public class CityDomain {
	
    private Long id;
    private String name;
    private String state;
    private long population;
    
	public static List<CityDomain> toCityDomain(List<City> cities){
		
		List<CityDomain> domains = new ArrayList<>();
		
		for (City city : cities) {
			CityDomain domain = new CityDomain();
			domain.setId(city.getId());
			domain.setName(city.getName());
			domain.setPopulation(city.getPopulation());
			domain.setState(city.getState());
			
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
