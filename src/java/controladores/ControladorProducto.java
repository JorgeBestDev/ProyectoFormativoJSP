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
import modelos.Producto;

/**
 *
 * @author gutie
 */
@WebServlet(name = "ControladorProducto", urlPatterns = {"/ControladorProducto"})
public class ControladorProducto extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        
        
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
        String id = request.getParameter("fIdProducto");
        String nombre = request.getParameter("fNombreProducto");
        String cantidad = request.getParameter("fCantidadProducto");
        String descripcion = request.getParameter("fDescripcionProducto");
        String accion = request.getParameter("fAccion");

        int idProducto = 0;
        try {
            idProducto = Integer.parseInt(id);
        } catch (NumberFormatException nfe) {

        }

        int cantidadProducto = 0;
        try {
            cantidadProducto = Integer.parseInt(cantidad);
        } catch (NumberFormatException nfe) {

        }

        Producto unProducto = new Producto();
        unProducto.setIdProducto(idProducto);
        unProducto.setNombreProducto(nombre);
        unProducto.setCantidadProducto(cantidadProducto);
        unProducto.setDescripcionProducto(descripcion);

        String mensaje = "";
        switch (accion.toLowerCase()) {
            case "insertar":
                unProducto.insertar();
                mensaje = "Inserto Producto";
                break;
            case "modificar":
                unProducto.modificar();
                mensaje = "Modifico Producto";
                break;
            case "eliminar":
                unProducto.eliminar();
                mensaje = "Elimino Producto";
                break;
        }
        request.getRequestDispatcher("/WEB-INF/formularioProducto.jsp?msj=" + mensaje).forward(request, response);

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
