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
import modelos.DetallePres;

/**
 *
 * @author gutie
 */
@WebServlet(name = "ControladorDetallePres", urlPatterns = {"/ControladorDetallePres"})
public class ControladorDetallePres extends HttpServlet {

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
            out.println("<title>Servlet ControladorDetallePres</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorDetallePres at " + request.getContextPath() + "</h1>");
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
        String id     = request.getParameter("fIdDetallePres");
        String idPro  = request.getParameter("fIdProductoF");
        String idPres = request.getParameter("fIdPrestamoF");
        String accion = request.getParameter("fAccion");      
        
        int idDetallePres = 0;
        try{
            idDetallePres = Integer.parseInt(id);
        } catch (NumberFormatException nfe){
            
        }
        
        int idProductoF = 0;
        try{
            idProductoF = Integer.parseInt(idPro);
        } catch (NumberFormatException nfe){
            
        }
        
        int idPrestamoF = 0;
        try{
            idPrestamoF = Integer.parseInt(idPres);
        } catch (NumberFormatException nfe){
            
        }
        
        DetallePres unDetalle = new DetallePres();
        unDetalle.setIdDetallePres(idDetallePres);
        unDetalle.setIdProductoF(idProductoF);
        unDetalle.setIdPrestamoF(idPrestamoF);
        
        String mensaje = "";
        switch(accion.toLowerCase()){
            case "insertar":
                unDetalle.insertar();
                mensaje = "Inserto Detalle Prestamo";
            break;
            case "modificar":
                unDetalle.modificar();
                mensaje = "Modifico Detalle Prestamo";
            break;
            case "eliminar":
                unDetalle.eliminar();
                mensaje = "Elimino Detalle Prestamo";
            break;    
        }
        request.getRequestDispatcher("/WEB-INF/formularioDetallPres.jsp?msj="+mensaje).forward(request, response);
        
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
