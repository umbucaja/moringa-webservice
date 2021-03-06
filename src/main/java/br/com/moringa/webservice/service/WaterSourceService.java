package br.com.moringa.webservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.moringa.webservice.domain.object.WaterSourceDomain;
import br.com.moringa.webservice.entity.WaterSource;
import br.com.moringa.webservice.repository.WaterSourceRepository;

@Service
public class WaterSourceService {

    @Autowired
    WaterSourceRepository wsRepository;

    public List<WaterSource> findAll(){
        return wsRepository.findAll();
    }

    public List<WaterSource> findByCityId(Long id){
        return wsRepository.findByCityId(id);
    }

    public WaterSource findById(Long id){
        return wsRepository.findById(id);
    }

    public void addMeasurements (List<WaterSourceDomain> wsListDomain){

        List<WaterSource> wsList = new ArrayList<WaterSource>();

        for (WaterSourceDomain waterSourceDomain : wsListDomain) {

            WaterSource ws = wsRepository.findByName(waterSourceDomain.getName());
            if(null != ws){

                ws.getWaterSourceMeasurements().addAll(WaterSourceDomain.parseMeasurementList(waterSourceDomain.getWaterSourceMeasurements()));
                wsList.add(ws);
                wsRepository.save(ws);
            }
        }
    }

}
