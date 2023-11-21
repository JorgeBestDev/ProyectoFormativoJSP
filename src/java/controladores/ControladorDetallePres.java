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
import modelos.DetallePres;
import modelos.Prestamo;    
import modelos.Producto;

/**
 *
 * @author gutie
 */
@WebServlet(name = "ControladorDetallePres", urlPatterns =
{
    "/ControladorDetallePres"
})
public class ControladorDetallePres extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
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
        String id = request.getParameter("fIdDetallePres");
        String idPro = request.getParameter("fIdProductoF");
        String idPres = request.getParameter("fIdPrestamoF");
        String accion = request.getParameter("fAccion");

        String vistaDetallePres = "/WEB-INF/formularioDetallePres.jsp"; // Ruta a tu archivo JSP
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vistaDetallePres);
        dispatcher.forward(request, response);
        
        int idDetallePres = 0;
        try
        {
            idDetallePres = Integer.parseInt(id);
        } catch (NumberFormatException nfe)
        {

        }

        int idProductoF = 0;
        try
        {
            idProductoF = Integer.parseInt(idPro);
        } catch (NumberFormatException nfe)
        {

        }

        int idPrestamoF = 0;
        try
        {
            idPrestamoF = Integer.parseInt(idPres);
        } catch (NumberFormatException nfe)
        {

        }

        DetallePres unDetalle = new DetallePres();
        unDetalle.setIdDetallePres(idDetallePres);

        Producto pro = new Producto();
        pro.setIdProducto(idProductoF);
        unDetalle.setIdProductoF(pro);

        Prestamo pre = new Prestamo();
        pre.setIdPrestamo(idPrestamoF);
        unDetalle.setIdPrestamoF(pre);

        String mensaje = "";
        switch (accion.toLowerCase())
        {
            case "insertar" ->
            {
                unDetalle.insertar();
                mensaje = "Inserto Detalle Prestamo";
            }
            case "modificar" ->
            {
                unDetalle.modificar();
                mensaje = "Modifico Detalle Prestamo";
            }
            case "eliminar" ->
            {
                unDetalle.eliminar();
                mensaje = "Elimino Detalle Prestamo";

        }
        }
        request.getRequestDispatcher("/WEB-INF/formularioDetallPres.jsp?msj=" + mensaje).forward(request, response);

        
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
