package br.com.moringa.webservice.entity;


/**
 * Created by Thiago Almeida on 16/06/2016.
 */

public class Observacao {

/*    @Id
    @GeneratedValue(generator = "id", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "id")*/
    private Long id;

    private String municipio;
    private String acude;
    private String capMax;
    private String volAtual;
    private String percentualAtual;
    private String data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getAcude() {
        return acude;
    }

    public void setAcude(String acude) {
        this.acude = acude;
    }

    public String getCapMax() {
        return capMax;
    }

    public void setCapMax(String capMax) {
        this.capMax = capMax;
    }

    public String getVolAtual() {
        return volAtual;
    }

    public void setVolAtual(String volAtual) {
        this.volAtual = volAtual;
    }

    public String getPercentualAtual() {
        return percentualAtual;
    }

    public void setPercentualAtual(String percentualAtual) {
        this.percentualAtual = percentualAtual;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return municipio+", "+acude+", "+capMax+", "+volAtual+", "+percentualAtual+", "+data;
    }
}
