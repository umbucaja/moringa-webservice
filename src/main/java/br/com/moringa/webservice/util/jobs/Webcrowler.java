package br.com.moringa.webservice.util.jobs;

import br.com.moringa.webservice.entity.Observacao;
import br.com.moringa.webservice.repository.ObservacaoRepository;
import br.com.moringa.webservice.util.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

/**
 * Created by Thiago Almeida on 20/06/2016.
 */
@Configuration
@EnableAsync
@EnableScheduling
public class Webcrowler {

//    @Autowired
//    ObservacaoRepository observacaoRepository;

    @Scheduled(fixedDelay=5000)
    public void getObservations(){
        String url = "http://site2.aesa.pb.gov.br/aesa/volumesAcudes.do?metodo=preparaUltimosVolumesPorAcude2";
        List<Observacao> list = Parser.getObservacao(url);
        //observacaoRepository.save(list);
    }

}
