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
    private String nombreCompleto;
    
    // Metodo constructor con conexion
    public Suscripcion_pareja(Conexion connect){
        this.conn = connect.conn;
        this.stmt = connect.stmt;
    }
    
    public Suscripcion_pareja(int idSuscripcion, int idPareja,int idPagoPareja, int idApadrinado, Date fechaUltimoPago
                               ,String nombreCompleto){
        
        this.idSuscripcion = idSuscripcion;
        this.idPareja = idPareja;
        this.idPagoPareja = idPagoPareja;
        this.idApadrinado = idApadrinado;
        this.fechaUltimoPago = fechaUltimoPago;
        this.nombreCompleto = nombreCompleto;
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
    
    //Se obtienen las suscripciones con el ID del padrino
    public Suscripcion_pareja obtenerSuscripciones(int id){
        
        
        
        int idSuscripcion;
        int idPareja;
        int idPagoPareja;
        int idApadrinado;
        Date fechaUltimoPago;
        String nombreCompleto;
        
        try{
            pStmt = conn.prepareStatement(
                "SELECT idSuscripcion,suscripcion_pareja.idPareja,idPagoPareja,suscripcion_pareja.idApadrinado,"
                        + "fechaUltimoPago,nombreCompleto FROM suscripcion_pareja,apadrinados WHERE suscripcion_pareja.idPareja = ? AND "
                        + "suscripcion_pareja.idApadrinado = apadrinados.idApadrinado");
            pStmt.setInt(1,id);
            ResultSet rs = pStmt.executeQuery();
            if(rs.next()){
                
                idSuscripcion = rs.getInt("idSuscripcion");
                idPareja = rs.getInt("idPareja");
                idPagoPareja = rs.getInt("idPagoPareja");
                idApadrinado = rs.getInt("idApadrinado");
                fechaUltimoPago = rs.getDate("fechaUltimoPago");
                nombreCompleto = rs.getString("nombreCompleto");
                Suscripcion_pareja suscripcion = new Suscripcion_pareja(idSuscripcion,idPareja,idPagoPareja,idApadrinado,fechaUltimoPago,nombreCompleto);
                
                return suscripcion;

            }
            
        
        }
        catch(SQLException e){
            return null;
        }
        
        return null;
    }

    /**
     * @return the idSuscripcion
     */
    public int getIdSuscripcion() {
        return idSuscripcion;
    }

    /**
     * @param idSuscripcion the idSuscripcion to set
     */
    public void setIdSuscripcion(int idSuscripcion) {
        this.idSuscripcion = idSuscripcion;
    }

    /**
     * @return the idPareja
     */
    public int getIdPareja() {
        return idPareja;
    }

    /**
     * @param idPareja the idPareja to set
     */
    public void setIdPareja(int idPareja) {
        this.idPareja = idPareja;
    }

    /**
     * @return the idPagoPareja
     */
    public int getIdPagoPareja() {
        return idPagoPareja;
    }

    /**
     * @param idPagoPareja the idPagoPareja to set
     */
    public void setIdPagoPareja(int idPagoPareja) {
        this.idPagoPareja = idPagoPareja;
    }

    /**
     * @return the idApadrinado
     */
    public int getIdApadrinado() {
        return idApadrinado;
    }

    /**
     * @param idApadrinado the idApadrinado to set
     */
    public void setIdApadrinado(int idApadrinado) {
        this.idApadrinado = idApadrinado;
    }

    /**
     * @return the fechaUltimoPago
     */
    public Date getFechaUltimoPago() {
        return fechaUltimoPago;
    }

    /**
     * @param fechaUltimoPago the fechaUltimoPago to set
     */
    public void setFechaUltimoPago(Date fechaUltimoPago) {
        this.fechaUltimoPago = fechaUltimoPago;
    }

    /**
     * @return the nombreCompleto
     */
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     * @param nombreCompleto the nombreCompleto to set
     */
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
    
    
        
}
