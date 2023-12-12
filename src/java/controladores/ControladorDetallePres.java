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
import jakarta.servlet.http.HttpSession;
import java.math.BigInteger;
import java.util.ArrayList;
import modelos.DetallePres;
import modelos.Persona;
import modelos.Prestamo;
import modelos.Producto;
import modelos.Rol;
import modelos.Usuario;

/**
 *
 * @author gutie
 */
@WebServlet(name = "ControladorDetallePres", urlPatterns
        = {
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
        HttpSession session = request.getSession();
        Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuario");

        String id = request.getParameter("fIdDetallePres");
        String idPro = request.getParameter("fIdProductoF");
        String idPres = request.getParameter("fIdPrestamoF");
        String accion = request.getParameter("fAccion");

        Producto productosListados = new Producto();
        ArrayList<Producto> listaProductos = productosListados.listar(0);
        request.setAttribute("listaProductos", listaProductos);

        Prestamo prestamosListados = new Prestamo();
        ArrayList<Prestamo> listaPrestamos = prestamosListados.listar(0);
        request.setAttribute("listaPrestamos", listaPrestamos);

        DetallePres detallesListados = new DetallePres();
        ArrayList<DetallePres> listaDetalles = detallesListados.listar(0);
        request.setAttribute("listaDetalles", listaDetalles);

        BigInteger bigIntegerIdDetallePres = null;

        if (id != null && !id.isEmpty()) {
            bigIntegerIdDetallePres = new BigInteger(id);
        }

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

        Producto unProducto = new Producto();
        unProducto.setIdProducto(bigIntegerProducto);
        unDetalle.setIdProductoF(unProducto);

        Prestamo unPrestamo = new Prestamo();
        unPrestamo.setIdPrestamo(bigIntegerPrestamo);
        unDetalle.setIdPrestamoF(unPrestamo);

        request.setAttribute("listaDetalles", listaDetalles);
        request.setAttribute("unDetalle", unDetalle);
        request.setAttribute("unProducto", unProducto);
        request.setAttribute("per", unPrestamo);

        String mensaje = "";
        switch (accion) {
            case "insertar" -> {
                unDetalle.insertar();
                mensaje = "Inserto Detalle Prestamo";
            }
            case "modificar" -> {
                unDetalle.modificar();
                mensaje = "Modifico Detalle Prestamo";
            }
            case "eliminar" -> {
                unDetalle.eliminar();
                mensaje = "Elimino Detalle Prestamo";
            }
            case "volver" -> {

                if (usuarioEnSesion != null) {
                    String rol = usuarioEnSesion.getIdRolF().getNombreRol();
                    String vistaDestino;

                    if ("Administrador".equals(rol)) {
                        vistaDestino = "/WEB-INF/Administrador.jsp";
                    } else if ("EncargadoAlmacen".equals(rol)) {
                        vistaDestino = "/WEB-INF/EncargadoAlmacen.jsp";
                    } else {
                        // Si no es Administrador ni EncargadoAlmacen, redirigir a la vista index.jsp
                        vistaDestino = "/index.jsp";
                        // Invalidar la sesión
                        session.invalidate();
                    }

                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vistaDestino);
                    dispatcher.forward(request, response);
                }

            }

        }
        String vistaDetallePres = "/WEB-INF/formularioDetallePres.jsp"; // Ruta a tu archivo JSP
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
