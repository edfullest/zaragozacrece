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
public class ControlCargarApadrinados extends HttpServlet {
    
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
                
                //Creo la conexion a la base de datos
                Conexion conn = new Conexion();
                boolean hayApadrinados = false;
                
                
                request.setCharacterEncoding("UTF-8");
                
                int idPadrino = (Integer)session.getAttribute("idPadrino");
                //Ademas, cargo sus apadrinados
                Apadrinados apadrinado = new Apadrinados(conn);
                Pareja pareja = new Pareja(conn);
                
                ArrayList<Apadrinados> apadrinados = apadrinado.obtenerApadrinadoIdPadrino(idPadrino);
                ArrayList<Apadrinados> apadrinadosPareja = new ArrayList<Apadrinados>();
                int idPareja = pareja.obtenerIdPareja(idPadrino);
                
                if(idPareja!=-1){
                    apadrinadosPareja = apadrinado.obtenerApadrinadoIdPareja(idPareja);
                    if (apadrinadosPareja!=null && !apadrinadosPareja.isEmpty()){
                        apadrinados.add(apadrinadosPareja.get(0));
                    }
                }
                
                if ( apadrinados !=null && !apadrinados.isEmpty()){
                    hayApadrinados = true;
                    request.setAttribute("apadrinados", apadrinados);
                    
                }
                
                request.setAttribute("hayApadrinados", hayApadrinados);
                request.getRequestDispatcher("CuentaPadrino").forward(request, response);
                
            }
            
            
            
        }
        
        
        //Si no, se redirige a la pagina correspondiente
        else{
            request.setAttribute("badlogin",true);
            request.getRequestDispatcher("IniciaSesion").forward(request, response);
            
        }
        
        
        
    }
    
    public ArrayList<Apadrinados >obtenerApadrinados(int idPadrino){
           
                //Creo la conexion a la base de datos
                Conexion conn = new Conexion();
                boolean hayApadrinados = false;

                //Ademas, cargo sus apadrinados
                Apadrinados apadrinado = new Apadrinados(conn);
                Pareja pareja = new Pareja(conn);
                
                ArrayList<Apadrinados> apadrinados = apadrinado.obtenerApadrinadoIdPadrino(idPadrino);
                ArrayList<Apadrinados> apadrinadosPareja = new ArrayList<Apadrinados>();
                int idPareja = pareja.obtenerIdPareja(idPadrino);
                
                if(idPareja!=-1){
                    apadrinadosPareja = apadrinado.obtenerApadrinadoIdPareja(idPareja);
                    if (apadrinadosPareja!=null && !apadrinadosPareja.isEmpty()){
                        apadrinados.add(apadrinadosPareja.get(0));
                    }
                }
                
                if ( apadrinados !=null && !apadrinados.isEmpty()){
                    hayApadrinados = true;
                    return apadrinados;
                    
                }
                
                else{
                    return null;
                }
             
        
    }
    
}
