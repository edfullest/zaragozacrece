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
                    
                    <div class="body">
                        <form action="ControlNuevaNota" method="post" id="sky-form" class="sky-form" >
                            <header>Crea una nueva nota</header>
                            
                            <fieldset>					
                                
                                <div class="row">
                                    <section class="col col-7">
                                        <label class="label">Título de la publicación</label>
                                        <label class="input">
                                            <i class="icon-append fa fa-tint"></i>
                                            <input type="text" name="titulo" id="titulo">
                                        </label>
                                    </section>
                                    
                                </div>
                                
                                
                                <div class="row">
                                    <section class="col col-5">
                                        <label class="label">Autor</label>
                                        <label class="input">
                                            <i class="icon-append fa fa-user"></i>
                                            <input type="text" name="nombre" id="nombre">
                                        </label>
                                    </section>
                                </div>
                                
                                
                                <p>
                                    
                                    <textarea class="ckeditor" cols="80" name="editor1" id="editor1"  rows="10">
                                                                <h1><strong>SUPER DUPER AWESOME</strong></h1>
                                                                    
                                                                    <p>&nbsp;</p>
                                                                        
                                                                    <ol>
                                                                            <li><strong>S&Uacute;PER OP</strong></li>
                                                                            <li><strong>MUY OP</strong></li>
                                                                            <li><strong>UBER OP</strong></li>
                                                                            <li><strong>CHEFF</strong></li>
                                                                    </ol>
                                                                        
                                    </textarea>
                                </p>
                                <c:choose>
                                    <c:when test="${goodnote}">
                                        <b><font color="green">Tu nota se ha subido exitosamente</font></b>
                                        </c:when>
                                    </c:choose>   
                            </fieldset>
                            
                            <input type="hidden" name="idAdmin" value=${sessionScope.idAdmin}>
                            
                            
                            
                            
                            
                            
                            <footer>
                                <button type="submit" class="button">Subir Nota</button>
                            </footer>
                            
                            
                        </form>
                        
                    </div> 
                    
                </div>
                
                
                
                <script type="text/javascript">
                    $(function()
                    {
                        /*
                                                      // Regular datepicker
                                                      $('#fechaPlanPago').datepicker({
                                                          dateFormat: 'dd.mm.yy',
                                                          prevText: '<i class="fa fa-chevron-left"></i>',
                                                          nextText: '<i class="fa fa-chevron-right"></i>'
                                                      });
                         */
                        
                        
                        $("#sky-form").validate(
                                {
                                    
                                    // Rules for form validation
                            rules:
                                    {
                                        nombre:
                                        {
                                            required: true,
                                },
                                titulo:
                                        {
                                            required: true,
                                    
                                },
                                editor1:
                                        {
                                            required: true,
                                },
                                
                                
                            },
                            
                            // Messages for form validation
                            messages:
                                    {
                                        nombre:
                                        {
                                            required: 'Por favor introduce tu nombre',
                                },
                                titulo:
                                        {
                                            required: 'Ponle un título a la publicación',
                                },
                                
                                editor1:
                                        {
                                            required: 'Ponle contenido!',
                                    
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
                <form action="sample_posteddata.php" method="post">
                    
                </form>
            </body>
            
            
        </c:when >
        <c:when test="${sessionScope.goodlogin==false}">
            
            <jsp:forward page = "admin" />
            
        </c:when >
        
        <c:when test="${sessionScope.goodlogin==null}">
            
            <jsp:forward page = "admin" />
            
        </c:when >
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