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
        <link rel="stylesheet" href="ckeditor/samples/sample.css">
        <!--[if lt IE 9]>
                <link rel="stylesheet" href="css/sky-forms-ie8.css">
        <![endif]-->
            
        <script src="js/jquery.min.js"></script>
        <script src="js/jquery.form.min.js"></script>
        <script src="js/jquery.validate.min.js"></script>	
        <script src="js/jquery-ui.min.js"></script>
        <script src="ckeditor/ckeditor.js"></script>
            
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
                            <a href="#"><i class="fa fa-users"></i>Apadrinados<i class="fa fa-indicator fa-chevron-down"></i></a> 
                                
                            <div class="grid-container3">
                                <ul>
                                    <li>
                                        <a href="nuevoApadrinado"><i class="fa fa-plus-circle"></i>Añadir Apadrinado</a>
                                    </li>
                                    <li>
                                        <a href="apadrinados?tipo=${"cargarApadrinados"}"><i class="fa fa-bar-chart-o"></i>Nueva entrada de apadrinado</a>
                                    </li>
                                    <li>
                                        <a href="#"><i class="fa fa-gears"></i>Editar apadrinados</a>
                                    </li>
                                    <li>
                                        <a href="#"><i class="fa fa-table"></i>Apadrinados registrados</a>
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
                                        <a href="#"><i class="fa fa-lightbulb-o"></i>Asignar apadrinados a padrinos</a>
                                    </li>
                                        
                                    <li>
                                        <a href="#"><i class="fa fa-money"></i>Asignar pagos</a>
                                    </li>
                                        
                                    <li>
                                        <a href="#"><i class="fa fa-table"></i>Padrinos registrados</a>
                                    </li>
                                        
                                </ul>
                            </div>
                                
                        </li>
                        <!--/ Padrinos -->
                            
                        <!-- Apartado para apadrinar un niño -->
                            
                        <li>
                            <a href="Notas"><i class="fa fa-comments-o"></i>Añadir Nota</a>
                                
                        </li>
                            
                        <!--/ Apartado para apadrinar un niño  -->
                            
                        <!-- Mi cuenta,esto es si existe sesion -->
                            
                            
                            
                            
                        <!-- Mi cuenta,esto es si existe sesion -->
                            
                        <!-- Apartado para cerrar sesión -->
                        <li>
                            
                            <a href="ControlAdminLogIn?logout=${"true"} "><i class="fa fa-sign-out"></i>Cierra Sesión</a>
                                
                                
                        </li>
                        <!--/ Apartado para cerrar sesión -->
                            
                            
                    </ul>
                    <!--/ Termina el menú -->
                        
                        
                </div>
                    
                    
                <div class="body">
                    <form action="ControlCargarPagos" method="GET" id="sky-form" class="sky-form" >
                        <header>Nuevo pago de <b>${nombreCompleto}</b> con correo <b>${correoPadrino}</b></header>
                            
                        <fieldset>
                            
                            <div class="row">
                                <section class="col col-5">
                                    <label class="label">¿Este padrino quiere apadrinar solo? Recuerda que si el pago que te dio es de <b>375 pesos</b>, quiere apadrinar en pareja</label>
                                    <section>
                                        <div class="inline-group">
                                            <label class="radio"><input type="radio" name="radioButton" id="radioButton"><i></i>Si</label>
                                        </div>
                                    </section>
                                    
                                        
                                </section>
                                
                                <section class="col col-5">
                                    <label class="label">¿Pagó <b>750 pesos</b>?</label>
                                        <div class="inline-group">
                                            <label class="radio"><input type="radio" name="radioButton2" id="radioButton2"><i></i>Si</label>
                                        </div>
                                    </section>
                            </div>
                            <div class="row">
                                <section class ="col col-5">
                                    
                                    
                                    <label class="label">Select single date</label>	
                                    <label class="input">
                                        <i class="icon-append fa fa-calendar"></i>
                                        <input type="text" name="fechaPago" id="fechaPago">
                                    </label>
                                        
                                </section>
                            </div>    
                                
                                <input type="hidden" name="tipo" value="asignarNuevoPago">
                                <input type="hidden" name="idPadrino" value="${param.idPadrino}">
                                
                        </fieldset>
                            
                            
                        <footer>
                            <button type="submit" class="button">Añadir Pago</button>
                        </footer>
                    </form>
                        
                </div>
                    
                    
                    
                    
                    
                <script type="text/javascript">
                    $(function()
                    {
                        
                        // Regular datepicker
                        $('#fechaPago').datepicker({
                            dateFormat: 'dd.mm.yy',
                            prevText: '<i class="fa fa-chevron-left"></i>',
                            nextText: '<i class="fa fa-chevron-right"></i>'
                        });
                        
                        
                        
                        $("#sky-form").validate(
                                {
                                    
                                    // Rules for form validation
                            rules:
                                    {
                                        peso:
                                        {
                                            required: true,
                                    number: true,
                                },
                                nivelEscolar:
                                        {
                                            required: true,
                                },
                                
                                IMC:
                                        {
                                            required: true,
                                    number: true,
                                },
                                
                                
                                estatura:
                                        {
                                            required: true,
                                    number: true,
                                },
                                radioButton:
                                        {
                                            required: true,
                                    
                                },
                                
                                radioButton2:
                                        {
                                            required: true,
                                    
                                },
                                
                                fechaPago:{
                                  required: true  
                                },
                                
                            },
                            
                            // Messages for form validation
                            messages:
                                    {
                                        radioButton:
                                        {
                                            required: "Es necesario que contestes esta pregunta",  
                                    
                                },
                                        
                                         radioButton2:
                                        {
                                            required: "Es necesario que contestes esta pregunta",  
                                    
                                },
                                
                                fechaPago:{
                                  required: "Introduce la fecha del pago",  
                                },
                                
                                
                            },
                            
                            
                            
                            // Do not change code below
                            errorPlacement: function(error, element)
                            {
                                error.insertAfter(element.parent());
                            }
                        });
                    });			
                </script>
                    
                    
            </body>
                
                
                
                
        </c:when>
        <c:when test="${sessionScope.goodlogin==false}">
            <jsp:forward page = "admin" />
        </c:when>
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