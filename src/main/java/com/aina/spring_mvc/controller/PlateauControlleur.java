/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aina.spring_mvc.controller;

import com.aina.spring_mvc.model.Acteur;
import com.aina.spring_mvc.model.ActeurNonDisponible;
import com.aina.spring_mvc.model.Film;
import com.aina.spring_mvc.model.Plateau;
import com.aina.spring_mvc.model.PlateauNonDisponible;
import java.util.ArrayList;
import java.util.HashSet;
import org.aina.HibernateDao;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Mendrika
 */
@Controller
public class PlateauControlleur {
    @Autowired
    HibernateDao dao;
    @PostMapping("/Plateau/inserer")
    public String inserer(@ModelAttribute Plateau article,Model model){        
        dao.create(article);
        return render(model);
    }
    @GetMapping("/Plateau/inserer")
    public String render(Model model){        
        model.addAttribute("objet", new Plateau());  
        model.addAttribute("plateau",new ArrayList<Plateau>(new HashSet<Plateau>(dao.findAll(Plateau.class)))); 
        return "jsp/ajout_plateau";
    }
    @GetMapping("/Plateau/indisponible")
    public String indisponible(@RequestParam(name="id") int id,Model model){        
        model.addAttribute("plateau", dao.findById(Plateau.class,id));
        model.addAttribute("model", new PlateauNonDisponible()); 
        return "jsp/ajout_plateau_non_indisponible";
    }
    @PostMapping("/Plateau/indisponible")
    public String insererindisponible(@RequestParam(name = "id") int id,@ModelAttribute PlateauNonDisponible acteur,Model model){    
        Plateau plat=new Plateau();
        plat.setId(id);
        acteur.setPlateau(plat);
        Session ss=dao.getSessionFactory().openSession(); 
        ss.save(acteur);
        ss.close();        
        return indisponible(acteur.getPlateau().getId(), model);
    }
}
