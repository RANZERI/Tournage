/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aina.spring_mvc.controller;

import com.aina.spring_mvc.model.Acteur;
import com.aina.spring_mvc.model.Action;
import com.aina.spring_mvc.model.Film;
import com.aina.spring_mvc.model.Scene;
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

/**
 *
 * @author Mendrika
 */
@Controller
public class ActionControlleur {
    @Autowired
    HibernateDao dao;
    @PostMapping("/Action/inserer")
    public String inserer(@ModelAttribute("objet") Action article,Model model){  
        Session xx=dao.getSessionFactory().openSession();
        xx.save(article);
        xx.close();
        return render(model);
    }
    @GetMapping("/Action/inserer")
    public String render(Model model){        
        model.addAttribute("objet", new Action()); 
        Session xx=dao.getSessionFactory().openSession();
        model.addAttribute("action", new ArrayList<Action>(new HashSet<Action>(xx.createCriteria(Action.class).list()))); 
        model.addAttribute("acteur",new ArrayList<Acteur>(new HashSet<Acteur>(xx.createCriteria(Acteur.class).list()))); 
        model.addAttribute("scene",new ArrayList<Scene>(new HashSet<Scene>(xx.createCriteria(Scene.class).list())));
        xx.close();
        return "jsp/ajout_action";
    }
    @GetMapping("/")
    public String render(){
        return "jsp/searchArticle";
    }
}
