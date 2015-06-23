/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.controles;

import com.conexion.Conexion;
import com.entidades.Padrino;
import com.entidades.Pareja;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Lalo Serna
 */
public class ControlNuevoPadrino extends HttpServlet {
    
    public void init(ServletConfig config) throws ServletException {  
    super.init(config);
    
    
    //Your code  
}
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     //Creo la conexion a la base de datos
     Conexion conn = new Conexion();
     boolean bDoGet = false;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int idPadrino1=-1;
        int idPadrino2=-1;


        request.setCharacterEncoding("UTF-8");
        
       
        Padrino padrino = new Padrino(conn);

        
        String correo = request.getParameter("correo");
        String password = request.getParameter("password");
        String nombreCompleto = request.getParameter("nombreCompleto");
        String celular = request.getParameter("celular");
        String correo2 = request.getParameter("correo2");
        
        
        //Si el padrino no existe, entonces es nuevo el correo
        if (padrino.validarCorreo(correo)==-1 || bDoGet){
            bDoGet = false;
            
            //Se crea la conexion a pareja
            Pareja pareja = new Pareja(conn);
            //Se busca ese correo en pareja, para buscar si ya lo han asignado anteriormente
            boolean Match = pareja.buscarEnCorreo2(correo);
            
            if(Match && correo2.equals("")){
                //Se procede a crear la cuenta del correo original
                padrino.nuevoPadrino(correo,password,nombreCompleto,celular);
                ;
                //Se obtiene el id del padrino que agrega, para ponerlo en parejas como idPadrino2,
                //dado que esta registrado en parejas como -1
                idPadrino2=padrino.validarCuenta(correo,password);
                ;
                //El id es nuevo, asi que no se repite en  ningun lado
                //Se altera el renglon en parejas, y se actualiza el idPadrino2
                //basandonos en la columna correo2 
                pareja.modificaPadrino2(idPadrino2,correo);
                
                String correoIdPadrino1 = pareja.getCorreo1(correo);
                request.setAttribute("correo", correoIdPadrino1);
                request.getRequestDispatcher("ExitoPareja").forward(request, response);
                
            }
            //Si se encuentra, y el usuario puso un correo2
            else if (Match && correo2!=""){
                //Se procede a crear la cuenta del correo original
                padrino.nuevoPadrino(correo,password,nombreCompleto,celular);
                ;
                boolean Match2 = pareja.buscarEnCorreo1(correo2);
                //Si el correo2 que puso el usuario es igual al del correo1 en pareja,
                //entonces es un match satisfactorio (idPadrino1 puso en correo1 el correo2
                //que pone este usuario
                
                if (Match2){
                    //Se procede a crear la cuenta del correo original
                    padrino.nuevoPadrino(correo,password,nombreCompleto,celular);
                    ;
                    //Se obtiene el id del padrino que agrega, para ponerlo en parejas como idPadrino2,
                    //dado que esta registrado en parejas como -1
                    idPadrino2=padrino.validarCuenta(correo,password);
                    ;
                    //El id es nuevo, asi que no se repite en  ningun lado
                    //Se altera el renglon en parejas (busca con correo2, el cual es el que puso el usuario)
                    pareja.modificaPadrino2(idPadrino2,correo);
                    
                    //Busca con correo2, el cual es el que puso el usuario
                    String correoIdPadrino1 = pareja.getCorreo1(correo);
                    request.setAttribute("correo", correoIdPadrino1);
                    request.getRequestDispatcher("ExitoPareja").forward(request, response);
                }
                
                //Si el correo2 que puso no es igual al del correo1 en pareja,
                //se le avisa al usuario que ya lo habian matcheado a el. Es decir
                //un idPadrino1 puso su correo, pero idPadrino2 puso otro correo2
                //(el correo que deberia ser correo1) 
                else{
                    ;
                    //Se obtiene el correo1 en base al correo2
                    String correoIdPadrino1 = pareja.getCorreo1(correo);
                    request.setAttribute("correo",correoIdPadrino1);
                    request.setAttribute("correoPad1",correo2);
                    
                    //Se guarda lo que el usuario puso en el registro
                    request.setAttribute("nombreCompleto",nombreCompleto);
                    request.setAttribute("password",password);
                    request.setAttribute("celular",celular);
                    request.setAttribute("correo",correo);
                    //El correo 2 se borra, para que entre al primer if cuando
                    //se llame de nuevo el control
                    request.setAttribute("correo2","");
                    request.getRequestDispatcher("MatchError").forward(request, response);
                }
                
                
            }
            
            else if(!Match && (correo2.equals("")|| correo2.equals(" ") ) ){
                //Se agrega normalmente
                //Se procede a crear la cuenta del correo original
                 padrino.nuevoPadrino(correo,password,nombreCompleto,celular);
                request.getRequestDispatcher("Exito").forward(request, response);
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
                ;
                
                //Se valida la primera opcion
                //Se busca el correo2 en correo1 (para ver si el correo que el puso
                //ya esta registrado con una pareja)
                boolean MatchCorreo = pareja.buscarEnCorreo1(correo2);
                //Se busca tambien en correo2
                boolean MatchCorreo2 = pareja.buscarEnCorreo2(correo2);
                //1. Se encuentra
                if(MatchCorreo){
                    ;
                    //Hay que ver si ese renglon tiene un -1 en su idPadrino1
                    //o un numero que no sea -1
                    ArrayList<String> lista = new ArrayList();
                    lista=pareja.buscarEnPadrino1(correo2);
                    int idPad1 = Integer.parseInt(lista.get(0));
                    ;
                    String corr1 = lista.get(1);
                    ;
                    
                    String corr2=pareja.getCorreo2(corr1);
                    //Obtengo el id del padrino2, que esta en 
                    int idPad2=padrino.validarCorreo(corr2);
                   if (idPad2!=-1){
                       //Error, idPadrino2 ya tiene padrino fijo
                       request.setAttribute("correoRegistrado", corr2);
                       request.setAttribute("correo",correo2);
                       request.getRequestDispatcher("TienePadrino").forward(request, response);
                    }
                   
                   //Significa que no se ha registrado el correo2
                   else if(idPad2==-1){
                       //Error, idPadrino2 ya tiene padrino fijo
                       request.setAttribute("correoRegistrado", corr2);
                       request.setAttribute("correo",correo2);
                       request.getRequestDispatcher("OtroPadrino").forward(request, response);
                   }
                    


                    
                }
                
                else if(MatchCorreo2){
                    ;
                    //Hay que ver si ese renglon tiene un -1 en su idPadrino2
                    //o un numero que no sea -1
                    ArrayList<String> lista = new ArrayList();
                    lista=pareja.buscarEnPadrino2(correo2); //En padrino2
                    int idPad2 = Integer.parseInt(lista.get(0));
                    ;
                    String corr2 = lista.get(1);
                    ;
                    String corr1=pareja.getCorreo1(correo2);
                   if (idPad2!=-1){
                        //Error, idPadrino2 ya tiene padrino fijo
                       request.setAttribute("correoRegistrado", corr2);
                       request.setAttribute("correo",corr1);
                       request.getRequestDispatcher("TienePadrino").forward(request, response);
                    }
                    
                    else if (idPad2==-1 && corr2!=correo){
                        //Error,Aqui lo que pasa es que el correo que puso el usuario
                        //no matchea al correo2 que espera idPadrino1
                       request.setAttribute("correoRegistrado", corr2);
                       request.setAttribute("correo",corr1);
                       request.getRequestDispatcher("OtroPadrino").forward(request, response);
                    }

                }
                

                
                //Si el usuario pone el mismo correo que en correo2, se le arroja error
                
                else if (correo.equals(correo2)){
                    
                    request.getRequestDispatcher("ParejaRecursiva").forward(request, response);
                }
                
                //Aqui se valida que no ponga como padrino un usuario ya registrado, y que no quiere pareja
                
                else if(padrino.validarCorreo(correo2)!=-1){
                    request.setAttribute("correo2",correo2);
                    request.getRequestDispatcher("PersonaRegistrada").forward(request, response);
                }
                
                //Aqui se hace la alta normal, dado que el correo2 que puso no esta registrado
                else{
                    //Se procede a crear la cuenta del correo original
                    padrino.nuevoPadrino(correo,password,nombreCompleto,celular);
                    ;
                    //Se obtiene el id del padrino que agrega, para ponerlo en parejas
                    idPadrino1=padrino.validarCuenta(correo,password);
                    ;
                    pareja.nuevaPareja(idPadrino1,-1,correo,correo2);
                    request.setAttribute("correo2",correo2);
                    request.getRequestDispatcher("exito").forward(request, response);
                }
 
            }
            
            
            
            
            
        }//Termina validacion de si existe el correo que puso el usuario
        
        /*
        //Si viene del doGet, significa que el usuario quiere apadrinar con el correo
        //que esta matcheado en parejas
        else if(bDoGet){
            bDoGet = false;
            ;
            Pareja pareja = new Pareja(conn);
                //Se obtiene el id del padrino que agrega, para ponerlo en parejas como idPadrino2,
                //dado que esta registrado en parejas como -1
                ;
                ;
                idPadrino2=padrino.validarCuenta(correo,password);
                ;
                //El id es nuevo, asi que no se repite en  ningun lado
                //Se altera el renglon en parejas, y se actualiza el idPadrino2
                //basandonos en la columna correo2 
                pareja.modificaPadrino2(idPadrino2,correo);
        }
        */
        //De lo contrario, esto significa que ese correo ya esta registrado, y se despacha el usuario a una pagina de error
        else{
            request.getRequestDispatcher("ErrorCorreo").forward(request, response);
        }
    
        
        
        
    }
/*
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
 */    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        //El tipo de mensaje me dice de que interfaz viene
        String tipoMensaje = request.getParameter("tipoMensaje");
        
        ;
        //Se verifica que tipo de error es
        
        //El request viene del jsp que le indica al usuario que tuvo exito
        if (tipoMensaje.equals("Exito")){
            //Se le redirecciona a nuevopadrino
            request.getRequestDispatcher("IniciaSesion").forward(request, response);
        }
        
        //El request viene del jsp que le indica al usuario correo repetido
        if (tipoMensaje.equals("ErrorCorreo")){
            //Se le redirecciona a nuevopadrino
            request.getRequestDispatcher("Apadrina").forward(request, response);
        }
        
        //El request viene de la interfaz error match o bien, de un padrino que quiere apadrinar con alguien mas
        if (tipoMensaje.equals("MatchError") || tipoMensaje.equals("pareja")){
            String boton = request.getParameter("boton");
            //Si el usuario presiono el boton de apadrinar con el correo que le
            //aparece...
            if(boton.equals("apadrina")){
                //Se pasa al doPost
                bDoGet = true;
                doPost(request,response);
            }
            else if(boton.equals("regresa")){
                request.getRequestDispatcher("Apadrina").forward(request, response);
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
    

    
    
