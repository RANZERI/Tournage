/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aina.spring_mvc.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Mendrika
 */

@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Acteur implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nom")
    private String nom;    
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "idActeur",cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<Action> scenes;
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "idActeur",cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<ActeurNonDisponible> nondisponible;

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setScenes(Set<Action> scenes) {
        this.scenes = scenes;
    }

    public void setNondisponible(Set<ActeurNonDisponible> nondisponible) {
        this.nondisponible = nondisponible;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public Set<Action> getScenes() {
        return scenes;
    }

    public Set<ActeurNonDisponible> getNondisponible() {
        return nondisponible;
    }
    public boolean isNonDisponible(LocalDate lp){
        List<ActeurNonDisponible> arrays=new ArrayList<>(this.getNondisponible());
        for(int i=0;i<arrays.size();i++){
            if(arrays.get(i).getDates().isEqual(lp)){
                return true;
            }
        }
        return false;
    }
    
    
}
