/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author gutie
 */
public class Administrador {
    private int idAdministrador;
    private String nombreAdministrador;
    private String tipoDocAdministrador;
    private int noDocAdministrador;
    private int celAdministrador;
    private String correoAdministrador;
    private String conAdministrador;
    int paginacion;

    public int getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(int idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public String getNombreAdministrador() {
        return nombreAdministrador;
    }

    public void setNombreAdministrador(String nombreAdministrador) {
        this.nombreAdministrador = nombreAdministrador;
    }

    public String getTipoDocAdministrador() {
        return tipoDocAdministrador;
    }

    public void setTipoDocAdministrador(String tipoDocAdministrador) {
        this.tipoDocAdministrador = tipoDocAdministrador;
    }

    public int getNoDocAdministrador() {
        return noDocAdministrador;
    }

    public void setNoDocAdministrador(int noDocAdministrador) {
        this.noDocAdministrador = noDocAdministrador;
    }

    public int getCelAdministrador() {
        return celAdministrador;
    }

    public void setCelAdministrador(int celAdministrador) {
        this.celAdministrador = celAdministrador;
    }

    public String getCorreoAdministrador() {
        return correoAdministrador;
    }

    public void setCorreoAdministrador(String correoAdministrador) {
        this.correoAdministrador = correoAdministrador;
    }

    public String getConAdministrador() {
        return conAdministrador;
    }

    public void setConAdministrador(String conAdministrador) {
        this.conAdministrador = conAdministrador;
    }
    
    public ArrayList listar (int pagina){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        ArrayList listaAdm = new ArrayList();
        Administrador elAdm;
        String listado = "SELECT * FROM "+this.getClass().getSimpleName()+" ORDER BY idAdministrador";
        
        if (pagina>0) {
            int paginacionMax = pagina * this.paginacion;
            int paginacionMin = paginacionMax - this.paginacion;
            listado = "SELECT * FROM "+this.getClass().getSimpleName()+
                    " ORDER BY idAdministrador LIMIT "+paginacionMin+","+paginacionMax;
        }
        
        try {
            ResultSet rs = st.executeQuery(listado);
            while (rs.next()) {
                elAdm = new Administrador();
                elAdm.setIdAdministrador(rs.getInt("idAdministrador"));
                elAdm.setNombreAdministrador(rs.getString("nombreAdministrador"));                
                elAdm.setTipoDocAdministrador(rs.getString("tipoDocAdministrador"));
                elAdm.setNoDocAdministrador(rs.getInt("noDocAdministrador"));
                elAdm.setCelAdministrador(rs.getInt("celAdministrador"));
                elAdm.setCorreoAdministrador(rs.getString("correoAdministrador"));
                elAdm.setConAdministrador(rs.getString("conAdministrador"));
                listaAdm.add(elAdm);
            }
        } catch (SQLException ex) {
            System.err.println("Error al listar administrador:"+ex.getLocalizedMessage());
        }
        conexion.desconectar();
        return listaAdm;
    }
    
    public void insertar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try {
            st.executeUpdate("INSERT INTO Administrador(idAdministrador,nombreAdministrador,tipoDocAdministrador,"
                    + "noDocAdministrador, celAdministrador,correoAdministrador,conAdministrador)"
                    +"VALUES("+getIdAdministrador()+",'"+getNombreAdministrador()+"','"+getTipoDocAdministrador()+"',"
                    +getNoDocAdministrador()+","+getCelAdministrador()+",'"+getCorreoAdministrador()+"','"+getConAdministrador()+"')");
        } catch(SQLException ex) {
            System.err.println("Error al insertar Administrador:"+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public void modificar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try {
            st.executeUpdate("UPDATE Administrador SET nombreAdministrador='"+getNombreAdministrador()+"',tipoDocAdministrador='"
                    +getTipoDocAdministrador()+"',noDocAdministrador="+getNoDocAdministrador()+",celAdministrador="+getCelAdministrador()
                    +",correoAdministrador='"+getCorreoAdministrador()+"',conAdministrador='"+getConAdministrador()
                    +"' WHERE idAdministrador="+getIdAdministrador());
        } catch (SQLException ex) {
            System.err.println("Error al modificar Administrador:"+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public void eliminar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try {
            st.executeUpdate("DELETE FROM Administrador WHERE idAdministrador="+getIdAdministrador());
        } catch (SQLException ex) {
            System.err.println("Error al eliminar Administrador:"+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public int cantidadPaginas(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        int cantidadDeBloques = 0;
        try {
            ResultSet rs = st.executeQuery("SELECT CEIL(COUNT(idAdministrador)/"+this.paginacion+") AS cantidad FROM "
                    +this.getClass().getSimpleName());
            if (rs.next()){
                cantidadDeBloques = rs.getInt("cantidad");
            }
        } catch (SQLException ex) {
            System.err.println("Error al obtener la cantidad de paginas "+ex.getLocalizedMessage());
        }
        return cantidadDeBloques;
    }
}
