//Algo mas
package com.entidades;

import com.conexion.Conexion;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

// Clase base de cuentas de usuario
public class Apadrinados {
    
    // Atributos para conexion con base de datos
    Connection conn;
    Statement stmt;
    PreparedStatement pStmt;
    
    //Atributos de la clase
    
    private int idApadrinado;
    private String nombreCompleto;
    private String comunidad;
    private int idPadrino;
    private int idPareja;
    private int idAdmin;
    
    
    // Metodo constructor con conexion
    public Apadrinados(Conexion connect){
        this.conn = connect.conn;
        this.stmt = connect.stmt;
    }
    
    // Metodo constructor de clase
    public Apadrinados(int idApadrinado,String nombreCompleto,String comunidad,int idPadrino,int idPareja, int idAdmin){
        this.idApadrinado = idApadrinado;
        this.nombreCompleto = nombreCompleto;
        this.comunidad = comunidad;
        this.idPadrino = idPadrino;
        this.idPareja = idPareja;
        this.idAdmin = idAdmin;
    }

    //Crear nuevo apadrinado
    public void nuevoApadrinado(String nombreCompleto,String comunidad,int idAdmin){
        
        
        try{
             pStmt = conn.prepareStatement(
                "INSERT INTO apadrinados (nombreCompleto,comunidad,idAdmin)" +
                    " VALUES (?, ?, ?) ");
            pStmt.setString(1,nombreCompleto);
            pStmt.setString(2,comunidad);
            pStmt.setInt(3,idAdmin);
            pStmt.executeUpdate();
         

        }
        catch(SQLException e){
        
        }
        

        
    }
    
        //Crear nuevo apadrinado
    public void editarApadrinado(int idApadrinado,String nombreCompleto,String comunidad){
        
        
        try{
             pStmt = conn.prepareStatement(
                "UPDATE apadrinados SET nombreCompleto=?, comunidad=? " +
                    " WHERE idApadrinado = ? ");
            pStmt.setString(1,nombreCompleto);
            pStmt.setString(2,comunidad);
            pStmt.setInt(3,idApadrinado);
            pStmt.executeUpdate();
         

        }
        catch(SQLException e){
        
        }
        

        
    }
    
    public int obtenerIdPadrino(int idApadrinado){
        int idPadrino = -1;
        
                
        try{
            pStmt = conn.prepareStatement(
                "SELECT idPadrino FROM apadrinados WHERE idApadrinado = ?");
            pStmt.setInt(1,idApadrinado);
            ResultSet rs = pStmt.executeQuery();
            while(rs.next()){
                
                idPadrino = rs.getInt("idPadrino");
                return idPadrino;
            
            }
        
            return -1;
        }
        catch(SQLException e){
            return -1;
        }

    }
    
     public ArrayList<String> obtenerDatosApadrinado(int idApadrinado){
        ArrayList<String> datos = new ArrayList<String>();
        
                
        try{
            pStmt = conn.prepareStatement(
                "SELECT nombreCompleto,comunidad FROM apadrinados WHERE idApadrinado = ?");
            pStmt.setInt(1,idApadrinado);
            ResultSet rs = pStmt.executeQuery();
            while(rs.next()){
                String nombreCompleto = rs.getString("nombreCompleto");
                String comunidad = rs.getString("comunidad");
                datos.add(nombreCompleto);
                datos.add(comunidad);
            
            }
        
            return datos;
        }
        catch(SQLException e){
            return null;
        }

    }
    
    //Obtiene apadrinados dependiendo del offset (renglon = offset + 1) y numero de
    //apadrinados que se quieren agarrar
    public ArrayList<Apadrinados> obtenerApadrinados(int offset,int num){
        
        ArrayList<Apadrinados> apadrinados = new ArrayList<Apadrinados>();
        int idApadrinado;
        String nombreCompleto;
        String comunidad;
        int idPadrino;
        int idPareja;
        int idAdmin;
        
        try{
            stmt.executeQuery("SELECT * FROM apadrinados ORDER BY comunidad asc LIMIT "+offset+","+num);
            ResultSet rs = stmt.getResultSet();
            while(rs.next()){
                idApadrinado = rs.getInt("idApadrinado");
                nombreCompleto = rs.getString("nombreCompleto");
                comunidad = rs.getString("comunidad");
                idPadrino = rs.getInt("idPadrino");
                idPareja = rs.getInt("idPareja");
                idAdmin = rs.getInt("idAdmin");
                
                Apadrinados apadrinado = new Apadrinados(idApadrinado,nombreCompleto,comunidad,idPadrino,idPareja,idAdmin);
                apadrinados.add(apadrinado);
            }
        
            return apadrinados;
        }
        catch(SQLException e){
            return null;
        }
        
        
        
    }
    
     //Obtiene apadrinados dependiendo del offset (renglon = offset + 1) y numero de
    //apadrinados que se quieren agarrar
    public ArrayList<Apadrinados> obtenerApadrinadosSinPadrinos(int offset,int num){
        
        ArrayList<Apadrinados> apadrinados = new ArrayList<Apadrinados>();
        int idApadrinado;
        String nombreCompleto;
        String comunidad;
        int idPadrino;
        int idPareja;
        int idAdmin;
        
        try{
            stmt.executeQuery("SELECT * FROM apadrinados WHERE idPadrino=-1 AND idPareja=-1 ORDER BY comunidad asc LIMIT "+offset+","+num);
            ResultSet rs = stmt.getResultSet();
            while(rs.next()){
                idApadrinado = rs.getInt("idApadrinado");
                nombreCompleto = rs.getString("nombreCompleto");
                comunidad = rs.getString("comunidad");
                idPadrino = rs.getInt("idPadrino");
                idPareja = rs.getInt("idPareja");
                idAdmin = rs.getInt("idAdmin");
                
                Apadrinados apadrinado = new Apadrinados(idApadrinado,nombreCompleto,comunidad,idPadrino,idPareja,idAdmin);
                apadrinados.add(apadrinado);
            }
        
            return apadrinados;
        }
        catch(SQLException e){
            return null;
        }
        
        
        
    }
    
    //Se obtienen numero de notas
    public int getNumeroApadrinadosSinPadrinos(){
        
      
        int numApadrinados;
   
        
        try{
            stmt.executeQuery("SELECT COUNT(*) FROM apadrinados WHERE idPadrino=-1 AND idPareja=-1");
            ResultSet rs = stmt.getResultSet();
            while(rs.next()){
                numApadrinados = rs.getInt(1);
                return numApadrinados;
            }
        
            return -1;
        }
        catch(SQLException e){
            return -1;
        }

    }
    
    
   //Se obtienen numero de notas
    public int getNumeroApadrinados(){
        
        ArrayList<Notas> notas = new ArrayList<Notas>();
        int idApadrinado;
   
        
        try{
            stmt.executeQuery("SELECT idApadrinado FROM apadrinados ORDER BY idApadrinado desc");
            ResultSet rs = stmt.getResultSet();
            while(rs.next()){
                idApadrinado = rs.getInt("idApadrinado");
                return idApadrinado;
            }
        
            return -1;
        }
        catch(SQLException e){
            return -1;
        }

    }
    
       //Se obtienen numero de notas
    public int obtenerIdPareja(int idApadrinado){
        
        int idPareja = -100;
        
        try{
            pStmt = conn.prepareStatement(
                "SELECT idPareja FROM apadrinados WHERE idApadrinado = ?");
            pStmt.setInt(1,idApadrinado);
            ResultSet rs = pStmt.executeQuery();
            if(rs.next()){
                idPareja = rs.getInt("idPareja");
                
            }
        
            return idPareja;
        }
        catch(SQLException e){
            return -1000;
        }

    }
    
           //Se obtienen numero de notas
    public void asignarPadrino(int idPadrino,int idApadrinado){
   
        
        try{
             pStmt = conn.prepareStatement(
                "UPDATE apadrinados SET idPadrino=? WHERE idApadrinado = ? ");
            pStmt.setInt(1,idPadrino);
            pStmt.setInt(2,idApadrinado);
            pStmt.executeUpdate();
         

        }
        catch(SQLException e){
        
        }

    }
    
             //Se obtienen numero de notas
    public void asignarPareja(int idPareja,int idApadrinado){
   
        
        try{
             pStmt = conn.prepareStatement(
                "UPDATE apadrinados SET idPareja=? WHERE idApadrinado = ? ");
            pStmt.setInt(1,idPareja);
            pStmt.setInt(2,idApadrinado);
            pStmt.executeUpdate();
         

        }
        catch(SQLException e){
        
        }

    }
    
                 //Se obtienen numero de notas
    public ArrayList<Apadrinados> obtenerApadrinadoIdPadrino(int idPadrino){
   
        
        ArrayList<Apadrinados> apadrinados = new ArrayList<Apadrinados>();
        int idApadrinado;
        String nombreCompleto;
        String comunidad;
        int idPadrino1;
        int idPareja;
        int idAdmin;
        
        try{
            pStmt= conn.prepareStatement("SELECT * FROM apadrinados WHERE idPadrino = ? ORDER BY nombreCompleto asc");
            pStmt.setInt(1, idPadrino);
            ResultSet rs = pStmt.executeQuery();
            while(rs.next()){
                idApadrinado = rs.getInt("idApadrinado");
                nombreCompleto = rs.getString("nombreCompleto");
                comunidad = rs.getString("comunidad");
                idPadrino1 = rs.getInt("idPadrino");
                idPareja = rs.getInt("idPareja");
                idAdmin = rs.getInt("idAdmin");
                
                Apadrinados apadrinado = new Apadrinados(idApadrinado,nombreCompleto,comunidad,idPadrino1,idPareja,idAdmin);
                apadrinados.add(apadrinado);
            }
        
            return apadrinados;
        }
        catch(SQLException e){
            return null;
        }

    }
    
                     //Se obtienen numero de notas
    public ArrayList<Apadrinados> obtenerApadrinadoIdPareja(int idPareja){
   
        
        ArrayList<Apadrinados> apadrinados = new ArrayList<Apadrinados>();
        int idApadrinado;
        String nombreCompleto;
        String comunidad;
        int idPadrino;
        int idPareja1;
        int idAdmin;
        
        try{
            pStmt.executeQuery("SELECT * FROM apadrinados WHERE idPareja = ? ORDER BY nombreCompleto asc");
            pStmt.setInt(1, idPareja);
            ResultSet rs = pStmt.executeQuery();
            while(rs.next()){
                idApadrinado = rs.getInt("idApadrinado");
                nombreCompleto = rs.getString("nombreCompleto");
                comunidad = rs.getString("comunidad");
                idPadrino = rs.getInt("idPadrino");
                idPareja1 = rs.getInt("idPareja");
                idAdmin = rs.getInt("idAdmin");
                
                Apadrinados apadrinado = new Apadrinados(idApadrinado,nombreCompleto,comunidad,idPadrino,idPareja1,idAdmin);
                apadrinados.add(apadrinado);
            }
        
            return apadrinados;
        }
        catch(SQLException e){
            return null;
        }

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

    /**
     * @return the comunidad
     */
    public String getComunidad() {
        return comunidad;
    }

    /**
     * @param comunidad the comunidad to set
     */
    public void setComunidad(String comunidad) {
        this.comunidad = comunidad;
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
     * @return the idAdmin
     */
    public int getIdAdmin() {
        return idAdmin;
    }

    /**
     * @param idAdmin the idAdmin to set
     */
    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }
    

        
  }
    
