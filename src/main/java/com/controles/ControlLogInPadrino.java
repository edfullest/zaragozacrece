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
import com.entidades.Periodo;
import com.entidades.Suscripcion;
import com.entidades.Suscripcion_pareja;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
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
            Suscripcion suscripcion = new Suscripcion(conn);
            Suscripcion_pareja suscripcionpareja = new Suscripcion_pareja(conn);
            Periodo periodo = new Periodo(conn);
            int idPareja = pareja.obtenerIdPareja(idPadrino);
            ArrayList<Apadrinados> apadrinados = apadrinado.obtenerApadrinadoIdPadrino(idPadrino);
            ArrayList<Apadrinados> apadrinadosPareja = new ArrayList<Apadrinados>();
            ArrayList<Periodo> periodos = new ArrayList<Periodo>();
            ArrayList<Suscripcion> suscripciones = new ArrayList<Suscripcion>();
            Suscripcion_pareja unasuscripcionpareja;
            
            Calendar calFechaFinalAnterior = Calendar.getInstance();
            Calendar calFechaFinalActual = Calendar.getInstance();
            Calendar calFechaInicialActual = Calendar.getInstance();
            
            //Nada mas hay un periodo
            if(periodos.size()==1){
                calFechaFinalAnterior.setTime(periodos.get(0).getFechaFinal());
                calFechaFinalAnterior.add(Calendar.MONTH, 3);
                
                calFechaInicialActual.setTime(periodos.get(0).getFechaInicio());
                calFechaFinalActual.setTime(periodos.get(0).getFechaFinal());
            }
            else if(periodos.size()==2){
                calFechaFinalAnterior.setTime(periodos.get(0).getFechaFinal());
                calFechaFinalAnterior.add(Calendar.MONTH, 3);
                
                calFechaInicialActual.setTime(periodos.get(1).getFechaInicio());
                calFechaFinalActual.setTime(periodos.get(1).getFechaFinal());
            }
            
            suscripciones = suscripcion.obtenerSuscripciones(idPadrino);
            
            if(suscripciones!=null && suscripciones.size()!=0){
                int iNoProblem = 0;
                int iRenovar = 0;
                int iQuitar = 0;
                
                
                for (int i=0;i<suscripciones.size();i++){
                    Suscripcion unasuscripcion = suscripciones.get(i);
                    Calendar calFechaUltimoPago = Calendar.getInstance();
                    calFechaUltimoPago.setTime(unasuscripcion.getFechaUltimoPago());
                    
                    if(calFechaUltimoPago.after(calFechaInicialActual) && calFechaUltimoPago.before(calFechaFinalActual)){
                        iNoProblem++;
                    }
                    else if(calFechaUltimoPago.before(calFechaFinalAnterior)){
                        iRenovar++;
                    }
                    else{
                        
                        int j=0;
                        boolean encontro=false;
                        do{
                            
                            //Se quita ese apadrinado
                            Apadrinados unapadrinado = apadrinados.get(j);
                            
                            if(unapadrinado.getIdApadrinado()==unasuscripcion.getIdApadrinado()){
                                encontro=true;
                                apadrinados.remove(j);
                            }
                            
                            j++;
                            
                        }while(!encontro && j<apadrinados.size());
                        
                        iQuitar++;
                    }
                    
                }
                
                session.setAttribute("iNoProblem", iNoProblem);
                session.setAttribute("iRenovar", iRenovar);
                session.setAttribute("iQuitar", iQuitar);
                session.setAttribute("suscripciones", suscripciones);
                
            }
            
            
            
            if(idPareja!=-1){
                apadrinadosPareja = apadrinado.obtenerApadrinadoIdPareja(idPareja);
                System.out.println("Size"+ apadrinadosPareja.size());
                if (apadrinadosPareja!=null && !apadrinadosPareja.isEmpty()){
                    apadrinados.add(apadrinadosPareja.get(0));
                    
                }
                
                unasuscripcionpareja = suscripcionpareja.obtenerSuscripciones(idPareja);
                
                
                if(unasuscripcionpareja!= null){
                    String advertenciaPareja="";
                    Calendar calFechaUltimoPago = Calendar.getInstance();
                    calFechaUltimoPago.setTime(unasuscripcionpareja.getFechaUltimoPago());
                    
                    if(calFechaUltimoPago.after(calFechaInicialActual) && calFechaUltimoPago.before(calFechaFinalActual)){
                        advertenciaPareja = "todoBien";
                    }
                    else if(calFechaUltimoPago.before(calFechaFinalAnterior)){
                        advertenciaPareja = "renovar";
                    }
                    else{
                        
                        int j=0;
                        boolean encontro=false;
                        do{
                            
                            //Se quita ese apadrinado
                            Apadrinados unapadrinado = apadrinados.get(j);
                            
                            if(unapadrinado.getIdApadrinado()==unasuscripcionpareja.getIdApadrinado()){
                                encontro=true;
                                apadrinados.remove(j);
                            }
                            
                            j++;
                            
                        }while(!encontro && j<apadrinados.size());
   
                        advertenciaPareja = "quitar";
                    }
                    
                    session.setAttribute("advertenciaPareja",advertenciaPareja);
                    session.setAttribute("suscripcionPareja",unasuscripcionpareja);
                    
                }
                
                session.setAttribute("idPareja", idPareja);
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
