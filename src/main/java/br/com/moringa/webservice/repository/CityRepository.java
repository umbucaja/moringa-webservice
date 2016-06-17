package br.com.moringa.webservice.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.moringa.webservice.entity.City;

public interface CityRepository extends CrudRepository<City, Long> {

	List<City> findAll();
	
}
