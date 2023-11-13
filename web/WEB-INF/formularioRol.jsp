<%-- 
    Document   : formularioRol
    Created on : 9/11/2023, 11:16:11 a. m.
    Author     : gutie
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Formulario Rol</title>
    </head>
    <jsp:useBean id="unRol" class="modelo.Rol" scope="request" />
    <body>
        <jsp:include page="jspf/menu.jspf"></jsp:include>
        <h1>Formulario Rol</h1>
        <table border="1">
            <tr>
                <th>Nombre Rol</th>
            </tr>
        <c:forEach items="${unRol.listar(0)}" var="elRol">
            <tr>
                <form action="ContorladorRol" method="post">
                     <td><input type="hidden" name="fIdRol" value="${elRol.idRol}">
                            <input type="text" name="fNombreRol" value="${elRol.nombreRol}"></td>
                     <td><button type="submit" name="fAccion" value="Modificar">Modificar</button>
                         <button type="submit" name="fAccion" value="Eliminar">Eliminar</button></td>
                </form>
            </tr>
        </c:forEach> 
            <tr>
            <form action="ControladorRol" method="post">
                <td><input type="number" name="fIdRol" value="0">
                        <input type="text" name="fNombreRol"></td>
                <td><button type="submit" name="fAccion" value="Insertar">Insertar</button>
                    <button type="reset" name="fAccion" value="Limpiar">Limpiar</button></td>
            </form>
            </tr>
        </table>
        
        <c:forEach begin="1" end="5" var="i">
            <c:out value="${i}"/>
        </c:forEach>
        
    </body>
</html>
