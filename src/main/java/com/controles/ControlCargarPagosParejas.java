/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.controles;

import com.conexion.Conexion;
import com.entidades.Apadrinados;
import com.entidades.Padrino;
import com.entidades.Pago;
import com.entidades.Pago_pareja;
import com.entidades.Pareja;
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
                    
                    request.getRequestDispatcher("cargarPagosParejas?tipo=cargarPagos").forward(request, response);
                    
                    
                }
                
                else if (tipo!=null && tipo.equals("cargarTodasParejas")){
                    Conexion conn = new Conexion();
                    Pareja pareja = new Pareja(conn);
                    Suscripcion_pareja suscripcionpareja = new Suscripcion_pareja(conn);
                    
                    
                    //Sistema de paginado
                    int paginaActual = 1;
                    int parejasPorPagina = 20;
                    if(request.getParameter("paginaActual") != null)
                        paginaActual = Integer.parseInt(request.getParameter("paginaActual"));
                    
                    
                    ArrayList<Pareja> parejasAQuitar = pareja.obtenerTodasParejas((paginaActual-1)*parejasPorPagina,parejasPorPagina);
                    ArrayList<String> parejasConSuscripcion = suscripcionpareja.obtenerTodasSuscripciones();
                    ArrayList<Pareja> parejas = new ArrayList<Pareja> ();
                    
                    
                    for(int i=0;i<parejasAQuitar.size();i++){
                        Pareja unaPareja = parejasAQuitar.get(i);
                        boolean entro=false;
                        for (int j=0;j<parejasConSuscripcion.size();j++){
                            int idParejaSuscripcion = Integer.parseInt(parejasConSuscripcion.get(j));
                            
                            if(unaPareja.getIdPareja() == idParejaSuscripcion ){
                                
                                entro = true;
                                break;
                            }
                            
                        }
                        
                        if(!entro){
                            parejas.add(unaPareja);
                            
                        }
                        
                        
                        
                    }
                    int numParejas = parejas.size();
                    
                    int numPaginas = (int)Math.ceil(numParejas*1.0/parejasPorPagina);
                    
                    ArrayList<String> nombreColumnas = new ArrayList<String>();
                    nombreColumnas.add("ID");
                    nombreColumnas.add("Correo1");
                    nombreColumnas.add("Correo2");
                    nombreColumnas.add("");
                    
                    request.setAttribute("nombreColumnas", nombreColumnas);
                    request.setAttribute("parejas", parejas);
                    request.setAttribute("numPaginas", numPaginas);
                    request.setAttribute("paginaActual", paginaActual);
                    
                    request.getRequestDispatcher("todasParejas").forward(request, response);
                    
                    
                }
                
                
                else if (tipo!=null && tipo.equals("redirigirSeleccionarPadrino")){
                    
                    
                    int idPadrino1 = Integer.parseInt(request.getParameter("idPadrino1"));
                    int idPadrino2 = Integer.parseInt(request.getParameter("idPadrino2"));
                    int idPareja = Integer.parseInt(request.getParameter("idPareja"));
                    String correo1 = request.getParameter("correo1");
                    String correo2 = request.getParameter("correo2");
                    
                    
                    Conexion conn = new Conexion();
                    Padrino padrino = new Padrino(conn);
                    Pago_pareja pagopareja = new Pago_pareja(conn);
                    
                    Pago_pareja pagoSinApadrinado = pagopareja.obtenerPagoSinApadrinado(idPareja);
                    String nombre1 = padrino.getNombre(idPadrino1);
                    String nombre2 = padrino.getNombre(idPadrino2);
                    String celular1 = padrino.obtenerCelular(idPadrino1);
                    String celular2 = padrino.obtenerCelular(idPadrino2);
                    
                    System.out.println("nombre1 " +nombre1);
                    System.out.println("nombre2 " +nombre2);
                    System.out.println("celular1 " +celular1);
                    System.out.println("celular2 " +celular2);
                    
                    System.out.println(pagoSinApadrinado.isPago1());
                    System.out.println(pagoSinApadrinado.isPago2());
                    
                    //Ya pago el primer padrino
                    if(pagoSinApadrinado!=null && pagoSinApadrinado.isPago1() && !pagoSinApadrinado.isPago2()){
                        request.setAttribute("correo2", correo2);
                        request.setAttribute("nombre2", nombre2);
                        request.setAttribute("celular2", celular2);
                        request.setAttribute("idPadrino2", idPadrino2);
                        request.setAttribute("pago1",true);
                        request.setAttribute("pago2",false);
                        
                    }
                    
                    //Ya pago el segundo padrino
                    else if (pagoSinApadrinado!=null && pagoSinApadrinado.isPago2() && !pagoSinApadrinado.isPago1()){
                        request.setAttribute("correo1", correo1);
                        request.setAttribute("nombre1", nombre1);
                        request.setAttribute("celular1", celular1);
                        request.setAttribute("idPadrino1", idPadrino1);
                        request.setAttribute("pago1",false);
                        request.setAttribute("pago2",true);
                        
                    }
                    //Ambos pagaron
                    else if(pagoSinApadrinado!=null && pagoSinApadrinado.isPago1() && pagoSinApadrinado.isPago2()){
                        
                    }
                    //Ninguno ha pagado
                    else{
                        System.out.println("nadie ha pagado");
                        request.setAttribute("correo1", correo1);
                        request.setAttribute("correo2", correo2);
                        request.setAttribute("nombre1", nombre1);
                        request.setAttribute("nombre2", nombre2);
                        request.setAttribute("celular1", celular1);
                        request.setAttribute("celular2", celular2);
                        request.setAttribute("idPadrino1", idPadrino1);
                        request.setAttribute("idPadrino2", idPadrino2);
                        request.setAttribute("pago1",false);
                        request.setAttribute("pago2",false);
                    }
                    
                    ArrayList<String> nombreColumnas = new ArrayList<String>();
                    nombreColumnas.add("ID");
                    nombreColumnas.add("Nombre");
                    nombreColumnas.add("Correo");
                    nombreColumnas.add("Celular");
                    nombreColumnas.add("");
                    
                    request.setAttribute("nombreColumnas",nombreColumnas);
                    request.setAttribute("idPareja", idPareja);
                    request.getRequestDispatcher("seleccionarPadrino").forward(request, response);
                    
                }
                
                else if (tipo!=null && tipo.equals("redirigirCrearPago")){
                    String idPadrino1 = request.getParameter("idPadrino1");
                    String idPadrino2 = request.getParameter("idPadrino2");
                    String idPareja = request.getParameter("idPareja");
                    String nombreCompleto = request.getParameter("nombreCompleto");
                    String correoPadrino = request.getParameter("correoPadrino");
                    boolean isPago1 = Boolean.parseBoolean(request.getParameter("pago1"));
                    boolean isPago2 = Boolean.parseBoolean(request.getParameter("pago2"));
                    
                    System.out.println("redirigir crear Pago id Padrino 1 "+idPadrino1);
                    System.out.println("redirigir crear Pago id Padrino 2 "+idPadrino2);
                    
                    if(idPadrino1==null){
                        
                        request.setAttribute("idPadrino2",idPadrino2);
                        
                    }
                    
                    else if(idPadrino2==null){
                        request.setAttribute("idPadrino1",idPadrino1);
                    }
                    
                    
                    
                    request.setAttribute("pago1", isPago1);
                    request.setAttribute("pago2", isPago2);
                    request.setAttribute("nombreCompleto",nombreCompleto);
                    request.setAttribute("correoPadrino",correoPadrino);
                    request.setAttribute("idPareja",idPareja);
                    request.getRequestDispatcher("crearPagoPareja").forward(request, response);
                }
                
                else if (tipo!=null && tipo.equals("asignarNuevoPago")){
                    
                    Conexion conn = new Conexion();
                    Pago_pareja pago = new Pago_pareja(conn);
                    int idPareja = Integer.parseInt(request.getParameter("idPareja"));
                    String fechaPago = request.getParameter("fechaPago");
                    Date fechaDePago = dateConverter(fechaPago);
                    Pago_pareja pagodepareja = pago.obtenerPagoSinApadrinado(idPareja);
                    boolean isPago1 = Boolean.parseBoolean(request.getParameter("pago1"));
                    boolean isPago2 = Boolean.parseBoolean(request.getParameter("pago2"));
                    
                    System.out.println(isPago1);
                    System.out.println(isPago2);
                    if(isPago1 && !isPago2){
                        
                        pago.actualizarPago2(idPareja, -1, true, fechaDePago);
                        
                    }
                    
                    else if(isPago2 && !isPago1){
                        
                        pago.actualizarPago1(idPareja, -1, true, fechaDePago);
                    }
                    
                    else if (!isPago2 && !isPago1){
                        
                        if(request.getParameter("idPadrino1")!=null){
                            
                            pago.nuevoPago1(idPareja, -1, true, fechaDePago);
                        }
                        
                        else if(request.getParameter("idPadrino2")!=null){
                            pago.nuevoPago2(idPareja, -1, true, fechaDePago);
                        }
                        
                    }
                    
                    
                    
                    request.setAttribute("exito", true);
                    request.getRequestDispatcher("cargarPagosParejas?tipo=cargarTodasParejas").forward(request, response);
                    
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
