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
        <link rel="stylesheet" href="css/style.css"> <!-- Resource style -->
        <link rel="stylesheet" href="css/reset.css"> <!-- Resource style -->
            
        <!--[if lt IE 9]>
                <link rel="stylesheet" href="css/sky-forms-ie8.css">
        <![endif]-->
            
            
            
        <script src="js/jquery.min.js"></script>
        <script src="js/jquery.form.min.js"></script>
        <script src="js/jquery.validate.min.js"></script>	
        <script src="js/jquery-ui.min.js"></script>
        <script src="js/modernizr.js"></script>
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
                            <a href="ControlCargarApadrinados"><i class="fa fa-dashboard"></i>Mi cuenta</a>
                                
                        </li>
                            
                        <% } %>
                            
                        <!--/ Apartado para cerrar sesión -->
                            
                        <c:choose>
                            <c:when test="${hayApadrinados}">
                                <li aria-haspopup="true">
                                    <a href="#"><i class="fa fa-group"></i><b>Apadrinados</b><i class="fa fa-indicator fa-chevron-down"></i></a>
                                    <div class="grid-container3">
                                        <ul>
                                            <c:forEach items="${apadrinados}" var="apadrinado">
                                                
                                                <c:choose>
                                                    <c:when test="${nombreApadrinado.equals(apadrinado.nombreCompleto)}">
                                                        <li><a href="cargarInfo?idApadrinado=${apadrinado.idApadrinado}"><i class="fa fa-angle-right"></i><b>${apadrinado.nombreCompleto}</b></a></li>
                                                                </c:when>
                                                                <c:otherwise>
                                                        <li><a href="cargarInfo?idApadrinado=${apadrinado.idApadrinado}"><i class="fa fa-child"></i>${apadrinado.nombreCompleto}</a></li>    
                                                            </c:otherwise>
                                                                
                                                                
                                                </c:choose>
                                                    
                                                    
                                                    
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
                            
                            
                        <!-- Apartado para contactar  -->
                        <li class="right">
                            <a href="contactar"><i class="fa fa-phone"></i>Contáctanos</a>
                        </li>
                        <!--/ Apartado para contactar -->
                    </ul>
                        
                    <h1 class="header123">Estás viendo los datos de ${nombreApadrinado}</h1>
                        
                        
                        
                    <div class="sky-tabs sky-tabs-pos-left sky-tabs-anim-flip sky-tabs-response-to-icons">
                        
                        <c:choose>
                            <c:when test="${cambio}">
                                <input type="radio" name="sky-tabs"  id="sky-tab1" class="sky-tab-content-1">
                                <label for="sky-tab1"><span><span><i class="fa fa-eye"></i>Datos de padrino(s)</span></span></label>
                                    
                                <input type="radio" name="sky-tabs" id="sky-tab2" class="sky-tab-content-2">
                                <label for="sky-tab2"><span><span><i class="fa fa-line-chart"></i>Estadísticas de Peso</span></span></label>
                                    
                                <input type="radio" name="sky-tabs" id="sky-tab3" class="sky-tab-content-3">
                                <label for="sky-tab3"><span><span><i class="fa fa-bar-chart-o"></i>Estadísticas de IMC</span></span></label>
                                    
                                <input type="radio" name="sky-tabs" id="sky-tab4" class="sky-tab-content-4">
                                <label for="sky-tab4"><span><span><i class="fa fa-university"></i>Estadísticas de Nivel Escolar </span></span></label>
                                    
                                <input type="radio" name="sky-tabs" id="sky-tab5" class="sky-tab-content-5">
                                <label for="sky-tab5"><span><span><i class="fa fa-area-chart"></i>Estadísticas de estatura </span></span></label>    
                                    
                                <input type="radio" name="sky-tabs" checked id="sky-tab6" class="sky-tab-content-6">
                                <label for="sky-tab6"><span><span><i class="fa fa-leanpub"></i>Cartas </span></span></label> 
                                        </c:when>
                                        <c:otherwise>
                                <input type="radio" name="sky-tabs" checked id="sky-tab1" class="sky-tab-content-1">
                                <label for="sky-tab1"><span><span><i class="fa fa-eye"></i>Datos de padrino(s)</span></span></label>
                                    
                                <input type="radio" name="sky-tabs" id="sky-tab2" class="sky-tab-content-2">
                                <label for="sky-tab2"><span><span><i class="fa fa-line-chart"></i>Estadísticas de Peso</span></span></label>
                                    
                                <input type="radio" name="sky-tabs" id="sky-tab3" class="sky-tab-content-3">
                                <label for="sky-tab3"><span><span><i class="fa fa-bar-chart-o"></i>Estadísticas de IMC</span></span></label>
                                    
                                <input type="radio" name="sky-tabs" id="sky-tab4" class="sky-tab-content-4">
                                <label for="sky-tab4"><span><span><i class="fa fa-university"></i>Estadísticas de Nivel Escolar </span></span></label>
                                    
                                <input type="radio" name="sky-tabs" id="sky-tab5" class="sky-tab-content-5">
                                <label for="sky-tab5"><span><span><i class="fa fa-area-chart"></i>Estadísticas de estatura </span></span></label>    
                                    
                                <input type="radio" name="sky-tabs" id="sky-tab6" class="sky-tab-content-6">
                                <label for="sky-tab6"><span><span><i class="fa fa-leanpub"></i>Cartas </span></span></label> 
                                        </c:otherwise>
                                            
                        </c:choose>
                            
                            
                        <ul>
                            <li class="sky-tab-content-1">					
                                <div class="typography">
                                    <h1>Datos del apadrinado</h1>
                                    <b>Nombre: </b> ${nombreApadrinado}
                                    <b>Comunidad :</b> ${comunidad}
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
                                
                            <li class="sky-tab-content-6">
                                <div class="typography">
                                    
                                    <div class="body"> 
                                        
                                        
                                        <c:forEach items="${cartas}" var="carta">
                                            <br>
                                            <div class="sky-tabs sky-tabs-pos-top-left sky-tabs-response-to-icons">
                                                <div class="textbox-2">					
                                                    <div class="typography">
                                                        <br>
                                                        <b>Fecha: </b><c:out value="${carta.fechaEntrada}"/>
                                                        <br> 
                                                        <c:out value="${carta.carta}" escapeXml="false"/>
                                                            
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                            
                                    </div>
                                </div>
                                <br>
                                <div class="sky-tabs sky-tabs-pos-top-left sky-tabs-response-to-icons">
                                    
                                    <section>
                                        
                                        <nav role="navigation"> 
                                            <ul class="cd-pagination">
                                                <c:forEach begin="1" end="${numPaginas}" var="i">
                                                    <c:choose>
                                                        <c:when test="${paginaActual eq i}">
                                                            <li><a class="current">${i}</a></li>
                                                            </c:when>
                                                            <c:otherwise>
                                                            <li><a href="cargarInfo?idApadrinado=${param.idApadrinado}&paginaActual=${i}">${i}</a></li>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                        
                                            </ul>
                                        </nav> 
                                            
                                            
                                    </section>
                                        
                                    <!--/ tabs -->
                                        
                                        
                                </div>
                                    
                                <!--/ tabs -->
                                    
                                    
                                    
                                    
                                    
                                    
                            </li>	
                        </ul>
                            
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