/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author gutie
 */
public class Rol {

    private BigInteger idRol;
    private String nombreRol;
    int paginacion;

    public BigInteger getIdRol() {
        return idRol;
    }

    public void setIdRol(BigInteger idRol) {
        this.idRol = idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    @Override
    public String toString() {
        return "Rol{"
                + "idRol=" + idRol
                + ", nombreRol='" + nombreRol + '\''
                + '}';
    }

    public ArrayList listar(int pagina) {
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        ArrayList listaRol = new ArrayList();
        Rol elRol;
        String listado = "SELECT * FROM " + this.getClass().getSimpleName() + " ORDER BY idRol";

        if (pagina > 0)
        {
            int paginacionMax = pagina * this.paginacion;
            int paginacionMin = paginacionMax - this.paginacion;
            listado = "SELECT * FROM " + this.getClass().getSimpleName()
                    + " ORDER BY idRol LIMIT " + paginacionMin + "," + paginacionMax;
        }

        try
        {
            ResultSet rs = st.executeQuery(listado);
            while (rs.next())
            {
                elRol = new Rol();
                elRol.setIdRol(BigInteger.valueOf(rs.getLong("idRol")));
                elRol.setNombreRol(rs.getString("nombreRol"));
                listaRol.add(elRol);
            }
        } catch (SQLException ex)
        {
            System.err.println("Error al listar rol:" + ex.getLocalizedMessage());
        }
        conexion.desconectar();
        return listaRol;
    }

    public void insertar() {
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try
        {
            st.executeUpdate("INSERT INTO Rol(idRol,nombreRol) VALUES(" + getIdRol() + ",'" + getNombreRol() + "')");
        } catch (SQLException ex)
        {
            System.err.println("Error al insertar rol:" + ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }

    public void modificar() {
    Conexion conexion = new Conexion();
    try (Connection conn = (Connection) conexion.conectar();
         PreparedStatement pstmt = conn.prepareStatement("UPDATE Rol SET nombreRol=? WHERE idRol=?")) {

        pstmt.setString(1, getNombreRol());
        pstmt.setObject(2, getIdRol(), java.sql.Types.BIGINT);

        pstmt.executeUpdate();

    } catch (SQLException ex) {
        System.err.println("Error al modificar rol: " + ex.getMessage());
    } finally {
        conexion.desconectar();
    }
}


    public void eliminar() {
        Conexion conexion = new Conexion();
        try(Connection conn = (Connection) conexion.conectar();
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Rol WHERE idRol = ?")){
            
            pstmt.setObject(1, getIdRol(), java.sql.Types.BIGINT);
            pstmt.executeLargeUpdate();
        } catch (SQLException ex)
        {
            System.err.println("Error al eliminar rol:" + ex.getMessage());
        }finally{
            conexion.desconectar();
        }
        
    }

    public int cantidadPaginas() {
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        int cantidadDeBloques = 0;
        try
        {
            ResultSet rs = st.executeQuery("SELECT CEIL(COUNT(idRol)/" + this.paginacion + ") AS cantidad FROM "
                    + this.getClass().getSimpleName());
            if (rs.next())
            {
                cantidadDeBloques = rs.getInt("cantidad");
            }
        } catch (SQLException ex)
        {
            System.err.println("Error al obtener la cantidad de paginas " + ex.getLocalizedMessage());
        }
        return cantidadDeBloques;
    }

    void getNombreRol(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
