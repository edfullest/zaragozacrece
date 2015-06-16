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
    
    // Metodo constructor con conexion
    public Suscripcion(Conexion connect){
        this.conn = connect.conn;
        this.stmt = connect.stmt;
    }
    
        // Metodo constructor de la clase
    public Suscripcion(int idSuscripcion, int idPadrino, int idPago, int idApadrinado, Date fechaUltimoPago){
        
            this.idSuscripcion = idSuscripcion;
            this.idPadrino = idPadrino;
            this.idPago = idPago;
            this.idApadrinado=idApadrinado;
            this.fechaUltimoPago=fechaUltimoPago;
            
        
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
        
        try{
            pStmt = conn.prepareStatement(
                "SELECT * FROM suscripcion WHERE idPadrino = ?");
            pStmt.setInt(1,id);
            ResultSet rs = pStmt.executeQuery();
            while(rs.next()){
                
                idSuscripcion = rs.getInt("idSuscripcion");
                idPadrino = rs.getInt("idPadrino");
                idPago = rs.getInt("idPago");
                idApadrinado = rs.getInt("idApadrinado");
                fechaUltimoPago = rs.getDate("fechaUltimoPago");
                
                
                Suscripcion suscripcion = new Suscripcion(idSuscripcion,idPadrino,idPago,idApadrinado,fechaUltimoPago);
                
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

    
  
  }
    
