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
public class Producto {
    
    private int idProducto;
    private String nombreProducto;
    private int cantidadProducto;
    private String descripcionProducto;
    int paginacion;

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }
    
    public ArrayList listar (int pagina){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        ArrayList listaPro = new ArrayList();
        Producto elPro;
        String listado = "SELECT * FROM "+this.getClass().getSimpleName()+" ORDER BY idProducto";
        
        if (pagina>0) {
            int paginacionMax = pagina * this.paginacion;
            int paginacionMin = paginacionMax - this.paginacion;
            listado = "SELECT * FROM "+this.getClass().getSimpleName()+
                    " ORDER BY idProducto LIMIT "+paginacionMin+","+paginacionMax;
        }
        
        try {
            ResultSet rs = st.executeQuery(listado);
            while (rs.next()) {
                elPro = new Producto();
                elPro.setIdProducto(rs.getInt("idProducto"));
                elPro.setNombreProducto(rs.getString("nombreProducto"));                
                elPro.setCantidadProducto(rs.getInt("cantidadProducto"));
                elPro.setDescripcionProducto(rs.getString("descripcionProducto"));
                listaPro.add(elPro);
            }
        } catch (SQLException ex) {
            System.err.println("Error al listar producto:"+ex.getLocalizedMessage());
        }
        conexion.desconectar();
        return listaPro;
    }  
    
    public void insertar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try {
            st.executeUpdate("INSERT INTO Producto(idProducto,nombreProducto,cantidadProducto,descripcionProducto)"
                    + "VALUES("+getIdProducto()+",'"+getNombreProducto()+"',"+getCantidadProducto()+",'"
                    +getDescripcionProducto()+"')");
        } catch(SQLException ex) {
            System.err.println("Error al insertar producto:"+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public void modificar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try {
            st.executeUpdate("UPDATE Producto SET nombreProducto='"+getNombreProducto()+"',cantidadProducto="
                    +getCantidadProducto()+",descripcionProducto='"+getDescripcionProducto()
                    +"' WHERE idProducto="+getIdProducto());
        } catch (SQLException ex) {
            System.err.println("Error al modificar producto:"+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public void eliminar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try {
            st.executeUpdate("DELETE FROM Producto WHERE idProducto="+getIdProducto());
        } catch (SQLException ex) {
            System.err.println("Error al eliminar producto:"+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public int cantidadPaginas(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        int cantidadDeBloques = 0;
        try {
            ResultSet rs = st.executeQuery("SELECT CEIL(COUNT(idProducto)/"+this.paginacion+") AS cantidad FROM "
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
