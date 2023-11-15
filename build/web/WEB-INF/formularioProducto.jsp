<%-- 
    Document   : formularioProducto
    Created on : 10/11/2023, 11:04:52 a. m.
    Author     : gutie
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Formulario Producto</title>
    </head>
    <jsp:useBean id="unProducto" class="modelo.Producto" scope="request" />
    <body>
        <jsp:include page="jspf/menu.jspf"></jsp:include>
        <h1>Formulario Producto</h1>
        <table border="1">
            <tr>
                <th>Nombre</th>
                <th>Cantidad</th>
                <th>Descripcion</th>
            </tr>
        <c:forEach items="${unProducto.listar(0)}" var="elProducto">
            <tr>
            <form action="ControladorProdcuto" method="posst">
                <td><input type="hidden" name="fIdProducto" value="${elProducto.idProducto}">
                        <input type="text" name="fNombreProducto" value="${elProducto.nombreProducto}"></td>
                    <td><input type="number" name="fCantidadProducto value="${elProducto.cantidadProducto}"></td>
                    <td><input type="text" name="fDescripcionProducto" value="${elProducto.descripcionProducto}"></td>
                    <td><button type="submit" name="fAccion" value="Modificar">Modificar</button>
                        <button type="submit" name="fAccion" value="Eliminar">Eliminar</button></td>
            </form>
            </tr>
        </c:forEach>
            <tr>
            <form action="ControladorProducto" method="post">
                <td><input type="number" name="fIdProducto" value="0">
                        <input type="text" name="fNombreProducto"></td>
                    <td><input type="number" name="fCantidadProducto"></td>
                    <td><input type="text" name="fDescripcion Producto"></td>
                    <td><button type="submit" name="fAccion" value="Insertar">Insertar</button>
                        <button type="reset" name="fAccion" value="Limpiar">Limpiar</button></td>
            </form>
            </tr>
        </table>
        
    </body>
</html>
