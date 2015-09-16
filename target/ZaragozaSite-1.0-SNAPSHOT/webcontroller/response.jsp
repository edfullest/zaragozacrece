<%-- 
    Document   : response
    Created on : Feb 22, 2010, 7:21:40 PM
    Author     : jeff
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>

    <ul>
<li><p><b>First Name:</b>
   <%= request.getParameter("name")%>
</p></li>

</ul>
    
  

</html>
