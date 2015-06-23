/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.controles;

import com.conexion.Conexion;
import com.entidades.Apadrinados;
import com.entidades.Entrada;
import com.entidades.Pago;
import com.entidades.Pago_pareja;
import com.entidades.Pareja;
import java.io.IOException;
import java.io.PrintWriter;
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
public class ControlPanelPadrino extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        //El false es para que no cree una sesion, y si es nulo, no existe sesion
        if(request.getSession(false) != null){
            HttpSession session = request.getSession();
            
            //Se manda a admin si es nula la sesion
            if(session.getAttribute("goodlogin") == null){
                request.getRequestDispatcher("IniciaSesion").forward(request, response);
            }
            
            //Si existe la sesion, y es un buen login, entonces se pueden acceder a los atributos del request
            else if((Boolean)session.getAttribute("goodlogin") == true){
                
                int idPadrino = (Integer)session.getAttribute("idPadrino");

                int opcion;
                //Aqui cambia conforme el contexto, si viene de un control, es getAttribute
                //Si viene d eun jsp, es getParameter
                if(request.getParameter("opcion")!=null){
                    
                    opcion = Integer.parseInt(request.getParameter("opcion"));
                }
                else{
                    
                    opcion = Integer.parseInt((String)request.getAttribute("opcion"));
                }
                
                
                //Si el admin hace el request de nuevoApadrinado, se hace uno nuevo
                if(opcion == 1){
                
                }
                //panelApadrina
                else if(opcion == 2){
               
                    
                    Conexion conn = new Conexion();
                    //Se debe verificar si tiene pareja o no
                    Pareja pareja = new Pareja(conn);
                    
                    ArrayList<String> arrPadrino2=pareja.obtenerDatosPadrino2(idPadrino);
                    
                    //Se busca con .obtenerDatosPadrino1 (ya que probablemente este padrino sea el padrino2 en la pareja)
                    ArrayList<String> arrPadrino1=pareja.obtenerDatosPadrino1(idPadrino);
                    if(arrPadrino2!=null && arrPadrino2.size()!=0){
                        //Muestra menu de pareja
                        request.setAttribute("pareja",true);
                        int idPareja = Integer.parseInt(arrPadrino2.get(0));
                        int idPadrino2 = Integer.parseInt(arrPadrino2.get(1));
                        String correo2= arrPadrino2.get(2);
                        
                        
                        //Si el idPadrino2 es diferente de -1, entonces ese padrino ya esta registrado
                        if(idPadrino2 != -1){
                            
                            //Se procede a buscar su ultimo pago
                            //Y ver quienes han pagado
                            
                            Pago_pareja pagopareja = new Pago_pareja(conn);
                            
                            ArrayList<Pago_pareja> pagospareja = pagopareja.obtenerInfo(idPareja);
                            
                            //Si hay pagos
                            if(pagospareja!=null && pagospareja.size()!=0){
                                
                                boolean pagoAcreditado=true;
                                int i=0;
                                int aux;
                                do{
                                    Pago_pareja pagodepareja= pagospareja.get(i);
                                    pagoAcreditado=pagodepareja.isAcreditado();
                                    aux=i;
                                    i++;
                                    
                                }while(pagoAcreditado && i<pagospareja.size());
                                
                                if(i==pagospareja.size() && pagoAcreditado){
                                    
                                    //Todos los pagos estan acreditados, es decir, ya se tiene apadrinado asignado
                                    //Ya se asigno una suscripcion
                                    request.setAttribute("respuesta", "asignado");
                                    request.setAttribute("correo", correo2);
                                    request.getRequestDispatcher("panelApadrina").forward(request, response);
                                }
                                
                                else if(!pagoAcreditado){
                                    
                                    Pago_pareja pagodepareja=pagospareja.get(aux);
                                    Date fechaPago1=pagodepareja.getFechaPago1();
                                    Date fechaPago2=pagodepareja.getFechaPago2();
                                    int idApadrinado=pagodepareja.getIdApadrinado();
                                    pagodepareja.getIdPareja();
                                    //Ya pagaron ambos padrinos
                                    if(fechaPago1!=null && fechaPago2!=null){
                                        //Se manda un mensaje diciendo que se esta esperando a que se les asigne un apadrinado
                                        
                                        request.setAttribute("respuesta", "esperandoAdmin");
                                        request.setAttribute("correo", correo2);
                                        
                                        request.getRequestDispatcher("panelApadrina").forward(request, response);
                                    }
                                    //Ya pago padrino2, pero este padrino no
                                    else if(fechaPago1 == null && fechaPago2!=null){
                                        
                                        request.setAttribute("respuesta", "noPagaste");
                                        session.setAttribute("correo", correo2);
                                        session.setAttribute("idPareja", idPareja);
                                        session.setAttribute("padrino1", true);
                                        session.setAttribute("respuesta", "noPagaste");
                                        session.setAttribute("idApadrinado", idApadrinado);
                                        request.getRequestDispatcher("panelApadrina").forward(request, response);
                                        
                                    }
                                    //Ya pago este padrino pero el otro no
                                    else if(fechaPago1!=null && fechaPago2==null){
                                        
                                        request.setAttribute("respuesta", "noPagoOtro");
                                        request.setAttribute("correo", correo2);
                                        
                                        request.getRequestDispatcher("panelApadrina").forward(request, response);
                                        
                                    }
                                    
                                    //Nadie ha pagado
                                    else{
                                        
                                        //Si el padrino ya tiene apadrinado asignado, se le asigna a la sesion este atributo
                                        if(request.getAttribute("idApadrinado")!=null){
                                            int idApadrinado1=(Integer)request.getAttribute("idApadrinado");
                                            session.setAttribute("idApadrinado", idApadrinado1);
                                        }
                                        
                                        request.setAttribute("respuesta", "nadieHaPagado");
                                        session.setAttribute("correo", correo2);
                                        session.setAttribute("idPareja", idPareja);
                                        session.setAttribute("padrino1", true);
                                        
                                        session.setAttribute("respuesta", "nadieHaPagado");
                                        request.getRequestDispatcher("panelApadrina").forward(request, response);
                                        
                                    }
                                    
                                    
                                }
                            }
                            //No hay pagos
                            //Nadie ha pagado
                            else{
                                //Si el padrino ya tiene apadrinado asignado, se le asigna a la sesion este atributo
                                if(request.getAttribute("idApadrinado")!=null){
                                    int idApadrinado=(Integer)request.getAttribute("idApadrinado");
                                    session.setAttribute("idApadrinado", idApadrinado);
                                }
                                request.setAttribute("respuesta", "nadieHaPagado");
                                session.setAttribute("correo", correo2);
                                session.setAttribute("idPareja", idPareja);
                                session.setAttribute("padrino1", true);
                                session.setAttribute("respuesta", "nadieHaPagado");
                                request.getRequestDispatcher("panelApadrina").forward(request, response);
                                
                            }
                            
                            
                        }
                        
                        //Es -1, y se le debe de avisar al padrino1 que no se ha registrado padrino2
                        else{
                            request.setAttribute("respuesta", "sinRegistro");
                            request.setAttribute("correo", correo2);
                            session.setAttribute("idPareja", idPareja);
                            session.setAttribute("padrino1", true);
                            request.getRequestDispatcher("panelApadrina").forward(request, response);
                        }
                        
                    }
                    
                    //El padrino esta registrado como padrino2 en parejas
                    else if(arrPadrino1!=null && arrPadrino1.size()!=0){
                        //Muestra menu de pareja
                        request.setAttribute("pareja",true);
                        int idPareja = Integer.parseInt(arrPadrino1.get(0));
                        int idPadrino1 = Integer.parseInt(arrPadrino1.get(1));
                        String correo1= arrPadrino1.get(2);
                        
                        
                        //Si el idPadrino2 es diferente de -1, entonces ese padrino ya esta registrado
                        if(idPadrino1 != -1){
                            
                            //Se procede a buscar su ultimo pago
                            //Y ver quienes han pagado
                            
                            Pago_pareja pagopareja = new Pago_pareja(conn);
                            
                            ArrayList<Pago_pareja> pagospareja = pagopareja.obtenerInfo(idPareja);
                            
                            //Si hay pagos
                            if(pagospareja!=null && pagospareja.size()!=0){
                                boolean pagoAcreditado=true;
                                int i=0;
                                int aux;
                                do{
                                    Pago_pareja pagodepareja= pagospareja.get(i);
                                    pagoAcreditado=pagodepareja.isAcreditado();
                                    aux=i;
                                    i++;
                                    
                                }while(pagoAcreditado && i<pagospareja.size());
                                
                                if(i==pagospareja.size() && pagoAcreditado){
                                    //Todos los pagos estan acreditados, es decir, ya se tiene apadrinado asignado
                                    //Ya se asigno una suscripcion
                                    request.setAttribute("respuesta", "asignado");
                                    request.setAttribute("correo", correo1);
                                    request.getRequestDispatcher("panelApadrina").forward(request, response);
                                }
                                
                                else if(!pagoAcreditado){
                                    Pago_pareja pagodepareja=pagospareja.get(aux);
                                    Date fechaPago1=pagodepareja.getFechaPago1();
                                    Date fechaPago2=pagodepareja.getFechaPago2();
                                    int idApadrinado=pagodepareja.getIdApadrinado();
                                    pagodepareja.getIdPareja();
                                    //Ya pagaron ambos padrinos
                                    if(fechaPago1!=null && fechaPago2!=null){
                                        //Se manda un mensaje diciendo que se esta esperando a que se les asigne un apadrinado
                                        
                                        request.setAttribute("respuesta", "esperandoAdmin");
                                        request.setAttribute("correo", correo1);
                                        request.getRequestDispatcher("panelApadrina").forward(request, response);
                                    }
                                    //Ya pago padrino1, pero este padrino no
                                    else if(fechaPago1 != null && fechaPago2==null){
                                        
                                        request.setAttribute("respuesta", "noPagaste");
                                        session.setAttribute("correo", correo1);
                                        session.setAttribute("idPareja", idPareja);
                                        session.setAttribute("padrino1", false);
                                        session.setAttribute("respuesta", "noPagaste");
                                        session.setAttribute("idApadrinado", idApadrinado);
                                        request.getRequestDispatcher("panelApadrina").forward(request, response);
                                        
                                    }
                                    //Ya pago este padrino, pero el otro no
                                    else if(fechaPago1==null && fechaPago2!=null){
                                        
                                        request.setAttribute("respuesta", "noPagoOtro");
                                        request.setAttribute("correo", correo1);
                                        
                                        request.getRequestDispatcher("panelApadrina").forward(request, response);
                                        
                                    }
                                    
                                    //Nadie ha pagado
                                    else{
                                        //Si el padrino ya tiene apadrinado asignado, se le asigna a la sesion este atributo
                                        if(request.getAttribute("idApadrinado")!=null){
                                            int idApadrinado1=(Integer)request.getAttribute("idApadrinado");
                                            session.setAttribute("idApadrinado", idApadrinado1);
                                        }
                                        
                                        request.setAttribute("respuesta", "nadieHaPagado");
                                        session.setAttribute("correo", correo1);
                                        session.setAttribute("idPareja", idPareja);
                                        session.setAttribute("padrino1", false);
                                        session.setAttribute("respuesta", "nadieHaPagado");
                                        request.getRequestDispatcher("panelApadrina").forward(request, response);
                                        
                                    }
                                    
                                    
                                }
                            }
                            //No hay pagos
                            //Nadie ha pagado
                            else{
                                //Si el padrino ya tiene apadrinado asignado, se le asigna a la sesion este atributo
                                if(request.getAttribute("idApadrinado")!=null){
                                    int idApadrinado=(Integer)request.getAttribute("idApadrinado");
                                    session.setAttribute("idApadrinado", idApadrinado);
                                }
                                request.setAttribute("respuesta", "nadieHaPagado");
                                session.setAttribute("correo", correo1);
                                session.setAttribute("idPareja", idPareja);
                                session.setAttribute("padrino1", false);
                                session.setAttribute("respuesta", "nadieHaPagado");
                                request.getRequestDispatcher("panelApadrina").forward(request, response);
                                
                            }
                            
                            
                            
                            
                        }
                        
                    }
                    
                    //No tiene pareja
                    else{
                     
                        request.setAttribute("unico",true);
                        request.getRequestDispatcher("panelApadrina").forward(request, response);
                        
                        
                        
                    }
                    
                    
                    
                    
                }
                
                
                else if(opcion == 3){
                    
                
                    
                    Conexion conn = new Conexion();
                    Pago pago = new Pago(conn);
              
                    //Se busca para ver si ya ha hecho pagos
                    ArrayList<Pago> pagos=pago.obtenerPagos(idPadrino);
                    boolean todosAcreditados=true;
                    //Si hay pagos
                  
                    if(pagos.size()!=0){
                        
                        for(int i=0;i<pagos.size();i++){
                            Pago unpago= pagos.get(i);
                            todosAcreditados=unpago.isAcreditado();
                            if(!todosAcreditados){
                                break;
                            }
                            
                            
                        }
                        
                        //Todos los pagos están acreditados, puede apadrinar a alguien más
                        if(todosAcreditados){
                            request.setAttribute("respuesta", "todoAcreditado");
                            request.getRequestDispatcher("apadrinaSolo").forward(request, response);
                        }
                        else{
                            //Se regresa la cantidad de no acreditados
                            request.setAttribute("respuesta", "noAcreditado");
                            request.getRequestDispatcher("apadrinaSolo").forward(request, response);
                        }
                    }
                    //No hay ningún pago, la ventana es igual a todosAcreditados
                    else{
                       
                        request.setAttribute("respuesta", "noPagos");
                        request.getRequestDispatcher("apadrinaSolo").forward(request, response);
                    }
                    
                    
                    
                }
                
                else if(opcion==4){
                    ArrayList<String> nombreColumnas = new ArrayList<String>();
                    nombreColumnas.add("Estado de la suscripción");
                    nombreColumnas.add("Apadrinado");
                    nombreColumnas.add("Última Fecha de Pago");
                    nombreColumnas.add("");
                    request.setAttribute("nombreColumnas", nombreColumnas);
                    request.getRequestDispatcher("misSuscripciones").forward(request, response);
                }
                
                
            }
            
        }
        
    }
    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //El false es para que no cree una sesion, y si es nulo, no existe sesion
        if(request.getSession(false) != null){
            HttpSession session = request.getSession();
            
            //Checo si es nulo primero
            
            if(session.getAttribute("goodlogin") == null){
          
                request.getRequestDispatcher("IniciaSesion").forward(request, response);
            }
            
            //Redirigo a paypal
            else if((Boolean)session.getAttribute("goodlogin") == true){
                
                String opcion = (String)request.getParameter("opcion");
          
                
                
                
                if(opcion!=null){
                    request.setAttribute("opcion", opcion);
                    
                    doPost(request,response);
                }
                
                else{
                    String cargar = (String)request.getAttribute("tipo");
                    //Si es nulo, se intenta con el getParameter, ya que pudiera venir de un jsp
                    if(cargar==null){
                        cargar=request.getParameter("tipo");
                    }
                    
                    //Si no es nulo, se hace el doPost
                    if(cargar!=null){
                        doPost(request,response);
                    }
                    
                    else{
                        String send=request.getParameter("send");
                        
                        if(send!=null && send.equals("redirigirPaypal")){
                            request.getRequestDispatcher("redirigirPaypal").forward(request, response);
                        }
                        else if(send.equals("regresar")){
                            
                            request.setAttribute("opcion", "2");
                            doPost(request,response);
                        }
                        else if(send.equals("regresarSolo")){
                            request.setAttribute("opcion", "3");
                            doPost(request,response);
                        }
                        
                    }
                    
                }
                
                
                
                
            }
            
        }
        
        
    }
    
    
    
}
