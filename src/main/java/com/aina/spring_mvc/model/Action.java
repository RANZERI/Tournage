/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aina.spring_mvc.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.sql.Time;
import java.time.LocalTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Action implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "idActeur")
    @JsonBackReference
    private Acteur idActeur;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "idscene")
    @JsonBackReference
    private Scene idscene;
    @Column(name = "descriptions")
    private String description;
    @Column(name = "dialogue")
    private String dialogue;
    @DateTimeFormat(pattern = "HH:mm")
    @Column(name = "durre")
    private LocalTime durre;

    public void setId(int id) {
        this.id = id;
    }

    public void setIdActeur(Acteur idActeur) {
        this.idActeur = idActeur;
    }



    public void setDescription(String description) {
        this.description = description;
    }

    public void setDialogue(String dialogue) {
        this.dialogue = dialogue;
    }

    public void setDurre(LocalTime durre) {
        this.durre = durre;
    }

    public int getId() {
        return id;
    }


    public Acteur getIdActeur() {
        return idActeur;
    }

    public Scene getIdscene() {
        return idscene;
    }

    public void setIdscene(Scene idscene) {
        this.idscene = idscene;
    }



    public String getDescription() {
        return description;
    }

    public String getDialogue() {
        return dialogue;
    }

    public LocalTime getDurre() {
        return durre;
    }
    
    
}
