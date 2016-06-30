package br.com.moringa.webservice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.moringa.webservice.entity.WaterSourceMeasurement;

public interface WaterSourceMeasurementRepository extends CrudRepository<WaterSourceMeasurement, Long> {

    @Override
    List<WaterSourceMeasurement> findAll();
    List<WaterSourceMeasurement> findByWaterSourceId(Long id);
    List<WaterSourceMeasurement> findByDateBeforeAndWaterSourceId(Date end, Long id);
    List<WaterSourceMeasurement> findByDateAfterAndWaterSourceId(Date start, Long id);
    List<WaterSourceMeasurement> findByDateBetweenAndWaterSourceId(Date start, Date end, Long id);
    WaterSourceMeasurement findFirstByWaterSourceIdOrderByDateDesc(Long id);

}