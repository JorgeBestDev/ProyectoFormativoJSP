<%-- 
    Document   : formularioRegistroPc
    Created on : 13/11/2023, 10:35:41 a. m.
    Author     : gutie
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Formulario RegistroPc</title>
    </head>
    <jsp:useBean id="unRegistro" class="modelos.RegistroPc" scope="request" />
    <body>
        <jsp:include page="jspf/menu.jspf"></jsp:include>
        <h1>Formulario RegistroPc</h1>
        <table border="1">
            <tr>
                <th>Marca Pc</th>
                <th>Color Pc</th>
                <th>Serial Pc</th>
                <th>Usuario</th>
                <th>Entrada Pc</th>
                <th>Salida Pc</th>
            </tr>
        <c:forEach items="${unRegistro.listar(0)}" var="elRegistro">
            <tr>
                <form action="ControladorRegistroPc" method="post">
                    <td><input type="hidden" name="fIdRegistro" value="${elRegistro.idRegistro}">
                        <input type="text" name="fMarcaPc" value="${elRegistro.marcaPc}"></td>
                    <td><input type="text" name="fColorPc" value="${elRegistro.colorPc}"></td>
                    <td><input type="text" name="fSerialPc" value="${elRegistro.serialPc}"></td>
                    <td><input type="number" name="fIdUsuF" value="${elRegistro.idUsuF}"></td>
                    <td><input type="text" name="fEntradaPc" value="${elRegistro.entradaPc}"></td>
                    <td><input type="text" name="fSalidaPc" value="${elRegistro.salidaPc}"></td>
                    <td><button type="submit" name="fAccion" value="Modificar">Modificar</button>
                        <button type="submit" name="fAccion" value="Eliminar">Eliminar</button></td>
                </form>
            </tr>
        </c:forEach> 
            <tr>
                <form action="ControladorRegistroPc" method="post">
                    <td><input type="number" name="fIdRegistro" value="0">
                        <input type="text" name="fMarcaPc"></td>
                    <td><input type="text" name="fColorPc"></td>
                    <td><input type="text" name="fSerialPc"></td>
                    <td><input type="number" name="fIdUsuF"></td>
                    <td><input type="text" name="fEntradaPc"></td>
                    <td><input type="text" name="fSalidaOc"></td>
                    <td><button type="submit" name="fAccion" value="Insertar">Insertar</button>
                        <button type="reset" name="fAccion" value="Limpiar">Limpiar</button></td>
                </form>
            </tr>
        </table>
      
    </body>
</html>
