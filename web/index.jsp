<%-- 
    Document   : index
    Created on : 5/10/2023, 10:45:45 a. m.
    Author     : Sena
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ingreso Inventario SENA CGAO</title>
        <link rel="stylesheet" href="css/style.css"/>
        <link rel="stylesheet" href="css/bootstrap.css"/>
        <link rel="icon" type="image/vnd.icon" href="assets/favicon.ico">
    </head>
    <body class="bg-info d-flex justify-content-center align-items-center vh-100">
        <div class="bg-white p-5 rounded-5 text-secondary shadow" style="width: 25rem">
            <div class="d-flex justify-content-center">
                <img src="assets/acount_user_icon.png" alt="not found" style="height: 7rem"/>
            </div>
            <div class="text-center fs-1 fw-bold">
                Login
            </div>
            <form action="loginController" method="POST">
                <div class="input-group mt-4">
                    <div class="input-group-text bg-info">
                        <img src="assets/person_icon.png" alt="alt" style="height: 1rem"/>
                    </div>
                    <input class="form-control bg-light" name="txtUsuario" id="txtUsuario" type="text" placeholder="Username"/>
                </div>
                <div class="input-group mt-1">
                    <div class="input-group-text bg-info">
                        <img src="assets/lock_icon.png" alt="alt" style="height: 1rem"/>
                    </div>
                    <input class="form-control bg-light" name="txtPassword" id="txtPassword" type="password" placeholder="Password"/>
                </div>
                <div class="d-flex justify-content-around mt-1">
                    <div class="d-flex align-items-center gap-1">
                        <input class="form-check-input" type="checkbox">
                        <div class="pt-1" style="font-size: 0.9rem">Remember me</div>
                    </div>
                    <div class="pt-1">
                        <a class="text-decoration-none text-info fw-semibold fst-italic" style="font-size: 0.9rem" href="#">Forgot Password?</a>
                    </div>
                </div>
                <div>
                    <button class="btn btn-info text-white w-100 mt-4 fw-semibold shadow-sm" type="submit" value="verificar" name="verificar">
                        Login
                    </button>
                </div>
                <div class="d-flex gap-1 justify-content-center mt-1">
                    <div>Dont have a acount?</div>
                    <a href="#" class="text-decoration-none text-info fw-semibold">Register</a>
                </div>
                <div class="p-3">
                    <div class="border-bottom text-center" style="height: 0.9rem">
                        <span class="bg-white px-3">
                            or
                        </span>
                    </div>
                </div>
                <a class="text-decoration-none" href="google.html">
                    <div class="d-flex btn gap-2 justify-content-center border  mt-3 shadow-sm">
                        <img src="assets/google_icon.png" alt="alt" style="height: 1.6rem"/>
                        <div class="fw-semibold text-secondary">Continue with google</div>
                    </div>
                </a>
            </form>
            
        </div>
        <script src="js/bootstrap.bundle.js"></script>
    </body>
</html>
