/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author gutie
 */
public class Persona {

    private BigInteger idPersona;
    private String nombrePersona;
    private BigInteger noFichaPersona;
    private String correoPersona;
    private BigInteger celularPersona;
    private String tipoIdentificacionPersona;
    private BigInteger noIdentificacionPersona;
    int paginacion;

    public BigInteger getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(BigInteger idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public BigInteger getNoFichaPersona() {
        return noFichaPersona;
    }

    public void setNoFichaPersona(BigInteger noFichaPersona) {
        this.noFichaPersona = noFichaPersona;
    }

    public String getCorreoPersona() {
        return correoPersona;
    }

    public void setCorreoPersona(String correoPersona) {
        this.correoPersona = correoPersona;
    }

    public BigInteger getCelularPersona() {
        return celularPersona;
    }

    public void setCelularPersona(BigInteger celularPersona) {
        this.celularPersona = celularPersona;
    }

    public String getTipoIdentificacionPersona() {
        return tipoIdentificacionPersona;
    }

    public void setTipoIdentificacionPersona(String tipoIdentificacionPersona) {
        this.tipoIdentificacionPersona = tipoIdentificacionPersona;
    }

    public BigInteger getNoIdentificacionPersona() {
        return noIdentificacionPersona;
    }

    public void setNoIdentificacionPersona(BigInteger noIdentificacionPersona) {
        this.noIdentificacionPersona = noIdentificacionPersona;
    }

    public ArrayList listar(int pagina) {
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        ArrayList listaPersona = new ArrayList();
        Persona laPersona;

        String listado = "SELECT * FROM " + this.getClass().getSimpleName() + " ORDER BY idPersona";

        if (pagina > 0) {
            int paginacionMax = pagina * this.paginacion;
            int paginacionMin = paginacionMax - this.paginacion;
            listado = "SELECT * FROM " + this.getClass().getSimpleName()
                    + " ORDER BY idPersona LIMIT " + paginacionMin + "," + paginacionMax;
        }

        try {
            ResultSet rs = st.executeQuery(listado);
            while (rs.next()) {
                laPersona = new Persona();
                laPersona.setIdPersona(BigInteger.valueOf(rs.getLong("idPersona")));
                laPersona.setNombrePersona(rs.getString("nombrePersona"));
                laPersona.setNoFichaPersona(BigInteger.valueOf(rs.getLong("noFichaPersona")));
                laPersona.setCorreoPersona(rs.getString("correoPersona"));
                laPersona.setCelularPersona(BigInteger.valueOf(rs.getLong("celularPersona")));
                laPersona.setTipoIdentificacionPersona(rs.getString("tipoIdentificacionPersona"));
                laPersona.setNoIdentificacionPersona(BigInteger.valueOf(rs.getLong("noIdentificacionPersona")));
                listaPersona.add(laPersona);
            }
        } catch (SQLException ex) {
            System.err.println("Error al listar administrador:" + ex.getLocalizedMessage());
        }
        conexion.desconectar();
        return listaPersona;
    }

    public void insertar() {
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try {
            st.executeUpdate("INSERT INTO Persona(idPersona,nombrePersona,noFichaPersona,correoPersona,celularPersona,tipoIdentificacionPersona,noIdentificacionPersona) "
                    + "VALUES(" + getIdPersona() + ",'" + getNombrePersona() + "'," + getNoFichaPersona() + ",'" + getCorreoPersona() + "',"
                    + getCelularPersona() + ",'" + getTipoIdentificacionPersona() + "'," + getNoIdentificacionPersona() + ")");
        } catch (SQLException ex) {
            System.err.println("Error al insertar Persona:" + ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }

    public void modificar() {
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try {
            st.executeUpdate("UPDATE Persona SET nombrePersona='" + getNombrePersona() + "', noFichaPersona='" + getNoFichaPersona() + "', correoPersona='"
                    + getCorreoPersona() + "', celularPersona=" + getCelularPersona() + ", tipoIdentificacionPersona='" + getTipoIdentificacionPersona()
                    + "', noIdentificacionPersona='" + getNoIdentificacionPersona() + "' WHERE idPersona=" + getIdPersona());
        } catch (SQLException ex) {
            System.err.println("Error al modificar Persona:" + ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }

    public void eliminar() {
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try {
            st.executeUpdate("DELETE FROM Persona WHERE idPersona=" + getIdPersona());
        } catch (SQLException ex) {
            System.err.println("Error al eliminar Persona:" + ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }

    public int cantidadPaginas() {
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        int cantidadDeBloques = 0;
        try {
            ResultSet rs = st.executeQuery("SELECT CEIL(COUNT(idPersona)/" + this.paginacion + ") AS cantidad FROM "
                    + this.getClass().getSimpleName());
            if (rs.next()) {
                cantidadDeBloques = rs.getInt("cantidad");
            }
        } catch (SQLException ex) {
            System.err.println("Error al obtener la cantidad de paginas " + ex.getLocalizedMessage());
        }
        return cantidadDeBloques;
    }

    public boolean buscar(String documento) {
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();

        String listado = "SELECT * FROM " + this.getClass().getSimpleName() + " WHERE noIdentificacionPersona = " + documento + " ORDER BY idPersona";
        try {
            ResultSet rs = st.executeQuery(listado);
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            System.err.println("Error al listar administrador:" + ex.getLocalizedMessage());
        }
        conexion.desconectar();
        return false;
    }
}
