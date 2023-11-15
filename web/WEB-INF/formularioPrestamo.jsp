<%-- 
    Document   : formularioPrestamo
    Created on : 14/11/2023, 10:24:46 a. m.
    Author     : gutie
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Formulario Prestamo</title>
    </head>
    <jsp:useBean id="unPrestamo" class="modelo.Prestamo" scope="request" />
    <body>
        <jsp:include page="jspf/menu.jspf"></jsp:include>
        <h1>Formulario Prestamo</h1>
        <table border="1">
            <tr>
                <th>Fecha Prestamo</th>
                <th>Fecha Entrega Prestamo</th>
                <th>Observacion Prestamo</th>
                <th>Usuario</th>
                <th>Persona</th>
            </tr>
        <c:forEach items="${unPrestamo.listar(0)}" var="elPrestamo">
            <tr>
                <form action="ControladorPrestamo" method="post">
                    <td><input type="hidden" name="fIdPrestamo" value="${elPrestamo.idPrestamo}">
                        <input type="date" name="fFechaPrestamo" value="${elPrestamo.fechaPrestamo}"></td>
                    <td><input type="date" name="fFechaEntregaPrestamo" value="${elPrestamo.fechaEntregaPrestamo}"></td>
                    <td><input type="text" name="fObservacionPrestamo" value="${elPrestamo.observacionPrestamo}"></td>
                    <td><input type="text" name="fIdUsuF" value="${elPrestamo.idUsuF}"></td>
                    <td><input type="text" name="fIdPersonaF" value="${elPrestamo.idPersonaF}"></td>
                    <td><button type="submit" name="fAccion" value="Modificar">Modificar</button>
                        <button type="submit" name="fAccion" value="Eliminar">Eliminar</button></td>
                </form>
            </tr>
        </c:forEach> 
            <tr>
                <form action="ControladorPrestamo" method="post">
                    <td><input type="number" name="fIdPrestamo" value="0">
                        <input type="date" name="fFechaPrestamo"></td>
                    <td><input type="date" name="fFechaEntregaPrestamo"></td>
                    <td><input type="text" name="fObservacionPrestamo"></td>
                    <td><input type="number" name="fIdUsuF"></td>
                    <td><input type="number" name="fIdPersonaF"></td>
                    <td><button type="submit" name="fAccion" value="Insertar">Insertar</button>
                        <button type="reset" name="fAccion" value="Limpiar">Limpiar</button></td>
                </form>
            </tr>
        </table>
        
    </body>
</html>
