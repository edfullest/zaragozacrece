/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.controles;

import com.conexion.Conexion;
import com.entidades.Pago;
import com.entidades.Pago_pareja;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Lalo Serna
 */
public class ControlPagar extends HttpServlet {
    
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
                ;
                request.getRequestDispatcher("IniciaSesion").forward(request, response);
            }
            
            
            else if((Boolean)session.getAttribute("goodlogin") == true){
                
                //Checo los errores de paypal
                
                
                String tipoPago =(String)request.getAttribute("tipoPago");
                String pago =(String)request.getAttribute("pago");
                Boolean exitoso = (Boolean)session.getAttribute("exitoso");
                
                if(tipoPago!=null && tipoPago.equals("paypal")){
                    String errors = (String)session.getAttribute("error");
                    String tipo = (String)request.getAttribute("tipo");
                    
                    //Si viene de apadrinar en parejas..
                    if( pago!=null && pago.equals("nuevo")){
                        request.getRequestDispatcher("redirigirPaypal").forward(request, response);
                    }
                    
                    if(tipo.equals("apadrinapareja")){
                        
                        //Si no hay errores...
                        if(exitoso!=null && exitoso && errors==null){
                            
                            int idPadrino = (Integer)session.getAttribute("idPadrino");
                            int idPareja = (Integer)session.getAttribute("idPareja");
                            //Verifico quien hace el request, si el padrino1 o el padrino2
                            boolean padrino1 = (Boolean)session.getAttribute("padrino1");
                            String respuesta=(String)session.getAttribute("respuesta");
                            int idApadrinado = -1;
                            if(session.getAttribute("idApadrinado")!=null){
                                idApadrinado =(Integer)session.getAttribute("idApadrinado");
                            }
                            try{
                                //La respuesta viene de un padrino1
                                if(respuesta.equals("nadieHaPagado") && padrino1){
                                    
                                    //Se hace el pago correspondiente
                                    //Obtengo la fecha actual
                                    SimpleDateFormat sdf = new SimpleDateFormat();
                                    Date d = new Date();
                                    sdf.applyPattern("yyyy-MM-dd");
                                    String newFecha = sdf.format(d);
                                    
                                    Date fechaPago = sdf.parse(newFecha);
                                    
                                    Conexion conn = new Conexion();
                                    Pago_pareja pagopareja = new Pago_pareja(conn);
                                    
                                    pagopareja.nuevoPago1(idPareja,idApadrinado,true, fechaPago);
                                    request.setAttribute("tipo", tipo);
                                    request.getRequestDispatcher("redirigirPaypal").forward(request, response);
                                    
                                    
                                    
                                }
                                //Se viene de un padrino2
                                else if(respuesta.equals("nadieHaPagado") && !padrino1){
                                    
                                    //Se hace el pago correspondiente
                                    //Obtengo la fecha actual
                                    SimpleDateFormat sdf = new SimpleDateFormat();
                                    Date d = new Date();
                                    sdf.applyPattern("yyyy-MM-dd");
                                    String newFecha = sdf.format(d);
                                    
                                    Date fechaPago = sdf.parse(newFecha);
                                    
                                    Conexion conn = new Conexion();
                                    Pago_pareja pagopareja = new Pago_pareja(conn);
                                    
                                    pagopareja.nuevoPago2(idPareja,idApadrinado,true, fechaPago);
                                    request.setAttribute("tipo", tipo);
                                    request.getRequestDispatcher("redirigirPaypal").forward(request, response);
                                    
                                }
                                
                                //Padrino 1 no pago
                                else if(respuesta.equals("noPagaste") && padrino1){
                                    
                                    //Se hace el pago correspondiente
                                    //Obtengo la fecha actual
                                    SimpleDateFormat sdf = new SimpleDateFormat();
                                    Date d = new Date();
                                    sdf.applyPattern("yyyy-MM-dd");
                                    String newFecha = sdf.format(d);
                                    
                                    Date fechaPago = sdf.parse(newFecha);
                                    
                                    Conexion conn = new Conexion();
                                    Pago_pareja pagopareja = new Pago_pareja(conn);
                                    
                                    pagopareja.actualizarPago1(idPareja,idApadrinado,true, fechaPago);
                                    request.setAttribute("tipo", tipo);
                                    request.getRequestDispatcher("redirigirPaypal").forward(request, response);
                                    
                                }
                                
                                //Padrino 2 no pago
                                else if(respuesta.equals("noPagaste") && !padrino1){
                                    
                                    //Se hace el pago correspondiente
                                    //Obtengo la fecha actual
                                    SimpleDateFormat sdf = new SimpleDateFormat();
                                    Date d = new Date();
                                    sdf.applyPattern("yyyy-MM-dd");
                                    String newFecha = sdf.format(d);
                                    
                                    Date fechaPago = sdf.parse(newFecha);
                                    
                                    Conexion conn = new Conexion();
                                    Pago_pareja pagopareja = new Pago_pareja(conn);
                                    
                                    pagopareja.actualizarPago2(idPareja,idApadrinado,true, fechaPago);
                                    request.setAttribute("tipo", tipo);
                                    request.getRequestDispatcher("redirigirPaypal").forward(request, response);
                                    
                                }
                                
                                
                                
                            }
                            
                            catch(Exception e){}
                            
                        }
                        
                        
                        else{
                            
                            session.removeAttribute("error");
                            
                        }
                        
                    }
                    
                    //Apadrina solo
                    
                    
                    else if(tipo.equals("apadrinasolo")){
                        
                        if(tipoPago!=null && tipoPago.equals("paypal")){
                            
                            //Si no hay errores...
                            if(exitoso!=null && exitoso && errors==null){
                                
                                int idPadrino = (Integer)session.getAttribute("idPadrino");
                                int idApadrinado = -1;
                                if(session.getAttribute("idApadrinado")!=null){
                                    idApadrinado =(Integer)session.getAttribute("idApadrinado");
                                }
                                
                                Conexion conn = new Conexion();
                                Pago pagPago=new Pago(conn);
                                //Se hace el pago correspondiente
                                //Obtengo la fecha actual
                                SimpleDateFormat sdf = new SimpleDateFormat();
                                Date d = new Date();
                                sdf.applyPattern("yyyy-MM-dd");
                                String newFecha = sdf.format(d);
                                Date fechaPago = new Date();
                                try{
                                    fechaPago = sdf.parse(newFecha);
                                }
                                
                                catch(Exception ex){}
                                
                                
                                pagPago.nuevoPago(idPadrino, idApadrinado, fechaPago);
                                request.setAttribute("tipo", tipo);
                                request.getRequestDispatcher("redirigirPaypal").forward(request, response);
                                
                                
                                
                                
                                
                                
                            }
                            
                            
                            else{
                                
                                session.removeAttribute("error");
                                
                            }
                            
                            
                            
                            
                            
                        }
                        
                        
                        
                        
                        
                    }
                    
                }
                
                
               session.removeAttribute("exitoso");
               
            }
            
            
            
            
        }
    }
}


