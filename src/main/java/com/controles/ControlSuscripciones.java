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
import com.entidades.Suscripcion_pareja;
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
public class ControlSuscripciones extends HttpServlet {
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
            
            
            else if((Boolean)session.getAttribute("goodlogin") == true){
                Conexion conn = new Conexion();
                
                String pago = (String)request.getAttribute("pago");
                //Si viene de apadrinar en parejas..
                if( pago!=null && pago.equals("nuevo")){
                    request.getRequestDispatcher("redirigirPaypal").forward(request, response);
                }
                
                //Se hace la renovacion de la suscripcion
                else if (pago!=null && pago.equals("viejo")){
                    
                }
                
                else if(pago==null){
                    
                    String lugar = request.getParameter("lugar");
                    String tipo = request.getParameter("tipo");
                    String accion = request.getParameter("accion");
                    String idSuscripcion = request.getParameter("idSuscripcion");
                    String correo = request.getParameter("correo");
                    
                    if(lugar!=null && lugar.equals("misSuscripciones")){
                        session.setAttribute("idSuscripcion",idSuscripcion);
                        session.setAttribute("correo", correo);
                        
                        if(tipo!=null && tipo.equals("pareja")){
                            
                            session.setAttribute("suscripcionPareja", true);
                            if(accion!=null && accion.equals("renovar")){
                                session.setAttribute("tipo", "renovar");
                            }
                            else if(accion!=null && accion.equals("activar")){
                                session.setAttribute("tipo", "activar");
                            }
                            
                            
                        }
                        
                        else if(tipo!=null && tipo.equals("unico")){
                            
                            session.setAttribute("suscripcionPareja", false);
                            if(accion!=null && accion.equals("renovar")){
                                
                                session.setAttribute("tipo", "renovar");
                            }
                            else if(accion!=null && accion.equals("activar")){
                                session.setAttribute("tipo", "activar");
                            }
                        }
                        request.getRequestDispatcher("pagarPaypal").forward(request, response);
                        
                    }
                    
                    
                    else if(lugar!=null && lugar.equals("paypal")){
                        
                        
                        
                        
                    }
                    
                    
                }//Termina pago==null
                
                
                
                
            }//Termina if de la sesion
            
        }
        
    }
    
    
}
