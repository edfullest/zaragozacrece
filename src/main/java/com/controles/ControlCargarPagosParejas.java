/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.controles;

import com.conexion.Conexion;
import com.entidades.Apadrinados;
import com.entidades.Pago;
import com.entidades.Pago_pareja;
import com.entidades.Suscripcion;
import com.entidades.Suscripcion_pareja;
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
 * cargarPagosParejas
 * @author Lalo Serna
 */
public class ControlCargarPagosParejas extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
                
                String tipo = request.getParameter("tipo");
                
                if(tipo!=null && tipo.equals("cargarPagos")){
                    Conexion conn = new Conexion();
                    Pago_pareja pago = new Pago_pareja(conn);
                    //Sistema de paginado
                    int paginaActual = 1;
                    int pagosPorPagina = 20;
                    if(request.getParameter("paginaActual") != null)
                        paginaActual = Integer.parseInt(request.getParameter("paginaActual"));
                    
                    
                    
                    
                    int numPagos = pago.getNumeroPagos();
                    
                    ArrayList<Pago_pareja> pagospareja = pago.mostrarPagos((paginaActual-1)*pagosPorPagina,pagosPorPagina);
                    
                    ArrayList<String> nombreColumnas = new ArrayList<String>();
                    nombreColumnas.add("idPago");
                    nombreColumnas.add("Correo 1");
                    nombreColumnas.add("Correo 2");
                    nombreColumnas.add("Fecha de Pago 1");
                    nombreColumnas.add("Fecha de Pago 2");
                    nombreColumnas.add("");
                    System.out.println("yeahbaby");
                    
                    int numPaginas = (int)Math.ceil(numPagos*1.0/pagosPorPagina);
                    
                    request.setAttribute("pagos", pagospareja);
                    request.setAttribute("nombreColumnas", nombreColumnas);
                    request.setAttribute("numPaginas", numPaginas);
                    request.setAttribute("paginaActual", paginaActual);
                    request.getRequestDispatcher("pagosDeParejas").forward(request, response);
                    
                }
                
                else if(tipo!=null && tipo.equals("redirigirAsignar")){
                    int idPagoPareja = Integer.parseInt(request.getParameter("idPagoPareja"));
                    int idPareja = Integer.parseInt(request.getParameter("idPareja"));
                    String fechaPago1 = request.getParameter("fechaPago1");
                    String fechaPago2 = request.getParameter("fechaPago2");
                    
                    
                    Conexion conn = new Conexion();
                    Apadrinados apadrinado = new Apadrinados(conn);
                    
                    //Sistema de paginado
                    int paginaActual = 1;
                    int personasPorPagina = 20;
                    if(request.getParameter("paginaActual") != null)
                        paginaActual = Integer.parseInt(request.getParameter("paginaActual"));
                    
                    ArrayList<String> nombreColumnas = new ArrayList<String>();
                    nombreColumnas.add("ID");
                    nombreColumnas.add("Nombre");
                    nombreColumnas.add("Comunidad");
                    nombreColumnas.add("");
                    
                    //Lista de objetos del tipo Nota
                    //Se obtienen todas las notas
                    ArrayList<Apadrinados> apadrinados = apadrinado.obtenerApadrinadosSinPadrinos((paginaActual-1)*personasPorPagina,personasPorPagina);
                    int numApadrinados = apadrinado.getNumeroApadrinadosSinPadrinos();
                    
                    int numPaginas = (int)Math.ceil(numApadrinados*1.0/personasPorPagina);
                    
                    request.setAttribute("apadrinados", apadrinados);
                    request.setAttribute("nombreColumnas", nombreColumnas);
                    request.setAttribute("numPaginas", numPaginas);
                    request.setAttribute("paginaActual", paginaActual);
                    request.setAttribute("idPagoPareja", idPagoPareja);
                    request.setAttribute("fechaPago1", fechaPago1);
                    request.setAttribute("fechaPago2", fechaPago2);
                    request.setAttribute("idPareja", idPareja);
                    request.getRequestDispatcher("asignarApadrinadoPareja").forward(request, response);
                    
                    
                }
                
                else if(tipo!=null&& tipo.equals("asignar")){
                    int idPagoPareja = Integer.parseInt(request.getParameter("idPagoPareja"));
                    int idPareja = Integer.parseInt(request.getParameter("idPareja"));
                    String fechaPago1 = request.getParameter("fechaPago1");
                    String fechaPago2 = request.getParameter("fechaPago2");
                    int idApadrinado = Integer.parseInt(request.getParameter("idApadrinado"));
                    Date fechaPagoUno = new Date();
                    Date fechaPagoDos = new Date();
                    Date fechaUltimoPago = new Date();
                    try{
                        DateFormat formatter ;
                        
                        formatter = new SimpleDateFormat("yyyy-MM-dd");
                        fechaPagoUno = formatter.parse(fechaPago1);
                        fechaPagoDos = formatter.parse(fechaPago2);
                        
                        if(fechaPagoUno.compareTo(fechaPagoDos)>0){
                            fechaUltimoPago=fechaPagoUno;
                        }else if(fechaPagoUno.compareTo(fechaPagoDos)<0){
                            fechaUltimoPago = fechaPagoDos;
                        }
                        else{
                            fechaUltimoPago = fechaPagoUno;
                        }
                    }
                    
                    catch(Exception e){}
                    Conexion conn = new Conexion();
                    Suscripcion_pareja suscripcion = new Suscripcion_pareja(conn);
                    Apadrinados apadrinado = new Apadrinados(conn);
                    Pago_pareja pago = new Pago_pareja(conn);
                    
                    suscripcion.nuevaSuscripcion(idPareja, idPagoPareja, idApadrinado, fechaUltimoPago);
                    apadrinado.asignarPareja(idPareja, idApadrinado);
                    pago.acreditarPago(idPagoPareja);
                    
                    request.setAttribute("exito", true);
                    
                    request.getRequestDispatcher("pagosDeParejas?tipo=cargarPagos").forward(request, response);
                    
                    
                }
                
                
                
            }
            
        }
        
        
    }
    
    
    
}
