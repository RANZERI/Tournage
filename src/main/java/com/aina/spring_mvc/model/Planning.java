/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aina.spring_mvc.model;
import de.jollyday.HolidayCalendar;
import de.jollyday.HolidayManager;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import org.aina.HibernateDao;


/**
 *
 * @author Mendrika
 */
@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Planning implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "dates")
    private LocalDate jour;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "idScene")
    @JsonBackReference
    private Scene idScene;
    @Transient
    private boolean prise;
    public void setId(int id) {
        this.id = id;
    }

    public void setJour(LocalDate jour) {
        this.jour = jour;
    }

    public void setIdScene(Scene idScene) {
        this.idScene = idScene;
    }
    public static List<Planning> add(List<Scene> scene){
        List<Planning> plan=new ArrayList<>();
        for(int i=0;i<scene.size();i++){
            Planning gg=new Planning();
            gg.setIdScene(scene.get(i));
            gg.setJour(LocalDate.parse( scene.get(i).getRemarque()));
            plan.add(gg);
        }
        return plan;
    }
    public Scene getIdScene() {
        return idScene;
    }
    public static boolean efa_ao(Scene xx,List<Planning> plan){
        for(int i=0;i<plan.size();i++){
            if(xx.getId()==plan.get(i).getIdScene().getId()){
                return true;
            }
        }
        return false;
    }
    public static Planning getPla(Scene xx,List<Planning> plan){
        for(int i=0;i<plan.size();i++){
            if(xx.getId()==plan.get(i).getIdScene().getId()){
                return plan.get(i);
            }
        }
        return null;
    }
    public static List<Scene> get_list_jour(List<Plateau> plateau,List<Scene> xxzz,List<Planning> mbola,LocalDate debut,LocalTime finish){
        List<Scene> list=new ArrayList();
        for(int i=0;i<plateau.size();i++){
            List<Scene> plat=new ArrayList<>(plateau.get(i).getScenes());
            for(int j=0;j<plat.size();j++){
                if(!plat.get(j).isNonDisponible(debut) ){
                    List<Action> act=new ArrayList<>(plat.get(j).getActions()); 
                    for(int a=0;a<act.size();a++){                
                        finish=finish.plusHours(act.get(a).getDurre().getHour()).plusMinutes(act.get(a).getDurre().getMinute());
                    }
                    if(finish.getHour()>8){
                        debut=debut.plusDays(1);
                        Duration aax=Duration.between(LocalTime.parse("08:00:00"),finish);
                        int hours=(int)aax.toMinutes()/60;
                        finish=LocalTime.parse(String.format("%02d", hours)+":"+String.format("%02d", aax.toMinutes()%60)+":00");
                    }
                    if(xxzz.contains(plat.get(j)) && !efa_ao(plat.get(j),mbola) &&  !isDateInDisponible(debut)){
                        plat.get(j).setRemarque(debut.toString());  
                        System.out.println(debut+"      xsxsx       "+plat.get(j).getDescriptions());
                        list.add(plat.get(j));                    
                    }
                }            
            }
        }        
        return list;
    }
    
    public static List<Plateau> get_plateau(List<Scene> planning,List<Planning> mbola,LocalDate date){
        List<Plateau> jp=new ArrayList();
        int ind=0;
        for(int i=0;i<planning.size();i++){
            if(!planning.get(i).getIdPlateau().isNonDisponible(date) && !efa_ao(planning.get(i),mbola)){
                jp.add(planning.get(i).getIdPlateau());
                if(!jp.contains(planning.get(i))){
                    ind++;
                }
            }            
            if(ind==2){
                break;
            }
        }
        return jp;
    }
    public static boolean isDateInDisponible(LocalDate x){
        HolidayManager holidayManager = HolidayManager.getInstance(HolidayCalendar.FRANCE);
        boolean isHoliday = holidayManager.isHoliday(x);
        if(x.getDayOfWeek() ==DayOfWeek.SUNDAY || x.getDayOfWeek()== DayOfWeek.SATURDAY || isHoliday){ 
            return true;
        }      
        return false;
    }
    public static List<Planning> planifier(List<Scene> planning,LocalDate debut,LocalDate fin){
        List<Planning> plan=new ArrayList<>();
        List<Plateau> pla=null;
        LocalTime finish=LocalTime.parse("00:00:00");
        while(true){
            pla=Planning.get_plateau(planning,plan, debut);
             List<Scene> po=get_list_jour(pla,planning,plan,debut,finish); 
             
            System.out.println(po.size()+"  bbbb    ");        
            plan.addAll(Planning.add(po));
            if(po.size()==0){
                debut=debut.plusDays(1);
            }
            if(debut.isEqual(fin)){
                for(int j=0;j<planning.size();j++){
                    if(!efa_ao(planning.get(j),plan)){
                        Planning gg=new Planning();
                        gg.setIdScene(planning.get(j));
                        plan.add(gg);
                    }
                }
                break;
            }
        }
        return plan;
    }

    public int getId() {
        return id;
    }

    public LocalDate getJour() {
        return jour;
    }

    public boolean isPrise() {
        return prise;
    }

    public void setPrise(boolean prise) {
        this.prise = prise;
    }

    
}
