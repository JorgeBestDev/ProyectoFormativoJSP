
<%-- 
    Document   : formularioUsuario
    Created on : 13/11/2023, 12:14:09 p. m.
    Author     : gutie
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Formulario Usuario</title>
    </head>
    <jsp:useBean id="unUsuario" class="modelo.Usuario" scope="request" />
    <body>
        <jsp:include page="jspf/menu.jspf"></jsp:include>
        <h1>Formulario Usuario</h1>
        <table border="1">
            <tr>
                <th>Nombre</th>
                <th>Tipo Documento</th>
                <th>Numero Documento</th>
                <th>Celular</th>
                <th>Correo</th>
                <th>Rol</th>
                <th>Usuario</th>
                <th>Contraseña</th>
            </tr>
        <c:forEach items="${unUsuario.listar(0)}" var="elUsuario">
            <tr>
                <form action="ControladorUsuario" method="post">
                    <td><input type="hidden" name="fIdUsu" value="${unUsuario.idUsu}">
                        <input type="text" name="fNombreUsu" value="${unUsuario.nombreUsu}"></td>
                    <td><input type="text" name="fTipoDocUsu" value="${unUsuario.tipoDocUsu}"></td>
                    <td><input type="number" name="fNoDocUsu" value="${unUsuario.noDocUsu}"></td>
                    <td><input type="number" name="fCelUsu" value="${unUsuario.celUsu}"></td>
                    <td><input type="text" name="fCorreoUsu" value="${unUsuario.correoUsu}"></td>
                    <td><input type="number" name="fIdRolF" value="${unUsuario.idRolF}"></td>
                    <td><input type="text" name="fUsuario" value="${unUsuario.usuario}"></td>
                    <td><input type="text" name="fContraseña" value="${unUsuario.contraseña}"></td>
                    <td><button type="submit" name="fAccion" value="Modificar">Modificar</button>
                        <button type="submit" name="fAccion" value="Eliminar">Eliminar</button></td>
                </form>
            </tr>
        </c:forEach> 
            <tr>
                <form action="ControladorUsuario" method="post">
                    <td><input type="number" name="fIdUsu" value="0">
                        <input type="text" name="fNombreUsu"></td>
                    <td><input type="text" name="fTipoDocUsu"></td>
                    <td><input type="number" name="fNoDocUsu"></td>
                    <td><input type="number" name="fCelUsu"></td>
                    <td><input type="text" name="fCorreoUsu"></td>
                    <td><input type="number" name="fIdRolF"></td>
                    <td><input type="text" name="fUsuario"></td>
                    <td><input type="text" name="fContraseñas"></td>
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
