/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.util.ArrayList;
import modelos.Rol;
import modelos.Usuario;

/**
 *
 * @author gutie
 */
@WebServlet(name = "ControladorUsuario", urlPatterns = {"/ControladorUsuario"})
public class ControladorUsuario extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id     = request.getParameter("fIdUsu");
        String nombre = request.getParameter("fNombreUsu");
        String tipo   = request.getParameter("fTipoDocUsu");
        String no     = request.getParameter("fNoDocUsu");
        String celular= request.getParameter("fCelUsu");
        String correo = request.getParameter("fCorreoUsu");
        String idRol  = request.getParameter("fIdRolF");
        String usu    = request.getParameter("fUsuario");
        String con    = request.getParameter("fContraseña");
        String accion = request.getParameter("fAccion");      
        
        Usuario usuariosListados = new Usuario();
        ArrayList<Usuario> listaUsuarios = usuariosListados.listar(0);
        request.setAttribute("listaUsuarios", listaUsuarios);
        
        Rol rolesListados = new Rol();
        ArrayList<Rol> listaRoles = rolesListados.listar(0);
        request.setAttribute("listaRoles", listaRoles);
        
        BigInteger bigIntegerIdUsu = null;

        if (id != null && !id.isEmpty()) {
            bigIntegerIdUsu = new BigInteger(id);
        }
        BigInteger bigIntegerNoDocUsu = null;

        if (no != null && !no.isEmpty()) {
            bigIntegerNoDocUsu = new BigInteger(no);
        }
        BigInteger bigIntegerCelular = null;

        if (celular != null && !celular.isEmpty()) {
            bigIntegerCelular = new BigInteger(celular);
        }
        
        BigInteger bigIntegerIdRol = null;

        if (idRol != null && !idRol.isEmpty()) {
            bigIntegerIdRol = new BigInteger(idRol);
        }
        
        Usuario unUsuario = new Usuario();
        unUsuario.setIdUsu(bigIntegerIdUsu);
        unUsuario.setNombreUsu(nombre);
        unUsuario.setTipoDocUsu(tipo);
        unUsuario.setNoDocUsu(bigIntegerNoDocUsu);
        unUsuario.setCelUsu(bigIntegerCelular);
        unUsuario.setCorreoUsu(correo);
        
        Rol rol = new Rol();
        rol.setIdRol(bigIntegerIdRol);
        unUsuario.setIdRolF(rol);
        unUsuario.setUsuario(usu);
        unUsuario.setContraseña(con);
        
        
        request.setAttribute("listaUsuarios", listaUsuarios);
        request.setAttribute("unUsuario", unUsuario);
        request.setAttribute("unRol", rol);
        
        switch(accion.toLowerCase()){
            case "insertar" -> {
                unUsuario.insertar();
            }
            case "modificar" -> {
                unUsuario.modificar();
            }
            case "eliminar" -> {
                unUsuario.eliminar();
            }    
        }
        String vistaDetallePres = "/WEB-INF/formularioUsuario.jsp"; // Ruta a tu archivo JSP
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vistaDetallePres);
        dispatcher.forward(request, response);
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
