//Algo mas
package com.entidades;

import com.conexion.Conexion;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

// Clase base de cuentas de usuario
public class Pago {
    
    // Atributos para conexion con base de datos
    Connection conn;
    Statement stmt;
    PreparedStatement pStmt;
    
    //Atributos de la clase
    
    private int idPago;
    private int idPadrino;
    private int idApadrinado;
    private Date fechaPago;
    private boolean acreditado;
    private String nombreCompleto;
    
    // Metodo constructor con conexion
    public Pago(Conexion connect){
        this.conn = connect.conn;
        this.stmt = connect.stmt;
    }
    
        // Metodo constructor de la clase
    public Pago(int idPago, int idPadrino, int idApadrinado,
            Date fechaPago,boolean acreditado){
        
            this.idPago = idPago;
            this.idPadrino = idPadrino;
            this.idApadrinado=idApadrinado;
            this.fechaPago=fechaPago;
            this.acreditado=acreditado;
        
    }
    
            // Metodo constructor de la clase
    public Pago(int idPago, int idPadrino,String nombreCompleto, int idApadrinado,
            Date fechaPago,boolean acreditado){
        
            this.idPago = idPago;
            this.idPadrino = idPadrino;
            this.idApadrinado=idApadrinado;
            this.fechaPago=fechaPago;
            this.acreditado=acreditado;
            this.nombreCompleto = nombreCompleto;
        
    }
    

    


    //Se registra el nuevo pago
    public void nuevoPago(int idPadrino,int idApadrinado,Date fechaPago){
        
        
        try{
             pStmt = conn.prepareStatement(
                "INSERT INTO pago (idPadrino,idApadrinado,fechaPago)" +
                    " VALUES (?, ?, ?) ");
            pStmt.setInt(1,idPadrino);
            pStmt.setInt(2, idApadrinado);
            pStmt.setDate(3,new java.sql.Date(fechaPago.getTime()));
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
            stmt.executeQuery("SELECT idPago,pago.idPadrino,idApadrinado,fechaPago,acreditado,correo "
                    + "FROM pago,padrinos WHERE pago.idPadrino=padrinos.idPadrino AND pago.acreditado=0 AND idApadrinado=-1 ORDER BY idPago desc LIMIT "+offset+","+num);
            ResultSet rs = stmt.getResultSet();
            
            while(rs.next()){
               
                idPago = rs.getInt("idPago");
                idPadrino = rs.getInt("idPadrino");
                idApadrinado = rs.getInt("idApadrinado");
                fechaPago = rs.getDate("fechaPago");
                acreditado = (rs.getInt("acreditado")!=0);
                nombreCompleto = rs.getString("correo");
                
                
                Pago pago = new Pago(idPago,idPadrino,nombreCompleto,idApadrinado,fechaPago,acreditado);
                pagos.add(pago);
                
            }
        
            return pagos;
        }
        catch(SQLException e){
            ;
            return null;
        }
        
        
        
    }
     
      public int mostrarPagoSinAcreditar(int idPa){
           
        int idPago = -1;
        int idPadrino;
        int idApadrinado;
        Date fechaPago;
        boolean acreditado;
    
        
        try{
            stmt.executeQuery("SELECT idPago FROM pago WHERE pago.idPadrino="+idPa+" AND pago.acreditado=0 AND idApadrinado!=-1" );
            ResultSet rs = stmt.getResultSet();  
            if(rs.next()){
                idPago = rs.getInt("idPago"); 
            }
        
            return idPago;
        }
        catch(SQLException e){
            ;
            return -1;
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
    
               //Se obtienen numero de notas
    public void acreditarPago(int idPago){
   
        
        try{
             pStmt = conn.prepareStatement(
                "UPDATE pago SET acreditado=? WHERE idPago = ? ");
            pStmt.setInt(1,1);
            pStmt.setInt(2,idPago);
            pStmt.executeUpdate();
         

        }
        catch(SQLException e){
        
        }

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
     * @return the fechaPago
     */
    public Date getFechaPago() {
        return fechaPago;
    }

    /**
     * @param fechaPago the fechaPago to set
     */
    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    /**
     * @return the acreditado
     */
    public boolean isAcreditado() {
        return acreditado;
    }

    /**
     * @param acreditado the acreditado to set
     */
    public void setAcreditado(boolean acreditado) {
        this.acreditado = acreditado;
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
    
