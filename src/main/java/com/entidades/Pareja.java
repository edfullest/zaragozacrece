package com.entidades;

import com.conexion.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

// Clase base de cuentas de usuario
public class Pareja {
    
    // Atributos para conexion con base de datos
    Connection conn;
    Statement stmt;
    PreparedStatement pStmt;
    
    // Metodo constructor con conexion
    public Pareja(Conexion connect){
        this.conn = connect.conn;
        this.stmt = connect.stmt;
    }
    
    //Creo una nueva pareja
    public void nuevaPareja(int idPadrino1,int idPadrino2,String correo1,String correo2){
        try{
            pStmt = conn.prepareStatement(
                    "INSERT INTO pareja (idPadrino1,idPadrino2,correo1,"
                            + "correo2)" +
                            " VALUES (?, ?, ?, ?) ");
            pStmt.setInt(1,idPadrino1);
            
            pStmt.setInt(2,idPadrino2);
            
            pStmt.setString(3,correo1);
            pStmt.setString(4,correo2);
            pStmt.executeUpdate();
        }
        catch(SQLException e){
        }
        

    }
    
    //Se buca correo en las columnas correo1 y correo2
    public boolean buscarCorreo(String correo){
        //Se busca en correo1
        boolean correo1 = buscarEnCorreo1(correo);
        //Entonces si existe un correo reigstrado en correo1, hay match
        if(correo1){
            return true;
        }
        //No esta en correo1, se busca en correo2
        else{
            //Se busca en correo2
            boolean correo2 = buscarEnCorreo2(correo);
            
            //Si esta en correo2, es true, y si no,
            //no se encontro en ningun lado
            return correo2;
            
        }
        
    }
    
    //Se busca correo en las columna correo1
    public boolean buscarEnCorreo1(String correo){
        try {
            stmt.executeQuery ("SELECT correo1 FROM pareja WHERE correo1 = '"
                    + correo + "'");
            ResultSet rs = stmt.getResultSet();
            //Entonces si existe un correo reigstrado en correo1, hay match
            if(rs.next()){
                rs.close();
                return true;
            }
            //Ese correo no esta en correo1
            else{
                return false;
            }
        }
        
        catch (SQLException e) {
            return false;
            
        }
   
    }
    
      //Se obtiene correo1 en base al correo2!!!!!!!
    public String getCorreo1(String correo){
        try {
            stmt.executeQuery ("SELECT correo1 FROM pareja WHERE correo2 = '"
                    + correo + "'");
            ResultSet rs = stmt.getResultSet();
            //Entonces si existe un correo reigstrado en correo2, hay match
            if(rs.next()){
                String correo1 = rs.getString("correo1");
                rs.close();
                return correo1;
            }
            //Ese correo no esta en correo1
            else{
                return "";
            }
        }
        
        catch (SQLException e) {
            return "";
            
        }
  
    }
    
      //Se obtiene correo2 en base al correo1!!!!!!!
       public String getCorreo2(String correo){
        try {
            stmt.executeQuery ("SELECT correo2 FROM pareja WHERE correo1 = '"
                    + correo + "'");
            ResultSet rs = stmt.getResultSet();
            //Entonces si existe un correo reigstrado en correo2, hay match
            if(rs.next()){
                String correo2 = rs.getString("correo2");
                rs.close();
                return correo2;
            }
            //Ese correo no esta en correo1
            else{
                return "";
            }
        }
        
        catch (SQLException e) {
            return "";
            
        }
  
    }
    
    //Se busca correo en las columna correo1
    public boolean buscarEnCorreo2(String correo){
        try {
            stmt.executeQuery ("SELECT correo2 FROM pareja WHERE correo2 = '"
                    + correo + "'");
            ResultSet rs = stmt.getResultSet();
            //Entonces si existe un correo reigstrado en correo2, hay match
            if(rs.next()){
                rs.close();
                return true;
            }
            //Ese correo no esta en correo1
            else{
                return false;
            }
        }
        
        catch (SQLException e) {
            return false;
            
        }
  
    }
    
    //Se modifica la columna idPadrino2 basandonos en el correo2 que se recibe
    public void modificaPadrino2(int idPadrino2,String correo2){
        try {
            stmt.executeUpdate ("UPDATE pareja SET idPadrino2 = " + idPadrino2 + " WHERE correo2  = '" 
                    + correo2 + "'"); 
        }
        
        catch (SQLException e) {
            
        }
    }
    
    //Se busca correo en la columna correo1
    public ArrayList<String> buscarEnPadrino2(String correo){
        ArrayList<String> lista = new ArrayList();
        int idPadrino2;
        String correo2;
        try {
            stmt.executeQuery ("SELECT idPadrino2,correo2 FROM pareja WHERE correo2 = '"
                    + correo + "'");
            ResultSet rs = stmt.getResultSet();
            //Entonces si existe un correo registrado en correo1, hay match, y 
            //obtiene el id del Padrino2 (para ver si es -1 o una cuenta)
            if(rs.next()){
                idPadrino2 = rs.getInt("idPadrino2");
                correo2 = rs.getString("correo2");
                lista.add(Integer.toString(idPadrino2));
                lista.add(correo2);
                rs.close();
                return lista;
            }
            
            else{
                return lista;
            }

        }
        
        catch (SQLException e) {
            return lista;
            
        }
        
    }
    
        //Se busca correo en la columna correo1
    public ArrayList<String> buscarEnPadrino1(String correo){
        ArrayList<String> lista = new ArrayList();
        int idPadrino1;
        String correo1;
        try {
            stmt.executeQuery ("SELECT idPadrino1,correo1 FROM pareja WHERE correo1 = '"
                    + correo + "'");
            ResultSet rs = stmt.getResultSet();
            //Entonces si existe un correo registrado en correo1, hay match, y 
            //obtiene el id del Padrino2 (para ver si es -1 o una cuenta)
            if(rs.next()){
                idPadrino1 = rs.getInt("idPadrino1");
                correo1 = rs.getString("correo1");
                lista.add(Integer.toString(idPadrino1));
                lista.add(correo1);
                rs.close();
                return lista;
            }
            
            else{
                return lista;
            }

        }
        
        catch (SQLException e) {
            return lista;
            
        }
        
    }
    
        
    //Se obtienen ambos ID de la pareja con el id de la pareja
    public ArrayList<String> obtenerAmbosID(int idPareja){
        ArrayList<String> idsPadrinos = new ArrayList();
        int idPadrino1;
        int idPadrino2;
        try {
            pStmt = conn.prepareStatement(
                    "SELECT idPadrino1,idPadrino2 FROM pareja WHERE idPareja = ?");
            
            pStmt.setInt(1,idPareja);
            ResultSet rs = pStmt.executeQuery();
            //Entonces si existe un correo registrado en correo1, hay match, y 
            //obtiene el id del Padrino2 (para ver si es -1 o una cuenta)
            if(rs.next()){
                idPadrino1 = rs.getInt("idPadrino1");
                idPadrino2 = rs.getInt("idPadrino2");
              
                idsPadrinos.add(Integer.toString(idPadrino1));
                idsPadrinos.add(Integer.toString(idPadrino2));
                
                return idsPadrinos;
            }
            
            else{
                return idsPadrinos;
            }

        }
        
        catch (SQLException e) {
            return idsPadrinos;
            
        }
        
    }
    
        //Se obtiene el ID de la pareja buscando el id del padrino ya sea en padrino1 o padrino2
    public int obtenerIdPareja(int idPadrino){

        try {
            pStmt = conn.prepareStatement(
                    "SELECT idPareja FROM pareja WHERE idPadrino1 = ? OR idPadrino2= ?");
            
            pStmt.setInt(1,idPadrino);
            pStmt.setInt(2,idPadrino);
            ResultSet rs = pStmt.executeQuery();
            //Entonces si existe un correo registrado en correo1, hay match, y 
            //obtiene el id del Padrino2 (para ver si es -1 o una cuenta)
            if(rs.next()){
                int idPareja = rs.getInt("idPareja");
            
                
                return idPareja;
            }
            
            else{
                return -1;
            }

        }
        
        catch (SQLException e) {
            return -1;
            
        }
        
    }
    
    
        //Se obtienen los datos del padrino 2 con el idPadrino1
    public ArrayList<String> obtenerDatosPadrino2(int idPadrino1){
        ArrayList<String> datosPadrino2 = new ArrayList();
        try {
            pStmt = conn.prepareStatement(
                    "SELECT idPareja,idPadrino2,correo2 FROM pareja WHERE idPadrino1 = ?");
            
            pStmt.setInt(1,idPadrino1);
            ResultSet rs = pStmt.executeQuery();
            //Entonces si existe un correo registrado en correo1, hay match, y 
            //obtiene el id del Padrino2 (para ver si es -1 o una cuenta)
            if(rs.next()){
                int idPareja = rs.getInt("idPareja");
                int idPadrino2 = rs.getInt("idPadrino2");
                String correo2 = rs.getString("correo2");
                
                datosPadrino2.add(Integer.toString(idPareja));
                datosPadrino2.add(Integer.toString(idPadrino2));
                datosPadrino2.add(correo2);
                
                return datosPadrino2;
            }
            
            else{
                return datosPadrino2;
            }

        }
        
        catch (SQLException e) {
            return datosPadrino2;
            
        }
        
    }
    
           //Se obtienen los datos del padrino 1 con el idPadrino2
    public ArrayList<String> obtenerDatosPadrino1(int idPadrino2){
        ArrayList<String> datosPadrino1 = new ArrayList();
        try {
            pStmt = conn.prepareStatement(
                    "SELECT idPareja,idPadrino1,correo1 FROM pareja WHERE idPadrino2 = ?");
            
            pStmt.setInt(1,idPadrino2);
            ResultSet rs = pStmt.executeQuery();
            //Entonces si existe un correo registrado en correo1, hay match, y 
            //obtiene el id del Padrino2 (para ver si es -1 o una cuenta)
            if(rs.next()){
                int idPareja = rs.getInt("idPareja");
                int idPadrino1 = rs.getInt("idPadrino1");
                String correo1 = rs.getString("correo1");
              
                datosPadrino1.add(Integer.toString(idPareja));
                datosPadrino1.add(Integer.toString(idPadrino1));
                datosPadrino1.add(correo1);
                
                return datosPadrino1;
            }
            
            else{
                return datosPadrino1;
            }

        }
        
        catch (SQLException e) {
            return datosPadrino1;
            
        }
        
    }
    
       
    public ArrayList<String> obtenerConIDPareja(int idPareja){
        ArrayList<String> datosPadrinos = new ArrayList();
        try {
            pStmt = conn.prepareStatement(
                    "SELECT idPadrino1,idPadrino2,correo1,correo2 FROM pareja WHERE idPareja = ?");
            
            pStmt.setInt(1,idPareja);
            ResultSet rs = pStmt.executeQuery();
            //Entonces si existe un correo registrado en correo1, hay match, y 
            //obtiene el id del Padrino2 (para ver si es -1 o una cuenta)
            if(rs.next()){
                int idPadrino1 = rs.getInt("idPadrino1");
                int idPadrino2 = rs.getInt("idPadrino2");
                String correo1 = rs.getString("correo1");
                String correo2 = rs.getString("correo2");
              
                datosPadrinos.add(Integer.toString(idPadrino1));
                datosPadrinos.add(Integer.toString(idPadrino2));
                datosPadrinos.add(correo1);
                datosPadrinos.add(correo2);
                
                return datosPadrinos;
            }
            
            else{
                return datosPadrinos;
            }

        }
        
        catch (SQLException e) {
            return datosPadrinos;
            
        }
        
    }
    
    
    
  }
    
