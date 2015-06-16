<!DOCTYPE html> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                            
                        <%--Verifico que exista una sesion de padrino--%>
                        <% if (session.getAttribute("idPadrino") == null) { %>
                            
                        <%--Si existe, entonces cargo lo que se tenga que cargar--%>
                        <% } else {%>
                        <li>
                            <a href="ControlCargarApadrinados"><i class="fa fa-dashboard"></i><b>Mi cuenta</b></a>
                                
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
        
        
        
        <%-- Empieza LogIn de Admin --%>
		<div class="body body-s">		
			<form action="ControlAdminLogIn" method="post"  id="sky-form" class="sky-form">
				<header>Login de Admins</header>
				
				<fieldset>					
					<section>
						<div class="row">
							<label class="label col col-4">Nombre de usuario</label>
							<div class="col col-8">
								<label class="input">
									<i class="icon-append fa fa-user"></i>
									<input type="text" name="username" id="username">
								</label>
							</div>
						</div>
					</section>
					
					<section>
						<div class="row">
							<label class="label col col-4">Contraseña</label>
							<div class="col col-8">
								<label class="input">
									<i class="icon-append fa fa-lock"></i>
									<input type="password" name="password" id="password">
								</label>
							</div>
						</div>
					</section>
                                    
                                    <c:choose>
                                        <c:when test="${badlogin}">
                                            <b><font color="red">Nombre de usuario o password incorrectos</font></b>
                                        </c:when>
                                    </c:choose>
                              
				</fieldset>
				<footer>
					<button type="submit" class="button">Log in</button>
				</footer>
			</form>			
		</div>
		

		<script type="text/javascript">
			$(function()
			{
				// Validation for login form
				$("#sky-form").validate(
				{					
					// Rules for form validation
					rules:
					{
						username:
						{
							required: true,
							
						},
						password:
						{
							required: true,
							minlength: 3,
							maxlength: 20
						}
					},
										
					// Messages for form validation
					messages:
					{
						username:
						{
							required: 'Por favor escribe tu nombre de usuario',
						},
						password:
						{
							required: 'Por favor escribe tu contraseña'
						}
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
</html>