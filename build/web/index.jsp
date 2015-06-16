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
			<ul class="sky-mega-menu sky-mega-menu-response-to-icons">
				<!-- home -->
                                
				<li>
                                    
					<a href="/ccz"><i class="fa fa-single fa-home"></i></a>
                                        
                                        
				</li>
				<!--/ home -->
				
				<!-- about -->
				<li>
                                       <a href="conocenos"><i class="fa fa-star"></i>Conócenos</a>
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
                                        <a href="CuentaPadrino"><i class="fa fa-dashboard"></i>Mi cuenta</a>
                                        
                                        </li>
                                        
                                    <% } %>
				
                                <!-- Mi cuenta,esto es si existe sesion -->
                                
                                <!-- Apartado para iniciar sesión -->
                                    
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
					<a href="https://www.facebook.com/Zaragozacrece?fref=ts"><i class="fa fa-facebook"></i>Contáctanos</a>
				</li>
				<!--/ Apartado para contactar -->
			</ul>
			<!--/ Termina el menú -->
                        
                        <div class="imagen-principal"></div>
			
		</div>
	</body>

  
</html>
    
    <%--
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form name="Name Input Form" action="webcontroller/response.jsp">
            <input type="text" name="name" />
            <input type="submit" value="OK"/> 
        </form>
        <h2>wass</h2>
    </body>
</html>
--%>