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
    public Suscripcion(int idPadrino,int idPago, int idApadrinado,
            Date fechaUltimoPago){
        
            this.idPago = idPago;
            this.idPadrino = idPadrino;
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
    
