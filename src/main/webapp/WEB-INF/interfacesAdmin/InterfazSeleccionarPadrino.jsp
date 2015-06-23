<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : index
    Created on : May 20, 2015, 3:11:10 PM
    Author     : Lalo Serna
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    
    <head>
        
        <meta property="fb:app_id"          content="1234567890" /> 
        <meta property="og:type"            content="article" /> 
        <meta property="og:url"             content="http://201.156.168.107:8080/ccz/" /> 
        <meta property="og:title"           content="Creciendo con Zaragoza" /> 
        <meta property="og:image"           content="https://lh5.googleusercontent.com/-1WA5JpxGGF0/VWArUdlDahI/AAAAAAAAA2o/jT9P6kTJuyo/s480-no/Background-04.jpg" /> 
        <meta property="og:description"    content="Sitio web dedicado al grupo estudiantil Creciendo con Zaragoza" />
        <meta charset="utf-8">
        
        
        <title>Creciendo con Zaragoza</title>
        
        <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
        
        <link rel="stylesheet" href="css/demo.css">
        <link rel="stylesheet" href="css/font-awesome.css">
        <link rel="stylesheet" href="css/sky-tabs.css">
        <link rel="stylesheet" href="css/sky-mega-menu.css">
        <link rel="stylesheet" href="css/sky-forms.css">
        <link rel="stylesheet" href="css/sky-forms-green.css">
        <link rel="stylesheet" type="text/css" href="css/normalize.css" />
        <link rel="stylesheet" type="text/css" href="css/demo.css" />
        <link rel="stylesheet" type="text/css" href="css/component.css" />
        <link rel="stylesheet" href="css/reset.css"> <!-- CSS reset -->
        <link rel="stylesheet" href="css/style.css"> <!-- Resource style -->
        
        <!--[if lt IE 9]>
                <link rel="stylesheet" href="css/sky-forms-ie8.css">
        <![endif]-->
        
        <script src="js/jquery.min.js"></script>
        <script src="js/jquery.form.min.js"></script>
        <script src="js/jquery.validate.min.js"></script>	
        <script src="js/jquery-ui.min.js"></script>
        <script src="js/modernizr.js"></script>
        
        <!--[if lt IE 9]>
                <link rel="stylesheet" href="css/sky-tabs-ie8.css">
                <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
                <script src="js/sky-tabs-ie8.js"></script>
        <![endif]-->
        
        
        
    </head>
    <%--TABLAS BONITAS   
       <sql:query var="result" dataSource="jdbc/zaragoza">
                       SELECT * FROM admins
                   </sql:query>
   <div class="component">
                   <table
                       <!-- column headers -->
                       <tr>
                       <c:forEach var="columnName" items="${result.columnNames}">
                           <th><c:out value="${columnName}"/></th>
                       </c:forEach>
                       </tr>
                       <!-- column data -->
                       <c:forEach var="row" items="${result.rowsByIndex}">
                           <
                           <tr>
                           <c:forEach var="column" items="${row}">
                               <td><c:out value="${column}"/></td>
                           </c:forEach>
                                
                           </tr>
                                
                       </c:forEach>
                   </table>
       </div>
    --%>
    
    <c:choose>
        <c:when test="${sessionScope.goodlogin}">
            <body class="bg-cyan">
                <div class="body">
                    
                     <!-- mega menu -->
                    <ul class="sky-mega-menu">
                        
                        <li>
                                    
                            <a href="InterfazAdmin"><i class="fa fa-single fa-home"></i></a>
                                        
                                        
			</li>
                        
                        <!-- Apadrinados -->
                        
                        <li aria-haspopup="true">
                            <a href="#"><i class="fa fa-child"></i>Apadrinados<i class="fa fa-indicator fa-chevron-down"></i></a> 
                                        
                            <div class="grid-container3">
                                <ul>
                                    <li>
                                        <a href="nuevoApadrinado"><i class="fa fa-plus-circle"></i>Añadir Apadrinado</a>
                                    </li>
                                    <li>
                                        <a href="apadrinados?tipo=${"cargarApadrinados"}"><i class="fa fa-bar-chart-o"></i>Nueva entrada de apadrinado</a>
                                    </li>
                                    <li>
                                        <a href="apadrinados?tipo=${"editarApadrinado"}"><i class="fa fa-gears"></i>Editar apadrinado</a>
                                    </li>
                                    
                                    
                                    
                                    <li>
                                        
                                        <a href="apadrinados?tipo=${"apadrinadosRegistrados"}"><i class="fa fa-table"></i>Apadrinados registrados</a>
                                    </li>
                                </ul>
                            </div>
                                                                    
                        </li>
                        <!--/ Apadrinados -->
                        
                        <!-- Padrinos -->
                        
                        <li aria-haspopup="true">
                            <a href="#"><i class="fa fa-user"></i>Padrinos<i class="fa fa-indicator fa-chevron-down"></i></a> 
                                        
                            <div class="grid-container3">
                                <ul>
                                    <li>
                                        <a href="ControlCargarPagos?tipo=cargarPagos"><i class="fa fa-user"></i>Asignar apadrinados a padrinos</a>
                                    </li>
                                                                                    
                              
                                     <li>
                                        <a href="ControlCargarPagos?tipo=cargarTodosPadrinados"><i class="fa fa-money"></i>Nuevo pago de padrino</a>
                                    </li>
                                    <%--
                                    <li>
                                        <a href="ControlCargarPagos?tipo=cargarTodasSuscripciones"><i class="fa fa-repeat"></i>Renovar/Reactivar suscripcion de padrino</a>
                                    </li> 
                                   --%>
                                                                                    
                                </ul>
                            </div>
                                                                    
                        </li>
                        
                        <li aria-haspopup="true">
                            <a href="#"><i class="fa fa-users"></i>Parejas<i class="fa fa-indicator fa-chevron-down"></i></a> 
                                        
                            <div class="grid-container3">
                                <ul>
                              
                                                                                    
                                    <li>
                                        <a href="cargarPagosParejas?tipo=cargarPagos"><i class="fa fa-users"></i>Asignar apadrinados a parejas</a>
                                    </li>
                                    
                                     <li>
                                        <a href="cargarPagosParejas?tipo=cargarTodasParejas"><i class="fa fa-money"></i>Nuevo pago de pareja</a>
                                    </li>
                                     <%--
                                    <li>
                                        <a href="ControlCargarPagos?tipo=cargarTodasSuscripciones"><i class="fa fa-repeat"></i>Renovar/Reactivar suscripcion de pareja</a>
                                    </li> 
                                   --%>
                                                                                    
                                </ul>
                            </div>
                                                                    
                        </li>
                        <!--/ Padrinos -->
                        
                        <!-- Apartado para apadrinar un niño -->
                        
                        <li>
                            <a href="Notas"><i class="fa fa-comments-o"></i>Añadir Nota</a>
                            
                        </li>
                        
                        <!--/ Apartado para apadrinar un niño  -->
                        
                       
                        
                        <!-- Apartado para cerrar sesión -->
                        <li>
                                    
                            <a href="ControlAdminLogIn?logout=${"true"} "><i class="fa fa-sign-out"></i>Cierra Sesión</a>
                            
                            
                        </li>
                        <!--/ Apartado para cerrar sesión -->
                        
                        
                    </ul>
                    <!--/ Termina el menú -->
                </div>
                <!--/ Termina el menú -->
                
                <!--/ Cargo los apadrinados que se van a editar -->
                
                <div class="body"> 
                    
                     <br>
                    <br>
                    <!-- tabs -->
                    
                    
                    <c:choose>
                        <c:when test="${exito eq 'true'}">
                            <br>
                            <b><font color="white">Listo! Se ha registrado ese pago, ahora tienes que asignarle un apadrinado!</font></b>
                            </c:when>
                    </c:choose> 
                    <div class="sky-tabs sky-tabs-pos-top-left sky-tabs-response-to-icons">
                        <div class="textbox">
                            <section>
                                <h1><b>IMPORTANTE:</b></h1>
                                <p>Este apartado es para asignar pagos a parejas. Sólo procede si algún padrino de la pareja pagó 375 pesos.</p>
                            </section>
                        </div>
                    </div>
                    <div class="component">
                        
                        
                        <table id="tabla">
                            <tr>
                                <c:forEach items="${nombreColumnas}" var="columnas" >
                                    <th><c:out value="${columnas}"/></th>
                                    </c:forEach>
                            </tr>
                            
                            <c:choose>
                                <c:when test="${idPadrino1!=null && idPadrino2==null}">
                                    
                                    <tr>
                                    <td><c:out value="${param.idPadrino1}"/>
                                    </td>
                                    <td><c:out value="${nombre1}"/>
                                    </td>

                                    <td><c:out value="${param.correo1}"/>
                                    </td>
                                    
                                    <td><c:out value="${celular1}"/>
                                    </td>
                                    
                                    <td><a href="cargarPagosParejas?tipo=redirigirCrearPago&idPareja=${param.idPareja}&idPadrino1=${param.idPadrino1}&nombreCompleto=${nombre1}&correoPadrino=${correo1}&pago1=${pago1}&pago2=${pago2}">
                                            <button type="button" class="button-table"><i class="fa fa-caret-square-o-right"></i> Seleccionar Padrino1</button></a></td>
                                    
                                    
                                    
                                    </tr>
                                    
                                    
                                </c:when>
                                <c:when test="${idPadrino2!=null && idPadrino1==null}">
                                    <tr>
                                    <td><c:out value="${param.idPadrino2}"/>
                                    </td>
                                    <td><c:out value="${nombre2}"/>
                                    </td>

                                    <td><c:out value="${param.correo2}"/>
                                    </td>
                                    
                                    <td><c:out value="${celular2}"/>
                                    </td>
                                    
                                    <td><a href="cargarPagosParejas?tipo=redirigirCrearPago&idPareja=${param.idPareja}&idPadrino2=${param.idPadrino2}&nombreCompleto=${nombre2}&correoPadrino=${correo2}&pago1=${pago1}&pago2=${pago2}">
                                            <button type="button" class="button-table"><i class="fa fa-caret-square-o-right"></i> Seleccionar Padrino2</button></a></td>
                                    
                                    
                                    
                                </tr>
                                </c:when>
                                
                                <c:when test="${idPadrino1!=null && idPadrino1 != null}">
                                    
                                    <tr>
                                    <td><c:out value="${param.idPadrino1}"/>
                                    </td>
                                    <td><c:out value="${nombre1}"/>
                                    </td>

                                    <td><c:out value="${param.correo1}"/>
                                    </td>
                                    
                                    <td><c:out value="${celular1}"/>
                                    </td>
                                    
                                    <td><a href="cargarPagosParejas?tipo=redirigirCrearPago&idPareja=${param.idPareja}&idPadrino1=${param.idPadrino1}&nombreCompleto=${nombre1}&correoPadrino=${correo1}&pago1=${pago1}&pago2=${pago2}">
                                            <button type="button" class="button-table"><i class="fa fa-caret-square-o-right"></i> Seleccionar Padrino11</button></a></td>
                                    
                                    
                                    
                                    </tr>
                                
                                    <c:choose>
                                        <c:when test="${idPadrino2!=-1}">
                                            <tr>
                                    <td><c:out value="${param.idPadrino2}"/>
                                    </td>
                                    <td><c:out value="${nombre2}"/>
                                    </td>

                                    <td><c:out value="${param.correo2}"/>
                                    </td>
                                    
                                    <td><c:out value="${celular2}"/>
                                    </td>
                                    
                                    <td><a href="cargarPagosParejas?tipo=redirigirCrearPago&idPareja=${param.idPareja}&idPadrino2=${idPadrino2}&nombreCompleto=${nombre2}&correoPadrino=${param.correo2}&pago1=${pago1}&pago2=${pago2}">
                                            <button type="button" class="button-table"><i class="fa fa-caret-square-o-right"></i> Seleccionar Padrino22</button></a></td>
                                    
                                    
                                    
                                </tr>
                                            
                                        </c:when>
                                
                                <c:otherwise>
                                    <b><font color="white">¡El otro padrino no se ha registrado aún!</font></b>
                                </c:otherwise>
                                    </c:choose>
                                
                                    
                                    
                                </c:when>
                                    
                                    <c:otherwise> 
                                        <b><font color="white">¡Ya pagaron ambos padrinos! Asígnales un apadrinado.</font></b>
                                        
                                    </c:otherwise>
                            </c:choose>
                           
                                
                                
                            
                        </table>
                        
                        
                    </div>
                    
                    
                    
                    
                   
                </div>
                
                
                
                
            </body>
            
            
        </c:when>
        <c:when test="${sessionScope.goodlogin==false}">
            <jsp:forward page = "admin" />
        </c:when>
    </c:choose>  
    
    
    
    
</html>
