<%-- 
    Document   : jspReportMovimientos
    Created on : 20 ago. 2020, 1:33:52 p. m.
    Author     : hpulgar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            System.out.println("....Aquica process movimientos de Almacen...");
            //System.out.println("Nombre del Señor: "+request.getParameter("nombre") +"****");
            //System.out.println("Apellido del Señor: "+request.getParameter("apellido") +"****");
            System.out.println("Tipo de movimiento: "+request.getParameter("tipo_mov") +"****");
            System.out.println("Fecha inicio: "+request.getParameter("fecha1") +"****");
            System.out.println("Feha final: "+request.getParameter("fecha2") +"****");
        %>    
    </body>
</html>
