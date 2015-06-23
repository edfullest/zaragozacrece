//Algo mas
package com.entidades;

import com.conexion.Conexion;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

// Clase base de cuentas de usuario
public class Suscripcion {
    
    // Atributos para conexion con base de datos
    Connection conn;
    Statement stmt;
    PreparedStatement pStmt;
    
    //Atributos de la clase
    
    private int idSuscripcion;
    private int idPadrino;
    private int idPago;
    private int idApadrinado;
    private Date fechaUltimoPago;
    private String nombreCompleto;
    private String correo;
    
    // Metodo constructor con conexion
    public Suscripcion(Conexion connect){
        this.conn = connect.conn;
        this.stmt = connect.stmt;
    }
    
        // Metodo constructor de la clase
    public Suscripcion(int idSuscripcion, int idPadrino, int idPago, int idApadrinado, Date fechaUltimoPago, String nombreCompleto){
        
            this.idSuscripcion = idSuscripcion;
            this.idPadrino = idPadrino;
            this.idPago = idPago;
            this.idApadrinado=idApadrinado;
            this.fechaUltimoPago=fechaUltimoPago;
            this.nombreCompleto = nombreCompleto;
            
        
    }
    
        // Metodo constructor de la clase
    public Suscripcion(int idSuscripcion, int idPadrino, int idPago, int idApadrinado, Date fechaUltimoPago, String nombreCompleto, String correo){
        
            this.idSuscripcion = idSuscripcion;
            this.idPadrino = idPadrino;
            this.idPago = idPago;
            this.idApadrinado=idApadrinado;
            this.fechaUltimoPago=fechaUltimoPago;
            this.nombreCompleto = nombreCompleto;
            this.correo = correo;
            
        
    }
 
    //Se registra el nuevo pago
    public void nuevaSuscripcion(int idPadrino,int idPago,int idApadrinado,Date fechaUltimoPago){
        
        
        try{
             pStmt = conn.prepareStatement(
                "INSERT INTO suscripcion (idPadrino,idPago,idApadrinado,fechaUltimoPago)" +
                    " VALUES (?, ?, ?, ?) ");
            pStmt.setInt(1,idPadrino);
            pStmt.setInt(2,idPago);
            pStmt.setInt(3, idApadrinado);
            pStmt.setDate(4,new java.sql.Date(fechaUltimoPago.getTime()));
            pStmt.executeUpdate();
         

        }
        catch(SQLException e){
        
        }

      
    }
    
    
    //Se obtienen las suscripciones con el ID del padrino
    public ArrayList<Suscripcion> obtenerSuscripciones(int id){
        
        ArrayList<Suscripcion> suscripciones = new ArrayList<Suscripcion>();
        
        int idSuscripcion;
        int idPadrino;
        int idPago;
        int idApadrinado;
        Date fechaUltimoPago;
        String nombreCompleto;
        
        try{
            pStmt = conn.prepareStatement(
                "SELECT idSuscripcion,suscripcion.idPadrino,idPago,suscripcion.idApadrinado,fechaUltimoPago,"
                        + "nombreCompleto FROM suscripcion,apadrinados WHERE suscripcion.idPadrino = ? AND suscripcion.idApadrinado = apadrinados.idApadrinado");
            pStmt.setInt(1,id);
            ResultSet rs = pStmt.executeQuery();
            while(rs.next()){
                
                idSuscripcion = rs.getInt("idSuscripcion");
                idPadrino = rs.getInt("idPadrino");
                idPago = rs.getInt("idPago");
                idApadrinado = rs.getInt("idApadrinado");
                fechaUltimoPago = rs.getDate("fechaUltimoPago");
                nombreCompleto = rs.getString("nombreCompleto");
                
                Suscripcion suscripcion = new Suscripcion(idSuscripcion,idPadrino,idPago,idApadrinado,fechaUltimoPago,nombreCompleto);
                
                suscripciones.add(suscripcion);

            }
        
            return suscripciones;
        }
        catch(SQLException e){
            return suscripciones;
        }

    }
    
     //Se registra el nuevo pago
    public void actualizarFechaPago(int idSuscripcion,int idPago,Date fechaUltimoPago){
        
        
        try{
             pStmt = conn.prepareStatement(
               "UPDATE suscripcion SET fechaUltimoPago=?,idPago=?" +
                    " WHERE idSuscripcion = ? ");
            pStmt.setDate(1,new java.sql.Date(fechaUltimoPago.getTime()));
            pStmt.setInt(2, idPago);
            pStmt.setInt(3,idSuscripcion);  
            pStmt.executeUpdate();
         

        }
        catch(SQLException e){
        
        }

      
    }
    
    //Se obtienen las suscripciones con el ID del padrino
    public ArrayList<Suscripcion> obtenerTodasSuscripciones(int offset,int num){
        
        ArrayList<Suscripcion> suscripciones = new ArrayList<Suscripcion>();
        
        int idSuscripcion;
        int idPadrino;
        int idPago;
        int idApadrinado;
        Date fechaUltimoPago;
        String nombreCompleto;
        String correo;
        
        try{
            pStmt = conn.prepareStatement(
                "SELECT idSuscripcion,suscripcion.idPadrino,idPago,suscripcion.idApadrinado,"
                        + "fechaUltimoPago,apadrinados.nombreCompleto,padrinos.correo "
                        + "FROM suscripcion,apadrinados,padrinos WHERE suscripcion.idApadrinado = apadrinados.idApadrinado "
                        + "AND suscripcion.idPadrino=padrinos.idPadrino ORDER BY idSuscripcion descLIMIT ?,?");
            pStmt.setInt(1,offset);
            pStmt.setInt(2,num);
            ResultSet rs = pStmt.executeQuery();
            while(rs.next()){
                
                idSuscripcion = rs.getInt("idSuscripcion");
                idPadrino = rs.getInt("idPadrino");
                idPago = rs.getInt("idPago");
                idApadrinado = rs.getInt("idApadrinado");
                fechaUltimoPago = rs.getDate("fechaUltimoPago");
                nombreCompleto = rs.getString("nombreCompleto");
                correo = rs.getString("correo");
                
                Suscripcion suscripcion = new Suscripcion(idSuscripcion,idPadrino,idPago,idApadrinado,fechaUltimoPago,nombreCompleto,correo);
                
                suscripciones.add(suscripcion);

            }
        
            return suscripciones;
        }
        catch(SQLException e){
            return suscripciones;
        }

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
     * @return the idPadrino
     */
    public int getIdPadrino() {
        return idPadrino;
    }

    /**
     * @param idPadrino the idPadrino to set
     */
    public void setIdPadrino(int idPadrino) {
        this.idPadrino = idPadrino;
    }

    /**
     * @return the idPago
     */
    public int getIdPago() {
        return idPago;
    }

    /**
     * @param idPago the idPago to set
     */
    public void setIdPago(int idPago) {
        this.idPago = idPago;
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
    
