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
        <link rel="stylesheet" href="graficas/samples/style.css" type="text/css">
        <!--[if lt IE 9]>
                <link rel="stylesheet" href="css/sky-forms-ie8.css">
        <![endif]-->
            
        <script src="js/jquery.min.js"></script>
        <script src="js/jquery.form.min.js"></script>
        <script src="js/jquery.validate.min.js"></script>	
        <script src="js/jquery-ui.min.js"></script>
        <script src="graficas/amcharts/amcharts.js" type="text/javascript"></script>
        <script src="graficas/amcharts/serial.js" type="text/javascript"></script>
        <script src="graficas/amcharts/themes/light.js" type="text/javascript"></script>
        <script src="graficas/amcharts/themes/dark.js" type="text/javascript"></script>
            
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
                    
                        
                        
                </div>
                    
                <div class="body">
                    <h1 class="header123">Estás viendo los datos de <b>${nombreCompleto}</b> ID: <b>${idApadrinado} </b></h1>
                    <!-- tabs -->
                    <div class="sky-tabs sky-tabs-pos-top-left sky-tabs-anim-flip sky-tabs-response-to-icons">
                        <input type="radio" name="sky-tabs" checked id="sky-tab1" class="sky-tab-content-1">
                        <label for="sky-tab1"><span><span><i class="fa fa-eye"></i>Datos de padrino(s)</span></span></label>
                            
                        <input type="radio" name="sky-tabs" id="sky-tab2" class="sky-tab-content-2">
                        <label for="sky-tab2"><span><span><i class="fa fa-bar-chart-o"></i>Estadísticas de Peso</span></span></label>
                            
                        <input type="radio" name="sky-tabs" id="sky-tab3" class="sky-tab-content-3">
                        <label for="sky-tab3"><span><span><i class="fa fa-bar-chart-o"></i>Estadísticas de IMC</span></span></label>
                            
                        <input type="radio" name="sky-tabs" id="sky-tab4" class="sky-tab-content-4">
                        <label for="sky-tab4"><span><span><i class="fa fa-bar-chart-o"></i>Estadísticas de Nivel Escolar </span></span></label>
                            
                        <input type="radio" name="sky-tabs" id="sky-tab5" class="sky-tab-content-5">
                        <label for="sky-tab5"><span><span><i class="fa fa-bar-chart-o"></i>Estadísticas de estatura </span></span></label>                               
                            
                        <ul>
                            <li class="sky-tab-content-1">					
                                <div class="typography">
                                    
                                    <c:choose>
                                        
                                        <c:when test="${apadrinadoSinPadrinos}">
                                            <h2>Este apadrinado aún no tiene padrinos :( </h2>
                                            
                                        </c:when>
                                        <c:otherwise>
                                            <h2>Datos de padrino(s)</h2>
                                    <c:choose>
                                        
                                        <%-- Padrino unico --%>
                                        <c:when test="${nombrePadrinoUnico != null }">
                                            <h3><b>Nombre de padrino: </b> ${nombrePadrinoUnico} &nbsp;&nbsp;&nbsp;&nbsp; <b> Correo: </b> ${correoPadrinoUnico}</h3>
                                        </c:when>
                                            
                                        <%-- Dos Padrinos --%>
                                        <c:otherwise>
                                            <h3>
                                            <br>
                                            <b>Nombre de padrino 1: </b> ${nombrePadrino1} &nbsp;&nbsp;&nbsp;&nbsp; <b> Correo: </b> ${correoPadrino1}
                                            <br>
                                            <br>
                                            <b>Nombre de padrino 2: </b> ${nombrePadrino2} &nbsp;&nbsp;&nbsp;&nbsp; <b> Correo: </b> ${correoPadrino2}
                                            </h3>
                                        </c:otherwise>
                                            
                                    </c:choose>
                                        </c:otherwise>
                                        
                                    </c:choose>
                                    
                                </div>
                            </li>
                                
                            <li class="sky-tab-content-2">
                                <div class="typography">
                                    
                                    
                                    
                                    <!-- amCharts javascript code -->
                                    <script type="text/javascript">
                                        
                                        var chartData = new Array();
                                        <c:forEach items="${entradas}" var="entradas"> 
                                            entrada = new Object();
                                            entrada.date = "${entradas.fechaEntrada}"; 
                                            entrada.column1 = ${entradas.peso}; 
                                            
                                            chartData.push(entrada);
                                        </c:forEach>  
                                            
                                            AmCharts.makeChart("chartdiv",
                                            {
                                                "theme": "light",
                                                "type": "serial",
                                                "categoryField": "date",
                                                "dataDateFormat": "YYYY-MM-DD",
                                                "startDuration": 1,
                                                "categoryAxis": {
                                                    "gridPosition": "start"
                                                },
                                                "trendLines": [],
                                                "graphs": [
                                                    {
                                                        "balloonColor": "#008000",
                                                        "balloonText": "[[title]] capturado el día [[category]]: \n\
                                                        [[value]] kg",
                                                        "bullet": "round",
                                                        "fillAlphas": 0.23,
                                                        "fillColors": "#008000",
                                                        "fixedColumnWidth": 0,
                                                        "fontSize": 1,
                                                        "id": "AmGraph-1",
                                                        "lineColor": "#0000FF",
                                                        "title": "Peso ",
                                                        "valueField": "column1"
                                                    },
                                                ],
                                                "guides": [],
                                                "valueAxes": [
                                                    {
                                                        "id": "ValueAxis-1",
                                                        "title": "Peso (kg)"
                                                    }
                                                ],
                                                "allLabels": [],
                                                "balloon": {},
                                                "legend": {
                                                    "useGraphSettings": true
                                                },
                                                "titles": [
                                                    {
                                                        "id": "Title-1",
                                                        "size": 15,
                                                        "text": "Peso del apadrinado"
                                                    }
                                                ],
                                                "dataProvider": chartData
                                            }
                                                    );
                                    </script>
                                        
                                        
                                        
                                    <div id="chartdiv" style="width:100%; height:400px;"></div>
                                        
                                        
                                </div>
                            </li>
                                
                            <li class="sky-tab-content-3">
                                <div class="typography">
                                    
                                    
                                    <!-- amCharts javascript code -->
                                    <script type="text/javascript">
                                        
                                        var chartData = new Array();
                                        <c:forEach items="${entradas}" var="entradas"> 
                                            entrada = new Object();
                                            entrada.date = "${entradas.fechaEntrada}"; 
                                            entrada.column1 = ${entradas.IMC}; 
                                            
                                            chartData.push(entrada);
                                        </c:forEach>  
                                            
                                            AmCharts.makeChart("chartdiv2",
                                            {
                                                "theme": "light",
                                                "type": "serial",
                                                "path": "http://www.amcharts.com/lib/3/",
                                                "categoryField": "date",
                                                "dataDateFormat": "YYYY-MM-DD",
                                                "startDuration": 1,
                                                "dataProvider": chartData,
                                                "categoryAxis": {
                                                    "gridPosition": "start"
                                                },
                                                "trendLines": [],
                                                "graphs": [
                                                    {
                                                        "balloonColor": "#0000FF",
                                                        "balloonText": "[[title]] capturado el día [[category]]: \n\
                                                        [[value]] ",
                                                        "bullet": "round",
                                                        "fillAlphas": 0.23,
                                                        "fillColors": "#0000FF",
                                                        "fixedColumnWidth": 0,
                                                        "fontSize": 1,
                                                        "id": "AmGraph-1",
                                                        "lineColor": "#008000",
                                                        "title": "IMC ",
                                                        "valueField": "column1"
                                                    },
                                                ],
                                                "guides": [],
                                                "valueAxes": [
                                                    {
                                                        "id": "ValueAxis-1",
                                                        "title": "IMC (Índice de masa Corporal)",
                                                        
                                                    }
                                                ],
                                                "allLabels": [],
                                                "balloon": {},
                                                "legend": {
                                                    "useGraphSettings": true
                                                },
                                                "titles": [
                                                    {
                                                        "id": "Title-1",
                                                        "size": 15,
                                                        "text": "IMC del Apadrinado"
                                                    }
                                                ],
                                                
                                            }
                                                    );
                                    </script>
                                        
                                        
                                        
                                    <div id="chartdiv2" style="width:100%; height:400px;"></div>
                                        
                                </div>
                            </li>
                                
                            <li class="sky-tab-content-4">
                                <div class="typography">
                                    
                                    
                                    <!-- amCharts javascript code -->
                                    <script type="text/javascript">
                                        
                                        var chartData = new Array();
                                        <c:forEach items="${entradas}" var="entradas"> 
                                            entrada = new Object();
                                            entrada.date = "${entradas.fechaEntrada}"; 
                                            entrada.column1 = ${entradas.nivelEscolar}; 
                                            
                                            chartData.push(entrada);
                                        </c:forEach>  
                                            
                                            AmCharts.ready(function () {
                                                var chart = new AmCharts.AmSerialChart();
                                                chart.dataProvider = chartData;
                                                chart.categoryField = "date";
                                                
                                                chart.addTitle("Nivel Escolar del apadrinado", 15)
                                                chart.dataDateFormat = "YYYY-MM-DD";
                                                chart.startDuration = 1;
                                                
                                                
                                                // AXES
                                                // category                
                                                var categoryAxis = chart.categoryAxis;
                                                // categoryAxis.parseDates = true; // as our data is date-based, we set parseDates to true
                                                // categoryAxis.minPeriod = "DD"; // our data is daily, so we set minPeriod to DD
                                                
                                                categoryAxis.gridPosition = "start";
                                                
                                                
                                                // GRAPH
                                                var graph = new AmCharts.AmGraph();
                                                graph.title = "Nivel Escolar";
                                                graph.balloonColor = "#FF0000";
                                                graph.balloonText = "[[title]] capturado el día [[category]]";
                                                graph.valueField = "column1";
                                                graph.bullet = "round";
                                                graph.fillAlphas = "0.23";
                                                graph.fillColors = "#FF8000";
                                                graph.fixedColumnWidth = "#0";
                                                graph.fontSize = "1";
                                                graph.id = "AmGraph-1";
                                                graph.lineColor = "#FF0000";
                                                chart.addGraph(graph);
                                                
                                                
                                                // value
                                                var valueAxis = new AmCharts.ValueAxis();
                                                
                                                //valueAxis.axisAlpha = 0;
                                                //valueAxis.inside = true;
                                                valueAxis.id = "ValueAxis-1";
                                                valueAxis.title = "Nivel Escolar";
                                                
                                                function formatLabel(value, valueString, axis){
                                                    
                                                    // let's say we dont' want minus sign next to negative numbers
                                                    if(value == 0) {
                                                        valueString = "-";
                                                    }
                                                    
                                                    // let's say we dont' want minus sign next to negative numbers
                                                    if(value == 1) {
                                                        valueString = "Preescolar";
                                                    }
                                                    
                                                    if(value == 2) {
                                                        valueString = "Primaria menor";
                                                    }
                                                    
                                                    if(value == 3) {
                                                        valueString = "Primaria mayor";
                                                    }
                                                    
                                                    if(value == 4) {
                                                        valueString = "Secundaria";
                                                    }
                                                    
                                                    if(value == 5) {
                                                        valueString = "Preparatoria";
                                                    }
                                                    
                                                    if(value == 6) {
                                                        valueString = "Universidad";
                                                    }
                                                    
                                                    if(value == 7) {
                                                        valueString = "Graduado";
                                                    }
                                                    
                                                    
                                                    
                                                    
                                                    return valueString;
                                                }
                                                valueAxis.labelFunction = formatLabel;
                                                
                                                chart.addValueAxis(valueAxis);
                                                
                                                
                                                
                                                // LEGEND
                                                var legend = new AmCharts.AmLegend();
                                                legend.useGraphSettings = true;
                                                chart.addLegend(legend);
                                                
                                                // CURSOR
                                                //var chartCursor = new AmCharts.ChartCursor();
                                                //chart.addChartCursor(chartCursor);
                                                
                                                // WRITE
                                                chart.write("chartdiv3");
                                            });
                                    </script>
                                        
                                        
                                        
                                    <div id="chartdiv3" style="width:100%; height:400px;"></div>
                                        
                                </div>
                            </li>
                            
                                                            
                            <li class="sky-tab-content-5">
                                <div class="typography">
                                    
                                    
                                    <!-- amCharts javascript code -->
                                    <script type="text/javascript">
                                        
                                        var chartData2 = new Array();
                                        <c:forEach items="${entradas}" var="entradas"> 
                                            entrada = new Object();
                                            entrada.date = "${entradas.fechaEntrada}"; 
                                            entrada.column1 = ${entradas.estatura}; 
                                            
                                            chartData2.push(entrada);
                                        </c:forEach>  
                                            
                                            AmCharts.makeChart("chartdiv4",
                                            {
                                                "theme": "light",
                                                "type": "serial",
                                                "path": "http://www.amcharts.com/lib/3/",
                                                "categoryField": "date",
                                                "dataDateFormat": "YYYY-MM-DD",
                                                "startDuration": 1,
                                                "dataProvider": chartData2,
                                                "categoryAxis": {
                                                    "gridPosition": "start"
                                                },
                                                "trendLines": [],
                                                "graphs": [
                                                    {
                                                        "balloonColor": "#4B0082",
                                                        "balloonText": "[[title]] capturada el día [[category]]: \n\
                                                        [[value]] cm",
                                                        "bullet": "round",
                                                        "fillAlphas": 0.23,
                                                        "fillColors": "#4B0082",
                                                        "fixedColumnWidth": 0,
                                                        "fontSize": 1,
                                                        "id": "AmGraph-1",
                                                        "lineColor": "#050080",
                                                        "title": "Estatura ",
                                                        "valueField": "column1"
                                                    },
                                                ],
                                                "guides": [],
                                                "valueAxes": [
                                                    {
                                                        "id": "ValueAxis-1",
                                                        "title": "Estatura (cm)",
                                                        
                                                    }
                                                ],
                                                "allLabels": [],
                                                "balloon": {},
                                                "legend": {
                                                    "useGraphSettings": true
                                                },
                                                "titles": [
                                                    {
                                                        "id": "Title-1",
                                                        "size": 15,
                                                        "text": "Estatura del apadrinado"
                                                    }
                                                ],
                                                
                                            }
                                                    );
                                    </script>
                                        
                                        
                                        
                                    <div id="chartdiv4" style="width:100%; height:400px;"></div>
                                        
                                </div>
                            </li>	
                        </ul>
                    </div>
                    <!--/ tabs -->
                        
                </div>
                    
                    
                    
                    
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