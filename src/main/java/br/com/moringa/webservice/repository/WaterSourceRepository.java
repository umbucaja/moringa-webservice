package br.com.moringa.webservice.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import br.com.moringa.webservice.entity.WaterSource;

public interface WaterSourceRepository extends CrudRepository<WaterSource, Long> {

    @Override
    List<WaterSource> findAll();
    List<WaterSource> findByCityId(Long id);
    WaterSource findById(Long id);
    WaterSource findByName(String name);
    Set<WaterSource> findByCities_Id(Long id);
}
