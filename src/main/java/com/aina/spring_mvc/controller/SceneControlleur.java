/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aina.spring_mvc.controller;

import com.aina.spring_mvc.model.Auteur;
import com.aina.spring_mvc.model.Film;
import com.aina.spring_mvc.model.Plateau;
import com.aina.spring_mvc.model.Scene;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.aina.HibernateDao;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
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
public class SceneControlleur {
    @Autowired
    HibernateDao dao;
    @PostMapping("/Scene/inserer")
    public String inserer(@ModelAttribute(name = "objet") Scene article,Model model){     
        article.setStatut("creer");
        Session xx=dao.getSessionFactory().openSession();
        xx.save(article);
        xx.close();
        return render(model);
    }
    @GetMapping("/Generer")
    public String PDF(Model model) {
        try {
            Document document = new Document();
            File myFile =new File("Scene.pdf");
            myFile.createNewFile();
            PdfWriter.getInstance(document, new FileOutputStream(myFile));
            document.open();
            Scene scene = new Scene();
            scene.setStatut("planifier");
            Session xx=dao.getSessionFactory().openSession();
            Example example = Example.create(scene).ignoreCase();
            List<Scene> results = xx.createCriteria(scene.getClass()).add((Criterion)example).list();

            // Add the title to the document
            Paragraph title = new Paragraph("Scène");
            title.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(title);

            // Add the order information to the document
            Paragraph orderInfo = new Paragraph("Order Information:");
            orderInfo.setSpacingAfter(10f);
            document.add(orderInfo);

            PdfPTable orderTable = new PdfPTable(6);
            orderTable.setWidthPercentage(100);
            orderTable.setSpacingBefore(10f);
            orderTable.setSpacingAfter(10f);

            PdfPCell cell1 = new PdfPCell(new Paragraph("Scene:"));
            PdfPCell cell2 = new PdfPCell(new Paragraph("idAuteur:"));
            PdfPCell cell3 = new PdfPCell(new Paragraph("idFilm:"));
            PdfPCell cell4 = new PdfPCell(new Paragraph("Description:"));
            PdfPCell cell5 = new PdfPCell(new Paragraph("idPlateau:"));
            PdfPCell cell6 = new PdfPCell(new Paragraph("Statut:"));

            orderTable.addCell(cell1);
            orderTable.addCell(cell2);
            orderTable.addCell(cell3);
            orderTable.addCell(cell4);
            orderTable.addCell(cell5);
            orderTable.addCell(cell6);

            for(int i=0;i<results.size();i++){
                String id = String.valueOf(results.get(i).getId());
//                String idaut = String.valueOf(scene.get(i).getIdAuteur());
//                String idf = String.valueOf(scene.get(i).getIdFilm());
//                String idf = String.valueOf(scene.get(i).getIdFilm());
                Auteur a = new Auteur();
                Plateau p = new Plateau();
                Film f = new Film();
                a.setId(results.get(i).getIdAuteur().getId());
                p.setId(results.get(i).getIdPlateau().getId());
                f.setId(results.get(i).getIdFilm().getId());
                int ia = a.getId();
                int ip = p.getId();
                int idf = f.getId();
                orderTable.addCell(id);
                orderTable.addCell(String.valueOf(ia));
                orderTable.addCell(String.valueOf(idf));
                orderTable.addCell(new Paragraph(results.get(i).getDescriptions()));
                orderTable.addCell(String.valueOf(ip));
                orderTable.addCell(new Paragraph(results.get(i).getStatut()));
            }
            document.add(orderTable);
            document.close();
            Desktop.getDesktop().open(myFile);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return render(model);
    }
    @GetMapping("/Scene/inserer")
    public String render(Model model){        
        model.addAttribute("objet", new Scene()); 
        model.addAttribute("filtrer", new Scene()); 
        Session xx=dao.getSessionFactory().openSession();
        model.addAttribute("scene", new ArrayList<Scene>(new HashSet<Scene>(xx.createCriteria(Scene.class).list()))); 
        model.addAttribute("film", new ArrayList<Film>(new HashSet<Film>(xx.createCriteria(Film.class).list()))); 
        model.addAttribute("plateau", new ArrayList<Plateau>(new HashSet<Plateau>(xx.createCriteria(Plateau.class).list()))); 
        model.addAttribute("auteur", new ArrayList<Auteur>(new HashSet<Auteur>(xx.createCriteria(Auteur.class).list()))); 
        xx.close();
        return "jsp/ajout_scene";
    }
    @PostMapping("/Scene/filtrer")
    public String filtrer(@ModelAttribute Scene scene,Model model){        
        model.addAttribute("objet", new Scene()); 
        model.addAttribute("filtrer", new Scene()); 
        Session xx=dao.getSessionFactory().openSession();
        Example example = Example.create(scene).ignoreCase();
        List<Scene> results = xx.createCriteria(scene.getClass()).add((Criterion)example).list();
        model.addAttribute("scene", new ArrayList<Scene>(new HashSet<Scene>(results))); 
        model.addAttribute("film", new ArrayList<Film>(new HashSet<Film>(xx.createCriteria(Film.class).list()))); 
        model.addAttribute("plateau", new ArrayList<Plateau>(new HashSet<Plateau>(xx.createCriteria(Plateau.class).list()))); 
        model.addAttribute("auteur", new ArrayList<Auteur>(new HashSet<Auteur>(xx.createCriteria(Auteur.class).list()))); 
        xx.close(); 
        return "jsp/ajout_scene";
    }
}
