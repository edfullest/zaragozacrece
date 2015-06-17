/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.controles;

import com.clases.Carta;
import com.conexion.Conexion;
import com.entidades.Apadrinados;
import com.entidades.Entrada;
import com.entidades.Notas;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Lalo Serna
 */
public class ControlCargarInfo extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //El false es para que no cree una sesion, y si es nulo, no existe sesion
        if(request.getSession(false) != null){
            HttpSession session = request.getSession();
            
            //Checo si es nulo primero
            
            if(session.getAttribute("goodlogin") == null){
                System.out.println("sesion nula");
                request.getRequestDispatcher("IniciaSesion").forward(request, response);
            }
            
            //Redirigo a paypal
            else if((Boolean)session.getAttribute("goodlogin") == true){
                Conexion conn = new Conexion();
                Entrada entrada  = new Entrada (conn);
                Apadrinados apadrinado  = new Apadrinados (conn);
                
                int idApadrinado = Integer.parseInt(request.getParameter("idApadrinado"));
                int idPadrino = (Integer)session.getAttribute("idPadrino");
                boolean padrinoCorrecto = false;
                boolean parejaCorrecta = false;
                
                ArrayList<Apadrinados> apadrinadosDeLaCuenta = (ArrayList<Apadrinados>)session.getAttribute("apadrinados");
                int i=0;
                do{
                    
                    Apadrinados unapadrinado =apadrinadosDeLaCuenta.get(i);
                    if(unapadrinado.getIdApadrinado()==idApadrinado){
                        padrinoCorrecto = true;
                    }
                    i++;
                    
                }while(!padrinoCorrecto && i<apadrinadosDeLaCuenta.size());
                
                
                if(padrinoCorrecto){
                    ArrayList<Entrada> entradas = entrada.obtenerEntradas(idApadrinado);
                    ArrayList<String> datos = apadrinado.obtenerDatosApadrinado(idApadrinado);
                    
                    //Sistema de paginado
                    int paginaActual = 1;
                    int cartasPorPagina = 5;
                    boolean cambio = false;
                    if(request.getParameter("paginaActual") != null){
                        paginaActual = Integer.parseInt(request.getParameter("paginaActual"));
                        cambio = true;
                        
                    }
                        
          
                    
                    
                  
                    //Se obtienen todas las cartas
                    ArrayList<Carta> cartas = entrada.obtenerCartas((paginaActual-1)*cartasPorPagina,cartasPorPagina,idApadrinado);
                    int numCartas = entrada.getNumCartas(idApadrinado);
                    
                    int numPaginas = (int)Math.ceil(numCartas*1.0/cartasPorPagina);
                    
                    request.setAttribute("cartas", cartas);
                    request.setAttribute("numPaginas", numPaginas);
                    request.setAttribute("paginaActual", paginaActual);
                    request.setAttribute("cambio",cambio);
                    request.setAttribute("entradas",entradas);
                    request.setAttribute("nombreApadrinado",datos.get(0));
                    request.setAttribute("comunidad",datos.get(1));
                    request.setAttribute("idApadrinado", idApadrinado);
                    
                    request.getRequestDispatcher("miApadrinado").forward(request, response);
                    
                }
                else{
                    request.getRequestDispatcher("CuentaPadrino").forward(request, response);
                }
                
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
