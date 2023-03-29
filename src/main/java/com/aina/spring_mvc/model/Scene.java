/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aina.spring_mvc.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

/**
 *
 * @author Mendrika
 */

@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Scene implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "descriptions")
    private String descriptions;
    @Column(name = "statut")
    private String statut;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "idPlateau")
    @JsonBackReference
    private Plateau idPlateau;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "idFilm")
    @JsonBackReference
    private Film idFilm;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "idAuteur")
    @JsonBackReference
    private Auteur idAuteur;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "idscene")
    @JsonBackReference
    private Set<Action> idscene; 
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "idScene")
    @JsonBackReference
    private Planning idScene;
    @Transient
    private String remarque;
    public void setId(int id) {
        this.id = id;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public void setIdPlateau(Plateau idPlateau) {
        this.idPlateau = idPlateau;
    }

    public void setIdFilm(Film idFilm) {
        this.idFilm = idFilm;
    }

    public void setIdAuteur(Auteur idAuteur) {
        this.idAuteur = idAuteur;
    }

    public Set<Action> getIdscene() {
        return idscene;
    }

    public void setIdscene(Set<Action> idscene) {
        this.idscene = idscene;
    }

    public Planning getIdScene() {
        return idScene;
    }

    public void setIdScene(Planning idScene) {
        this.idScene = idScene;
    }

    public int getId() {
        return id;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public String getStatut() {
        return statut;
    }

    public Plateau getPlateau() {
        return idPlateau;
    }

    public Film getFilm() {
        return idFilm;
    }

    public Set<Action> getActions() {
        return idscene;
    }

    public Planning getPlanning() {
        return idScene;
    }

    public Plateau getIdPlateau() {
        return idPlateau;
    }

    public Film getIdFilm() {
        return idFilm;
    }

    public Auteur getIdAuteur() {
        return idAuteur;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }
    public LocalTime getDurre(){
        LocalTime toparse=LocalTime.parse("00:00:00");
        List<Action> act=new ArrayList(new HashSet(idscene));
        for(int i=0;i<act.size();i++){
            toparse=toparse.plusHours(act.get(i).getDurre().getHour()).plusMinutes(act.get(i).getDurre().getMinute());
        }
        return toparse;
    }
        public boolean isNonDisponible(LocalDate lp){
        List<Action> arrays=new ArrayList<>(this.getIdscene());
        for(int i=0;i<arrays.size();i++){
            if(arrays.get(i).getIdActeur().isNonDisponible(lp)){
                return true;
            }
        }
        return false;
    }
}
