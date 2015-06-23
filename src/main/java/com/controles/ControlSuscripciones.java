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
import com.entidades.Pago;
import com.entidades.Pago_pareja;
import com.entidades.Pareja;
import com.entidades.Suscripcion;
import com.entidades.Suscripcion_pareja;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
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
         
                request.getRequestDispatcher("IniciaSesion").forward(request, response);
            }
            
            
            else if((Boolean)session.getAttribute("goodlogin") == true){
                Conexion conn = new Conexion();
                
                String pago = (String)request.getAttribute("pago");
    
                session.getAttribute("exitoso");
                boolean exitoso=false;
                if(session.getAttribute("exitoso")!=null){
                    exitoso=(Boolean)session.getAttribute("exitoso");
                }
                
                String error = (String)request.getAttribute("error");
                //Si viene de apadrinar en parejas..
                if( pago!=null && pago.equals("nuevo")){
                    request.getRequestDispatcher("renovar").forward(request, response);
                }
                
                
                //Se hace la renovacion de la suscripcion
                else if (pago!=null && pago.equals("viejo") &&  exitoso && error==null ){
                    
                    boolean suscripcionPareja = (Boolean)session.getAttribute("esSuscripcionPareja");
                    int idApadrinado = Integer.parseInt((String)session.getAttribute("idApadrinado"));
                    int idSuscripcion = Integer.parseInt((String)session.getAttribute("idSuscripcion"));
               
                    int idPadrino = (Integer)session.getAttribute("idPadrino");
                    
                    Date fechaPago = new Date();
                        try{
                            SimpleDateFormat sdf = new SimpleDateFormat();
                            Date d = new Date();
                            sdf.applyPattern("yyyy-MM-dd");
                            String newFecha = sdf.format(d);
                            ;
                            fechaPago = sdf.parse(newFecha);
                        }
                        
                        catch(Exception e){}
                        //Obtengo la fecha actual
                    
                    if(suscripcionPareja){
                        
                        int idPareja = (Integer)session.getAttribute("idPareja");
                        
                        Pago_pareja pagopareja = new Pago_pareja(conn);
                        Pareja pareja = new Pareja(conn);
                        Suscripcion_pareja suscripcion= new Suscripcion_pareja(conn);
                        
                        ArrayList<String> idsPadrinos= pareja.obtenerConIDPareja(idPareja);
                        boolean esPadrino1 = false;
                        boolean esPadrino2 = false;
                        
                        
                        
                        if(Integer.parseInt(idsPadrinos.get(0))==idPadrino){
                            esPadrino1 = true;
                        }
                        else if(Integer.parseInt(idsPadrinos.get(1))==idPadrino){
                            esPadrino2 = true;
                        }
                        
                        Pago_pareja unpagoNoAcreditado = pagopareja.obtenerPagoConApadrinado(idPareja, idApadrinado);
                        
                        if(unpagoNoAcreditado!=null && unpagoNoAcreditado.isPago1() && !unpagoNoAcreditado.isPago2()){
                            int idPagoPareja = unpagoNoAcreditado.getIdPagoPareja();
                            pagopareja.actualizarPago2(idPareja, idApadrinado, true, fechaPago);
                            pagopareja.acreditarPago(idPagoPareja);
                            
                            suscripcion.actualizarFechaPago(idSuscripcion,idPagoPareja,fechaPago);
                         
                        }
                        
                        else if(unpagoNoAcreditado!=null && unpagoNoAcreditado.isPago2() && !unpagoNoAcreditado.isPago1()){
                            int idPagoPareja = unpagoNoAcreditado.getIdPagoPareja();
                            pagopareja.actualizarPago1(idPareja, idApadrinado, true, fechaPago);
                            pagopareja.acreditarPago(idPagoPareja);
                            
                            suscripcion.actualizarFechaPago(idSuscripcion,idPagoPareja,fechaPago);
                            
                            
                        }
                        
                        else if(unpagoNoAcreditado == null){
                            
                            if(esPadrino1){
                                pagopareja.nuevoPago1(idPareja, idApadrinado, true, fechaPago);
                            }
                            else{
                                pagopareja.nuevoPago2(idPareja, idApadrinado, true, fechaPago);
                            }
                            
                        }
                        
                        
                    }
                    
                    
                    else{
                        
                        Pago pagPago = new Pago(conn);
                        Suscripcion suscripcion = new Suscripcion(conn);
                        
                        pagPago.nuevoPago(idPadrino, idApadrinado, fechaPago);
                        int idPago = pagPago.mostrarPagoSinAcreditar(idPadrino);
                        pagPago.acreditarPago(idPago);
                        
                        suscripcion.actualizarFechaPago(idSuscripcion, idPago, fechaPago);
                        
                        
                    }
                    
                    
                    request.setAttribute("correo",(String)session.getAttribute("thiscorreo"));
                    request.setAttribute("password",(String)session.getAttribute("thispassword"));
                    session.removeAttribute("esSuscripcionPareja");
                    session.removeAttribute("idApadrinado");
                    session.removeAttribute("idSuscripcion");
                    request.getRequestDispatcher("ControlLogInPadrino?redireccionar=redireccionar").forward(request, response);
                    session.removeAttribute("exitoso");
                    session.removeAttribute("error");
                    session.removeAttribute("tipo");
                }
                
                else if(pago==null){
                    
                    
                    String tipo = request.getParameter("tipoSuscripcion");
                    String accion = request.getParameter("accion");
                    String idSuscripcion = request.getParameter("idSuscripcion");
                    String idApadrinado = request.getParameter("idApadrinado");
                    session.setAttribute("idApadrinado", idApadrinado);
                    Suscripcion suscripcion = new Suscripcion(conn);
                    ArrayList<Suscripcion> suscripciones = (ArrayList<Suscripcion>)session.getAttribute("suscripciones");
                    
                    
                    
                    if(tipo!=null && tipo.equals("pareja")){
                        
                        session.setAttribute("esSuscripcionPareja", true);
                        Suscripcion_pareja unasuscripcionpareja = (Suscripcion_pareja)session.getAttribute("suscripcionPareja");
                        if(Integer.parseInt(idSuscripcion)!=unasuscripcionpareja.getIdSuscripcion()){
                            
                            request.getRequestDispatcher("ControlPanelPadrino?opcion=4").forward(request, response);
                        }
                        
                        
                        else if(accion!=null && accion.equals("renovar")){
                            session.setAttribute("tipo", "renovar");
                            session.setAttribute("idSuscripcion", idSuscripcion);
                            request.getRequestDispatcher("pagarPaypal").forward(request, response);
                        }
                        else if(accion!=null && accion.equals("activar")){
                            session.setAttribute("idSuscripcion", idSuscripcion);
                            session.setAttribute("tipo", "activar");
                            request.getRequestDispatcher("pagarPaypal").forward(request, response);
                        }
                        
                        
                    }
                    
                    else if(tipo!=null && tipo.equals("unico")){
                        
                        session.setAttribute("esSuscripcionPareja", false);
                     
                        boolean encontro=false;
                        int i=0;
                        //Si el id que puso no esta...
                   
                        do{
                            Suscripcion unasuscripcion = suscripciones.get(i);
                           
                       
                            if(unasuscripcion.getIdSuscripcion()==Integer.parseInt(idSuscripcion)){
                                
                                encontro = true;
                                session.setAttribute("idSuscripcion",idSuscripcion);
                            }
                            i++;
                            
                        }while(!encontro && i<suscripciones.size());
                        
                        if(!encontro){
                            
                            request.getRequestDispatcher("ControlPanelPadrino?opcion=4").forward(request, response);
                            
                        }
                        
                        else if(accion!=null && accion.equals("renovar")){
                            session.setAttribute("idSuscripcion", idSuscripcion);
                            session.setAttribute("tipo", "renovar");
                            request.getRequestDispatcher("pagarPaypal").forward(request, response);
                        }
                        else if(accion!=null && accion.equals("activar")){
                            session.setAttribute("idSuscripcion", idSuscripcion);
                            session.setAttribute("tipo", "activar");
                            request.getRequestDispatcher("pagarPaypal").forward(request, response);
                        }
                    }
                    
                    
                    
                    
                    
                    
                }//Termina pago==null
                
                
                
                
            }//Termina if de la sesion
            
        }
        
    }
    
    
}
