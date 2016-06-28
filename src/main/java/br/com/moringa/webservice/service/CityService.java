package br.com.moringa.webservice.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.moringa.webservice.domain.object.CityDomain;
import br.com.moringa.webservice.domain.object.LitersPerPersonDomain;
import br.com.moringa.webservice.domain.object.WaterSourceDomain;
import br.com.moringa.webservice.entity.City;
import br.com.moringa.webservice.entity.WaterSource;
import br.com.moringa.webservice.entity.WaterSourceMeasurement;
import br.com.moringa.webservice.repository.CityRepository;

@Service
public class CityService {

    @Autowired
    CityRepository cityRepository;

    public List<CityDomain> findAll(){
        List<City> cities = cityRepository.findAll();
        List<CityDomain> domainList = CityDomain.toCityDomain(cities);
    	
    	return domainList;
    }

    public CityDomain findOne(Long id){
    	City city = cityRepository.findOne(id);
    	CityDomain domain = new CityDomain(city);
    	
        return domain;
    }

    public City findById(Long id){
    	City city = cityRepository.findOne(id);
        return city;
    }
    
    public List<CityDomain> findByName(String name) {
    	
        List<City> cities = cityRepository.findByName(name);
        List<CityDomain> domainList = CityDomain.toCityDomain(cities);
    	
    	return domainList;
    }

    public Set<WaterSourceDomain> findWaterSourcesByCityId(Long id){
        City city = cityRepository.findById(id);
        Set<WaterSourceDomain> domainList = WaterSourceDomain.toWaterSource(city.getWaterSources());        
        
        return domainList;
    }

    public List<City> findByWaterSourcesId(Long id){
        return cityRepository.findByWaterSourcesId(id);
    }

    public LitersPerPersonDomain findLitersPerPerson(Long id){

        City city = findById(id);
        List<CityDomain> cityDomainList = new ArrayList<>();
        LitersPerPersonDomain liters = new LitersPerPersonDomain();

        if(city != null){

            float amountOfLiters = 0;
            float amountOfPopulation = 0;

            liters.setCity(city.getName());
            liters.setLiters(0);
            for (WaterSource waterSource : city.getWaterSources()) {

                //Sum amountOfLiters of each water source;
                Hibernate.initialize(waterSource);

                List <WaterSourceMeasurement> wsm = waterSource.getWaterSourceMeasurements();

                if(wsm != null && !wsm.isEmpty()){

                    Collections.sort(wsm, (o1, o2) -> o1.getDate().compareTo(o2.getDate()));
                    amountOfLiters =+ wsm.get(wsm.size()-1).getValue();

                    List<City> cities = findByWaterSourcesId(waterSource.getId());
                    List<CityDomain> tempList = CityDomain.toCityDomain(cities);

                    for (CityDomain cityDomain : tempList) {
                        if(!cityDomainList.contains(cityDomain)){
                            cityDomainList.add(cityDomain);
                            amountOfPopulation =+ cityDomain.getPopulation();
                        }
                    }
                }
            }

            //Calc the return
            if(amountOfPopulation != 0){
                liters.setLiters(amountOfLiters/amountOfPopulation);
            }


        }

        return liters;
    }


    public Date getEndOfWater(Long cityId){
        City city =  cityRepository.findOne(cityId);
        return getEndWater(city);
    }

    private Date getEndWater(City city) {
        float median = 0.136f;
        float cm = amountLitersByCity(city);
        float persons = amountPersonsByCity(city);
        long days = (long) (((cm/median)/persons)*1000*3600*24);
        Date result = new Date(new Date().getTime()+days);
        return result;
    }
    public List<Object> getInfoWaterSources(Long id){
        City city = cityRepository.findOne(id);
        List<Object> result = null;
        if(city != null){
            result = new LinkedList();
            for (WaterSource waterSource : city.getWaterSources()) {
                Hibernate.initialize(waterSource);
                Map<Object,Object> source = new HashMap<>();
                List <WaterSourceMeasurement> wsm = waterSource.getWaterSourceMeasurements();
                if(wsm != null && !wsm.isEmpty()){
                    Collections.sort(wsm, (o1, o2) -> o1.getDate().compareTo(o2.getDate()));
                    WaterSourceMeasurement w = wsm.get(wsm.size()-1);
                    source.put("name",waterSource.getName());
                    source.put("capacity",waterSource.getCapacity());
                    source.put("observed",w.getValue());
                    source.put("date",w.getDate());
                    source.put("percent",(w.getValue()/waterSource.getCapacity()));
                    result.add(source);
                }
            }
        }
        return result;
    }

    private float amountLitersByCity(City city){
        float amountOfLiters = 0;
        if(city != null){
            for (WaterSource waterSource : city.getWaterSources()) {
                Hibernate.initialize(waterSource);
                List <WaterSourceMeasurement> wsm = waterSource.getWaterSourceMeasurements();
                if(wsm != null && !wsm.isEmpty()){
                    Collections.sort(wsm, (o1, o2) -> o1.getDate().compareTo(o2.getDate()));
                    amountOfLiters =+ wsm.get(wsm.size()-1).getValue();
                }
            }
        }
        return  amountOfLiters;
    }

    private long amountPersonsByCity(City city){
        long amountOfPopulation = 0;
        if(city != null){
            List<CityDomain> cityDomainList = new ArrayList<>();
            for (WaterSource waterSource : city.getWaterSources()) {
                //Sum amountOfLiters of each water source;
                Hibernate.initialize(waterSource);
                List <WaterSourceMeasurement> wsm = waterSource.getWaterSourceMeasurements();

                if(wsm != null && !wsm.isEmpty()){
                    List<City> cities = findByWaterSourcesId(waterSource.getId());
                    List<CityDomain> tempList = CityDomain.toCityDomain(cities);

                    for (CityDomain cityDomain : tempList) {
                        if(!cityDomainList.contains(cityDomain)){
                            cityDomainList.add(cityDomain);
                            amountOfPopulation += cityDomain.getPopulation();
                        }
                    }
                }
            }
        }
        return amountOfPopulation;
    }



}
