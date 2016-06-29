package br.com.moringa.webservice.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.moringa.webservice.domain.object.WaterSourceDomain;
import br.com.moringa.webservice.domain.object.WaterSourceMeasurementDomain;
import br.com.moringa.webservice.entity.WaterSource;
import br.com.moringa.webservice.entity.WaterSourceMeasurement;
import br.com.moringa.webservice.repository.WaterSourceMeasurementRepository;
import br.com.moringa.webservice.repository.WaterSourceRepository;

@Service
public class WaterSourceMeasurementService {

    @Autowired
    WaterSourceMeasurementRepository wsmRepository;

    @Autowired
    WaterSourceRepository wsRepository;

    public List<WaterSourceMeasurement> findAll(){
        return wsmRepository.findAll();
    }

    public List<WaterSourceMeasurement> findByWaterSourceId(Long id){
        return wsmRepository.findByWaterSourceId(id);
    }

    public List<WaterSourceMeasurement> findByDateBeforeAndWaterSourceId(Date end, Long id) {
        return wsmRepository.findByDateBeforeAndWaterSourceId(end, id);
    }

    public List<WaterSourceMeasurement> findByDateAfterAndWaterSourceId(Date start, Long id) {
        return wsmRepository.findByDateAfterAndWaterSourceId(start, id);
    }

    public List<WaterSourceMeasurement> findByDateBetweenAndWaterSourceId(Date start, Date end, Long id) {
        return wsmRepository.findByDateBetweenAndWaterSourceId(start, end, id);
    }

    public WaterSourceMeasurement findLastByWaterSourceId(Long id) {
        return wsmRepository.findFirstByWaterSourceIdOrderByDateDesc(id);
    }

    public void addMeasurements (List<WaterSourceDomain> wsListDomain){

        for (WaterSourceDomain waterSourceDomain : wsListDomain) {

            WaterSource ws = wsRepository.findByName(waterSourceDomain.getName());

            for(WaterSourceMeasurementDomain domain: waterSourceDomain.getMeasurementList()){

                WaterSourceMeasurement wsm = domain.toWaterSourceMeasurement();
                wsm.setWaterSource(ws);

                try {
                    wsmRepository.save(wsm);
                } catch (DataIntegrityViolationException dive) {
                    // TODO: handle exception
                }
            }
        }
    }

}
