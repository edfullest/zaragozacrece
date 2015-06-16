/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controles;

import com.conexion.Conexion;
import com.entidades.Notas;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lalo Serna
 */
public class ControlCargarNotas extends HttpServlet {
 
    public void doGet(HttpServletRequest request, HttpServletResponse response)             
                  throws ServletException, IOException {
    
    
        Conexion conn = new Conexion();
        Notas note = new Notas(conn);
        
        //Sistema de paginado
        int paginaActual = 1;
        int notasPorPagina = 5;
        if(request.getParameter("paginaActual") != null)
            paginaActual = Integer.parseInt(request.getParameter("paginaActual"));
        
       
        
        //Lista de objetos del tipo Nota
        //Se obtienen todas las notas
        ArrayList<Notas> notas = note.obtenerNotas((paginaActual-1)*notasPorPagina,notasPorPagina);
        int numNotas = note.getNumeroNotas();
        
        int numPaginas = (int)Math.ceil(numNotas*1.0/notasPorPagina);
        
        request.setAttribute("notas", notas);
        request.setAttribute("numPaginas", numPaginas);
        request.setAttribute("paginaActual", paginaActual);
        
        System.out.println(numPaginas);
        System.out.println(paginaActual);
        RequestDispatcher rd = getServletContext()
                .getRequestDispatcher("/Blog");
        
        rd.forward(request, response);
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
