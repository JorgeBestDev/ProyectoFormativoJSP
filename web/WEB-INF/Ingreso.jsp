<%-- 
    Document   : Ingreso
    Created on : 21/11/2023, 10:49:35 a. m.
    Author     : gutie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ingreso</title>
    </head>
    <body>  
        <form action="controingres">
            <h1>Ingrese su documento</h1>
            <input type="number"  name="documento">
            <button name="fAccion" value="buscar">Entrar</button>
        </form>
    </body>
</html>
