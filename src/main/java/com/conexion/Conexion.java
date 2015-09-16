package com.conexion;
import java.sql.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;

public class Conexion {
    

  //Atributos de la Conexion
  public Connection conn;
  public Statement stmt;

  //Constructor para inicializar la conexiÃ³n a la base de datos
  public Conexion(){
      try {
        String userName = "root";
        String password = "nbuser";
        String url = "jdbc:mysql://localhost:3308/zaragoza_db";
        Class.forName ("com.mysql.jdbc.Driver").newInstance();
        conn = DriverManager.getConnection (url, userName, password);
        stmt = conn.createStatement();
      }catch (Exception e) { System.out.println (e); }
  }

}
