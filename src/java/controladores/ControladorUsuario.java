/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.math.BigInteger;
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
        
        BigInteger bigIntegerIdUsu = new BigInteger(id);
        BigInteger bigIntegerNoDocUsu = new BigInteger(no);
        BigInteger bigIntegerCelular = new BigInteger(celular);
        
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
        
        String mensaje = "";
        switch(accion.toLowerCase()){
            case "insertar" -> {
                unUsuario.insertar();
                mensaje = "Inserto Usuario";
            }
            case "modificar" -> {
                unUsuario.modificar();
                mensaje = "Modifico Usuario";
            }
            case "eliminar" -> {
                unUsuario.eliminar();
                mensaje = "Elimino Usuario";
            }    
        }
        request.getRequestDispatcher("/WEB-INF/formularioUsuario.jsp?msj="+mensaje).forward(request, response);
        
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
