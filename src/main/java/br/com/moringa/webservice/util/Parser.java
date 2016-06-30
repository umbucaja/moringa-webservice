package br.com.moringa.webservice.util;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;

import br.com.moringa.webservice.domain.object.RainFallMeasurementDomain;
import br.com.moringa.webservice.domain.object.Station;
import br.com.moringa.webservice.domain.object.WaterSourceDomain;
import br.com.moringa.webservice.domain.object.WaterSourceMeasurementDomain;

/**
 * Created by Thiago Almeida on 16/06/2016.
 */
public class Parser {

    public static List<Station> getRains(String url) throws ParseException {
        List<Station> stations = new LinkedList<Station>();
        Document doc = null;
        try {
            doc = Jsoup.parse(new URL(url).openStream(), "ISO-8859-1", url);
        } catch (IOException e) {

        }
        try{
            List<Node> elements = doc
                    .childNodes().get(0)
                    .childNodes().get(2)
                    .childNodes().get(1)
                    .childNodes().get(1)
                    .childNodes().get(0)
                    .childNodes().get(1)
                    .childNodes().get(8)
                    .childNodes().get(1)
                    .childNodes();
            String data = doc.childNodes().get(0).childNodes().get(2).childNodes().get(1).childNodes().get(1).childNodes().get(0).childNodes().get(1).childNodes().get(1).childNodes().get(1).childNodes().get(1).childNodes().get(2).toString();
            String mes = data.split("de")[3];
            String ano = data.split("de")[4].substring(0,5);

            Locale localeBR = new Locale("pt", "BR");
            SimpleDateFormat fmt = new SimpleDateFormat("dd'/'MMMM'/'yyyy", localeBR);
            Node days = elements.get(0);
            for (int i =2;i<elements.size();i++){
                if(i%2==0){
                    Station station = new Station();
                    StringEscapeUtils.unescapeHtml4(elements.get(i).childNodes().get(1).childNodes().get(0).toString());
                    station.setName(StringEscapeUtils.unescapeHtml4(elements.get(i).childNodes().get(1).childNodes().get(0).toString()));
                    station.setWsmDomainList(new LinkedList<RainFallMeasurementDomain>());
                    for (int j = 3; j < elements.get(i).childNodes().size() ; j++) {
                        if(j%2 != 0){
                            String day = days.childNodes().get(3).childNodes().get(0).childNodes().get(0).toString();
                            RainFallMeasurementDomain rfm = new RainFallMeasurementDomain();
                            try{
                                if(!elements.get(i).childNodes().get(j).childNodes().get(0).childNodes().get(0).toString().trim().equals("--")){
                                    try{
                                    	rfm.setValue(Float.valueOf(elements.get(i).childNodes().get(j).childNodes().get(0).childNodes().get(0).toString().trim()));
                                        String format = day+"/"+mes+"/"+ano;
                                        format = format.replace(" ","");
                                        rfm.setDate( fmt.parse(format));
                                        station.getWsmDomainList().add(rfm);
                                    }catch (Exception e){
                                        break;
                                    }
                                }
                                
                            }catch (Exception x){

                            }
                        }
                    }
                    stations.add(station);
                }
            }

        }catch (Exception e){

        }


        return stations;
    }

    public static List<WaterSourceDomain> getMeasurements(String url) throws ParseException{
        Document doc = null;

        Locale localeBR = new Locale("pt", "BR");
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy", localeBR);

        try {
            doc = Jsoup.parse(new URL(url).openStream(), "ISO-8859-1", url);
        } catch (IOException e) {

        }
        List<Node> elements = doc
                .childNodes().get(0)
                .childNodes().get(2)
                .childNodes().get(1)
                .childNodes().get(1)
                .childNodes().get(0)
                .childNodes().get(1)
                .childNodes().get(1)
                .childNodes().get(1)
                .childNodes().get(1)
                .childNodes().get(1)
                .childNodes().get(0)
                .childNodes().get(1)
                .childNodes().get(25)
                .childNodes().get(1)
                .childNodes();
        Node element = elements.get(0);
        //        List<Observacao> observacoes = new LinkedList<Observacao>();
        List<WaterSourceDomain> wsList = new LinkedList<>();

        for (int i = 2; i < elements.size(); i += 2) {
            //            Observacao obs = new Observacao();
            WaterSourceDomain ws = new WaterSourceDomain();
            WaterSourceMeasurementDomain wsm = new WaterSourceMeasurementDomain();

            String municipio = "";
            try {
                municipio = elements.get(i).childNodes().get(1).childNodes().get(1).childNodes().get(1).childNodes().get(0).toString();
                municipio = municipio.replace("<b>", "");
                municipio = municipio.replace("</b>", "");

            } catch (Exception e) {
                municipio = elements.get(i).childNodes().get(1).childNodes().get(1).childNodes().get(0).toString();
                municipio = municipio.replace("<b>", "");
                municipio = municipio.replace("</b>", "");
            }
            municipio = StringEscapeUtils.unescapeHtml4(municipio);
            String acude = elements.get(i).childNodes().get(3).childNodes().get(1).childNodes().get(1).childNodes().get(0).toString();
            acude = acude.replace("<b>", "");
            acude = acude.replace("</b>", "");

            acude = StringEscapeUtils.unescapeHtml4(acude);

            String capMax = elements.get(i).childNodes().get(5).childNodes().get(1).childNodes().get(1).childNodes().get(0).toString();
            capMax = capMax.replace("<b>", "");
            capMax = capMax.replace("</b>", "");


            String volAtual = "";
            try {
                volAtual = elements.get(i).childNodes().get(7).childNodes().get(1).childNodes().get(1).childNodes().get(0).toString();
                volAtual = volAtual.replace("<b>", "");
                volAtual = volAtual.replace("</b>", "");

            } catch (Exception e) {
                volAtual = elements.get(i).childNodes().get(7).childNodes().get(1).childNodes().toString();
                if (volAtual.contains("[")) {
                    volAtual = volAtual.replace("\n", "");
                    volAtual = volAtual.replace("[", "");
                    volAtual = volAtual.replace("]", "");
                    volAtual = volAtual.replace("*", "");
                } else {
                    volAtual = volAtual.replace("<b>", "");
                    volAtual = volAtual.replace("</b>", "");
                    volAtual = volAtual.replace("*", "");
                }
            }

            String volTotal = "";
            try {
                volTotal = elements.get(i).childNodes().get(9).childNodes().get(1).childNodes().get(1).childNodes().get(0).toString();
                if (volTotal.trim().equals("")) {
                    volTotal = elements.get(i).childNodes().get(9).childNodes().get(1).childNodes().get(1).childNodes().get(1).childNodes().get(0).toString();
                }
                volTotal = volTotal.replace("<b>", "");
                volTotal = volTotal.replace("</b>", "");
                volTotal = volTotal.replace("*", "");
            } catch (Exception e) {
                volTotal = elements.get(i).childNodes().get(9).childNodes().get(1).childNodes().toString();
                volTotal = volTotal.replace("\n", "");
                volTotal = volTotal.replace("[", "");
                volTotal = volTotal.replace("]", "");
                volTotal = volTotal.replace("*", "");
            }

            if (volTotal.equalsIgnoreCase("sangrando")) {
                volTotal = "100";
            }

            String data = "";
            try {
                data = elements.get(i).childNodes().get(11).childNodes().get(1).childNodes().get(1).childNodes().get(0).childNodes().get(0).toString();
                data = data.replace("<b>", "");
                data = data.replace("</b>", "");
                data = data.replace("*", "");
                data = data.trim();
            } catch (Exception e) {
                try {
                    data = elements.get(i).childNodes().get(11).childNodes().get(1).childNodes().get(1).childNodes().get(0).toString();
                    data = data.replace("<b>", "");
                    data = data.replace("</b>", "");
                    data = data.replace("*", "");
                    data = data.trim();
                } catch (Exception j) {
                    elements.get(i).childNodes().get(11).childNodes().get(1).childNodes().get(0).toString();
                    data = elements.get(i).childNodes().get(11).childNodes().get(1).childNodes().get(0).toString();
                    data = data.replace("\n", "");
                    data = data.replace("*", "");
                    data = data.trim();
                }
            }


            if(!"".equals(data)){
                ws.setName(acude);
                wsm.setDate(fmt.parse(data));
                wsm.setValue(Float.valueOf(volAtual));
                ws.getMeasurementList().add(wsm);

                wsList.add(ws);
            }

        }
        return wsList;
    }

    public static Date stringToDate(String format, String date) throws ParseException {
        Locale localeBR = new Locale("pt", "BR");
        SimpleDateFormat fmt = new SimpleDateFormat(format, localeBR);
        return fmt.parse(date);
    }
}
