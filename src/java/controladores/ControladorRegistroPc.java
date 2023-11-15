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
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import modelos.RegistroPc;

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
        String id       = request.getParameter("fIdRegistro");
        String marca    = request.getParameter("fMarcaPc");
        String color    = request.getParameter("fColorPc");
        String serial   = request.getParameter("fSerialPc");
        String idU      = request.getParameter("fIdUsuF");
        String entrada  = request.getParameter("fEntradaPc");
        String salida   = request.getParameter("fSalidaPc");
        String accion   = request.getParameter("fAccion");      
        
        int idRegistro = 0;
        try{
            idRegistro = Integer.parseInt(id);
        } catch (NumberFormatException nfe){
            
        }
        
        int idUsuF = 0;
        try{
            idUsuF = Integer.parseInt(idU);
        } catch (NumberFormatException nfe){
            
        }
        
        LocalDate entradaPc = LocalDate.now();
        try{
            entradaPc = LocalDate.parse(entrada);
        }catch(DateTimeParseException dtpe){
        
        }
        
        LocalDate salidaPc = LocalDate.now();
        try{
            salidaPc = LocalDate.parse(salida);
        }catch(DateTimeParseException dtpe){
        
        }
        
        RegistroPc unRegistro = new RegistroPc();
        unRegistro.setIdRegistro(idRegistro);
        unRegistro.setMarcaPc(marca);
        unRegistro.setColorPc(color);
        unRegistro.setSerialPc(serial);
        unRegistro.setIdUsuF(idUsuF);
        unRegistro.setEntradaPc(entradaPc);
        unRegistro.setSalidaPc(salidaPc);
        
        String mensaje = "";
        switch(accion.toLowerCase()){
            case "insertar":
                unRegistro.insertar();
                mensaje = "Inserto RegistroPc";
            break;
            case "modificar":
                unRegistro.modificar();
                mensaje = "Modifico RegistroPc";
            break;
            case "eliminar":
                unRegistro.eliminar();
                mensaje = "Elimino RegistroPc";
            break;    
        }
        request.getRequestDispatcher("/WEB-INF/formularioRegistroPc.jsp?msj="+mensaje).forward(request, response);
        
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
