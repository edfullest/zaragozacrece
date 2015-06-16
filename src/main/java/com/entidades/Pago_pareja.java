//Algo mas
package com.entidades;

import com.conexion.Conexion;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

// Clase base de cuentas de usuario
public class Pago_pareja {
    
    // Atributos para conexion con base de datos
    Connection conn;
    Statement stmt;
    PreparedStatement pStmt;
    
    //Atributos de la clase
    
    private int idPagoPareja;
    private int idPareja;
    private int idApadrinado;
    private boolean pago1;
    private boolean pago2;
    private Date fechaPago1;
    private Date fechaPago2;
    private boolean acreditado;
    private String correo1;
    private String correo2;
    
    
    
    // Metodo constructor con conexion
    public Pago_pareja(Conexion connect){
        this.conn = connect.conn;
        this.stmt = connect.stmt;
    }
    
        // Metodo constructor de la clase
    public Pago_pareja(int idPagoPareja, int idPareja, int idApadrinado,
            boolean pago1, boolean pago2, Date fechaPago1, Date fechaPago2, boolean acreditado){
            this.idPagoPareja = idPagoPareja;
            this.idPareja = idPareja;
            this.idApadrinado=idApadrinado;
            this.pago1=pago1;
            this.pago2=pago2;
            this.fechaPago1=fechaPago1;
            this.fechaPago2=fechaPago2;
            this.acreditado=acreditado;
        
    }
    
         // Metodo constructor de la clase
    public Pago_pareja(int idPagoPareja, int idPareja, int idApadrinado,
            Date fechaPago1, Date fechaPago2, boolean acreditado,
            String correo1,String correo2){
            this.idPagoPareja = idPagoPareja;
            this.idPareja = idPareja;
            this.idApadrinado=idApadrinado;
            this.pago1=pago1;
            this.pago2=pago2;
            this.fechaPago1=fechaPago1;
            this.fechaPago2=fechaPago2;
            this.acreditado=acreditado;
            this.correo1=correo1;
            this.correo2 = correo2;
        
    }
    


    //Se registra el pago1
    public void nuevoPago1(int idPareja,int idApadrinado,boolean pago1,Date fechaPago1){
        
        
        try{
             pStmt = conn.prepareStatement(
                "INSERT INTO pago_pareja (idPareja,idApadrinado,pago1,fechaPago1)" +
                    " VALUES (?, ?, ?, ?) ");
            pStmt.setInt(1,idPareja);
            pStmt.setInt(2,idApadrinado);
            if (pago1){
                pStmt.setInt(3,1);
            }
 
            pStmt.setDate(4,new java.sql.Date(fechaPago1.getTime()));
            pStmt.executeUpdate();
         

        }
        catch(SQLException e){
        
        }

      
    }
    
    
    //Se registra el pago1
    public void nuevoPago2(int idPareja,int idApadrinado,boolean pago2,Date fechaPago2){
        
        
        try{
             pStmt = conn.prepareStatement(
                "INSERT INTO pago_pareja (idPareja,idApadrinado,pago2,fechaPago2)" +
                    " VALUES (?, ?, ?, ?) ");
            pStmt.setInt(1,idPareja);
            pStmt.setInt(2, idApadrinado);
            if (pago2){
                pStmt.setInt(3,1);
            }
 
            pStmt.setDate(4,new java.sql.Date(fechaPago2.getTime()));
            pStmt.executeUpdate();
         

        }
        catch(SQLException e){
        
        }

      
    }
    
    public void actualizarPago1(int idPareja,int idApadrinado,boolean pago1,Date fechaPago1){
        
        
        try{
             pStmt = conn.prepareStatement(
                "UPDATE pago_pareja SET pago1=?, fechaPago1=? " +
                    " WHERE idPareja = ? AND acreditado=0");
            pStmt.setInt(1,1);
            pStmt.setDate(2,new java.sql.Date(fechaPago1.getTime()));
            pStmt.setInt(3,idPareja);
            pStmt.executeUpdate();
         

        }
        catch(SQLException e){
        
        }
        

        
    }
    
        
    public void actualizarPago2(int idPareja,int idApadrinado,boolean pago1,Date fechaPago1){
        
        
        try{
             pStmt = conn.prepareStatement(
                "UPDATE pago_pareja SET pago2=?, fechaPago2=? " +
                    " WHERE idPareja = ? AND acreditado=0");
            pStmt.setInt(1,1);
            pStmt.setDate(2,new java.sql.Date(fechaPago1.getTime()));
            pStmt.setInt(3,idPareja);
            pStmt.executeUpdate();
         

        }
        catch(SQLException e){
        
        }
        

        
    }
    
        
    public ArrayList<Pago_pareja> obtenerInfo(int id){
        
        ArrayList<Pago_pareja> pagospareja = new ArrayList<Pago_pareja>();
        
        int idPagoPareja;
        int idPareja;
        int idApadrinado;
        boolean pago1;
        boolean pago2;
        Date fechaPago1;
        Date fechaPago2;
        boolean acreditado;
        
        try{
            pStmt = conn.prepareStatement(
                "SELECT * FROM pago_pareja WHERE idPareja = ? ORDER BY idPagoPareja desc");
            pStmt.setInt(1,id);
            ResultSet rs = pStmt.executeQuery();
            while(rs.next()){
                
                idPagoPareja = rs.getInt("idPagoPareja");
                idPareja = rs.getInt("idPareja");
                idApadrinado = rs.getInt("idApadrinado");
                pago1 = (rs.getInt("pago1")!=0);
                pago2 = (rs.getInt("pago2")!=0);
                fechaPago1 = rs.getDate("fechaPago1");
                if(rs.wasNull()){
                    fechaPago1 = null;
                }
                fechaPago2 = rs.getDate("fechaPago2");
                if(rs.wasNull()){
                    fechaPago2 = null;
                }
                acreditado = (rs.getInt("acreditado")!=0);
                
                Pago_pareja pagopareja=new Pago_pareja(idPagoPareja,idPareja,idApadrinado,pago1,pago2,fechaPago1,fechaPago2,acreditado);
                
                pagospareja.add(pagopareja);

            }
        
            return pagospareja;
        }
        catch(SQLException e){
            return pagospareja;
        }
        

        
    }
    
   
 public ArrayList<Pago_pareja> mostrarPagos(int offset,int num){
        
         ArrayList<Pago_pareja> pagos = new ArrayList<Pago_pareja>();
        
        
        
        int idPagoPareja;
        int idPareja;
        int idApadrinado;
        Date fechaPago1;
        Date fechaPago2;
        boolean acreditado;
        String correo1;
        String correo2;
        
        try{
            stmt.executeQuery("SELECT idPagoPareja,pago_pareja.idPareja,idApadrinado,fechaPago1,fechaPago2,acreditado,correo1,correo2 "
                    + "FROM pago_pareja,pareja WHERE pago_pareja.idPareja=pareja.idPareja AND pago_pareja.acreditado=0 ORDER BY idPagoPareja desc LIMIT "+offset+","+num);
            ResultSet rs = stmt.getResultSet();
            
            while(rs.next()){
               
                idPagoPareja = rs.getInt("idPagoPareja");
                idPareja = rs.getInt("idPareja");
                idApadrinado = rs.getInt("idApadrinado");
                fechaPago1 = rs.getDate("fechaPago1");
                fechaPago2 = rs.getDate("fechaPago2");
                acreditado = (rs.getInt("acreditado")!=0);
                correo1 = rs.getString("correo1");
                correo2 = rs.getString("correo2");
                
                
                Pago_pareja unpago = new Pago_pareja(idPagoPareja,idPareja,idApadrinado
                        ,fechaPago1,fechaPago2,acreditado,correo1,correo2);
              
                
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
            stmt.executeQuery("SELECT COUNT(*) FROM pago_pareja;");
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
    public void acreditarPago(int idPagoPareja){
   
        
        try{
             pStmt = conn.prepareStatement(
                "UPDATE pago_pareja SET acreditado=? WHERE idPagoPareja = ? ");
            pStmt.setInt(1,1);
            pStmt.setInt(2,idPagoPareja);
            pStmt.executeUpdate();
         

        }
        catch(SQLException e){
        
        }

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
     * @return the pago1
     */
    public boolean isPago1() {
        return pago1;
    }

    /**
     * @param pago1 the pago1 to set
     */
    public void setPago1(boolean pago1) {
        this.pago1 = pago1;
    }

    /**
     * @return the pago2
     */
    public boolean isPago2() {
        return pago2;
    }

    /**
     * @param pago2 the pago2 to set
     */
    public void setPago2(boolean pago2) {
        this.pago2 = pago2;
    }

    /**
     * @return the fechaPago1
     */
    public Date getFechaPago1() {
        return fechaPago1;
    }

    /**
     * @param fechaPago1 the fechaPago1 to set
     */
    public void setFechaPago1(Date fechaPago1) {
        this.fechaPago1 = fechaPago1;
    }

    /**
     * @return the fechaPago2
     */
    public Date getFechaPago2() {
        return fechaPago2;
    }

    /**
     * @param fechaPago2 the fechaPago2 to set
     */
    public void setFechaPago2(Date fechaPago2) {
        this.fechaPago2 = fechaPago2;
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
     * @return the correo1
     */
    public String getCorreo1() {
        return correo1;
    }

    /**
     * @param correo1 the correo1 to set
     */
    public void setCorreo1(String correo1) {
        this.correo1 = correo1;
    }

    /**
     * @return the correo2
     */
    public String getCorreo2() {
        return correo2;
    }

    /**
     * @param correo2 the correo2 to set
     */
    public void setCorreo2(String correo2) {
        this.correo2 = correo2;
    }



        
  }
    
