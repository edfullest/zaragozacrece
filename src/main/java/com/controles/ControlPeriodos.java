/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controles;
import com.conexion.Conexion;
import com.entidades.Periodo;
import java.util.Calendar;
import java.util.Date;
/**
 *
 * @author Eduardo
 */
public class ControlPeriodos {
    
    public void actualizarPeriodo(){
        Conexion conn = new Conexion();
        Periodo periodo = new Periodo(conn);
        
        Periodo ultimoPeriodo = periodo.obtenerUltimoPeriodo();
        
        if (ultimoPeriodo.getFechaFinal() == new Date()){
            Calendar fechaFinal = Calendar.getInstance();
            fechaFinal.add(Calendar.MONTH, 5);
            Date dFechaFinal = fechaFinal.getTime();
            periodo.nuevoPeriodo(new Date(),dFechaFinal);   
        }
        else{
        
        }
    }
    
    
    
}
