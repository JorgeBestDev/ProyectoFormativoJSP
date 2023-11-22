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
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.sql.Date;
import java.util.ArrayList;
import modelos.Persona;
import modelos.Prestamo;
import modelos.Usuario;

/**
 *
 * @author gutie
 */
@WebServlet(name = "ControladorPrestamo", urlPatterns =
{
    "/ControladorPrestamo"
})
public class ControladorPrestamo extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
        String id = request.getParameter("fIdPrestamo");
        String fecha = request.getParameter("fFechaPrestamo");
        String entrega = request.getParameter("fFechaEntregaPrestamo");
        String observacion = request.getParameter("fObservacionPrestamo");
        String idU = request.getParameter("fIdUsuF");
        String idP = request.getParameter("fIdPersonaF");
        String accion = request.getParameter("fAccion");

        String vistaPrestamo = "/WEB-INF/formularioPrestamo.jsp"; // Ruta a tu archivo JSP
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vistaPrestamo);
        dispatcher.forward(request, response);

        Usuario usuarioModelo = new Usuario();
        System.out.println("listadoUsuarios" + usuarioModelo.listar(0));
        
        ArrayList<Usuario> listaUsuarios = usuarioModelo.listar(0);
        request.setAttribute("listaUsuarios", listaUsuarios);

// Obtener la lista de persona0s
        Persona personaModelo = new Persona();
        ArrayList<Persona> listaPersonas = personaModelo.listar(0); // Asegúrate de tener un método listar en tu modelo Persona
        request.setAttribute("listaPersonas", listaPersonas);

        int idPrestamo = 0;
        try
        {
            idPrestamo = Integer.parseInt(id);
        } catch (NumberFormatException nfe)
        {

        }

        BigInteger bigInteger = new BigInteger(idU);

        int idPersonaF = 0;
        try
        {
            idPersonaF = Integer.parseInt(idP);
        } catch (NumberFormatException nfe)
        {

        }

        LocalDate fechaPres = LocalDate.now();
        Date fechaP = Date.valueOf(fechaPres);
        try
        {
            fechaPres = LocalDate.parse(fecha);
            fechaP = Date.valueOf(fechaPres);
        } catch (DateTimeParseException dtpe)
        {

        }

        LocalDate fechaEntregaPres = LocalDate.now();
        Date entregaP = Date.valueOf(fechaEntregaPres);
        try
        {
            fechaEntregaPres = LocalDate.parse(entrega);
            entregaP = Date.valueOf(fechaEntregaPres);
        } catch (DateTimeParseException dtpe)
        {

        }

        Prestamo unPrestamo = new Prestamo();
        unPrestamo.setIdPrestamo(idPrestamo);
        unPrestamo.setFechaPrestamo(fechaP);
        unPrestamo.setFechaEntregaPrestamo(entregaP);
        unPrestamo.setObservacionPrestamo(observacion);

        Usuario usu = new Usuario();
        usu.setIdUsu(bigInteger);
        unPrestamo.setIdUsuF(usu);

        Persona per = new Persona();
        per.setIdPersona(idPersonaF);
        unPrestamo.setIdPersonaF(per);

        String mensaje = "";
        switch (accion.toLowerCase())
        {
            case "insertar" ->
            {
                unPrestamo.insertar();
                mensaje = "Inserto Prestamo";
            }
            case "modificar" ->
            {
                unPrestamo.modificar();
                mensaje = "Modifico Prestamo";
            }
            case "eliminar" ->
            {
                unPrestamo.eliminar();
                mensaje = "Elimino Prestamo";
            }
        }
        request.getRequestDispatcher("/WEB-INF/formularioPrestamo.jsp?msj=" + mensaje).forward(request, response);

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
