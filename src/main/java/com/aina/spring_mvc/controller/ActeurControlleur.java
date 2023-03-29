/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aina.spring_mvc.controller;

import com.aina.spring_mvc.model.Acteur;
import com.aina.spring_mvc.model.ActeurNonDisponible;
import com.aina.spring_mvc.model.Action;
import com.aina.spring_mvc.model.Film;
import com.aina.spring_mvc.model.Plateau;
import java.util.ArrayList;
import java.util.HashSet;
import org.aina.HibernateDao;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Mendrika
 */
@Controller
public class ActeurControlleur {
    @Autowired
    HibernateDao dao;
    @PostMapping("/Acteur/inserer")
    public String inserer(@ModelAttribute("acteur") Acteur acteur,Model model){   
        System.out.println("zdizjd  "+acteur.getNom());
        dao.create(acteur);
        return render(model);
    }
    @GetMapping("/Acteur/inserer")
    public String render(Model model){        
        model.addAttribute("acteur", new Acteur());  
        model.addAttribute("liste", new ArrayList<Acteur>(new HashSet<Acteur>(dao.findAll(Acteur.class)))); 
        return "jsp/ajout_acteur";
    }
    @GetMapping("/Acteur/indisponible")
    public String indisponible(@RequestParam(name="id") int id,Model model){        
        model.addAttribute("acteur", dao.findById(Acteur.class,id)); 
        model.addAttribute("model", new ActeurNonDisponible()); 
        return "jsp/ajout_acteur_non_indisponible";
    }
    @PostMapping("/Acteur/indisponible")
    public String insererindisponible(@RequestParam(name = "id") int id,@ModelAttribute ActeurNonDisponible acteur,Model model){            
        Acteur plat=new Acteur();
        plat.setId(id);
        acteur.setIdActeur(plat);
        Session ss=dao.getSessionFactory().openSession(); 
        ss.save(acteur);
        ss.close();
        return indisponible(id, model);
    }
}
