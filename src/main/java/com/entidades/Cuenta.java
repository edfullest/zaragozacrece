/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entidades;
import com.conexion.Conexion;
import java.sql.*;
import java.io.*;

public class Cuenta {
   Conexion conn;

   public Cuenta(Conexion conn){
      this.conn = conn;
      
   }

   public boolean validar(int idcuenta){
      try {
         conn.stmt.executeQuery ("SELECT idcuenta FROM cuenta WHERE idcuenta = " + idcuenta);
         ResultSet rs = conn.stmt.getResultSet();
         if (rs.next()) { //Va al primer registro si lo hay
            int nCuenta = rs.getInt ("idcuenta");
            rs.close(); 
            return( idcuenta == nCuenta );
         }else{ return false;}
      } catch (SQLException e) {}
      return false;
   }
      
   public void agregar(int idcuenta, String nombre, float saldo){
      try {
         String s = "INSERT INTO CUENTA (idcuenta, nombre, saldo)" +
                   " VALUES ("+ idcuenta + " , '" + nombre + "', " + saldo + " )";
         System.out.println(s); 
         conn.stmt.executeUpdate(s);
      }catch (Exception e) { System.out.println ("Cannot update database" + e ); }   
   }    
   
   public void setSaldo(int idcuenta, float saldo){
      try {
         String s = "UPDATE cuenta SET saldo = " + saldo + " WHERE idcuenta = " + idcuenta;
         conn.stmt.executeUpdate(s);
      } catch (SQLException e) {System.out.println ("Cannot execute disposicion()" + e);}
   }

   public int getUser(String nombre){
      int idcuenta = 0;
      try {
         conn.stmt.executeQuery ("SELECT iduser FROM users WHERE nombre = \"" + nombre+ "\" ");
         ResultSet rs = conn.stmt.getResultSet();
         rs.next(); //Va al registro ya validado
         idcuenta = rs.getInt("iduser");
         rs.close();
         return(idcuenta);
      } catch (SQLException e) {System.out.println ("Cannot do that query" + e);}
      return idcuenta;
   }
   
}
