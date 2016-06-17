package br.com.moringa.webservice.controller;

import br.com.moringa.webservice.entity.Observacao;
import br.com.moringa.webservice.repository.ObservacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Thiago Almeida on 17/06/2016.
 */

@RestController
@RequestMapping("/")
public class ObservacaoCtrl {

    @Autowired
    ObservacaoRepository observacaoRepository;


    @RequestMapping(value="",method = RequestMethod.GET)
    public ResponseEntity<List<Observacao>> findAll() {
        return new ResponseEntity<List<Observacao>>(observacaoRepository.findAll(), HttpStatus.OK);
    }

}
