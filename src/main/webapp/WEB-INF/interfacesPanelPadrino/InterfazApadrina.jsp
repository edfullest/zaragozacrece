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
                    
                    
                    
                    <!-- mega menu -->
                    <ul class="sky-mega-menu sky-mega-menu-anim-flip sky-mega-menu-response-to-icons">
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
                        
                          <!-- Mi cuenta,esto es si existe sesion -->
                            
                        <%--Verifico que exista una sesion de padrino--%>
                        <% if (session.getAttribute("idPadrino") == null) { %>
                            
                        <%--Si existe, entonces cargo lo que se tenga que cargar--%>
                        <% } else {%>
                        <li>
                            <a href="ControlCargarApadrinados"><i class="fa fa-dashboard"></i><b>Mi cuenta</b></a>
                                
                        </li>
                            
                        <% } %>
                        
                        
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
                        
                        <!-- Apartado para cerrar sesión -->
                        <li>       
                            <a href="ControlLogInPadrino?logout=${"true"} "><i class="fa fa-sign-out"></i>Cierra Sesión</a>
                        </li>
                        <!--/ Apartado para cerrar sesión -->
                        
                        <!-- Apartado para contactar  -->
                        <li class="right">
                            <a href="contactar"><i class="fa fa-phone"></i>Contáctanos</a>
                        </li>
                        <!--/ Apartado para contactar -->
                    </ul>
                  <!--/ Termina el menú -->
                        
                    <h1 class="header123">Bienvenido, ${sessionScope.nombreCompleto}</h1>
                    <div class="container">
                    <div class="left">
                            <aside>
                                
                            <!-- mega menu -->
                            <ul class="sky-mega-menu sky-mega-menu-pos-left sky-mega-menu-response-to-icons sky-mega-menu-anim-scale">
                                
                                
                               
                                    
                                <!--/ about -->
                                    
                                <!-- news -->
                                <li>
                                    <a href="ControlPanelPadrino?opcion=2&hayApadrinados=${hayApadrinados}"><i class="fa fa-arrows-h"></i><b>Apadrina en pareja</b></a>
                                        
                                </li>
                                <!--/ news -->
                                    
                                <!-- portfolio -->
                                <li>
                                    <a href="ControlPanelPadrino?opcion=3&hayApadrinados=${hayApadrinados}"><i class="fa fa-arrow-circle-up"></i>Apadrina solo</a>
                                        
                                </li>
                                <!--/ portfolio -->
                                    
                                <!-- blog -->
                                <li>
                                    <a href="ControlPanelPadrino?opcion=4&hayApadrinados=${hayApadrinados}"><i class="fa fa-cogs"></i>Configuración de cuenta</a>
                                </li>
                                <!--/ blog -->
                                    
                            </ul>
                                
                            <!--/ mega menu -->
                                
                        </aside>
                   
                
            </div>
              
		<div class="center">
                    
			<!-- tabs -->
			<div class="sky-tabs sky-tabs-pos-top-left sky-tabs-response-to-icons">
				<div class="textbox">					
					<div class="typography">
						
                                            <c:choose>
                                                
                                                <c:when test="${pareja==true && mensaje==null}" >
                                                    <c:choose>
                                                        <c:when test="${respuesta.equals('esperandoAdmin')}">
                                                            <p> <font size="4"> El pago más reciente hecho por ti y <b>${correo}</b> 
                                                            ya está registrado. Sólo falta que el administrador les asigne un apadrinado </font></p>
                                                            
                                                        </c:when>
                                                     
                                                            
                                                        <c:when test="${respuesta.equals('asignado')}">
                                                            <p> <font size="4">Tú y <b>${correo}</b> tienen su pago asignado a una suscripcion.
                                                            Mantente informado acerca del próximo viaje, porque la suscripción acaba días antes del mismo! </font></p>
                                                            
                                                        </c:when>
                                                        <c:when test="${respuesta.equals('noPagaste')}">
                                                            <p>  <font size="4">No has pagado y <b>${correo}</b> ya pagó!
                                                            Para pagar con PayPal, haga click en el botón "Pagar":</font>
                                                           
                                                            
                                                                <a  href="pagarPaypal?tipo=apadrinapareja"> 
                                                                    
                                                                    <button type="button" class="button">Pagar</button></a>
                                                            <br>
                                                            <br>        
                                                            
                                                            </p>
                                                        </c:when>
                                                        <c:when test="${respuesta.equals('noPagoOtro')}">
                                                            <font size="4">Ya pagaste pero <b>${correo}</b> aún no lo ha hecho!</font>
                                                            
                                                            
                                                        </c:when>
                                                        <c:when test="${respuesta.equals('nadieHaPagado')}">
                                                            <p><font size="4">No has pagado ni tú ni <b>${correo}</b>
                                                                Para pagar con PayPal, haga click en el botón "Pagar":</font>
                                                            <a  href="pagarPaypal?tipo=apadrinapareja"> <button type="button" class="button">Pagar</button></a>
                                                            <br>
                                                            <br>
                                                            </p>   
                                                            
                                                            
                                                            
                                                        </c:when>
                                                        <c:when test="${respuesta.equals('sinRegistro')}">
                                                            <font size="4"><b>${correo}</b> aún no te ha aceptado como pareja! </font>
                                                            
                                                            
                                                            
                                                        </c:when>
                                                        
                                                    </c:choose>
                                                    
                                                </c:when>
                                                <c:when test="${mensaje!=null}">
                                                    
                                                    <div class="sky-form">
                                                        <c:choose>
                                                            <c:when test="${mensaje.equals('ExitoPareja')}">
                                                                <div class="body">
                                                                    
                                                                    
                                                                    <header><i class ="fa fa-check"></i> Vínculo exitoso! Tú y <b>${correo}</b> decidieron apadrinar juntos a un niño </header>
                                                                    <fieldset>
                                                                        <p> Ahora que ya están vinculados, el pago del apadrinamiento se divide en dos</p>
                                                                        <p> ¡Ahora sólo falta pagar! Esto se puede hacer en tu cuenta, seleccionando la pestaña "Pagos"</p>
                                                                            
                                                                            
                                                                    </fieldset>
                                                                    <input type="hidden" name="tipoMensaje" value="Exito">
                                                                    <footer>
                                                                        <a href="ControlPanelPadrino?tipo=cargar&opcion=2">
                                                                            <button type="button" class="return-button">Regresar</button></a>
                                                                    </footer>
                                                                        
                                                                        
                                                                        
                                                                        
                                                                        
                                                                        
                                                                </div>
                                                            </c:when>
                                                            <c:when test="${mensaje.equals('exito')}">
                                                                
                                                                <header><i class ="fa fa-check"></i> Excelente! Ahora solo falta la confirmacion de <b>${correo2}</b> </header>
                                                                <fieldset>
                                                                    <p> Mándale un mensaje a tu amigo, para que se registre y ambos puedan hacer el pago correspondiente en la pestaña "Pagos"</p>
                                                                    <p><b>Importante:</b> tu amigo debe registrarse con el mismo correo que tu pusiste para que se haga un vínculo exitoso</p>
                                                                    
                                                                </fieldset>
                                                                <input type="hidden" name="tipoMensaje" value="Exito">
                                                                <footer>
                                                                    <a href="ControlPanelPadrino?tipo=cargar&opcion=2">
                                                                        <button type="button" class="return-button">Regresar</button></a>
                                                                </footer>
                                                                
                                                                
                                                                
                                                            </c:when>
                                                            <c:when test="${mensaje.equals('MatchError')}">
                                                                <header><i class ="fa fa-warning"></i> Ya te escogieron como pareja de apadrinamiento! <i class ="fa fa-warning"></i> </header>
                                                                <input type="hidden" name="tipoMensaje" value="MatchError">
                                                                    
                                                                <fieldset>
                                                                    <p>Vimos que quieres apadrinar con <b> ${correoPad1} </b></p>
                                                                    <p>El correo <b> ${correo} </b> te agregó como pareja de apadrinamiento</p>
                                                                        
                                                                        
                                                                        
                                                                    <p> </p>
                                                                        
                                                                </fieldset>
                                                                <footer>
                                                                    <a href="ControlPanelPadrino?tipo=cargar&opcion=2">
                                                                        <button type="button" class="return-button">Regresar</button></a>
                                                                    <a href="nuevaPareja?correo2=${correo2}">
                                                                        <button type="button" class="button">Apadrinar con ${correo} </button></a>
                                                                    
                                                                </footer>
                                                            </c:when>
                                                            <c:when test="${mensaje.equals('ParejaRecursiva')}">
                                                                <header><i class ="fa fa-warning"></i> ¿Espera qué? ¿Quieres apadrinar contigo mismo? Eso no se puede! </header>
                                                                    
                                                                <fieldset>
                                                                    <p>Si quieres apadrinar con alguien, procura que ese correo sea diferente al tuyo. </p>
                                                                    
                                                                    
                                                                </fieldset>
                                                                <input type="hidden" name="tipoMensaje" value="ErrorCorreo">
                                                                <footer>
                                                                    <button type="submit" class="button">Regresar</button>
                                                                </footer>
                                                                
                                                                
                                                            </c:when>
                                                            <c:when test="${mensaje.equals('OtroPadrino')}">
                                                                
                                                                <header><i class ="fa fa-warning"></i> Lo sentimos,<b> ${correo} </b> está esperando a que <b> ${correoRegistrado} </b> se registre </header>
                                                                    
                                                                <fieldset>
                                                                    <p>Pudiera ser que esa persona seas tú, y <b> ${correo} </b> te haya registrado con otro correo. Verifica que el correo que pusiste sea el mismo </p>
                                                              
                                                              
                                                                </fieldset>
                                                                <input type="hidden" name="tipoMensaje" value="ErrorCorreo">
                                                                <footer>
                                                                    <button type="submit" class="button">Regresar</button>
                                                                </footer>
                                                          
                                                            </c:when>
                                                            <c:when test="${mensaje.equals('TienePadrino')}">
                                                                <header><i class ="fa fa-warning"></i> Lo sentimos, <b> ${correo} </b> tiene como padrino a ${correoRegistrado} </header>
                                                                
                                                                <fieldset>
                                                                    <p> Esa persona ya tiene pareja de apadrinamiento!</p>
                                                                        
                                                                        
                                                                </fieldset>
                                                                <input type="hidden" name="tipoMensaje" value="ErrorCorreo">
                                                                <footer>
                                                                    <button type="submit" class="button">Regresar</button>
                                                                </footer>
                                                                    
                                                            </c:when>
                                                                
                                                                
                                                        </c:choose>
                                                    </div> 
                                                    </div>
                                                    </div>
                                                    </div>
                                                </c:when>
                                                    
                                                <c:when test="${unico==true}">
                                                    
                                                    <div class="sky-form">
                                                        
                                                        
                                                        
                                                        <header>Introduce el correo de la persona con quien quieras apadrinar</header>
                                                            
                                                        <fieldset>					
                                                            
                                                            
                                                            <section>
                                                                <label class="label">Correo Electrónico</label>
                                                                <label class="input">
                                                                    <i class="icon-append fa fa-envelope-o"></i>
                                                                    <input type="email" name="correo2" id="correo2">
                                                                </label>
                                                            </section>
                                                                
                                                                
                                                                
                                                                
                                                                
                                                        </fieldset>
                                                            
                                                            
                                                        <footer>
                                                            <a href='' onclick="this.href='nuevaPareja?correo2='+document.getElementById('correo2').value">
                                                                <button type="button" class="button">Mandar Solicitud</button>
                                                            </a>
                                                                
                                                        </footer>
                                                            
                                                            
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
                                                                            
                                                                            
                                                                            correo2:
                                                                            {
                                                                                required: true,
                                                                        email: true
                                                                    },
                                                                    
                                                                },
                                                                
                                                                // Messages for form validation
                                                                messages:
                                                                        {
                                                                            
                                                                            correo2:
                                                                            {
                                                                                required: 'Introduzca un correo',
                                                                        email: 'Introduzca un correo válido'
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
                                                        
                                                </c:when>
                                                    
                                            </c:choose>
					
				
			
			<!--/ tabs -->
                </div>
		 </div>        
                    
                    
                    
                            
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