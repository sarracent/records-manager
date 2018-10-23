/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import static java.lang.String.valueOf;
import java.util.Calendar;
import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.SECOND;
import static java.util.Calendar.YEAR;
import static java.util.Calendar.getInstance;

/**
 *
 * @author yohana
 */
public class Fecha{
    
    
    private final Calendar cal = getInstance();
  /* */
  public String fecha_confeccionada(){
  return dia()+"-"+mes()+"-"+año()+"-"+Hora();
  }
  
    public String fecha_confeccionada_exp(){
  return dia()+"-"+mes()+"-"+año()+"-"+HoraEx();
  }
    
    public String dia() {
     
        return ""+cal.get(DAY_OF_MONTH);    
    }

 
    public String mes() { 
   int mes=cal.get(MONTH)+1;
        return  ""+mes;
    }

   
    public int año() {
        return cal.get(YEAR);
    }
    
     public int segundos() {
        return cal.get(SECOND);
    }


    public String Hora() {
        String horas = valueOf(cal.get(HOUR_OF_DAY));
        String minutos = valueOf(cal.get(MINUTE));
        String hora =horas+""+minutos;
        return hora;
    }
    
     public String HoraEx() {
        String horas = valueOf(cal.get(HOUR_OF_DAY));
        String minutos = valueOf(cal.get(MINUTE));
        String hora =horas+":"+minutos;
        return hora;
    }
}
