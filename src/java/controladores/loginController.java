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
        System.out.println("estoy en el process");
        usu.setUsuario(usuario);
        usu.setContraseña(contraseña);
        switch (accion.toLowerCase())
        {
            case "verificar" ->
            {
                System.out.println("entra a verificar");
                System.out.println(usu.getUsuario());
                System.out.println(contraseña);
                usu.validar();
                System.out.println("termino validar");
                if (usu.validar() == true)
                {
                    String vistaAdministrador = "/WEB-INF/Administrador.jsp"; // Ruta a tu archivo JSP
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vistaAdministrador);
                    dispatcher.forward(request, response);

                } else if (usu.validar() == false)
                {
                    String vistaEncargado ="/WEB-INF/EncargadoAlmacen.jsp"; // Ruta a tu archivo JSP
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vistaEncargado);
                    dispatcher.forward(request, response);
                } else
                {
                    System.out.println("no verifico nada");
                    String index = "/index.jsp"; // Ruta a tu archivo JSP
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(index);
                    dispatcher.forward(request, response);
                    System.out.println("le valio el dispacher");
                }
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
