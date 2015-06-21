/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.controles;

import com.conexion.Conexion;
import com.entidades.Apadrinados;
import com.entidades.Entrada;
import com.entidades.Notas;
import com.entidades.Padrino;
import com.entidades.Pago;
import com.entidades.Pago_pareja;
import com.entidades.Pareja;
import com.entidades.Suscripcion;
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
public class ControlCargarPagos extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        
        
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
            
            //Redirigo a paypal
            else if((Boolean)session.getAttribute("goodlogin") == true){
                
                String tipo = request.getParameter("tipo");
                
                if(tipo!=null && tipo.equals("cargarPagos")){
                    Conexion conn = new Conexion();
                    Pago pago = new Pago(conn);
                    //Sistema de paginado
                    int paginaActual = 1;
                    int pagosPorPagina = 20;
                    if(request.getParameter("paginaActual") != null)
                        paginaActual = Integer.parseInt(request.getParameter("paginaActual"));
                    
                    
                    
                    
                    int numPagos = pago.getNumeroPagos();
                    
                    ArrayList<Pago> pagos = pago.mostrarPagos((paginaActual-1)*pagosPorPagina,pagosPorPagina);
                    
                    ArrayList<String> nombreColumnas = new ArrayList<String>();
                    nombreColumnas.add("idPago");
                    nombreColumnas.add("Correo");
                    
                    nombreColumnas.add("Fecha de Pago");
                    
                    nombreColumnas.add("");
                    System.out.println("yeahbaby");
                    
                    int numPaginas = (int)Math.ceil(numPagos*1.0/pagosPorPagina);
                    
                    request.setAttribute("pagos", pagos);
                    request.setAttribute("nombreColumnas", nombreColumnas);
                    request.setAttribute("numPaginas", numPaginas);
                    request.setAttribute("paginaActual", paginaActual);
                    request.getRequestDispatcher("cargarPagos").forward(request, response);
                    
                }
                
                else if(tipo!=null && tipo.equals("redirigirAsignar")){
                    int idPago = Integer.parseInt(request.getParameter("idPago"));
                    int idPadrino = Integer.parseInt(request.getParameter("idPadrino"));
                    String fechaPago = request.getParameter("fechaPago");
                    System.out.println(fechaPago);
                    
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
                    request.setAttribute("idPago", idPago);
                    request.setAttribute("fechaPago", fechaPago);
                    request.setAttribute("idPadrino", idPadrino);
                    request.getRequestDispatcher("asignarApadrinado").forward(request, response);
                    
                    
                }
                
                else if(tipo!=null&& tipo.equals("asignar")){
                    int idPago = Integer.parseInt(request.getParameter("idPago"));
                    int idPadrino = Integer.parseInt(request.getParameter("idPadrino"));
                    int idApadrinado = Integer.parseInt(request.getParameter("idApadrinado"));
                    String fecha = (request.getParameter("fechaPago"));
                    System.out.println("la fecha"+fecha);
                    Date fechaUltimoPago = new Date();
                    try{
                        DateFormat formatter ;
                        
                        formatter = new SimpleDateFormat("yyyy-MM-dd");
                        fechaUltimoPago = formatter.parse(fecha);
                    }
                    
                    catch(Exception e){}
                    Conexion conn = new Conexion();
                    Suscripcion suscripcion = new Suscripcion(conn);
                    Apadrinados apadrinado = new Apadrinados(conn);
                    Pago pago = new Pago(conn);
                    
                    suscripcion.nuevaSuscripcion(idPadrino, idPago, idApadrinado, fechaUltimoPago);
                    apadrinado.asignarPadrino(idPadrino, idApadrinado);
                    pago.acreditarPago(idPago);
                    
                    request.setAttribute("exito", true);
                    
                    request.getRequestDispatcher("ControlCargarPagos?tipo=cargarPagos").forward(request, response);
                    
                    
                }
                
                else if (tipo!=null && tipo.equals("cargarTodosPadrinados")){
                    Conexion conn = new Conexion();
                    Padrino padrino = new Padrino(conn);
                    
                    
                    
                    //Sistema de paginado
                    int paginaActual = 1;
                    int padrinosPorPagina = 20;
                    if(request.getParameter("paginaActual") != null)
                        paginaActual = Integer.parseInt(request.getParameter("paginaActual"));
                    

                    ArrayList<Padrino> padrinos = padrino.obtenerTodosPadrinos((paginaActual-1)*padrinosPorPagina,padrinosPorPagina);
                    int numPadrinos = padrino.getNumeroPadrinos();
                    
                    int numPaginas = (int)Math.ceil(numPadrinos*1.0/padrinosPorPagina);
                    
                    ArrayList<String> nombreColumnas = new ArrayList<String>();
                    nombreColumnas.add("ID");
                    nombreColumnas.add("Nombre");
                    nombreColumnas.add("Correo");
                    nombreColumnas.add("Celular");
                    nombreColumnas.add("");
                    
                    request.setAttribute("nombreColumnas", nombreColumnas);
                    request.setAttribute("padrinos", padrinos);
                    request.setAttribute("numPaginas", numPaginas);
                    request.setAttribute("paginaActual", paginaActual);
                    
                    request.getRequestDispatcher("todosPadrinos").forward(request, response);
                    
                    
                }
                
                else if (tipo!=null && tipo.equals("redirigirCrearPago")){
                    
                    String correo = request.getParameter("correo");
                    String nombreCompleto = request.getParameter("nombreCompleto");
                    int idPadrino = Integer.parseInt(request.getParameter("idPadrino"));
                    
                    request.setAttribute("correoPadrino", correo);
                    request.setAttribute("nombreCompleto", nombreCompleto);
                    request.setAttribute("idPadrino", idPadrino);
                    
                    
                    
                    
                    request.getRequestDispatcher("crearPagoPadrino").forward(request, response);
                    
                }
                
                else if (tipo!=null && tipo.equals("asignarNuevoPago")){
                    
                    Conexion conn = new Conexion();
                    Pago pago = new Pago(conn);
                    int idPadrino = Integer.parseInt(request.getParameter("idPadrino"));
                    String fechaPago = request.getParameter("fechaPago");
                    Date fechaDePago = dateConverter(fechaPago);
                    
                    pago.nuevoPago(idPadrino, -1, fechaDePago);
                    
                    request.setAttribute("exito", true);
                    request.getRequestDispatcher("ControlCargarPagos?tipo=cargarTodosPadrinados").forward(request, response);
                    
                }
                
                
                
                
                
            }
            
        }
        
        
    }
    
    private Date dateConverter(String oldDateString){
        
        final String OLD_FORMAT = "dd.MM.yyyy";
        final String NEW_FORMAT = "yyyy-MM-dd";
        String newDateString;
        Date dModificado = new Date();
        try{
            SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
            Date d = sdf.parse(oldDateString);
            sdf.applyPattern(NEW_FORMAT);
            newDateString = sdf.format(d);
            dModificado = sdf.parse(newDateString);
        }
        catch(Exception e){
            
        }
        return dModificado;
    }
    
    
    
}


    

