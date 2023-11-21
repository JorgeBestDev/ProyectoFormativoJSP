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
@WebServlet(name = "loginController", urlPatterns =
{
    "/loginController"
})
public class loginController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usuario = request.getParameter("txtUsuario");
        String contraseña = request.getParameter("txtPassword");
        String accion = request.getParameter("verificar");
        Usuario usu = new Usuario();

        HttpSession session = request.getSession(); // Obtener la sesión actual o crear una nueva si no existe

        usu.setUsuario(usuario);
        usu.setContraseña(contraseña);

        switch (accion) {
            case "verificar" -> {
                Boolean esValido = usu.validar();

                if (esValido != null) {
                    if (esValido) {
                        // Establecer datos en la sesión para uso posterior
                        session.setAttribute("usuario", usu);
                        
                        String vistaAdministrador = "/WEB-INF/Administrador.jsp";
                        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vistaAdministrador);
                        dispatcher.forward(request, response);
                    } else {
                        String vistaEncargado = "/WEB-INF/EncargadoAlmacen.jsp";
                        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vistaEncargado);
                        dispatcher.forward(request, response);
                    }
                } else {
                    System.out.println("No verificó nada");
                    String index = "/index.jsp";
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(index);
                    dispatcher.forward(request, response);
                    System.out.println("Le valió el dispatcher");
                }
            }
            case "volver"->
            {
                
            }
            default ->
                throw new AssertionError();
        }
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
