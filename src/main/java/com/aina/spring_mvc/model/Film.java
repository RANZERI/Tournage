/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aina.spring_mvc.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 *
 * @author Mendrika
 */

@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Film implements Serializable{
    @Id  
    @GeneratedValue(strategy=GenerationType.IDENTITY)  
    @PrimaryKeyJoinColumn  
    private int id;
    @Column(name = "nom")
    private String nom;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "idFilm")
    @JsonBackReference
    private Set<Scene> idFilm; 

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setIdFilm(Set<Scene> idFilm) {
        this.idFilm = idFilm;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public Set<Scene> getScenes() {
        return idFilm;
    }
    
}
