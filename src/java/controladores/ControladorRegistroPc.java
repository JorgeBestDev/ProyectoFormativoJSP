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
import modelos.RegistroPc;
import modelos.Usuario;

/**
 *
 * @author gutie
 */
@WebServlet(name = "ControladorRegistroPc", urlPatterns = {"/ControladorRegistroPc"})
public class ControladorRegistroPc extends HttpServlet {

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

        String vistaAdministrador = "/WEB-INF/formularioRegistroPc.jsp"; // Ruta a tu archivo JSP
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vistaAdministrador);
        dispatcher.forward(request, response);
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
        String id = request.getParameter("fIdRegistro");
        String marca = request.getParameter("fMarcaPc");
        String color = request.getParameter("fColorPc");
        String serial = request.getParameter("fSerialPc");
        String idU = request.getParameter("fIdUsuF");
        String idP = request.getParameter("fIdPerF");
        String entrada = request.getParameter("fEntradaPc");
        String salida = request.getParameter("fSalidaPc");
        String accion = request.getParameter("fAccion");

        RegistroPc registrosListados = new RegistroPc();
        ArrayList<RegistroPc> listaRegistros = registrosListados.listar(0);

        Usuario usuariosListados = new Usuario();
        ArrayList<Usuario> listaUsuarios = usuariosListados.listar(0);
        request.setAttribute("listaUsuarios", listaUsuarios);

        BigInteger bigIntegerIdRegistro = null;

        if (id != null && !id.isEmpty()) {
            bigIntegerIdRegistro = new BigInteger(id);
        }
        
        BigInteger bigIntegerU = null;

        if (idU != null && !idU.isEmpty()) {
            bigIntegerU = new BigInteger(idU);
        }
        BigInteger bigIntegerP = null;

        if (idP != null && !idP.isEmpty()) {
            bigIntegerP = new BigInteger(idP);
        }

        LocalDate entradaPc = LocalDate.now();
        Date entradaP = Date.valueOf(entradaPc);
        try {
            if (entrada != null && !entrada.isEmpty()) {
                entradaPc = LocalDate.parse(entrada);
                entradaP = Date.valueOf(entradaPc);
            }
        } catch (DateTimeParseException dtpe) {

        }

        LocalDate salidaPc = LocalDate.now();
        Date salidaP = Date.valueOf(salidaPc);
        try {
            if (salida != null && !salida.isEmpty()) {
                salidaPc = LocalDate.parse(salida);
                salidaP = Date.valueOf(salidaPc);
            }
        } catch (DateTimeParseException dtpe) {

        }

        RegistroPc unRegistro = new RegistroPc();
        unRegistro.setIdRegistro(bigIntegerIdRegistro);
        unRegistro.setMarcaPc(marca);
        unRegistro.setColorPc(color);
        unRegistro.setSerialPc(serial);

        Usuario usu = new Usuario();
        usu.setIdUsu(bigIntegerU);
        unRegistro.setIdUsuF(usu);
        
        Persona per = new Persona();
        per.setIdPersona(bigIntegerP);
        unRegistro.setIdPerF(per);

        unRegistro.setEntradaPc(entradaP);
        unRegistro.setSalidaPc(salidaP);

        request.setAttribute("listaRegistros", listaRegistros);
        request.setAttribute("unRegistro", unRegistro);
        request.setAttribute("usu", usu);
        request.setAttribute("per", per);

        switch (accion.toLowerCase()) {
            case "insertar" -> {
                unRegistro.insertar();
            }
            case "modificar" -> {
                unRegistro.modificar();
            }
            case "eliminar" -> {
                unRegistro.eliminar();
            }
        }
        String vistaProducto = "/WEB-INF/formularioRegistroPc.jsp";
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
