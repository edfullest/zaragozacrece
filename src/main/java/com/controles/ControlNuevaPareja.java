/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.controles;

import com.conexion.Conexion;
import com.entidades.Padrino;
import com.entidades.Pago_pareja;
import com.entidades.Pareja;
import java.io.IOException;
import java.io.PrintWriter;
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
public class ControlNuevaPareja extends HttpServlet {
    
    
    
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
                
                request.setCharacterEncoding("UTF-8");
                
                Conexion conn = new Conexion();
                Padrino padrino = new Padrino(conn);
                int idPadrino = (Integer)session.getAttribute("idPadrino");;
                
                String correo2 = request.getParameter("correo2");
                String correo = (String)session.getAttribute("thiscorreo");
                

                
                
                //Se crea la conexion a pareja
                Pareja pareja = new Pareja(conn);
                //Se busca ese correo en pareja, para buscar si ya lo han asignado anteriormente
                boolean Match = pareja.buscarEnCorreo2(correo);
                
                if(Match && correo2.equals("")){
                    //Se procede a crear la cuenta del correo original

                    //Se obtiene el id del padrino que agrega, para ponerlo en parejas como idPadrino2,
                    //dado que esta registrado en parejas como -1
                    
                    System.out.println("El idPadrino2 es:" + idPadrino);
                    //El id es nuevo, asi que no se repite en  ningun lado
                    //Se altera el renglon en parejas, y se actualiza el idPadrino2
                    //basandonos en la columna correo2
                    pareja.modificaPadrino2(idPadrino,correo);
                    
                    String correoIdPadrino1 = pareja.getCorreo1(correo);
                    request.setAttribute("correo", correoIdPadrino1);
                    request.setAttribute("opcion", "2");
                    request.setAttribute("tipo", "cargar");
                    request.setAttribute("mensaje", "ExitoPareja");
                    request.getRequestDispatcher("ControlPanelPadrino").forward(request, response);
                    
                }
                //Si se encuentra, y el usuario puso un correo2
                else if (Match && correo2!=""){

                    System.out.println("2");
                    boolean Match2 = pareja.buscarEnCorreo1(correo2);
                    //Si el correo2 que puso el usuario es igual al del correo1 en pareja,
                    //entonces es un match satisfactorio (idPadrino1 puso en correo1 el correo2
                    //que pone este usuario
                    
                    if (Match2){
      
                        System.out.println("2.1");
                        //Se obtiene el id del padrino que agrega, para ponerlo en parejas como idPadrino2,
                        //dado que esta registrado en parejas como -1
         
                        //El id es nuevo, asi que no se repite en  ningun lado
                        //Se altera el renglon en parejas (busca con correo2, el cual es el que puso el usuario)
                        pareja.modificaPadrino2(idPadrino,correo);
                        
                        //Busca con correo2, el cual es el que puso el usuario
                        String correoIdPadrino1 = pareja.getCorreo1(correo);
                        request.setAttribute("correo", correoIdPadrino1);
                        request.setAttribute("opcion", "2");
                        request.setAttribute("tipo", "cargar");
                        request.setAttribute("mensaje", "ExitoPareja");
                        request.getRequestDispatcher("ControlPanelPadrino").forward(request, response);
                    }
                    
                    //Si el correo2 que puso no es igual al del correo1 en pareja,
                    //se le avisa al usuario que ya lo habian matcheado a el. Es decir
                    //un idPadrino1 puso su correo, pero idPadrino2 puso otro correo2
                    //(el correo que deberia ser correo1)
                    else{
                        System.out.println("2.2");
                        //Se obtiene el correo1 en base al correo2
                        String correoIdPadrino1 = pareja.getCorreo1(correo);
                        request.setAttribute("correo",correoIdPadrino1);
                        request.setAttribute("correoPad1",correo2);
                        
                        request.setAttribute("opcion", "2");
                        request.setAttribute("tipo", "cargar");
                        request.setAttribute("mensaje", "MatchError");
                        request.setAttribute("correo2","");
                        request.getRequestDispatcher("ControlPanelPadrino").forward(request, response);
                    }
                    
                    
                }
                

                
                //No esta su correo en parejas, asi que hay que añadir la nueva entrada en parejas
                //esto podria ser por dos razones:
                // 1. El correo que puso el usuario no esta registrado en parejas, pero el correo2 que puso
                //si esta registrado. Lo que se hace es mandarle un mensaje de error diciendole que
                //no puede registrarse con ese correo2, ya que ya tiene un match
                // 2. El correo que puso el usuario no esta registrado en pareja, ni el correo2 que puso
                //esta registrado. Lo que se hace es hacer el nuevo registro con el idPadrino1 del que agrega
                //idPadrino2 -1 (no esta registrado el correo2), correo1 del padrino que agrega, y correo2
                //el correo 2
                else if(!Match && correo2!=""){
                    System.out.println("3");
                    
                    //Se valida la primera opcion
                    //Se busca el correo2 en correo1 (para ver si el correo que el puso
                    //ya esta registrado con una pareja)
                    boolean MatchCorreo = pareja.buscarEnCorreo1(correo2);
                    //Se busca tambien en correo2
                    boolean MatchCorreo2 = pareja.buscarEnCorreo2(correo2);
                    //1. Se encuentra
                    if(MatchCorreo){
                        System.out.println("4");
                        //Hay que ver si ese renglon tiene un -1 en su idPadrino1
                        //o un numero que no sea -1
                        ArrayList<String> lista = new ArrayList();
                        lista=pareja.buscarEnPadrino1(correo2);
                        int idPad1 = Integer.parseInt(lista.get(0));
                        System.out.println(idPad1);
                        String corr1 = lista.get(1);
                        System.out.println(corr1);
                        
                        String corr2=pareja.getCorreo2(corr1);
                        //Obtengo el id del padrino2, que esta en
                        int idPad2=padrino.validarCorreo(corr2);
                        if (idPad2!=-1){
                            //Error, idPadrino2 ya tiene padrino fijo
                            request.setAttribute("correoRegistrado", corr2);
                            request.setAttribute("correo",correo2);
                            request.setAttribute("opcion", "2");
                            request.setAttribute("tipo", "cargar");
                            request.setAttribute("mensaje", "TienePadrino");
                            request.getRequestDispatcher("ControlPanelPadrino").forward(request, response);
                        }
                        
                        //Significa que no se ha registrado el correo2
                        else if(idPad2==-1){
                            //Error, idPadrino2 ya tiene padrino fijo
                            request.setAttribute("correoRegistrado", corr2);
                            request.setAttribute("correo",correo2);
                            request.setAttribute("opcion", "2");
                            request.setAttribute("tipo", "cargar");
                            request.setAttribute("mensaje", "OtroPadrino");
                            request.getRequestDispatcher("ControlPanelPadrino").forward(request, response);
                        }
                        
                        
                        
                        
                    }
                    
                    else if(MatchCorreo2){
                        System.out.println("20");
                        //Hay que ver si ese renglon tiene un -1 en su idPadrino2
                        //o un numero que no sea -1
                        ArrayList<String> lista = new ArrayList();
                        lista=pareja.buscarEnPadrino2(correo2); //En padrino2
                        int idPad2 = Integer.parseInt(lista.get(0));
                        System.out.println(idPad2);
                        String corr2 = lista.get(1);
                        System.out.println(corr2);
                        String corr1=pareja.getCorreo1(correo2);
                        if (idPad2!=-1){
                            //Error, idPadrino2 ya tiene padrino fijo
                            request.setAttribute("correoRegistrado", corr2);
                            request.setAttribute("correo",corr1);
                            request.setAttribute("opcion", "2");
                            request.setAttribute("tipo", "cargar");
                            request.setAttribute("mensaje", "TienePadrino");
                            request.getRequestDispatcher("ControlPanelPadrino").forward(request, response);
                        }
                        
                        else if (idPad2==-1 && corr2!=correo){
                            //Error,Aqui lo que pasa es que el correo que puso el usuario
                            //no matchea al correo2 que espera idPadrino1
                            request.setAttribute("correoRegistrado", corr2);
                            request.setAttribute("correo",corr1);
                            request.setAttribute("opcion", "2");
                            request.setAttribute("tipo", "cargar");
                            request.setAttribute("mensaje", "OtroPadrino");
                            request.getRequestDispatcher("ControlPanelPadrino").forward(request, response);
                        }
                        
                    }
                    
                    
                    
                    //Si el usuario pone el mismo correo que en correo2, se le arroja error
                    
                    else if (correo.equals(correo2)){
                        request.setAttribute("opcion", "2");
                        request.setAttribute("tipo", "cargar");
                        request.setAttribute("mensaje", "ParejaRecursiva");
                        request.getRequestDispatcher("ControlPanelPadrino").forward(request, response);
                    }
                    
     
                    //Aqui se hace la alta normal, dado que el correo2 que puso no esta registrado
                    else{

                        System.out.println("5");
                        //Se obtiene el id del padrino que agrega, para ponerlo en parejas
                        
                        System.out.println(idPadrino);
                        pareja.nuevaPareja(idPadrino,-1,correo,correo2);
                        request.setAttribute("correo2",correo2);
                        request.setAttribute("opcion", "2");
                        request.setAttribute("tipo", "cargar");
                        request.setAttribute("mensaje", "exito");
                        request.getRequestDispatcher("ControlPanelPadrino").forward(request, response);
                    }
                    
                }
                
                
                
            }
        }
        
        
        
    }
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
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
