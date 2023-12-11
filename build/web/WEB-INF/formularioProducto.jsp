<%-- 
    Document   : formularioProducto
    Created on : 10/11/2023, 11:04:52 a. m.
    Author     : gutie
--%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="java.util.List" %>
<%@ page import="modelos.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap-grid.css" rel="stylesheet" type="text/css"/>
        <link href="css/bootstrap-reboot.css" rel="stylesheet" type="text/css"/>
        <link href="css/bootstrap-utilities.css" rel="stylesheet" type="text/css"/>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>

        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link rel="icon" type="image/vnd.icon" href="assets/favicon.ico">
        <title>Formulario Producto</title>
    </head>
    <jsp:useBean id="unProducto" class="modelos.Producto" scope="request" />
    <body>
        <header>
            <nav class="navbar bg-green">
                <a style="color: black" class="text-decoration-none navbar-toggler" href="#"><span class="navbar-toggler-icon"></span>
                    Home
                </a>
                <div class="dropdown">
                    <a class="px-6 nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown" href="#">Usuario</a>
                    <div class="dropdown-menu p-3 text-center">
                        <div class="desplegable-user ">
                            <a href="#"><img style="height: 5rem; width: 5rem" src="assets/user_img.png" alt="not found"/></a><br>
                            <a href="#">
                                <%-- Verifica si la sesión y el objeto Usuario existen --%>
                                <% if (session.getAttribute("usuario") != null) { %>
                                <%-- Obtiene el objeto Usuario de la sesión --%>
                                <% Usuario usuario = (Usuario) session.getAttribute("usuario"); %>
                                ${sessionScope.usuario.nombreUsu}
                                ${sessionScope.usuario.idRolF.nombreRol}
                                <% } else { %>
                                Invitado
                                <% } %>
                            </a>
                            <a href="#"></a>
                            <form action="loginController" method="post">
                                <button class="dropdown-item" name="fAccion" type="submit" value="salir" onclick="return confirm('¿Estás seguro de que deseas cerrar sesión?')">Salir</button>
                            </form>
                        </div>
                    </div>
                </div>
            </nav>
        </header>
        <main>
            <div class="divContenido">
                <div>
                    <div class="container mt-5">
                        <div id="myCarousel" class="carousel slide" data-bs-ride="carousel">
                            <!-- Indicadores de carrusel -->
                            <ol class="carousel-indicators">
                                <li data-bs-target="#myCarousel" data-bs-slide-to="0" class="active"></li>
                                <li data-bs-target="#myCarousel" data-bs-slide-to="1"></li>
                                <li data-bs-target="#myCarousel" data-bs-slide-to="2"></li>
                            </ol>

                            <!-- Slides de carrusel -->
                            <div class="carousel-inner">
                                <div class="carousel-item active">
                                    <img style="width: 100%" src="assets/banner_actualizacion_datos_sofia_.png" alt="NotFound">
                                </div>
                                <div class="carousel-item">
                                    <img style="width: 100%" src="assets/promo_SENA_digital.png" alt="NotFound">
                                </div>
                                <div class="carousel-item">
                                    <img style="width: 100%" src="assets/promo_ingles_EDW.png" alt="NotFound">
                                </div>
                            </div>

                            <!-- Controles de carrusel -->
                            <a class="carousel-control-prev" href="#myCarousel" role="button" data-bs-slide="prev">
                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Anterior</span>
                            </a>
                            <a class="carousel-control-next" href="#myCarousel" role="button" data-bs-slide="next">
                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Siguiente</span>
                            </a>
                        </div>
                    </div>
                </div>

                <div class="contenido mt-5"> 
                    <section class="section">
                        <form action="loginController" method="POST">
                            <button class="buttonLiContenido" type="submit" value="volver" name="fAccion">
                                <li class="liSection">
                                    <a class="aLiContenido">
                                        <svg xmlns="http://www.w3.org/2000/svg" height="24" viewBox="0 -960 960 960" width="24"><path d="M440-280h80v-160h160v-80H520v-160h-80v160H280v80h160v160Zm40 200q-83 0-156-31.5T197-197q-54-54-85.5-127T80-480q0-83 31.5-156T197-763q54-54 127-85.5T480-880q83 0 156 31.5T763-763q54 54 85.5 127T880-480q0 83-31.5 156T763-197q-54 54-127 85.5T480-80Zm0-80q134 0 227-93t93-227q0-134-93-227t-227-93q-134 0-227 93t-93 227q0 134 93 227t227 93Zm0-320Z"/></svg>
                                        Volver
                                    </a>
                                </li>
                            </button>
                        </form>
                    </section>
                    <article class="article">
                        <div class="divArticle">
                            <h1 style="margin-bottom: 3rem">Formulario Producto</h1>

                            <div class="divForm">
                                <form style="width: 75%" action="ControladorProducto" method="post">
                                    <input type="hidden" name="fIdProducto" value="${unProducto.idProducto}">

                                    <label for="nombreProducto" class="m-2 form-label">Producto:</label>
                                    <input type="text" id="nombreProducto" class="input-form m-2 form-control" name="fNombreProducto">

                                    <label for="cantidadProducto" class="m-2 form-label">Cantidad:</label>
                                    <input type="number" id="cantidadProducto" class="input-form m-2 form-control" name="fCantidadProducto">

                                    <label for="descripcionProducto" class="m-2 form-label">Descripcion/observaciones</label>
                                    <input type="text" id="descripcionProducto" class="input-form m-2 form-control" name="fDescripcionProducto">


                                    <button class="btn btn-dark m-4" type="submit" name="fAccion" value="insertar">Insertar</button>
                                    <button class="btn btn-dark m-4" type="reset" name="fAccion" value="Limpiar">Limpiar</button>
                                </form>
                            </div>
                            <h1 class="mt-5 ">Registros:</h1>
                            <form class="mt-5 mb-5" action="ControladorProducto" method="post">
                                <table class="table">
                                    <tr>
                                        <th>ID</th>
                                        <th>Nombre Producto</th>
                                        <th>Cantidad</th>
                                        <th>Descripcion</th>
                                    </tr>
                                    <c:choose>
                                        <c:when test="${empty listaProductos}">
                                            <tr>
                                                <td colspan="6">No hay registros disponibles</td>
                                            </tr>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${listaProductos}" var="unProducto">
                                                <tr>
                                                    <td><input class="input-form form-control" type="hidden" name="fIdProducto" value="${unProducto.idProducto}"><p>${unProducto.idProducto}</p></td>
                                                    <td>
                                                        <input class="input-form form-control" type="text" name="fNombreProducto" value="${unProducto.nombreProducto}">
                                                    </td>
                                                    <td>
                                                        <input class="input-form form-control" type="number" name="fCantidadProducto" value="${unProducto.cantidadProducto}">
                                                    </td>
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${unProducto.descripcionProducto eq null}">
                                                                <p>No hay Descripcion</p>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <input class="input-form form-control" type="text" name="fDescripcionProducto" value="${unProducto.descripcionProducto}">
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>

                                                    <td><button class="btn btn-dark" type="submit" value="modificar" name="fAccion" onclick="return confirm('¿Estás seguro de que deseas modificar el producto?')">Modificar</button></td>
                                                    <td><button class="btn btn-danger" type="submit" value="eliminar" name="fAccion" onclick="return confirm('¿Estás seguro de que deseas Eliminar el producto?')">Eliminar</button></td>
                                                </tr>
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </table>
                            </form>
                        </div>
                    </article>
                </div>

            </div>
        </main>

        <script src="js/bootstrap.bundle.js"></script>
    </body>
</html>
