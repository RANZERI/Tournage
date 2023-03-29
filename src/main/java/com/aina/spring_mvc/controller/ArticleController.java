//package com.aina.spring_mvc.controller;
//
//import com.aina.spring_mvc.gestion.Convertisseur;
//import com.aina.spring_mvc.model.Article;
//import com.aina.spring_mvc.model.Auteur;
//import com.aina.spring_mvc.model.NbArticle;
//import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
//import com.fasterxml.jackson.annotation.PropertyAccessor;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.sql.Timestamp;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.Base64;
//import java.util.Collections;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import javax.validation.Valid;
//import org.aina.HibernateDao;
//import org.hibernate.Session;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.web.bind.annotation.PathVariable;
//
//@Controller
//public class ArticleController {
//
//    @Autowired
//    HibernateDao dao;
//    @Value("${server.servlet.context-path}")
//    private String contextPath;
//    @GetMapping("/insererarticle")
//    public String formulaireCommune(HttpSession httpSession,Model model){
//        Auteur vv=(Auteur) httpSession.getAttribute("id");
//        if(vv==null){
//            return "redirect:login";
//        }
//        model.addAttribute("article", new Article());        
//        return "jsp/articleInsert";
//    }
//    @GetMapping("/")
//    public String render(){
//        return "jsp/searchArticle";
//    }
//    @GetMapping("/searchArticle")
//    public String rechercher(Model model,HttpSession httpSession,@RequestParam(name = "page",required = false,defaultValue = "1") String page){
//        try {
//            Auteur vv=(Auteur) httpSession.getAttribute("id");
//            if(vv==null){
//                return "redirect:login";
//            }
//            List<Article> art=null;
//            art=dao.findAll(Article.class);
//            if(vv.getEmail().compareTo("admin")==0){
//                model.addAttribute("bool", true);    
//            }
//            else{
//                int x=art.size();
//                int i=0;
//                while(i<x){                    
//                    if(art.get(i).getIdauteur().getIdauteur()!=vv.getIdauteur()){
//                        art.remove(i);
//                        x--;
//                    }
//                    else{
//                        i++;
//                    }
//                }
//                System.out.println(art.size());
//                model.addAttribute("bool", false);            
//            }
//            Collections.sort(art, (o1, o2) -> o1.getId() - o2.getId());
//            int limit=6;
//            art = (List<Article>) Article.list(art,limit);            
//            int pages=art.size();
//            pages/=limit;
//            pages++;
//            model.addAttribute("nb",page);
//            Article.filtrepagination(art, Integer.parseInt(page));
//            model.addAttribute("districts", art);
//        } catch (IOException ex) {
//            Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return "jsp/searchArticle";
//    }
//    @PostMapping("/insererarticle")
//    public String createCommune(@ModelAttribute Article article,HttpSession httpSession,HttpServletRequest request,Model model){
//        String spath=request.getServletContext().getRealPath("/MEDIA");  
//        System.out.println(spath);
//        try{
//            article.setEstvalider(false);
//            Auteur vv=(Auteur) httpSession.getAttribute("id");
//            article.setIdauteur(vv);
//            ObjectMapper map=new ObjectMapper();
////            map.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
////            System.out.println(map.writeValueAsString(article));
//            article.setDate_de_creation(LocalDateTime.now());
//            dao.create(article);
//            File myObj = new File("MEDIA/"+article.getId()+".png");
//            System.out.println(request.getContextPath()+"   ");
//            Path path = Paths.get(myObj.toURI());
//            Files.write(path, article.getFile().getBytes());
//            File parent = myObj.getParentFile();
////            if (parent != null && !parent.exists() && !parent.mkdirs()) {                
////            }
//            myObj.createNewFile();            
//            model.addAttribute("message","inserer avec succes");
//        }
//        catch(Exception ee){
//            ee.printStackTrace();
//            model.addAttribute("message",ee.getMessage());
//        }
//        return "jsp/articleInsert";
//    }
//    @PostMapping("/searchArticle")
//    public String search(@RequestParam(name="search") String xc,@RequestParam(name="etat") String xcx,HttpSession httpSession,Model model,@RequestParam(name = "page",required = false,defaultValue = "1") String page){        
//        List<Article> art;        
//        try {
//            Auteur vv=(Auteur) httpSession.getAttribute("id");
//            if(vv==null){
//                return "redirect:login";
//            }
//            art = Article.search(xc, xcx,dao.findAll(Article.class));
//            if(vv.getEmail().compareTo("admin")==0){
//                model.addAttribute("bool", true);    
//            }
//            else{
//                int x=art.size();
//                int i=0;
//                while(i<x){                    
//                    if(art.get(i).getIdauteur().getIdauteur()!=vv.getIdauteur()){
//                        art.remove(i);
//                        x--;
//                    }
//                    else{
//                        i++;
//                    }
//                }
//                System.out.println(art.size());
//                model.addAttribute("bool", false);            
//            }
//            Collections.sort(art, (o1, o2) -> o1.getId() - o2.getId());
//            int limit=6;
//            int pages=art.size();
//            pages/=limit;
//            pages++;
//            model.addAttribute("nb",page);
//            Article.filtrepagination(art, Integer.parseInt(page));
//            model.addAttribute("districts", art);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        return "jsp/searchArticle";
//    }
//    
//    @PostMapping("/")
//    public String login(@ModelAttribute Auteur article,HttpSession httpSession,Model model ){         
//        int  x=article.getId(dao);
//        if(x!=0){
//            Auteur pppp=article;
//            pppp.setIdauteur(x);
//            httpSession.setAttribute("id", pppp);
//            return "redirect:searchArticle";
//        }
//        model.addAttribute("message","erreur authentification");
//        return "jsp/index";
//    }
//    @PostMapping("/valider")
//    public String valider(@RequestParam(name="idd") int id,Model model){        
//        Article art=dao.findById(Article.class, id);       
//        art.setEstvalider(true);        
//        dao.update(art);
//        return "redirect:searchArticle";
//    }
//    @GetMapping("/publier")
//    public String renderpublier(@RequestParam(name="id") int id,Model model){        
//        Article art=dao.findById(Article.class, id);   
//        model.addAttribute("article", art);        
//        return "jsp/updateArticle";
//    }
//    @PostMapping("/publier")
//    public String publier(@RequestParam(name="id") int id,             
//            @RequestParam(name="publication",required = false) @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME) LocalDateTime date ,
//            Model model){        
//        Article art=dao.findById(Article.class, id);   
//        if(date!=null){
//            art.setDate_de_publication(date);
//        }
//        dao.update(art);
//        model.addAttribute("message", "inserer avec sucess");
//        model.addAttribute("article", art);
//        return "jsp/updateArticle";
//    }
//
//    @PostMapping("/refuser")
//    public String delete(@RequestParam(name="idd") int id, Model model){        
//        Article art=dao.findById(Article.class, id);       
//        dao.delete(art);
//        return "redirect:searchArticle";
//    }
//    @GetMapping("/")
//    public String listerFrontoffice(@RequestParam(name = "id",required = false,defaultValue = "1") String id,Model model){        
//        Article art=new Article();       
//        art.setEstvalider(true);
//        art.setOnPage(true);
//        List<Article> aart;
//        try {
//            int limit=6;
//            aart=dao.findWhere(art);
//            art.setOnPage(false);
//            aart.addAll(dao.findWhere(art));
////            Collections.sort(aart, (o1, o2) -> o1.getId() - o2.getId());
//            aart = (List<Article>) Article.list(aart,limit);            
//            int page=aart.size();
//            Article.filtrepagination(aart, Integer.parseInt(id));
//            System.out.println(aart.size()+"    vvvvv");
//            model.addAttribute("districts",aart);
//            page/=limit;
//            page++;
//            model.addAttribute("nb",page);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        return "jsp/listeFrontoffice";
//    }
//
//    public HibernateDao getDao() {
//        return dao;
//    }
//
//    public void setDao(HibernateDao dao) {
//        this.dao = dao;
//    }
//}
