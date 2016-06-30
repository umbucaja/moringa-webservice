package br.com.moringa.webservice.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table( uniqueConstraints = { @UniqueConstraint( columnNames = { "date", "water_source_id" } ) } )
public class WaterSourceMeasurement implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5283171155776506943L;

    @Id
    @GeneratedValue(generator = "id", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "id")
    private Long id;

    @Column
    private float value;

    @Column
    private Date date;

    @JsonBackReference
    @ManyToOne(targetEntity = WaterSource.class)
    private WaterSource waterSource;

    public WaterSourceMeasurement() {

    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getValue() {
        return this.value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public WaterSource getWaterSource() {
        return waterSource;
    }

    public void setWaterSource(WaterSource waterSource) {
        this.waterSource = waterSource;
    }
}
