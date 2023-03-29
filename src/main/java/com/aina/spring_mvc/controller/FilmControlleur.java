/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aina.spring_mvc.controller;

import com.aina.spring_mvc.model.Acteur;
import com.aina.spring_mvc.model.Action;
import com.aina.spring_mvc.model.Film;
import java.util.ArrayList;
import java.util.HashSet;
import org.aina.HibernateDao;
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
public class FilmControlleur {
    @Autowired
    HibernateDao dao;
    @PostMapping("/Film/inserer")
    public String inserer(@ModelAttribute Film article,Model model){        
        dao.create(article);
        return render(model);
    }
    @GetMapping("/Film/inserer")
    public String render(Model model){        
        model.addAttribute("film", new Film());  
        model.addAttribute("liste", new ArrayList<Film>(new HashSet<Film>(dao.findAll(Film.class))));  
        return "jsp/ajout_film";
    }
}
