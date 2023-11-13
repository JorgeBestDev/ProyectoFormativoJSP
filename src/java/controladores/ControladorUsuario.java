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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControladorUsuario</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorUsuario at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        String est    = request.getParameter("festado");
        String usu    = request.getParameter("fUsuario");
        String con    = request.getParameter("fContraseña");
        String accion = request.getParameter("fAccion");      
        
        int idUsu = 0;
        try{
            idUsu = Integer.parseInt(id);
        } catch (NumberFormatException nfe){
            
        }
        
        int noDocUsu = 0;
        try{
            noDocUsu = Integer.parseInt(no);
        } catch (NumberFormatException nfe){
            
        }
        
        int celUsu = 0;
        try{
            celUsu = Integer.parseInt(celular);
        } catch (NumberFormatException nfe){
            
        }
        
        int idRolF = 0;
        try{
            idRolF = Integer.parseInt(idRol);
        } catch (NumberFormatException nfe){
            
        }
        
        int estado = 0;
        try{
            estado = Integer.parseInt(est);
        } catch (NumberFormatException nfe){
            
        }
        
        Usuario unUsuario = new Usuario();
        unUsuario.setIdUsu(idUsu);
        unUsuario.setNombreUsu(nombre);
        unUsuario.setTipoDocUsu(tipo);
        unUsuario.setNoDocUsu(noDocUsu);
        unUsuario.setCelUsu(celUsu);
        unUsuario.setCorreoUsu(correo);
        unUsuario.setIdRolF(idRolF);
        unUsuario.setUsuario(usu);
        unUsuario.setContraseña(con);
        
        String mensaje = "";
        switch(accion.toLowerCase()){
            case "insertar":
                unUsuario.insertar();
                mensaje = "Inserto Usuario";
            break;
            case "modificar":
                unUsuario.modificar();
                mensaje = "Modifico Usuario";
            break;
            case "eliminar":
                unUsuario.eliminar();
                mensaje = "Elimino Usuario";
            break;    
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