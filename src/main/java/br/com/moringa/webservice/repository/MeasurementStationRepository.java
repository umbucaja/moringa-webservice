package br.com.moringa.webservice.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.moringa.webservice.entity.MeasurementStation;

public interface MeasurementStationRepository extends CrudRepository<MeasurementStation, Long> {

	List<MeasurementStation> findAll();
	
}