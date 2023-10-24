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
public class EncargadoAlm {
    
    private int idEncargado;
    private String nombreEncargado;
    private String tipoDocEncargado;
    private int noDocEncargado;
    private int celEncargado;
    private String correoEncargado;
    private String conEncargado;
    int paginacion;

    public int getIdEncargado() {
        return idEncargado;
    }

    public void setIdEncargado(int idEncargado) {
        this.idEncargado = idEncargado;
    }

    public String getNombreEncargado() {
        return nombreEncargado;
    }

    public void setNombreEncargado(String nombreEncargado) {
        this.nombreEncargado = nombreEncargado;
    }

    public String getTipoDocEncargado() {
        return tipoDocEncargado;
    }

    public void setTipoDocEncargado(String tipoDocEncargado) {
        this.tipoDocEncargado = tipoDocEncargado;
    }

    public int getNoDocEncargado() {
        return noDocEncargado;
    }

    public void setNoDocEncargado(int noDocEncargado) {
        this.noDocEncargado = noDocEncargado;
    }

    public int getCelEncargado() {
        return celEncargado;
    }

    public void setCelEncargado(int celEncargado) {
        this.celEncargado = celEncargado;
    }

    public String getCorreoEncargado() {
        return correoEncargado;
    }

    public void setCorreoEncargado(String correoEncargado) {
        this.correoEncargado = correoEncargado;
    }

    public String getConEncargado() {
        return conEncargado;
    }

    public void setConEncargado(String conEncargado) {
        this.conEncargado = conEncargado;
    }
    
    public ArrayList listar (int pagina){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        ArrayList listaEnc = new ArrayList();
        EncargadoAlm elEnc;
        String listado = "SELECT * FROM "+this.getClass().getSimpleName()+" ORDER BY idEncargado";
        
        if (pagina>0) {
            int paginacionMax = pagina * this.paginacion;
            int paginacionMin = paginacionMax - this.paginacion;
            listado = "SELECT * FROM "+this.getClass().getSimpleName()+
                    " ORDER BY idEncargado LIMIT "+paginacionMin+","+paginacionMax;
        }
        
        try {
            ResultSet rs = st.executeQuery(listado);
            while (rs.next()) {
                elEnc = new EncargadoAlm();
                elEnc.setIdEncargado(rs.getInt("idEncargado"));
                elEnc.setNombreEncargado(rs.getString("nombreEncargado"));
                elEnc.setTipoDocEncargado(rs.getString("tipoDocEncargado"));
                elEnc.setNoDocEncargado(rs.getInt("noDocEncargado"));
                elEnc.setCelEncargado(rs.getInt("celEncargado"));
                elEnc.setCorreoEncargado(rs.getString("correoEncargado"));
                elEnc.setConEncargado(rs.getString("conEncargado"));
                listaEnc.add(elEnc);
            }
        } catch (SQLException ex) {
            System.err.println("Error al listar encargado:"+ex.getLocalizedMessage());
        }
        conexion.desconectar();
        return listaEnc;
    }
    
    public void insertar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try {
            st.executeUpdate("INSERT INTO EncargadoAlm(idEncargado,nombreEncargado,tipoDocEncargado,"
                    + "noDocEncargado, celEncargado,correoEncargado,conEncargado)"
                    +"VALUES("+getIdEncargado()+",'"+getNombreEncargado()+"','"+getTipoDocEncargado()+"',"
                    +getNoDocEncargado()+","+getCelEncargado()+",'"+getCorreoEncargado()+"','"+getConEncargado()+"')");
        } catch(SQLException ex) {
            System.err.println("Error al insertar Encargado:"+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public void modificar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try {
            st.executeUpdate("UPDATE EncargadoAlm SET nombreEncargado='"+getNombreEncargado()+"',tipoDocEncargado='"
                    +getTipoDocEncargado()+"',noDocEncargado="+getNoDocEncargado()+",celEncargado="+getCelEncargado()
                    +",correoEncargado='"+getCorreoEncargado()+"',conEncargado='"+getConEncargado()
                    +"' WHERE idEncargado="+getIdEncargado());
        } catch (SQLException ex) {
            System.err.println("Error al modificar Encargado:"+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public void eliminar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try {
            st.executeUpdate("DELETE FROM EncargadoAlm WHERE idEncargado="+getIdEncargado());
        } catch (SQLException ex) {
            System.err.println("Error al eliminar Encargado:"+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public int cantidadPaginas(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        int cantidadDeBloques = 0;
        try {
            ResultSet rs = st.executeQuery("SELECT CEIL(COUNT(idEncargado)/"+this.paginacion+") AS cantidad FROM "
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
