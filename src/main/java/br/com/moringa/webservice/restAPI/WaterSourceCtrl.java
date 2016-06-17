package br.com.moringa.webservice.restAPI;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.moringa.webservice.entity.WaterSource;
import br.com.moringa.webservice.repository.WaterSourceRepository;

/**
 * Created by Luiz Corrêa on 17/06/2016.
 */

@RestController
@RequestMapping("/watersources")
public class WaterSourceCtrl {

    @Autowired
    WaterSourceRepository waterSourceRepository;


    @RequestMapping(value="",method = RequestMethod.GET)
    public ResponseEntity<List<WaterSource>> findAll() {
        return new ResponseEntity<List<WaterSource>>(waterSourceRepository.findAll(), HttpStatus.OK);
    }

}
