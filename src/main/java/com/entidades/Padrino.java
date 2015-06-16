package com.entidades;

import com.conexion.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

// Clase base de cuentas de usuario
public class Padrino {
    
    // Atributos para conexion con base de datos
    Connection conn;
    Statement stmt;
    PreparedStatement pStmt;
    
    // Metodo constructor con conexion
    public Padrino(Conexion connect){
        this.conn = connect.conn;
        this.stmt = connect.stmt;
    }

    

    
    //CREAR CUENTAS NUEVAS
    public void nuevoPadrino(String correo,String password,String nombreCompleto,
        String celular){
        
        
        try{
             pStmt = conn.prepareStatement(
                "INSERT INTO padrinos (correo,password,nombreCompleto,"
                        + "celular)" +
                    " VALUES (?, ?, ?, ?) ");
            pStmt.setString(1,correo);
            pStmt.setString(2,password);
            pStmt.setString(3,nombreCompleto);
            pStmt.setString(4,celular);
            pStmt.executeUpdate();
         

        }
        catch(SQLException e){
        
        }
        

        
    }
    
    //BUSCAR PADRINO MEDIANTE CORREO Y PASSWORD, REGRESA FALSO SI NO EXISTAN ESAS CREDENCIALES
    public int validarCuenta(String correo,String password) { 
        int idPadrino = -1;
        try {
            stmt.executeQuery ("SELECT idPadrino,correo,password FROM padrinos WHERE correo = '" 
                    + correo +"' AND password = '" + password + "'");
            ResultSet rs = stmt.getResultSet();
            if(rs.next()){
                idPadrino = rs.getInt("idPadrino");
                rs.close();
                return idPadrino;
            }
            else{
                return -1;
            }
            
            
         
        } catch (SQLException e) {
            return -1;
        }

    }
    
    //Obtener nombre de padrino con el idCuenta
    public String getNombre(int idPadrino) {
        String sNombre = ""; 
        try {
            stmt.executeQuery("SELECT nombreCompleto FROM padrinos WHERE idPadrino = " 
                    + idPadrino);
            ResultSet rs = stmt.getResultSet();
            rs.next();
            sNombre = rs.getString("nombreCompleto");
            rs.close();
            return (sNombre);
        } catch (SQLException e) {
	        
            System.out.println ("Cannot getNombre()" + e);
            return sNombre;
        }

        
    }
    
    /*
    //Obtener nombre de padrino con el idCuenta
    public void borrarPadrino(int idPadrino) {
        String sNombre = ""; 
        try {
            stmt.executeQuery("SELECT nombreCompleto FROM padrinos WHERE idPadrino = " 
                    + idPadrino);
            ResultSet rs = stmt.getResultSet();
            rs.next();
            sNombre = rs.getString("nombreCompleto");
            rs.close();
            return (sNombre);
        } catch (SQLException e) {
	        
            System.out.println ("Cannot getNombre()" + e);
            return sNombre;
        }

       
    }
    */ 
    
    //Obtener id de Padrino con correo
    public int validarCorreo(String correo) {
        int idPadrino = -1;
        try {
            stmt.executeQuery("SELECT idPadrino FROM padrinos WHERE correo = '" 
                    + correo +"'");
            ResultSet rs = stmt.getResultSet();
            
            if(rs.next()){
	            idPadrino = rs.getInt("idPadrino");
	            rs.close();
	            return idPadrino;
            }
            else{
	            return -1;
            }

        } catch (SQLException e) {
	        
            System.out.println ("Cannot getNombre()" + e);
            return -1;
        }
  
    }
    
        //Obtener correo con el id del padrino
    public String obtenerCorreo(int idPadrino) {
        String correo="";
        try {
            stmt.executeQuery("SELECT correo FROM padrinos WHERE idPadrino = "+idPadrino);
            ResultSet rs = stmt.getResultSet();
            
            if(rs.next()){
	            correo = rs.getString("correo");
	            rs.close();
	            return correo;
            }
            else{
	            return correo;
            }

        } catch (SQLException e) {
            return correo;
        }
  
    }
        
  }
    
