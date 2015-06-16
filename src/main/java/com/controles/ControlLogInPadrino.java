/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controles;

import com.conexion.Conexion;
import com.entidades.Apadrinados;
import com.entidades.Padrino;
import com.entidades.Pareja;
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


public class ControlLogInPadrino extends HttpServlet {
    
    
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        //Creo la conexion a la base de datos
        Conexion conn = new Conexion();
        int idPadrino;
        
        HttpSession session = request.getSession(true);
        request.setCharacterEncoding("UTF-8");
        System.out.println("encoding: "+request.getCharacterEncoding());
    
        String correo = request.getParameter("correo");
        String password = request.getParameter("password");
        
        System.out.println(password);
        Padrino padrino = new Padrino(conn);
        idPadrino = padrino.validarCuenta(correo,password);
        String nombre = padrino.getNombre(idPadrino);
        //Si es diferente de -1, si existe ese padrino con correo-password
              
    
        if (idPadrino != -1){
            session.setAttribute("idPadrino", idPadrino);
            session.setAttribute("nombreCompleto",nombre);
            session.setAttribute("thiscorreo",correo);
            session.setAttribute("goodlogin",true);
            boolean hayApadrinados = false;
            //Ademas, cargo sus apadrinados
            Apadrinados apadrinado = new Apadrinados(conn);
            Pareja pareja = new Pareja(conn);
            
            ArrayList<Apadrinados> apadrinados = apadrinado.obtenerApadrinadoIdPadrino(idPadrino);
            ArrayList<Apadrinados> apadrinadosPareja = new ArrayList<Apadrinados>();
            int idPareja = pareja.obtenerIdPareja(idPadrino);
            
            if(idPareja!=-1){
                 apadrinadosPareja = apadrinado.obtenerApadrinadoIdPareja(idPareja);
                 if (apadrinadosPareja!=null && !apadrinadosPareja.isEmpty()){
                     apadrinados.add(apadrinadosPareja.get(0));
                 }
            }
            
            if ( apadrinados !=null && !apadrinados.isEmpty()){
                hayApadrinados = true;
                session.setAttribute("apadrinados", apadrinados);
                
            }
            
            session.setAttribute("hayApadrinados", hayApadrinados);
            request.getRequestDispatcher("CuentaPadrino").forward(request, response);
            
        }
        
        
        //Si no, se redirige a la pagina correspondiente
        else{
            request.setAttribute("badlogin",true);
            request.getRequestDispatcher("IniciaSesion").forward(request, response);
            
        }

       
        
}
     
         
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
         //El false es para que no cree una sesion, y si es nulo, no existe sesion
       if(request.getSession(false) != null){
           HttpSession session = request.getSession();
           
           //Checo si es nulo primero
  
           if(session.getAttribute("goodlogin") == null){
               System.out.println("sesion nula");
               request.getRequestDispatcher("IniciaSesion").forward(request, response);
           }
          
           
           else if((Boolean)session.getAttribute("goodlogin") == true){
               String logout = request.getParameter("logout");
               if(logout!=null && logout.equals("")){
                   request.getRequestDispatcher("CuentaPadrino").forward(request, response);
               }
               
               else if(logout!=null && logout.equals("true")){
                   session.invalidate();
                   request.logout();
                   request.getRequestDispatcher("IniciaSesion").forward(request, response);
               }
               
               else{
                   session.invalidate();
                   request.logout();
                   request.getRequestDispatcher("IniciaSesion").forward(request, response);
               }
               
           }
           
           
           
       }
       
       else{
           System.out.println("Suppity");
       }
        
    }

}
