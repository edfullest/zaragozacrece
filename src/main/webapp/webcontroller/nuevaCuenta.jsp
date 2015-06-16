<%-- 
    Document   : nuevaCuenta
    Created on : May 22, 2015, 5:00:57 PM
    Author     : Lalo Serna
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">		
		<title>Creciendo con Zaragoza</title>
		
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
		
		<link rel="stylesheet" href="css/demo.css">
		<link rel="stylesheet" href="css/font-awesome.css">
		<link rel="stylesheet" href="css/sky-tabs.css">
		
		<!--[if lt IE 9]>
			<link rel="stylesheet" href="css/sky-tabs-ie8.css">
			<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
			<script src="js/sky-tabs-ie8.js"></script>
		<![endif]-->
	</head>
	
	<body class="bg-cyan">
		<div class="body">
		
			<!-- tabs -->
			<div class="sky-tabs sky-tabs-pos-top-left sky-tabs-anim-flip sky-tabs-response-to-icons">
				<input type="radio" name="sky-tabs" id="sky-tab1" class="sky-tab-content-1">
				<label for="sky-tab1"><span><span><i class="fa fa-archive"></i>Conócenos</span></span></label>
				
				<input type="radio" name="sky-tabs" id="sky-tab2" class="sky-tab-content-2">
				<label for="sky-tab2"><span><span><i class="fa fa-calendar"></i>Blog</span></span></label>
				
				<input type="radio" name="sky-tabs" checked id="sky-tab3" class="sky-tab-content-3">
				<label for="sky-tab3"><span><span><i class="fa fa-arrow-up"></i>Apadrina un niño</span></span></label>
				
				<input type="radio" name="sky-tabs" id="sky-tab4" class="sky-tab-content-4">
				<label for="sky-tab4"><span><span><i class="fa fa-picture-o"></i>Galería de Fotos</span></span></label>
                                
                                <input type="radio" name="sky-tabs" id="sky-tab5" class="sky-tab-content-5">
				<label for="sky-tab5"><span><span><i class="fa fa-sign-in"></i>Inicia Sesión</span></span></label>
                                
                                
                                
                                
				
				<ul>
					<li class="sky-tab-content-1">					
						<div class="typography">
							<h1>Cónocenos</h1>
                                                        
						</div>
					</li>
					
					<li class="sky-tab-content-2">
						<div class="typography">
							<h1>Entérate</h1>
							<p>Lorem Ipsum</p>
						</div>
					</li>
					
					<li class="sky-tab-content-3">
						<div class="typography">
							<h1>Apadrina un niño</h1>
                                                        <p>A continuación llena los siguientes recuadros para realizar el trámite de apadrinamiento</p>
                                                        <input type="text" name="nombreCompleto" value="" size="50" />
                                                        <input type="text" name="celular" value="" size="50" />
                                                        <input type="text" name="correo" value="" size="50" />
                                                        
							<p></p>
						</div>
					</li>
					
					<li class="sky-tab-content-4">
						<div class="typography">
							<h1>Inicia Sesión</h1>
							<p></p>
						</div>
					</li>
                                        
                                        <li class="sky-tab-content-5">
						<div class="typography">
							<h1>Ver Galería de Fotos</h1>
							<p></p>
						</div>
					</li>
                                        
                                        <li class="sky-tab-content-6">
						<div class="typography">
							<h1>Ver Galería de Fotos</h1>
							<p></p>
						</div>
					</li>
                                        
				</ul>
			</div>
			<!--/ tabs -->
			
		</div>
	</body>
</html>