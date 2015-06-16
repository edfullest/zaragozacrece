package com.entidades;

import com.conexion.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

// Clase base de cuentas de usuario
public class Administradores {
    
    // Atributos para conexion con base de datos
    Connection conn;
    Statement stmt;
    PreparedStatement pStmt;
    
    // Metodo constructor con conexion
    public Administradores(Conexion connect){
        this.conn = connect.conn;
        this.stmt = connect.stmt;
    }

    

    
    //CREAR ADMINS NUEVOS
    public void nuevoAdmin(String nombreCompleto,String username,String password){
        
        
        try{
             pStmt = conn.prepareStatement(
                "INSERT INTO admins (nombreCompleto,username,password)" +
                    " VALUES (?, ?, ?) ");
            pStmt.setString(1,nombreCompleto);
            pStmt.setString(2,username);
            pStmt.setString(3,password);
            pStmt.executeUpdate();
         

        }
        catch(SQLException e){
        
        }
        

        
    }
    
    //Buscar admin con username y password
    public ArrayList<String> validarCuenta(String username,String password) { 
        ArrayList<String> lista = new ArrayList<String>();
        int idAdmin = -1;
        
        try {
            stmt.executeQuery ("SELECT idAdmin,nombreCompleto FROM admins WHERE username = '" 
                    + username +"' AND password = '" + password + "'");
            ResultSet rs = stmt.getResultSet();
            if(rs.next()){
                idAdmin = rs.getInt("idAdmin");
                String nombreCompleto = rs.getString("nombreCompleto");
                rs.close();
                lista.add(Integer.toString(idAdmin));
                lista.add(nombreCompleto);
                return lista;
            }
            else{
                return lista;
            }
            
            
         
        } catch (SQLException e) {
            return lista;
        }

    }
    

        
  }
    
