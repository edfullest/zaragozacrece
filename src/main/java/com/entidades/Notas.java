package com.entidades;

import com.conexion.Conexion;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

// Clase base de cuentas de usuario
public class Notas {
    
    // Atributos para conexion con base de datos
    Connection conn;
    Statement stmt;
    PreparedStatement pStmt;
    
    private String fechaPublicacion;
    private String titulo;
    private String nombre;
    private String nota;
    
    
    // Metodo constructor con conexion
    public Notas(Conexion connect){
        this.conn = connect.conn;
        this.stmt = connect.stmt;
    }
    
    // Metodo constructor del objeto Nota
    public Notas(String fechaPublicacion, String titulo, String nombre, String nota){
        this.fechaPublicacion = fechaPublicacion;
        this.titulo = titulo;
        this.nombre = nombre;
        this.nota = nota;
    }
    
    
    
    
    //Crear nuevas notas
    public void nuevaNota(Date fechaPublicacion,String titulo,
            String nombre,String nota, int idAdmin){
        
        
        
        
        try{
            
            
            pStmt = conn.prepareStatement(
                    "INSERT INTO nota (fechaPublicacion,titulo,nombre,"
                            + "nota,idAdmin)" +
                            " VALUES (?,?,?,?,?) ");
            pStmt.setDate(1,new java.sql.Date(fechaPublicacion.getTime()));
            pStmt.setString(2,titulo);
            pStmt.setString(3,nombre);
            pStmt.setString(4,nota);
            pStmt.setInt(5,idAdmin);
            pStmt.executeUpdate();
            
            
        }
        catch(SQLException e){
            
        }
        
        
        
    }
    
    //Obtiene notas dependiendo del offset (renglon = offset + 1) y numero de
    //notas que se quieren agarrar
    public ArrayList<Notas> obtenerNotas(int offset,int num){
        
        ArrayList<Notas> notas = new ArrayList<Notas>();
        int idNota;
        Date fechaPublicacion;
        String titulo;
        String nombre;
        String nota;
        int idAdmin;
        
        try{
            stmt.executeQuery("SELECT * FROM nota ORDER BY idNota desc LIMIT "+offset+","+num);
            ResultSet rs = stmt.getResultSet();
            while(rs.next()){
                idNota = rs.getInt("idNota");
                fechaPublicacion = rs.getDate("fechaPublicacion");
                titulo = rs.getString("titulo");
                nombre = rs.getString("nombre");
                nota = rs.getString("nota");
                idAdmin = rs.getInt("idAdmin");
                
                //convierto la fecha
                Locale fechaLocal = new Locale.Builder().setLanguage("es").setRegion("MX").build();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",fechaLocal);
                Date d = fechaPublicacion;
                sdf.applyPattern("EEEE d MMMM yyyy");
                String sFechaPublicacion = sdf.format(d);
                
                Notas note = new Notas(sFechaPublicacion,titulo,nombre,nota);
                
                notas.add(note);
            }
        
            return notas;
        }
        catch(SQLException e){
            return null;
        }
        
        
        
    }
    
    
   //Se obtienen numero de notas
    public int getNumeroNotas(){
       
        int numNotas;
   
        
        try{
            stmt.executeQuery("SELECT COUNT(*) FROM nota;");
            ResultSet rs = stmt.getResultSet();
            while(rs.next()){
                numNotas = rs.getInt(1);
                return numNotas;
            }
        
            return -1;
        }
        catch(SQLException e){
            return -1;
        }
        
        
        
    }
    
    

    /**
     * @return the fechaPublicacion
     */
    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    /**
     * @param fechaPublicacion the fechaPublicacion to set
     */
    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the nota
     */
    public String getNota() {
        return nota;
    }

    /**
     * @param nota the nota to set
     */
    public void setNota(String nota) {
        this.nota = nota;
    }
    
    
}
