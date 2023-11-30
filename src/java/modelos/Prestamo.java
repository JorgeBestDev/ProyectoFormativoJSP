/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author gutie
 */
public class Prestamo {

    private BigInteger idPrestamo;
    private Date fechaPrestamo;
    private Date fechaEntregaPrestamo;
    private String observacionPrestamo;
    private Usuario idUsuF;
    private Persona idPersonaF;
    int paginacion = 10;

    public BigInteger getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(BigInteger idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaEntregaPrestamo() {
        return fechaEntregaPrestamo;
    }

    public void setFechaEntregaPrestamo(Date fechaEntregaPrestamo) {
        this.fechaEntregaPrestamo = fechaEntregaPrestamo;
    }

    public String getObservacionPrestamo() {
        return observacionPrestamo;
    }

    public void setObservacionPrestamo(String observacionPrestamo) {
        this.observacionPrestamo = observacionPrestamo;
    }

    public Usuario getIdUsuF() {
        return idUsuF;
    }

    public void setIdUsuF(Usuario idUsuF) {
        this.idUsuF = idUsuF;
    }

    public Persona getIdPersonaF() {
        return idPersonaF;
    }

    public void setIdPersonaF(Persona idPersonaF) {
        this.idPersonaF = idPersonaF;
    }

    public ArrayList listar(int pagina) {
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        ArrayList listaPre = new ArrayList();
        Prestamo elPre;
        String listado = "SELECT * FROM `prestamo` inner join usuario on idUsuF = idUsu inner join persona on idPersonaF = idPersona";

        if (pagina > 0) {
            int paginacionMax = pagina * this.paginacion;
            int paginacionMin = paginacionMax - this.paginacion;
            listado = "SELECT * FROM " + this.getClass().getSimpleName()
                    + " ORDER BY idPrestamo LIMIT " + paginacionMin + "," + paginacionMax;
        }

        try {
            ResultSet rs = st.executeQuery(listado);
            while (rs.next()) {
                elPre = new Prestamo();
                elPre.setIdPrestamo(BigInteger.valueOf(rs.getLong("idPrestamo")));
                elPre.setFechaPrestamo(rs.getDate("fechaPrestamo"));
                elPre.setFechaEntregaPrestamo(rs.getDate("fechaEntregaPrestamo"));
                elPre.setObservacionPrestamo(rs.getString("observacionPrestamo"));

                Usuario usu = new Usuario();
                usu.setIdUsu(BigInteger.valueOf(rs.getInt("idUsuF")));
                usu.setNombreUsu(rs.getString("nombreUsu"));
                elPre.setIdUsuF(usu);

                Persona per = new Persona();
                per.setIdPersona(BigInteger.valueOf(rs.getLong("idPersonaF")));
                per.setNombrePersona(rs.getString("nombrePersona"));
                elPre.setIdPersonaF(per);

                listaPre.add(elPre);
            }
        } catch (SQLException ex) {
            System.err.println("Error al listar prestamo:" + ex.getLocalizedMessage());
        }
        conexion.desconectar();
        return listaPre;
    }

    public void insertar() {
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();

        try {
            String sql = "INSERT INTO Prestamo(idPrestamo, fechaPrestamo, fechaEntregaPrestamo, observacionPrestamo, idUsuF, idPersonaF) "
                    + "VALUES (" + getIdPrestamo() + ", '" + getFechaPrestamo() + "', '" + getFechaEntregaPrestamo() + "', '"
                    + getObservacionPrestamo() + "', " + getIdUsuF().getIdUsu() + ", " + getIdPersonaF().getIdPersona() + ")";

            st.executeUpdate(sql);
        } catch (SQLException ex) {
            System.err.println("Error al insertar prestamo:" + ex.getLocalizedMessage());
        } finally {
            conexion.desconectar();
        }
    }

    public void modificar() {
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try {
            st.executeUpdate("UPDATE Prestamo SET fechaPrestamo='" + getFechaPrestamo() + "', fechaEntregaPrestamo='" + getFechaEntregaPrestamo() + "', "
                    + "observacionPrestamo='" + getObservacionPrestamo() + "', idUsuF=" + getIdUsuF() + ", idPersonaF=" + getIdPersonaF() + " WHERE idPrestamo=" + getIdPrestamo());
        } catch (SQLException ex) {
            System.err.println("Error al modificar prestamo:" + ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }

    public void eliminar() {
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try {
            st.executeUpdate("DELETE FROM Prestamo WHERE idPrestamo=" + getIdPrestamo());
            st.executeUpdate("ALTER TABLE prestamo AUTO_INCREMENT =0");
        } catch (SQLException ex) {
            System.err.println("Error al eliminar prestamo:" + ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }

    public int cantidadPaginas() {
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        int cantidadDeBloques = 0;
        try {
            ResultSet rs = st.executeQuery("SELECT CEIL(COUNT(idPrestamo)/" + this.paginacion + ") AS cantidad FROM "
                    + this.getClass().getSimpleName());
            if (rs.next()) {
                cantidadDeBloques = rs.getInt("cantidad");
            }
        } catch (SQLException ex) {
            System.err.println("Error al obtener la cantidad de paginas " + ex.getLocalizedMessage());
        }
        return cantidadDeBloques;
    }

}
