<%-- 
    Document   : main
    Created on : 18/10/2023, 7:29:55 a. m.
    Author     : Sena
--%>
<%
    if(session.getAttribute("usuario") !=null )) {
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css"/>
        <link rel="stylesheet" href="css/bootstrap.css"/>

        <title>Inventario SENA</title>
    </head>
    <body>
        <nav class="navbar bg-green">
            <a style="color: black" class="text-decoration-none navbar-toggler" href="#"><span class="navbar-toggler-icon"></span>
                Home
            </a>
            <div class="dropdown">
                <a class="px-6 nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown" href="#">Usuario</a>
                <div class="dropdown-menu p-3 text-center">
                    <div class="desplegable-user ">
                        <a href="#"><img style="height: 5rem; width: 5rem" src="assets/user_img.png" alt="not found"/></a><br>
                        <a href="#">${usuario.nombreUsu}</a>
                        <a href="#">${usuario.correoUsu}</a>
                        <a class="dropdown-item" href="srvUsuario?accion=cerrar">Salir</a>
                    </div>

                </div>
            </div>
    </nav>


    <script src="js/bootstrap.bundle.js"></script>
</body>
</html>
<%
}else 
{
    response.sendRedirect("/WEB-INF/EncargadoAlmacen.jsp");

}
%>