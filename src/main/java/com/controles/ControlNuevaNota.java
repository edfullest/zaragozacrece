/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controles;

import com.conexion.Conexion;
import com.entidades.Administradores;
import com.entidades.Notas;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
public class ControlNuevaNota extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
      
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        //El false es para que no cree una sesion, y si es nulo, no existe sesion
       if(request.getSession(false) != null){
           HttpSession session = request.getSession();
           
           //Se manda a admin si es nula la sesion
           if(session.getAttribute("goodlogin") == null){
               request.getRequestDispatcher("admin").forward(request, response);
           }
           
           //Si existe la sesion, y es un buen login, entonces se crea la nota
           if((Boolean)session.getAttribute("goodlogin") == true){
               
                    //Creo la conexion a la base de datos
               
               try{
                   Conexion conn = new Conexion();
                   session = request.getSession(true);
                   request.setCharacterEncoding("UTF-8");
               
                   
                   String titulo = request.getParameter("titulo");
                   String nombre = request.getParameter("nombre");
                   String nota = request.getParameter("editor1");
                   ;
                   
                   //Obtengo la fecha actual
                   SimpleDateFormat sdf = new SimpleDateFormat();
                   Date d = new Date();
                   sdf.applyPattern("yyyy-MM-dd");
                   String newFecha = sdf.format(d);
                   ;
                   Date fechaPublicacion = sdf.parse(newFecha);
        
                   //Obtendo el id del Admin
                   String admi = request.getParameter("idAdmin");
                   
                   int idAdmin = Integer.parseInt(request.getParameter("idAdmin"));

                   Notas note = new Notas(conn);
                   
                   note.nuevaNota(fechaPublicacion, titulo, nombre, nota, idAdmin);
                   request.setAttribute("goodnote",true);
                   request.getRequestDispatcher("Notas").forward(request, response);
                   
               }
               
               catch (Exception e){}
               
               
           }
           
           //Si no es buen login, entonces se manda a admin
           else if((Boolean)session.getAttribute("goodlogin") == false){
               session.invalidate();
               request.logout();
               request.getRequestDispatcher("admin").forward(request, response);
           }
           
           
           
           
       }

     }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
