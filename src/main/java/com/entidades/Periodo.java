//Algo mas
package com.entidades;

import com.conexion.Conexion;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

// Clase base de cuentas de usuario
public class Periodo {
    
    // Atributos para conexion con base de datos
    Connection conn;
    Statement stmt;
    PreparedStatement pStmt;
    
    //Atributos de la clase
    
    private int idPeriodo;
    private Date fechaInicio;
    private Date fechaFinal;
   
    
    // Metodo constructor con conexion
    public Periodo(Conexion connect){
        this.conn = connect.conn;
        this.stmt = connect.stmt;
    }
    
        // Metodo constructor de la clase
    public Periodo(int idPeriodo,Date fechaInicio,Date fechaFinal){
        
            this.idPeriodo = idPeriodo;
            this.fechaInicio = fechaInicio;
            this.fechaFinal = fechaFinal;
            
        
    }
 
    //Se registra el nuevo pago
    public void nuevoPeriodo(Date fechaInicio,Date fechaFinal){
        
        
        try{
             pStmt = conn.prepareStatement(
                "INSERT INTO periodo (fechaInicio,fechaFinal)" +
                    " VALUES (?, ?) ");
            pStmt.setDate(1,new java.sql.Date(fechaInicio.getTime()));
            pStmt.setDate(2,new java.sql.Date(fechaFinal.getTime()));
            pStmt.executeUpdate();
         

        }
        catch(SQLException e){
        
        }

      
    }
    

     //Se registra el nuevo pago
    public ArrayList<Periodo> obtenerUltimosDosPeriodos(){
        
       ArrayList<Periodo> periodos = new ArrayList<Periodo>();
        
        try{
             pStmt = conn.prepareStatement(
                    "SELECT * FROM periodo ORDER BY idPeriodo desc LIMIT 0,2");
       
            ResultSet rs = pStmt.executeQuery();
            
            while(rs.next()){
                ;
                int idPeriodo = rs.getInt("idPeriodo");
                Date fechaInicio = rs.getDate("fechaInicio");
                Date fechaFinal = rs.getDate("fechaFinal");
                
                Periodo periodo = new Periodo (idPeriodo,fechaInicio,fechaFinal);
                
                periodos.add(periodo);
                
            }
          
            return periodos;

        }
        catch(SQLException e){
        
        }

      return periodos;
    }
    
    public Periodo obtenerUltimoPeriodo(){
       
        
        try{
             pStmt = conn.prepareStatement(
                    "SELECT * FROM periodo ORDER BY idPeriodo desc LIMIT 0,1");
       
            ResultSet rs = pStmt.executeQuery();
            
            while(rs.next()){

                int idPeriodo = rs.getInt("idPeriodo");
                Date fechaInicio = rs.getDate("fechaInicio");
                Date fechaFinal = rs.getDate("fechaFinal");
                
                Periodo periodo = new Periodo (idPeriodo,fechaInicio,fechaFinal);
                return periodo;
                
            }
          return null;
            

        }
        catch(SQLException e){
        
        }

      return null;
    }

    /**
     * @return the idPeriodo
     */
    public int getIdPeriodo() {
        return idPeriodo;
    }

    /**
     * @param idPeriodo the idPeriodo to set
     */
    public void setIdPeriodo(int idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    /**
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFinal
     */
    public Date getFechaFinal() {
        return fechaFinal;
    }

    /**
     * @param fechaFinal the fechaFinal to set
     */
    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    
    
  
  }
    
