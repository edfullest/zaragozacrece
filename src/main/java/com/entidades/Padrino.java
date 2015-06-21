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
    
    
    //Atributos de la clase
    
    private int idPadrino;
    private String nombreCompleto;
    private String correo;
    private String celular;
    
    
    
    // Metodo constructor con conexion
    public Padrino(Conexion connect){
        this.conn = connect.conn;
        this.stmt = connect.stmt;
    }
    
    public Padrino (int idPadrino,String nombreCompleto,String correo, String celular){
        
        this.idPadrino = idPadrino;
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.celular = celular;
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
    
    
    //Obtener correo con el id del padrino
    public String obtenerTodosCorreos(int idPadrino) {
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
    
    //Obtener correo con el id del padrino
    public  ArrayList<Padrino> obtenerTodosPadrinos(int offset,int num) {
        ArrayList<Padrino> padrinos = new ArrayList<Padrino>();
        
        int idPadrino;
        String nombreCompleto;
        String correo;
        String celular;
        
        try {
            stmt.executeQuery("SELECT idPadrino,nombreCompleto,correo,celular FROM padrinos ORDER BY idPadrino desc LIMIT "+offset+","+num);
            ResultSet rs = stmt.getResultSet();
            
            while(rs.next()){
              
                idPadrino = rs.getInt("idPadrino");
              
                nombreCompleto = rs.getString("nombreCompleto");
               
                correo = rs.getString("correo");
            
                celular = rs.getString("celular");
              
                Padrino padrino = new Padrino(idPadrino,nombreCompleto,correo,celular);
                
                padrinos.add(padrino);
            }
            
            return padrinos;
            
        } catch (SQLException e) {
            return null;
        }
        
    }
    
    //Se obtienen numero de notas
    public int getNumeroPadrinos(){
        
        int numPadrinos;
        
        
        try{
            stmt.executeQuery("SELECT COUNT(*) FROM padrinos");
            ResultSet rs = stmt.getResultSet();
            if(rs.next()){
                numPadrinos = rs.getInt(1);
                return numPadrinos;
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
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }
    
    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    /**
     * @return the celular
     */
    public String getCelular() {
        return celular;
    }
    
    /**
     * @param celular the celular to set
     */
    public void setCelular(String celular) {
        this.celular = celular;
    }
    
}

