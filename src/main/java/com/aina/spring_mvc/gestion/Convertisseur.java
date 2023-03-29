/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aina.spring_mvc.gestion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author Mendrika
 */
public class Convertisseur {    
    public static int get_current_sequence(String table,Connection con) throws SQLException{
        int iaa=0;
        PreparedStatement stmt=null;
        try{
            String dona="SELECT last_value from "+table+"_id_seq";
            stmt=con.prepareStatement(dona);            
            ResultSet fin=stmt.executeQuery();
            while(fin.next()){
                iaa=fin.getInt(1);
            }
        }
        finally{
             stmt.close();            
        }
        return iaa;
    }
    public static String getString(LocalDate x){
        return x.getDayOfMonth()+"-"+x.getMonthValue()+"-"+x.getYear();
    }
    public static String getString(LocalDateTime x){
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return x.format(formatter);
    }
}
