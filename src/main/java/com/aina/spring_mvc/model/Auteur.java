/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aina.spring_mvc.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import org.aina.HibernateDao;

/**
 *
 * @author Mendrika
 */
@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Auteur implements Serializable{
    @Id  
    @GeneratedValue(strategy=GenerationType.IDENTITY)  
    @PrimaryKeyJoinColumn  
    private int id;
    @Column(name = "nom")
    private String nom;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "idAuteur",cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<Scene> article; 


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Set<Scene> getArticle() {
        return article;
    }

    public void setArticle(Set<Scene> article) {
        this.article = article;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getId(HibernateDao dao){
        List<Auteur> x=dao.findWhere(this);
        if(x.size()!=0){
            return x.get(0).getId();
        }
        return 0;
    }
    
    
}
