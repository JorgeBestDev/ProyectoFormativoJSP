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
import modelos.Persona;

/**
 *
 * @author gutie
 */
@WebServlet(name = "ControladorPersona", urlPatterns = {"/ControladorPersona"})
public class ControladorPersona extends HttpServlet {

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
        String id = request.getParameter("fIdPersona");
        String nombre = request.getParameter("fNombrePersona");
        String noFicha = request.getParameter("fNoFichaPersona");
        String correo = request.getParameter("fCorreoPersona");
        String celular = request.getParameter("fCelularPersona");
        String tipo = request.getParameter("fTipoIdentidentificacionPersona");
        String no = request.getParameter("fNoIdentificacionPersona");
        String accion = request.getParameter("fAccion");

        BigInteger bigIntegerIdPersona = new BigInteger(id);
        BigInteger bigIntegerNoFichaPersona = new BigInteger(noFicha);
        BigInteger bigIntegerCelularPersona = new BigInteger(celular);
        BigInteger bigIntegerIdentificacionPersona = new BigInteger(no);

        
        

        Persona unaPersona = new Persona();
        unaPersona.setIdPersona(bigIntegerIdPersona);
        unaPersona.setNombrePersona(nombre);
        unaPersona.setNoFichaPersona(bigIntegerNoFichaPersona);
        unaPersona.setCorreoPersona(correo);
        unaPersona.setCelularPersona(bigIntegerCelularPersona);
        unaPersona.setTipoIdentificacionPersona(tipo);
        unaPersona.setNoIdentificacionPersona(bigIntegerIdentificacionPersona);

        String mensaje = "";
        switch (accion.toLowerCase()) {
            case "insertar" -> {
                unaPersona.insertar();
                mensaje = "Inserto Persona";
            }
            case "modificar" -> {
                unaPersona.modificar();
                mensaje = "Modifico Persona";
            }
            case "eliminar" -> {
                unaPersona.eliminar();
                mensaje = "Elimino Persona";
            }

            case "buscar" -> {
                String docume = request.getParameter("documento");
                boolean rs = unaPersona.buscar(docume);

                if (rs) {

                    request.getRequestDispatcher("/WEB-INF/Ingreso.jsp?msj=Documento Valido").forward(request, response);
                } else {
                    request.getRequestDispatcher("/WEB-INF/Ingreso.jsp?msj=Documento Invalido").forward(request, response);
                }

            }
        }
        request.getRequestDispatcher("/WEB-INF/formularioPersona.jsp?msj=" + mensaje).forward(request, response);

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
