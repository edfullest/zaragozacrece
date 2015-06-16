<%-- 
    Document   : InterfazCerrarSesion
    Created on : May 24, 2015, 6:44:23 PM
    Author     : Lalo Serna
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    
    <%--Verifico que exista una sesion de padrino--%>
        <% if (session.getAttribute("idPadrino") == null) { %>
            <jsp:forward page = "index.jsp" />
        <%--Si existe, entonces cargo lo que se tenga que cargar--%>
        <% } else {%>
        
        <% session.invalidate(); 
        %>
           
            <jsp:forward page = "index.jsp" />
        <% } %>
        
 
</html>
