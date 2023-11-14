<%-- 
    Document   : formularioPersona
    Created on : 8/11/2023, 7:27:18 a. m.
    Author     : gutie
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Formulario Persona</title>
    </head>
    <jsp:useBean id="unaPersona" class="modelo.Persona" scope="request" />
    <body>
        <jsp:include page="jspf/menu.jspf"></jsp:include>
        <h1>Formulario Persona</h1>
        <table border="1">
            <tr>
                <th>Nombre</th>
                <th>Numero Ficha</th>
                <th>Correo</th>
                <th>Celular</th>
                <th>Tipo Identificacion</th>
                <th>Numero Identificacion</th>
            </tr>
        <c:forEach items="${unaPersona.listar(0)}" var="laPersona">
            <tr>
                <form action="ControladorPersona" method="post">
                    <td><input type="hidden" name="fIdPersona" value="${laPersona.idPersona}">
                        <input type="text" name="fNombrePersona" value="${laPersona.nombrePersona}"></td>
                    <td><input type="number" name="fNoFichaPersona" value="${laPersona.noFichaPersona}"></td>
                    <td><input type="text" name="fCorreoPersona" value="${laPersona.correoPersona}"></td>
                    <td><input type="number" name="fCelularPersona" value="${laPersona.celularPersona}"></td>
                    <td><input type="text" name="fTipoIdentificacionPersona" value="${laPersona.tipoIdentificacionPersona}"></td>
                    <td><input type="number" name="fNoIdentificacionPersona" value="${laPersona.noIdentificacionPersona}"></td>
                    <td><button type="submit" name="fAccion" value="Modificar">Modificar</button>
                        <button type="submit" name="fAccion" value="Eliminar">Eliminar</button></td>
                </form>
            </tr>
        </c:forEach> 
            <tr>
                <form action="ControladorPersona" method="post">
                    <td><input type="number" name="fIdPersona" value="0">
                        <input type="text" name="fNombrePersona"></td>
                    <td><input type="number" name="fNoFichaPersona"></td>
                    <td><input type="text" name="fCorreoPersona"></td>
                    <td><input type="number" name="fCelularPersona"></td>
                    <td><input type="text" name="fTipoIdentificacionPersona"></td>
                    <td><input type="number" name="fNoIdentificacionPersona"></td>
                    <td><button type="submit" name="fAccion" value="Insertar">Insertar</button>
                        <button type="reset" name="fAccion" value="Limpiar">Limpiar</button></td>
                </form>
            </tr>
        </table>
        
    </body>
</html>
