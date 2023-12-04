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
        <link href="css/bootstrap-grid.css" rel="stylesheet" type="text/css"/>
        <link href="css/bootstrap-reboot.css" rel="stylesheet" type="text/css"/>
        <link href="css/bootstrap-utilities.css" rel="stylesheet" type="text/css"/>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>

        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link rel="icon" type="image/vnd.icon" href="assets/favicon.ico">
        <title>Formulario Registro Pc</title>
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
                <th>Persona</th>
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
                                <%
                                    
                                %>
                            </a>
                            <a href="#"></a>
                            <a class="dropdown-item" href="srvUsuario?accion=cerrar">Salir</a>
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

                <div class="contenido text-center mt-5"> 
                    <section class="section">
                        <ul class="ulSection">
                            <form action="loginController" method="POST">
                                <button class="buttonLiContenido" type="submit" value="fAccion" name="fAccion">
                                    <li class="liSection">
                                        <a class="aLiContenido">
                                            <svg xmlns="http://www.w3.org/2000/svg" height="24" viewBox="0 -960 960 960" width="24"><path d="M440-280h80v-160h160v-80H520v-160h-80v160H280v80h160v160Zm40 200q-83 0-156-31.5T197-197q-54-54-85.5-127T80-480q0-83 31.5-156T197-763q54-54 127-85.5T480-880q83 0 156 31.5T763-763q54 54 85.5 127T880-480q0 83-31.5 156T763-197q-54 54-127 85.5T480-80Zm0-80q134 0 227-93t93-227q0-134-93-227t-227-93q-134 0-227 93t-93 227q0 134 93 227t227 93Zm0-320Z"/></svg>
                                            Volver
                                        </a>
                                    </li>
                                </button>
                            </form>
                            
                        </ul>
                    </section>
                    <article class="article container">
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
                    </article>
                </div>

            </div>
        </main>

        <script src="js/bootstrap.bundle.js"></script>
>>>>>>> e48b507a6db4cc66d31bca93eed4095c2ceeedca
    </body>
</html>
