/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aina.spring_mvc.controller;

import com.aina.spring_mvc.model.ActeurNonDisponible;
import com.aina.spring_mvc.model.Action;
import com.aina.spring_mvc.model.Film;
import com.aina.spring_mvc.model.Planning;
import com.aina.spring_mvc.model.Scene;
import com.aina.spring_mvc.model.Plateau;
import com.aina.spring_mvc.model.PlateauNonDisponible;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.aina.HibernateDao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
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
public class PlanningControlleur {
    @Autowired
    HibernateDao dao;    
    @GetMapping("/Planning/planifier")
    public String planifier(@RequestParam(name = "id") int id,Model model){    
        Scene sc=new Scene();
        Film fil=new Film();
        fil.setId(id);
        sc.setIdFilm(fil);
        sc.setStatut("creer");
        Session xx=dao.getSessionFactory().openSession();
        Example example = Example.create(sc).ignoreCase();
        List<Scene> results = xx.createCriteria(sc.getClass()).add((Criterion)example).list();
        model.addAttribute("id",id);
        model.addAttribute("plannification",new Film());
        model.addAttribute("scene", new ArrayList<Scene>(new HashSet<Scene>(results)));
        return "jsp/planning";
    }
    @PostMapping("/Planning/planifier")
    public String planning(HttpServletRequest request,Model model){        
        int id =Integer.parseInt(request.getParameter("id").toString());
        String[] idsene=request.getParameterValues("scene");
        List<Scene> scenes=new ArrayList<>();
        Session xx=dao.getSessionFactory().openSession();
        if(idsene!=null){
            for(int i=0;i<idsene.length;i++){
                System.out.println(Integer.parseInt(idsene[i])+"   vxcx");
                scenes.add(xx.get(Scene.class, Integer.parseInt(idsene[i])));
            }
            model.addAttribute("planning", Planning.planifier(scenes, LocalDate.parse(request.getParameter("dates1")), LocalDate.parse(request.getParameter("dates2"))));
        }
        xx.close();
        return planifier(id,model);
    }
    @PostMapping("/Planning/valider")
    public String valider(HttpServletRequest request,Model model){        
        int id =Integer.parseInt(request.getParameter("id").toString());
        String[] idsene=request.getParameterValues("valider");
        String[] date=request.getParameterValues("dates");
        String[] sene=request.getParameterValues("liste");
        Session xx=dao.getSessionFactory().openSession();
        for(int i=0;i<idsene.length;i++){
            Planning plan= new Planning();
            int indice=Integer.parseInt(idsene[i]);
            System.out.println(sene[indice]+"       "+indice);
            Scene xxx=xx.get(Scene.class, Integer.parseInt(sene[indice]));
            xxx.setStatut("planifier");
            Transaction tx=xx.beginTransaction();
            xx.update(xxx);
            tx.commit();
            plan.setIdScene(xxx);
            plan.setJour(LocalDate.parse(date[indice]));
            xx.save(plan);
            Plateau xxxx=xxx.getIdPlateau();
            PlateauNonDisponible xxxxx=new PlateauNonDisponible();
            xxxxx.setObservation(" tournage pour "+xxx.getDescriptions());
            xxxxx.setPlateau(xxxx);
            xxxxx.setDates(LocalDate.parse(date[indice]));
            xx.save(xxxxx);
            List<Action> acto=new ArrayList<>(new HashSet(xxx.getActions()));
            for(int h=0;h<acto.size();h++){
                ActeurNonDisponible nonsdiponible=new ActeurNonDisponible();
                nonsdiponible.setObservation(" tournage pour "+xxx.getDescriptions());
                nonsdiponible.setIdActeur(acto.get(h).getIdActeur());
                nonsdiponible.setDates(LocalDate.parse(date[indice]));
                xx.save(nonsdiponible);
            }
        }
        xx.close();
        return planifier(id,model);
    }
}
