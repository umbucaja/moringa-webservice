package br.com.moringa.webservice.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.moringa.webservice.entity.RainFallMeasurement;

public interface RainFallMeasurementRepository extends CrudRepository<RainFallMeasurement, Long> {

	List<RainFallMeasurement> findAll();
	
}
