package br.com.moringa.webservice.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.moringa.webservice.entity.WaterSource;

public interface WaterSourceRepository extends CrudRepository<WaterSource, Long> {

	List<WaterSource> findAll();
	
}
