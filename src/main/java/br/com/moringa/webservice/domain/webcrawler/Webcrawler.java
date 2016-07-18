package br.com.moringa.webservice.domain.webcrawler;

import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import br.com.moringa.webservice.domain.object.Station;
import br.com.moringa.webservice.domain.object.WaterSourceDomain;
import br.com.moringa.webservice.service.RainFallMeasurementService;
import br.com.moringa.webservice.service.WaterSourceMeasurementService;
import br.com.moringa.webservice.util.Parser;

/**
 * Created by Thiago Almeida on 20/06/2016.
 */
@Configuration
@EnableAsync
@EnableScheduling
public class Webcrawler {

	@Autowired
	WaterSourceMeasurementService wsmService;
	
    @Autowired
    RainFallMeasurementService rfmService;
	
    @Scheduled(cron="0 15 11 * * *")
    public void getObservations() throws ParseException{
        String url = "http://site2.aesa.pb.gov.br/aesa/volumesAcudes.do?metodo=preparaUltimosVolumesPorAcude2";
        List<WaterSourceDomain> list = Parser.getMeasurements(url);

        wsmService.addMeasurements(list);

    }

    @Scheduled(cron="0 15 11 * * *")
    public void getRainfall(){
        String url1 = "http://site2.aesa.pb.gov.br/aesa/monitoramentoPluviometria.do?metodo=listarChuvasDiarias&dia=1";
        String url2 = "http://site2.aesa.pb.gov.br/aesa/monitoramentoPluviometria.do?metodo=listarChuvasDiarias&dia=11";
        String url3 = "http://site2.aesa.pb.gov.br/aesa/monitoramentoPluviometria.do?metodo=listarChuvasDiarias&dia=21";
        List<Station> result = new LinkedList<Station>();
        try {
            result.addAll(Parser.getRains(url1));
            result.addAll(Parser.getRains(url2));
            result.addAll(Parser.getRains(url3));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        rfmService.addRainFalls(result);
        //TODO: Salvar no banco.
    }
}
