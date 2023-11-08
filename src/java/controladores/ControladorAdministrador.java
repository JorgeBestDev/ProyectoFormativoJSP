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
import modelos.Administrador;

/**
 *
 * @author gutie
 */
@WebServlet(name = "ControladorAdministrador", urlPatterns = {"/ControladorAdministrador"})
public class ControladorAdministrador extends HttpServlet {

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
            out.println("<title>Servlet ControladorAdministrador</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorAdministrador at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String id     = request.getParameter("fIdAdministrador");
        String nombre = request.getParameter("fNombreAdministrador");
        String tipo   = request.getParameter("fTipoDocAdministrador");
        String no     = request.getParameter("fNoDocAdministrador");
        String cel    = request.getParameter("fCelAdministrador");
        String correo = request.getParameter("fCorreoAdministrador");
        String con    = request.getParameter("fConAdministrador");
        
        int idAdministrador = 0;
        try{
            idAdministrador = Integer.parseInt(id);
        } catch (NumberFormatException nfe){
            
        }
        
        int noDocAdministrador = 0;
        try{
            noDocAdministrador = Integer.parseInt(id);
        } catch (NumberFormatException nfe){
            
        }
        
        int celAdministrador = 0;
        try{
            celAdministrador = Integer.parseInt(id);
        } catch (NumberFormatException nfe){
            
        }
        
        Administrador unAdm = new Administrador();
        unAdm.setIdAdministrador(idAdministrador);
        unAdm.setNombreAdministrador(nombre);
        unAdm.setTipoDocAdministrador(tipo);
        unAdm.setNoDocAdministrador(noDocAdministrador);
        unAdm.setCelAdministrador(celAdministrador);
        unAdm.setCorreoAdministrador(correo);
        unAdm.setConAdministrador(con);
        
        String mensaje = "";
        switch(accion.toLowerCase()){
            case "insertar":
                unAdm.insertar();
                mensaje = "Inserto Administrador";
            break;
            case "modificar":
                unAdm.modificar();
                mensaje = "Modifico Administrador";
            break;
            case "eliminar":
                unAdm.eliminar();
                mensaje = "Elimino Administrador";
            break;    
        }
        request.getRequestDispatcher("/WEB-INF/formularioAdministrador.jsp?msj="+mensaje).forward(request, response);
        
    }

}
