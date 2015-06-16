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
        <!--[if lt IE 9]>
                <link rel="stylesheet" href="css/sky-forms-ie8.css">
        <![endif]-->
                    
        <script src="js/jquery.min.js"></script>
        <script src="js/jquery.form.min.js"></script>
        <script src="js/jquery.validate.min.js"></script>	
        <script src="js/jquery-ui.min.js"></script>
                    
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
         
    <body class="bg-cyan">
            <div class="body">
                    
            
             <!-- mega menu -->
                    <ul class="sky-mega-menu sky-mega-menu-anim-flip sky-mega-menu-response-to-icons">
                        <!-- home -->
                            
                        <li>
                            
                            <a href="/ccz"><i class="fa fa-single fa-home"></i></a>
                                
                                
                        </li>
                        <!--/ home -->
                            
                        <!-- about -->
                        <li>
                            <a href="conocenos"><i class="fa fa-star"></i><b>Conócenos</b></a>
                        </li>
                        <!--/ about -->
                            
                      <!-- Blog -->
				<li>
					<a href="CargarNotas"><i class="fa fa-bullhorn"></i>Entérate</a>
				</li>
			<!--/ Blog -->
                            
                        <!-- Apartado para apadrinar un niño -->
                        <%--Verifico que exista una sesion de padrino--%>
                        <% if (session.getAttribute("idPadrino") == null) { %>
                        <li>
                            <a href="Apadrina"><i class="fa fa-arrow-circle-up"></i>Apadrina un niño</a>
                                
                        </li>
                        <%--Si existe, entonces cargo lo que se tenga que cargar--%>
                        <% } else {%>
                            
                            
                        <% } %>
                        <!--/ Apartado para apadrinar un niño  -->
                            
                        <!-- Mi cuenta,esto es si existe sesion -->
                            
                        <%--Verifico que exista una sesion de padrino--%>
                        <% if (session.getAttribute("idPadrino") == null) { %>
                            
                        <%--Si existe, entonces cargo lo que se tenga que cargar--%>
                        <% } else {%>
                        <li>
                            <a href="ControlCargarApadrinados"><i class="fa fa-dashboard"></i>Mi cuenta</a>
                                
                        </li>
                            
                        <% } %>
                        
                          <!--/ Apartado para cerrar sesión -->
                        
                           <c:choose>
                            <c:when test="${hayApadrinados}">
                                <li aria-haspopup="true">
                                    <a href="#"><i class="fa fa-group"></i>Apadrinados<i class="fa fa-indicator fa-chevron-down"></i></a>
                                    <div class="grid-container3">
                                        <ul>
                                            <c:forEach items="${apadrinados}" var="apadrinado">
                                            
                                            
                                                <li><a href="cargarInfo?idApadrinado=${apadrinado.idApadrinado}"><i class="fa fa-child"></i>${apadrinado.nombreCompleto}</a></li>
                                                            
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </li>           
                                                                                                
                                                                                                
                            </c:when>
                            
                        </c:choose>
                              
                                <%--Verifico que exista una sesion de padrino--%>
                                <% if (session.getAttribute("idPadrino") == null) { %>
                                <li aria-haspopup="true">
                                    <a href=""><i class="fa fa-sign-in"></i>Inicia Sesión<i class="fa fa-indicator fa-chevron-down"></i></a>
                                            
                                    <div class="grid-container3">
                                        <ul>
                                            <li>
                                                <a href="IniciaSesion"><i class="fa fa-user"></i>Padrinos</a>
                                            </li>
                                            <li>
                                                <a href="admin"><i class="fa fa-legal"></i>Admins</a>
                                            </li>
                                        </ul>
                                    </div>
                                </li>
                                <%--Si existe, entonces cargo lo que se tenga que cargar--%>
                                <% } else {%>
                                <li>
                                    <a href="cerrarSesion"><i class="fa fa-sign-out"></i>Cierra Sesión</a>
                                </li>  
                                <% } %>
                                        
                                <!--/ Apartado para iniciar sesión -->
                      
                            
                        <!-- Apartado para contactar  -->
                        <li class="right">
                            <a href="contactar"><i class="fa fa-phone"></i>Contáctanos</a>
                        </li>
                        <!--/ Apartado para contactar -->
                    </ul>
            <!--/ Termina el menú -->
                            
                            
                <div class="body">
                            
                <!-- tabs -->
                <div class="sky-tabs sky-tabs-pos-left sky-tabs-anim-flip sky-tabs-response-to-icons">
                    <input type="radio" name="sky-tabs" checked id="sky-tab1" class="sky-tab-content-1">
                    <label for="sky-tab1"><span><span><i class="fa fa-question"></i>¿Qué es Creciendo con Zaragoza?</span></span></label>
                                    
                    <input type="radio" name="sky-tabs" id="sky-tab2" class="sky-tab-content-2">
                    <label for="sky-tab2"><span><span><i class="fa fa-star"></i>¿Por qué Zaragoza?</span></span></label>
                                    
                    <input type="radio" name="sky-tabs" id="sky-tab3" class="sky-tab-content-3">
                    <label for="sky-tab3"><span><span><i class="fa fa-heart"></i>¿Cómo puedo ayudar?</span></span></label>
                                    
                                    
                    
                    <ul>
                        <li class="sky-tab-content-1">					
                            <div class="typography">
                                <h1>Acerca de nosotros</h1>
                                Creciendo con Zaragoza es un grupo formado por estudiantes del Tecnológico de Monterrey. En él se busca el desarrollo integral de las comunidades alejadas del municipio de General Zaragoza, en Nuevo León. 
                                <br>
                                <br>
                                Tenemos la misión de promover la participación de nuestra sociedad en general, para fomentar el desarrollo integral de las poblaciones dispersas más necesitadas de General Zaragoza. Así mismo, procurar que las habilidades de, tanto los habitantes como los participantes, formen parte activa de su propio desarrollo.
                                Buscamos impactar en tres áreas principales: 
                                <br>
                                <br>
                                1) Salud y Nutrición: 
                                Lograr el desarrollo y crecimiento integral en los niños y brindar una mejor calidad de vida para adultos mayores, 
                                a través de la elaboración de NutreTec y VidaTec. Complementos alimenticios que aportan los nutrientes necesarios para el 
                                desarrollo físico y mental de cada etapa, respectivamente.
                                <br>
                                <br>
                                2) Educación:
                                Un programa integral y compensatorio de educación que combata el analfabetismo y fomente una educación de calidad, a través del trabajo conjunto con CONAFE. Mediante la elaboración de manuales y planes educativos que faciliten la enseñanza en las escuelas rurales. 
                                <br>
                                <br>
                                3) Apoyo Económico:
                                Un programa que busca satisfacer algunas de las necesidades más básicas de las comunidades, como lo son el alimento y el vestido. Esto con la finalidad de reducir costos de vida de tal manera que se refleje en un crecimiento en la economía local. Así mismo, programas anexos que buscan la creación de sistemas económicos que proporcionen empleo y flujo de bienes entre las comunidades y el resto de la sociedad. 
                                
                                                
                            </div>
                        </li>
                                        
                        <li class="sky-tab-content-2">
                            <div class="typography">
                                <h1>Mesa de Campus</h1>
                                                
                            </div>
                        </li>
                        
                        <li class="sky-tab-content-3">
                            <div class="typography">
                                <h1>Mesa de Prepa</h1>
                                                
                            </div>
                        </li>
                        
                        
                    </ul>
                </div>
                <!--/ tabs -->
                
            </div>
    </body>
    
                    
        <div class="typography">
                            
    </div>
                                                    
                                                    
                                                    
                                                    
                                                    
</div>
</body>
            
            
            
            
</html>