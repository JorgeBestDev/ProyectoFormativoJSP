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

        BigInteger bigIntegerIdProducto = null;

        if (id != null && !id.isEmpty()) {
            bigIntegerIdProducto = new BigInteger(id);
        }

        BigInteger bigIntegerCantidad = null;

        if (id != null && !id.isEmpty()) {
            bigIntegerCantidad = new BigInteger(cantidad);
        }

        Producto unProducto = new Producto();
        unProducto.setIdProducto(bigIntegerIdProducto);
        unProducto.setNombreProducto(nombre);
        unProducto.setCantidadProducto(bigIntegerCantidad);
        unProducto.setDescripcionProducto(descripcion);

        String mensaje = "";
        switch (accion.toLowerCase()) {
            case "insertar" -> {
                unProducto.insertar();
                mensaje = "Inserto Producto";
            }
            case "modificar" -> {
                unProducto.modificar();
                mensaje = "Modifico Producto";
            }
            case "eliminar" -> {
                unProducto.eliminar();
                mensaje = "Elimino Producto";
                break;
            }
        }

        String vistaProducto = "/WEB-INF/formularioProducto.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vistaProducto);
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
