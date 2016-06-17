package br.com.moringa.webservice.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.moringa.webservice.entity.WaterSourceMeasurement;

public interface WaterSourceMeasurementRepository extends CrudRepository<WaterSourceMeasurement, Long> {

	List<WaterSourceMeasurement> findAll();
	
}