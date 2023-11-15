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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControladorPersona</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorPersona at " + request.getContextPath() + "</h1>");
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
            throws ServletException, IOException{
        String id     = request.getParameter("fIdPersona");
        String nombre = request.getParameter("fNombrePersona");
        String noFicha= request.getParameter("fNoFichaPersona");
        String correo = request.getParameter("fCorreoPersona");
        String celular= request.getParameter("fCelularPersona");
        String tipo   = request.getParameter("fTipoIdentidentificacionPersona");
        String no     = request.getParameter("fNoIdentificacionPersona");
        String accion = request.getParameter("fAccion");      
        
        int idPersona = 0;
        try{
            idPersona = Integer.parseInt(id);
        } catch (NumberFormatException nfe){
            
        }
        
        int noFichaPersona = 0;
        try{
            noFichaPersona = Integer.parseInt(noFicha);
        } catch (NumberFormatException nfe){
            
        }
        
        int celularPersona = 0;
        try{
            celularPersona = Integer.parseInt(celular);
        } catch (NumberFormatException nfe){
            
        }
        
        int noIdentificacionPersona = 0;
        try{
            noIdentificacionPersona = Integer.parseInt(no);
        } catch (NumberFormatException nfe){
            
        }
        
        Persona unaPersona = new Persona();
        unaPersona.setIdPersona(idPersona);
        unaPersona.setNombrePersona(nombre);
        unaPersona.setNoFichaPersona(noFichaPersona);
        unaPersona.setCorreoPersona(correo);
        unaPersona.setCelularPersona(celularPersona);
        unaPersona.setTipoIdentificacionPersona(tipo);
        unaPersona.setNoIdentificacionPersona(noIdentificacionPersona);
        
        String mensaje = "";
        switch(accion.toLowerCase()){
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
        }
        request.getRequestDispatcher("/WEB-INF/formularioPersona.jsp?msj="+mensaje).forward(request, response);
        
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
