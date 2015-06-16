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
        
    <c:choose>
        <c:when test="${sessionScope.goodlogin}">
            
            
            <body class="bg-cyan">
                
                
                <div class="body">
                    <c:choose>
                        <c:when test="${sessionScope.error!=null}">
                            <form action="" method="post" id="sky-form" class="sky-form" target="_top" >
                                
                                
                                <header><i class="fa fa-exclamation"></i> Hubo un error con el pago</header>
                                <fieldset>      
                                    <p>Esto se debe a que el banco rechazó su método de pago, si quiere intentar con otro,
                                        haga click en "Pagar con PayPal"</p>
                                </fieldset>
                                <footer>
                                    <c:choose>
                                        <c:when test="${param.tipo.equals('apadrinasolo')}" >
                                            <a href="ControlPanelPadrino?send=${"regresarSolo"}">
                                                <button type="button" class="return-button">Regresar</button></a>
                                                    
                                            <a href="${redirectURL}">
                                                <button type="button" class="button">Pagar con PayPal</button></a>   
                                            </c:when>
                                            <c:when test="${param.tipo.equals('apadrinapareja')}" >
                                            <a href="ControlPanelPadrino?send=${"regresar"}">
                                                <button type="button" class="return-button">Regresar</button></a>
                                                    
                                            <a href="${redirectURL}">
                                                <button type="button" class="button">Pagar con PayPal</button></a>   
                                                    
                                                    
                                        </c:when>
                                    </c:choose>
                                        
                                        
                                        
                                        
                                        
                                </footer>
                                    
                                    
                                    
                            </form>
                        </c:when>
                        <c:when test="${sessionScope.error==null && sessionScope.exitoso}">
                            
                            <form action="ControlPanelPadrino" method="post" id="sky-form" class="sky-form" target="_top" >
                                
                                
                                <header><i class="fa fa-check"></i>Pago Exitoso!</header>
                                <fieldset> 
                                    <p>Su pago se realizó exitosamente!</p>
                                        
                                </fieldset>
                                <footer>
                                    <c:choose>
                                        <c:when test="${param.tipo.equals('apadrinasolo')}" >
                                            <a href="ControlPanelPadrino?send=${"regresarSolo"}">
                                                <button type="button" class="return-button">Regresar</button></a>
                                           
                                            </c:when>
                                            <c:when test="${param.tipo.equals('apadrinapareja')}" >
                                            <a href="ControlPanelPadrino?send=${"regresar"}">
                                                <button type="button" class="return-button">Regresar</button></a>
                             
                                        </c:when>
                                    </c:choose>
                                        
                                        
                                        
                                </footer>
                                    
                                    
                                    
                            </form>
                                
                                
                        </c:when>
                        <c:otherwise>
                            <form action="" method="post" id="sky-form" class="sky-form" target="_top" >
                                
                                
                                <header>Para ir a PayPal, presione el botón "Pagar con PayPal", o bien, presione "Regresar" para quedarse en el sitio</header>
                                <fieldset>      
                                    
                                </fieldset>
                                <footer>
                                    <% System.out.println(request.getParameter("tipo")); %>
                                    <c:choose>
                                        <c:when test="${param.tipo.equals('apadrinasolo')}" >
                                            <% System.out.println("entra"); %>
                                            <a href="ControlPanelPadrino?send=${"regresarSolo"}">
                                                <button type="button" class="return-button">Regresar</button></a>
                                                    
                                            <a href="${redirectURL}">
                                                <button type="button" class="button">Pagar con PayPal</button></a>   
                                            </c:when>
                                            <c:when test="${param.tipo.equals('apadrinapareja')}" >
                                            <a href="ControlPanelPadrino?send=${"regresar"}">
                                                <button type="button" class="return-button">Regresar</button></a>
                                                    
                                            <a href="${redirectURL}">
                                                <button type="button" class="button">Pagar con PayPal</button></a>   
                                                    
                                                    
                                        </c:when>
                                    </c:choose>
                                            
                                </footer>
                                    
                                    
                                    
                            </form>
                        </c:otherwise>
                    </c:choose>
                        
                        
                        
                </div>
                    
                    
                    
                    
                    
                    
            </body>                             
                
        </c:when>
        <c:otherwise>
            <jsp:forward page = "IniciaSesion" />
        </c:otherwise>
    </c:choose>  
        
        
        
        
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