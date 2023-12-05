/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelos.Usuario;

/**
 *
 * @author Jorge
 */
@WebServlet(name = "loginController", urlPatterns
        =
        {
            "/loginController"
        })
public class loginController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String usuario = request.getParameter("txtUsuario");
        String contraseña = request.getParameter("txtPassword");
        String accion = request.getParameter("fAccion");
        Usuario usu = new Usuario();

        usu.setUsuario(usuario);
        usu.setContraseña(contraseña);

        switch (accion) {
            case "verificar" -> {
                Boolean esValido = usu.validar();

                if (esValido != null) {
                    usu.obtenerUsuarioPorCredenciales(usuario, contraseña);
                    if (esValido) {
                        session.setAttribute("usuario", usu);
                        String vistaAdministrador = "/WEB-INF/Administrador.jsp";
                        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vistaAdministrador);
                        dispatcher.forward(request, response);
                    } else {
                        session.setAttribute("usuario", usu);
                        String vistaEncargado = "/WEB-INF/EncargadoAlmacen.jsp";
                        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vistaEncargado);
                        dispatcher.forward(request, response);
                    }
                } else {
                    String index = "/index.jsp";
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(index);
                    dispatcher.forward(request, response);
                }
            }
            case "salir" -> {

                // Invalida la sesión
                session.invalidate();
                // Redirige a la página de inicio de sesión
                response.sendRedirect("index.jsp");

            }
            case "volver" -> {

                session.getAttribute(accion);

            }
            default ->
                throw new AssertionError();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
