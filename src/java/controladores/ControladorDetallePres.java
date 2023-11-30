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
import java.math.BigInteger;
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
        
        BigInteger bigIntegerIdDetallePres = new BigInteger(id);
        
        BigInteger bigIntegerProducto = null;

        if (idPro != null && !idPro.isEmpty()) {
            bigIntegerProducto = new BigInteger(idPro);
        }
        
        BigInteger bigIntegerPrestamo = null;

        if (idPres != null && !idPres.isEmpty()) {
            bigIntegerPrestamo = new BigInteger(idPres);
        }

        DetallePres unDetalle = new DetallePres();
        unDetalle.setIdDetallePres(bigIntegerIdDetallePres);

        Producto pro = new Producto();
        pro.setIdProducto(bigIntegerProducto);
        unDetalle.setIdProductoF(pro);

        Prestamo pre = new Prestamo();
        pre.setIdPrestamo(bigIntegerPrestamo);
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
