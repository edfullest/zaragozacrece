/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entidades;

import com.conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Lalo Serna
 */
public class Suscripcion_pareja {
    
        // Atributos para conexion con base de datos
    Connection conn;
    Statement stmt;
    PreparedStatement pStmt;
    
    //Atributos de la clase
    
    private int idSuscripcion;
    private int idPareja;
    private int idPagoPareja;
    private int idApadrinado;
    private Date fechaUltimoPago;
    
    // Metodo constructor con conexion
    public Suscripcion_pareja(Conexion connect){
        this.conn = connect.conn;
        this.stmt = connect.stmt;
    }
    
  
 
    //Se registra el nuevo pago
    public void nuevaSuscripcion(int idPareja,int idPagoPareja,int idApadrinado,Date fechaUltimoPago){
        
        
        try{
             pStmt = conn.prepareStatement(
                "INSERT INTO suscripcion_pareja (idPareja,idPagoPareja,idApadrinado,fechaUltimoPago)" +
                    " VALUES (?, ?, ?, ?) ");
            pStmt.setInt(1,idPareja);
            pStmt.setInt(2,idPagoPareja);
            pStmt.setInt(3, idApadrinado);
            pStmt.setDate(4,new java.sql.Date(fechaUltimoPago.getTime()));
            pStmt.executeUpdate();
         

        }
        catch(SQLException e){
        
        }

      
    }
    
    
        
    public ArrayList<Pago> obtenerPagos(int id){
        
        ArrayList<Pago> pagos = new ArrayList<Pago>();
        
        int idPago;
        int idPadrino;
        int idApadrinado;
        Date fechaPago;
        boolean acreditado;
        
        try{
            pStmt = conn.prepareStatement(
                "SELECT * FROM pago WHERE idPadrino = ? ORDER BY idPago desc");
            pStmt.setInt(1,id);
            ResultSet rs = pStmt.executeQuery();
            while(rs.next()){
                
                idPago = rs.getInt("idPago");
                idPadrino = rs.getInt("idPadrino");
                idApadrinado = rs.getInt("idApadrinado");
                fechaPago = rs.getDate("fechaPago");
                acreditado = (rs.getInt("acreditado")!=0);
                
                Pago pago = new Pago(idPago,idPadrino,idApadrinado,fechaPago,acreditado);
                
                pagos.add(pago);

            }
        
            return pagos;
        }
        catch(SQLException e){
            return pagos;
        }
        

        
    }
    
     public ArrayList<Pago> mostrarPagos(int offset,int num){
        
         ArrayList<Pago> pagos = new ArrayList<Pago>();
        
        
        
        int idPago;
        int idPadrino;
        int idApadrinado;
        Date fechaPago;
        boolean acreditado;
        String nombreCompleto;
        
        try{
            stmt.executeQuery("SELECT idPago,pago.idPadrino,idApadrinado,fechaPago,acreditado,nombreCompleto "
                    + "FROM pago,padrinos WHERE pago.idPadrino=padrinos.idPadrino AND pago.idApadrinado=-1 ORDER BY idPago desc LIMIT "+offset+","+num);
            ResultSet rs = stmt.getResultSet();
            
            while(rs.next()){
               
                idPago = rs.getInt("idPago");
                idPadrino = rs.getInt("idPadrino");
                idApadrinado = rs.getInt("idApadrinado");
                fechaPago = rs.getDate("fechaPago");
                acreditado = (rs.getInt("acreditado")!=0);
                nombreCompleto = rs.getString("nombreCompleto");
                
                
                Pago pago = new Pago(idPago,idPadrino,nombreCompleto,idApadrinado,fechaPago,acreditado);
                pagos.add(pago);
                
            }
        
            return pagos;
        }
        catch(SQLException e){
            System.out.println(e);
            return null;
        }
        
        
        
    }
    
      
   //Se obtienen numero de notas
    public int getNumeroPagos(){
       
        int numPagos;
   
        
        try{
            stmt.executeQuery("SELECT COUNT(*) FROM pago;");
            ResultSet rs = stmt.getResultSet();
            while(rs.next()){
                numPagos = rs.getInt(1);
                return numPagos;
            }
        
            return -1;
        }
        catch(SQLException e){
            return -1;
        }
        
        
        
    }
    
}
