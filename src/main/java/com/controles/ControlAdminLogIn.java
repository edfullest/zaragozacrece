/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controles;

import com.conexion.Conexion;
import com.entidades.Administradores;
import com.entidades.Padrino;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Lalo Serna
 */


public class ControlAdminLogIn extends HttpServlet {
    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        //El false es para que no cree una sesion, y si es nulo, no existe sesion
       if(request.getSession(false) != null){
           HttpSession session = request.getSession();
           
           if(session.getAttribute("goodlogin") == null){
               
                    //Creo la conexion a la base de datos
               Conexion conn = new Conexion();
               int idAdmin;
               session = request.getSession(true);
               request.setCharacterEncoding("UTF-8");
        
               
               String username = request.getParameter("username");
               String password = request.getParameter("password");
               
               
               Administradores admin = new Administradores(conn);
               ArrayList<String> lista = new ArrayList<String> ();
               lista = admin.validarCuenta(username, password);
               
               //No existe esa cuenta
               if(lista.isEmpty()){
                   request.setAttribute("badlogin", true);
                   request.getRequestDispatcher("admin").forward(request, response);
               }
               
               else{
                   idAdmin = Integer.parseInt(lista.get(0));
                   
                   session.setAttribute("goodlogin", true);
                   session.setAttribute("idAdmin", idAdmin);
                   request.getRequestDispatcher("InterfazAdmin").forward(request, response);
               }
               
           }
           
           else if((Boolean)session.getAttribute("goodlogin") == false){

               request.getRequestDispatcher("admin").forward(request, response);
           }
           
           else if((Boolean)session.getAttribute("goodlogin") == true){

               request.getRequestDispatcher("InterfazAdmin").forward(request, response);
 
           }
           
           
           
       }
       
       
        
       
              
    


       
        
     }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
         //El false es para que no cree una sesion, y si es nulo, no existe sesion
       if(request.getSession(false) != null){
           HttpSession session = request.getSession();
           
           //Checo si es nulo primero
  
           if(session.getAttribute("goodlogin") == null){
               ;
               request.getRequestDispatcher("admin").forward(request, response);
           }
          
           
           else if((Boolean)session.getAttribute("goodlogin") == true){
               String logout = request.getParameter("logout");
               if(logout.equals("")){
                   request.getRequestDispatcher("InterfazAdmin").forward(request, response);
               }
               
               else if(logout.equals("true")){
                   session.invalidate();
                   request.logout();
                   request.getRequestDispatcher("admin").forward(request, response);
               }
               
           }
           
           
           
       }
       
       else{
           
       }
        
    }

}