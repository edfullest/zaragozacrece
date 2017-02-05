package com.conexion;
import java.sql.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.net.*;

public class Conexion {


  //Atributos de la Conexion
  public Connection conn;
  public Statement stmt;



  //Constructor para inicializar la conexiÃ³n a la base de datos
  public Conexion(){
      try {
        // String userName = "root";
        // String password = "nbuser";
        // String url = "mysql://b6a578e56d299b:f2764aa7@us-cdbr-iron-east-04.cleardb.net/heroku_c0472c31d80655b?reconnect=true";
        //URI dbUri = new URI("mysql://b6a578e56d299b:f2764aa7@us-cdbr-iron-east-04.cleardb.net/heroku_c0472c31d80655b?reconnect=true");
        URI dbUri = new URI(System.getenv("CLEARDB_DATABASE_URL"));
        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:mysql://" + dbUri.getHost() + dbUri.getPath();
        //Class.forName ("com.mysql.jdbc.Driver").newInstance();
        conn = DriverManager.getConnection(dbUrl, username, password);;
        stmt = conn.createStatement();
      }catch (Exception e) { System.out.println (e); }
  }



}
