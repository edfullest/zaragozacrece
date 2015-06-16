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
import com.entidades.Pareja;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Lalo Serna
 */
public class ControlApadrinados extends HttpServlet {
    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        //El false es para que no cree una sesion, y si es nulo, no existe sesion
        if(request.getSession(false) != null){
            HttpSession session = request.getSession();
            
            //Se manda a admin si es nula la sesion
            if(session.getAttribute("goodlogin") == null){
                request.getRequestDispatcher("admin").forward(request, response);
            }
            
            //Si existe la sesion, y es un buen login, entonces se crea la nota
            else if((Boolean)session.getAttribute("goodlogin") == true){
                
                //Lugar de donde el usuario hace el post
                String tipo = request.getParameter("tipo");
                
                //Si el admin hace el request de nuevoApadrinado, se hace uno nuevo
                if(tipo.equals("nuevoApadrinado")){
                    Conexion conn = new Conexion();
                    Apadrinados apadrinado = new Apadrinados(conn);
                    
                    String nombreCompleto = request.getParameter("nombreCompleto");
                    String comunidad = request.getParameter("comunidad");
                    int idAdmin = Integer.parseInt(request.getParameter("idAdmin"));
                    
                    apadrinado.nuevoApadrinado(nombreCompleto, comunidad, idAdmin);
                    request.setAttribute("exito",true);
                    request.getRequestDispatcher("nuevoApadrinado").forward(request, response);
                }
                
                else if(tipo.equals("nuevaEntrada")){
                    Conexion conn = new Conexion();
                    Entrada entrada = new Entrada(conn);
                    
                    int idApadrinado = Integer.parseInt(request.getParameter("idApadrinado"));
                    String nivelEscolar = request.getParameter("nivelEscolar");
                    float peso = Float.parseFloat(request.getParameter("peso"));
                    float IMC = Float.parseFloat(request.getParameter("IMC"));
                    float estatura = Float.parseFloat(request.getParameter("estatura"));
                    Date fechaActual = obtenerFechaActual();
                    String carta = request.getParameter("carta");
                    
                    entrada.nuevaEntrada(idApadrinado, nivelEscolar, peso, IMC, estatura, fechaActual, carta);
                    System.out.println("apadrinados?tipo=cargarApadrinados");
                    response.sendRedirect("apadrinados?tipo=cargarApadrinados&exito=true&idApadrinadoEntrada="+idApadrinado);
                    
                }
                
                                
                else if(tipo.equals("editar")){
                    Conexion conn = new Conexion();
                    Apadrinados apadrinado = new Apadrinados(conn);
                    
                    String nombreCompleto = request.getParameter("nombreCompleto");
                    String comunidad = request.getParameter("comunidad");
                    int idApadrinado = Integer.parseInt(request.getParameter("idApadrinado"));
                    
                    apadrinado.editarApadrinado(idApadrinado,nombreCompleto,comunidad);
                    request.setAttribute("exito",true);
                    response.sendRedirect("apadrinados?tipo=editarApadrinado&exito=true");
                    
                }
                
                
                
                
                
                
            }
            
        }
        
    }
    
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Lugar de donde el usuario hace el get
        String tipo = request.getParameter("tipo");
        System.out.println(tipo);
        if (tipo.equals("")){
            request.getRequestDispatcher("admin").forward(request, response);
        }
        
        //El false es para que no cree una sesion, y si es nulo, no existe sesion
        if(request.getSession(false) != null){
            HttpSession session = request.getSession();
            
            //Se manda a admin si es nula la sesion
            if(session.getAttribute("goodlogin") == null){
                request.getRequestDispatcher("admin").forward(request, response);
            }
            
            //Si existe la sesion, se cargan los apadrinados
            else if((Boolean)session.getAttribute("goodlogin") == true){
                
                
                if(tipo.equals("cargarApadrinados")){
                    
                    
                    Conexion conn = new Conexion();
                    Apadrinados apadrinados = new Apadrinados(conn);
                    
                    String exito = request.getParameter("exito");
                    String idApadrinadoEntrada = request.getParameter("idApadrinadoEntrada");
                    //Sistema de paginado
                    int paginaActual = 1;
                    int apadrinadosPorPagina = 20;
                    if(request.getParameter("paginaActual") != null)
                        paginaActual = Integer.parseInt(request.getParameter("paginaActual"));
                    //Lista de objetos del tipo apadrinado
                    ArrayList<Apadrinados> listaApadrinados = apadrinados.obtenerApadrinados((paginaActual-1)*apadrinadosPorPagina,apadrinadosPorPagina);
                    int numApadrinados = apadrinados.getNumeroApadrinados();
                    System.out.println("numApadrinados"+numApadrinados);
                    ArrayList<String> nombreColumnas = new ArrayList<String>();
                    nombreColumnas.add("ID");
                    nombreColumnas.add("Nombre");
                    nombreColumnas.add("Comunidad");
                    nombreColumnas.add("Padrino/Padrinos");
                    nombreColumnas.add("");
                    int numPaginas = (int)Math.ceil(numApadrinados*1.0/apadrinadosPorPagina);
                    
                    request.setAttribute("apadrinados", listaApadrinados);
                    request.setAttribute("nombreColumnas", nombreColumnas);
                    request.setAttribute("numPaginas", numPaginas);
                    request.setAttribute("paginaActual", paginaActual);
                    
                    System.out.println("AdminPags"+numPaginas);
                    System.out.println("Pag aCTUAL "+paginaActual);
                    if(exito!= null && exito.equals("true")){
                        request.setAttribute("exito",true);
                        request.setAttribute("idApadrinadoEntrada",idApadrinadoEntrada);
                    }
                    
                    request.getRequestDispatcher("cargarApadrinados").forward(request, response);
                    
                }
                
                else if(tipo.equals("redirigirNuevaEntrada")){
                    int idApadrinado = Integer.parseInt(request.getParameter("idApadrinado"));
                    String nombreCompleto = request.getParameter("nombreCompleto");
                    String comunidad = request.getParameter("comunidad");
                    request.setAttribute("idApadrinado", idApadrinado);
                    request.setAttribute("nombreCompleto", nombreCompleto);
                    request.setAttribute("comunidad", comunidad);
                    request.getRequestDispatcher("nuevaEntrada").forward(request, response);
                }
                
                else if (tipo.equals("editarApadrinado")){
     
                    Conexion conn = new Conexion();
                    Apadrinados apadrinados = new Apadrinados(conn);
                    
                    String exito = request.getParameter("exito");
                    //Sistema de paginado
                    int paginaActual = 1;
                    int apadrinadosPorPagina = 20;
                    if(request.getParameter("paginaActual") != null)
                        paginaActual = Integer.parseInt(request.getParameter("paginaActual"));
                    //Lista de objetos del tipo apadrinado
                    ArrayList<Apadrinados> listaApadrinados = apadrinados.obtenerApadrinados((paginaActual-1)*apadrinadosPorPagina,apadrinadosPorPagina);
                    int numApadrinados = apadrinados.getNumeroApadrinados();
                    System.out.println("numApadrinados"+numApadrinados);
                    ArrayList<String> nombreColumnas = new ArrayList<String>();
                    nombreColumnas.add("ID");
                    nombreColumnas.add("Nombre");
                    nombreColumnas.add("Comunidad");
                    nombreColumnas.add("Padrino/Padrinos");
                    nombreColumnas.add("");
                    int numPaginas = (int)Math.ceil(numApadrinados*1.0/apadrinadosPorPagina);
                    
                    request.setAttribute("apadrinados", listaApadrinados);
                    request.setAttribute("nombreColumnas", nombreColumnas);
                    request.setAttribute("numPaginas", numPaginas);
                    request.setAttribute("paginaActual", paginaActual);
                    
                    System.out.println("AdminPags"+numPaginas);
                    System.out.println("Pag aCTUAL "+paginaActual);
                    if(exito!= null && exito.equals("true")){
                        request.setAttribute("exito",true);
                    }
                    
                    request.getRequestDispatcher("editarApadrinado").forward(request, response);
                    
                }
                
                else if (tipo.equals("redirigirEditarApadrinado")){
                    int idApadrinado = Integer.parseInt(request.getParameter("idApadrinado"));
                    String nombreCompleto = request.getParameter("nombreCompleto");
                    String comunidad = request.getParameter("comunidad");
                    request.setAttribute("idApadrinado", idApadrinado);
                    request.setAttribute("nombreCompleto", nombreCompleto);
                    request.setAttribute("comunidad", comunidad);
                    request.getRequestDispatcher("editar").forward(request, response);
                    
                }
                
                else if (tipo.equals("apadrinadosRegistrados")){
                    
                    Conexion conn = new Conexion();
                    Apadrinados apadrinados = new Apadrinados(conn);

                    //Sistema de paginado
                    int paginaActual = 1;
                    int apadrinadosPorPagina = 20;
                    if(request.getParameter("paginaActual") != null)
                        paginaActual = Integer.parseInt(request.getParameter("paginaActual"));
                    //Lista de objetos del tipo apadrinado
                    ArrayList<Apadrinados> listaApadrinados = apadrinados.obtenerApadrinados((paginaActual-1)*apadrinadosPorPagina,apadrinadosPorPagina);
                    int numApadrinados = apadrinados.getNumeroApadrinados();
                    System.out.println("numApadrinados"+numApadrinados);
                    ArrayList<String> nombreColumnas = new ArrayList<String>();
                    nombreColumnas.add("ID");
                    nombreColumnas.add("Nombre");
                    nombreColumnas.add("Comunidad");
                    nombreColumnas.add("Padrino/Padrinos");
                    nombreColumnas.add("");
                    int numPaginas = (int)Math.ceil(numApadrinados*1.0/apadrinadosPorPagina);
                    
                    request.setAttribute("apadrinados", listaApadrinados);
                    request.setAttribute("nombreColumnas", nombreColumnas);
                    request.setAttribute("numPaginas", numPaginas);
                    request.setAttribute("paginaActual", paginaActual);
                    
                    System.out.println("AdminPags"+numPaginas);
                    System.out.println("Pag aCTUAL "+paginaActual);
    
                    
                    request.getRequestDispatcher("apadrinadosRegistrados").forward(request, response);
                    
                }
                
                else if(tipo.equals("redirigirVerMas")){
                    int idApadrinado = Integer.parseInt(request.getParameter("idApadrinado"));
                    String nombreCompleto = request.getParameter("nombreCompleto");
                    String comunidad = request.getParameter("comunidad");
                    
                    //Se hará una conexión a la base de datos para extraer datos
                    //del padrino/padrinos
                                       
                    Conexion conn = new Conexion();
                    Apadrinados apadrinado = new Apadrinados(conn);
                    
                    System.out.println("Id Pareja esesses");
                    int idPareja = apadrinado.obtenerIdPareja(idApadrinado);
                    System.out.println("Id Pareja es");
                    System.out.println("Id Pareja es: "+idPareja);
                    //si el idPareja es -1, entonces al apadrinado se le asigno un padrino unicamente
                    if(idPareja == -1){
                        int idPadrino = apadrinado.obtenerIdPadrino(idApadrinado);
                        Padrino padrino = new Padrino (conn);
                        
                        //Se obtiene el nombre del padrino mediante su id
                        String nombrePadrinoUnico=padrino.getNombre(idPadrino);
                        
                        String correoPadrinoUnino=padrino.obtenerCorreo(idPadrino);
                        request.setAttribute("nombrePadrinoUnico",nombrePadrinoUnico);
                        request.setAttribute("correoPadrinoUnico",correoPadrinoUnino);
                        
                    }
                    
                    //El apadrinado tiene pareja de padrinos
                    else{
                        Pareja pareja = new Pareja (conn);
                        Padrino padrino = new Padrino (conn);
                        ArrayList<String> idsPadrinos = pareja.obtenerAmbosID(idPareja);
                        String nombrePadrino1 = padrino.getNombre(Integer.parseInt(idsPadrinos.get(0)));
                        String nombrePadrino2 = padrino.getNombre(Integer.parseInt(idsPadrinos.get(1)));
                        String correoPadrino1 = padrino.obtenerCorreo(Integer.parseInt(idsPadrinos.get(0)));
                        String correoPadrino2 = padrino.obtenerCorreo(Integer.parseInt(idsPadrinos.get(1)));
                        request.setAttribute("nombrePadrino1",nombrePadrino1);
			request.setAttribute("nombrePadrino2",nombrePadrino2);
                        request.setAttribute("correoPadrino1",correoPadrino1);
			request.setAttribute("correoPadrino2",correoPadrino2);
                        
                        
                    }
                    
                    //Se obtienen todas las entradas
                    
                    Entrada entrada  = new Entrada (conn);
                    
                    ArrayList<Entrada> entradas = entrada.obtenerEntradas(idApadrinado);
                    
                    
                    request.setAttribute("entradas",entradas);
                    request.setAttribute("idApadrinado", idApadrinado);
                    request.setAttribute("nombreCompleto", nombreCompleto);
                    request.setAttribute("comunidad", comunidad);
					
                    request.getRequestDispatcher("verMas").forward(request, response);
                    
                }
                
                
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
    
    private Date obtenerFechaActual(){
        Date fecha = new Date();
        try{
            //Obtengo la fecha actual
            SimpleDateFormat sdf = new SimpleDateFormat();
            Date d = new Date();
            sdf.applyPattern("yyyy-MM-dd");
            String newFecha = sdf.format(d);
            System.out.println(newFecha);
            fecha = sdf.parse(newFecha);
            
            
            
        }
        
        catch(Exception e){
           
        }
        return fecha;
        
    }
    
}
