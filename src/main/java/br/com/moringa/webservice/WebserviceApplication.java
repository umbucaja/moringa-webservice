package br.com.moringa.webservice;

import br.com.moringa.webservice.entity.Observacao;
import br.com.moringa.webservice.repository.ObservacaoRepository;
import br.com.moringa.webservice.util.Parser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class WebserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebserviceApplication.class, args);
	}
	@Bean
	public CommandLineRunner loadData(/*ObservacaoRepository observacaoRepository*/){
		String url = "http://site2.aesa.pb.gov.br/aesa/volumesAcudes.do?metodo=preparaUltimosVolumesPorAcude2";
		return (args) ->{
//			List<Observacao> list = Parser.getObservacao(url);
//			observacaoRepository.save(list);
		};
	}
}
