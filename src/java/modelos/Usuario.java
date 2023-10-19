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
public class Usuario {
    
    private int idUsu;
    private String nombreUsu;
    private String tipoDocUsu;
    private int noDocUsu;
    private int noFichaUsu;
    private String nombreFichaUsu;
    private int celUsu;
    private String correoUsu;
    private Rol idRolF;
    int paginacion;

    public int getIdUsu() {
        return idUsu;
    }

    public void setIdUsu(int idUsu) {
        this.idUsu = idUsu;
    }

    public String getNombreUsu() {
        return nombreUsu;
    }

    public void setNombreUsu(String nombreUsu) {
        this.nombreUsu = nombreUsu;
    }

    public String getTipoDocUsu() {
        return tipoDocUsu;
    }

    public void setTipoDocUsu(String tipoDocUsu) {
        this.tipoDocUsu = tipoDocUsu;
    }

    public int getNoDocUsu() {
        return noDocUsu;
    }

    public void setNoDocUsu(int noDocUsu) {
        this.noDocUsu = noDocUsu;
    }

    public int getNoFichaUsu() {
        return noFichaUsu;
    }

    public void setNoFichaUsu(int noFichaUsu) {
        this.noFichaUsu = noFichaUsu;
    }

    public String getNombreFichaUsu() {
        return nombreFichaUsu;
    }

    public void setNombreFichaUsu(String nombreFichaUsu) {
        this.nombreFichaUsu = nombreFichaUsu;
    }

    public int getCelUsu() {
        return celUsu;
    }

    public void setCelUsu(int celUsu) {
        this.celUsu = celUsu;
    }

    public String getCorreoUsu() {
        return correoUsu;
    }

    public void setCorreoUsu(String correoUsu) {
        this.correoUsu = correoUsu;
    }

    public Rol getIdRolF() {
        return idRolF;
    }

    public void setIdRolF(Rol idRolF) {
        this.idRolF = idRolF;
    }
    
    public ArrayList listar (int pagina){
        Conexion conexion = new Conexion();
        Statement st = conexion.conexion();
        ArrayList listaUsu = new ArrayList();
        Usuario elUsu;
        String listado = "SELECT * FROM `usuario` inner join rol on idRolF = idRol";
        
        if (pagina>0) {
            int paginacionMax = pagina * this.paginacion;
            int paginacionMin = paginacionMax - this.paginacion;
            listado = "SELECT * FROM "+this.getClass().getSimpleName()+
                    " ORDER BY idUsuario LIMIT "+paginacionMin+","+paginacionMax;
        }
        
        try {
            ResultSet rs = st.executeQuery(listado);
            while (rs.next()) {
                elUsu = new Usuario();
                elUsu.setIdUsu(rs.getInt("idUsu"));
                elUsu.setNombreUsu(rs.getString("nombreUsu"));                
                elUsu.setTipoDocUsu(rs.getString("tipoDocUsu"));
                elUsu.setNoDocUsu(rs.getInt("noDocUsu"));
                elUsu.setNoFichaUsu(rs.getInt("noFichaUsu"));
                elUsu.setNombreFichaUsu(rs.getString("nombreFichaUsu"));
                elUsu.setCelUsu(rs.getInt("celUsu"));
                elUsu.setCorreoUsu(rs.getString("correoUsu"));
                Rol rol = new Rol();
                rol.setIdRol(rs.getInt("idRol"));
                rol.setNombreRol(rs.getString("nombreRol"));
                elUsu.setIdRolF(rol);
                listaUsu.add(elUsu);
            }
        } catch (SQLException ex) {
            System.err.println("Error al listar usuario:"+ex.getLocalizedMessage());
        }
        conexion.desconectar();
        return listaUsu;
    }
    
    public void insertar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conexion();
        try {
            st.executeUpdate("INSERT INTO Usuario(idUsu,nombreUsu,tipoDocUsu,"
                    + "noDocUsu,noFichaUsu,nombreFichaUsu,celUsu,correoUsu,idRolF)"
                    +"VALUES("+getIdUsu()+",'"+getNombreUsu()+"','"+getTipoDocUsu()+"',"
                    +getNoDocUsu()+","+getNoFichaUsu()+",'"+getNombreFichaUsu()+"',"+getCelUsu()
                    +",'"+getCorreoUsu()+"',"+getIdRolF().getIdRol()+")");
        } catch(SQLException ex) {
            System.err.println("Error al insertar usuario:"+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public void modificar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conexion();
        try {
            st.executeUpdate("UPDATE Usuario SET nombreUsu='"+getNombreUsu()+"',tipoDocUsu='"
                    +getTipoDocUsu()+"',noDocUsu="+getNoDocUsu()+",celUsu="+getCelUsu()+",noFichaUsu="+getNoFichaUsu()
                    +",nombreFichaUsu='"+getNombreFichaUsu()+"',correoUsu='"+getCorreoUsu()+"',idRolF='"+getIdRolF().getIdRol()
                    +"' WHERE idUsu="+getIdUsu());
        } catch (SQLException ex) {
            System.err.println("Error al modificar usuario:"+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public void eliminar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conexion();
        try {
            st.executeUpdate("DELETE FROM Usuario WHERE idUsu="+getIdUsu());
        } catch (SQLException ex) {
            System.err.println("Error al eliminar usuario:"+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public int cantidadPaginas(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conexion();
        int cantidadDeBloques = 0;
        try {
            ResultSet rs = st.executeQuery("SELECT CEIL(COUNT(idUsu)/"+this.paginacion+") AS cantidad FROM "
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
