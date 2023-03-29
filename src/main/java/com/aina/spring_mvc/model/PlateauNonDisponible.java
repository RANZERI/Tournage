/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aina.spring_mvc.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Mendrika
 */
@Entity
public class PlateauNonDisponible implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER,  cascade = CascadeType.ALL)
    @JoinColumn(name = "idPlateau")
    private Plateau idPlateau;
    @Column(name="observation")
    private String observation;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name="dates")
    private LocalDate dates;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Plateau getPlateau() {
        return idPlateau;
    }

    public String getObservation() {
        return observation;
    }

    public LocalDate getDates() {
        return dates;
    }

    public void setPlateau(Plateau plateau) {
        this.idPlateau = plateau;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public void setDates(LocalDate dates) {
        this.dates = dates;
    }
    
}
