<%-- 
    Document   : formularioDetallePres
    Created on : 13/11/2023, 1:13:35 p. m.
    Author     : gutie
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Formulario Detalle Prestamo</title>
    </head>
    <jsp:useBean id="unDetalle" class="modelo.DetallePres" scope="request" />
    <body>
        <jsp:include page="jspf/menu.jspf"></jsp:include>
        <h1>Formulario Detalle Prestamo</h1>
        <table border="1">
            <tr>
                <th>Producto</th>
                <th>Prestamo</th>
            </tr>
        <c:forEach items="${unDetalle.listar(0)}" var="elDetalle">
            <tr>
                <form action="ControladorDetallePres" method="post">
                    <td><input type="hidden" name="fIdDetallePres" value="${unDetalle.idDetallePres}">
                        <input type="number" name="fIdProductoF" value="${unDetalle.idProductoF}"></td>
                    <td><input type="number" name="fIdPrestamoF" value="${unDetalle.idPrestamoF}"></td>
                    <td><button type="submit" name="fAccion" value="Modificar">Modificar</button>
                        <button type="submit" name="fAccion" value="Eliminar">Eliminar</button></td>
                </form>
            </tr>
        </c:forEach> 
            <tr>
                <form action="ControladorDetallePres" method="post">
                    <td><input type="number" name="fIdPersona" value="0">
                        <input type="number" name="fIdProductoF"></td>
                    <td><input type="number" name="fIdPrestmoF"></td>
                    <td><button type="submit" name="fAccion" value="Insertar">Insertar</button>
                        <button type="reset" name="fAccion" value="Limpiar">Limpiar</button></td>
                </form>
            </tr>
        </table>

    </body>
</html>
