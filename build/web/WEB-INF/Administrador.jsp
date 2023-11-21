<%-- 
    Document   : main
    Created on : 18/10/2023, 7:29:55 a. m.
    Author     : Sena
--%>
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
        <title>Inventario SENA</title>
    </head>
    
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
                            <form action="ControladorPersona" method="POST">
                                <button class="buttonLiContenido" type="submit" value="ingresoPc" name="ingresoPc">
                                    <li class="liSection">
                                        <a class="aLiContenido">
                                            <svg class="svgLiContenido" xmlns="http://www.w3.org/2000/svg" height="24" viewBox="0 -960 960 960" width="24"><path d="M480-480q-66 0-113-47t-47-113q0-66 47-113t113-47q66 0 113 47t47 113q0 66-47 113t-113 47ZM160-160v-112q0-34 17.5-62.5T224-378q62-31 126-46.5T480-440q66 0 130 15.5T736-378q29 15 46.5 43.5T800-272v112H160Zm80-80h480v-32q0-11-5.5-20T700-306q-54-27-109-40.5T480-360q-56 0-111 13.5T260-306q-9 5-14.5 14t-5.5 20v32Zm240-320q33 0 56.5-23.5T560-640q0-33-23.5-56.5T480-720q-33 0-56.5 23.5T400-640q0 33 23.5 56.5T480-560Zm0-80Zm0 400Z"/></svg>
                                            Persona
                                        </a>
                                    </li>
                                </button>
                            </form>
                            <form action="ControladorRegistroPc" method="POST">

                                <button class="buttonLiContenido" type="submit" value="ingresoPc" name="ingresoPc">
                                    <li class="liSection">
                                        <a class="aLiContenido">
                                            <svg xmlns="http://www.w3.org/2000/svg" height="24" viewBox="0 -960 960 960" width="24"><path d="m336-280 144-144 144 144 56-56-144-144 144-144-56-56-144 144-144-144-56 56 144 144-144 144 56 56ZM480-80q-83 0-156-31.5T197-197q-54-54-85.5-127T80-480q0-83 31.5-156T197-763q54-54 127-85.5T480-880q83 0 156 31.5T763-763q54 54 85.5 127T880-480q0 83-31.5 156T763-197q-54 54-127 85.5T480-80Zm0-80q134 0 227-93t93-227q0-134-93-227t-227-93q-134 0-227 93t-93 227q0 134 93 227t227 93Zm0-320Z"/></svg>
                                            Registro Pc
                                        </a>
                                    </li>
                                </button>
                            </form>
                            <form action="ControladorUsuario" method="POST">
                                <button class="buttonLiContenido" type="submit" value="ingresoPc" name="ingresoPc">
                                    <li class="liSection">
                                        <a class="aLiContenido">
                                            <svg class="svgLiContenido" xmlns="http://www.w3.org/2000/svg" height="24" viewBox="0 -960 960 960" width="24"><path d="M160-80v-80h640v80H160Zm320-160L200-600h160v-280h240v280h160L480-240Zm0-130 116-150h-76v-280h-80v280h-76l116 150Zm0-150Z"/></svg>
                                            Usuario
                                        </a>
                                    </li>
                                </button>
                            </form>
                        </ul>
                    </section>
                    <article class="article container">
                        <h1 class="m-5">Administrador</h1>
                        <p class="mt-3">
                            El Administrador se encarga de supervisar y controlar la salida y entrada de computadores
                        </p>
                    </article>
                </div>

            </div>
        </main>



        <script src="js/bootstrap.bundle.js"></script>
    </body>
</html>