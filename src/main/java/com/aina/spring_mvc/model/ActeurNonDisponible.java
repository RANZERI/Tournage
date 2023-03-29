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
public class ActeurNonDisponible implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER,  cascade = CascadeType.ALL)
    @JoinColumn(name = "idActeur")
    private Acteur idActeur;
    @Column(name="observation")
    private String observation;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name="dates")
    private LocalDate dates;

    public void setId(int id) {
        this.id = id;
    }

    public void setIdActeur(Acteur idActeur) {
        this.idActeur = idActeur;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public void setDates(LocalDate dates) {
        this.dates = dates;
    }

    public Acteur getActeur() {
        return idActeur;
    }

    public String getObservation() {
        return observation;
    }

    public LocalDate getDates() {
        return dates;
    }
    
    
}
