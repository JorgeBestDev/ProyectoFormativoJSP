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
public class DetallePres {
    
    private int idDetallePres;
    private Producto idProductoF;
    private Prestamo idPrestamoF;
    int paginacion;

    public int getIdDetallePres() {
        return idDetallePres;
    }

    public void setIdDetallePres(int idDetallePres) {
        this.idDetallePres = idDetallePres;
    }

    public Producto getIdProductoF() {
        return idProductoF;
    }

    public void setIdProductoF(Producto idProductoF) {
        this.idProductoF = idProductoF;
    }

    public Prestamo getIdPrestamoF() {
        return idPrestamoF;
    }

    public void setIdPrestamoF(Prestamo idPrestamoF) {
        this.idPrestamoF = idPrestamoF;
    }
    
    public ArrayList listar (int pagina){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        ArrayList listaDet = new ArrayList();
        DetallePres elDet;
        String listado = "SELECT * FROM `detallepres` inner join producto on idProductoF = idProducto inner join prestamo on idPrestamoF = idPrestamo";
        
        if (pagina>0) {
            int paginacionMax = pagina * this.paginacion;
            int paginacionMin = paginacionMax - this.paginacion;
            listado = "SELECT * FROM "+this.getClass().getSimpleName()+
                    " ORDER BY idDetallePres LIMIT "+paginacionMin+","+paginacionMax;
        }
        
        try {
            ResultSet rs = st.executeQuery(listado);
            while (rs.next()) {
                elDet = new DetallePres();
                elDet.setIdDetallePres(rs.getInt("idDetallePres"));
                
                Producto pro = new Producto();
                pro.setIdProducto(rs.getInt("idProducto"));
                pro.setNombreProducto(rs.getString("nombreProducto"));
                elDet.setIdProductoF(pro);
                
                Prestamo pre = new Prestamo();
                pre.setIdPrestamo(rs.getInt("idPrestamo"));
                elDet.setIdPrestamoF(pre);
                listaDet.add(elDet);
            }
        } catch (SQLException ex) {
            System.err.println("Error al listar detalle del prestamo:"+ex.getLocalizedMessage());
        } 
        conexion.desconectar();
        return listaDet;
    }
    
    public void insertar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try {
            st.executeUpdate("INSERT INTO DetallePres(idDetallePres,idProductoF,idPrestamoF)"
                    +"VALUES("+getIdDetallePres()+","+getIdProductoF().getIdProducto()+","
                    +getIdPrestamoF().getIdPrestamo()+")");
        } catch(SQLException ex) {
            System.err.println("Error al insertar detalle del prestamo:"+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public void modificar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try {
            st.executeUpdate("UPDATE DetallePres SET idProductoF="+getIdProductoF().getIdProducto()
                    +",idPrestamoF="+getIdPrestamoF().getIdPrestamo()+" WHERE idDetallePres="+getIdDetallePres());
        } catch (SQLException ex) {
            System.err.println("Error al modificar detalle del prestamo:"+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public void eliminar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try {
            st.executeUpdate("DELETE FROM DetallePres WHERE idDetallePres="+getIdDetallePres());
        } catch (SQLException ex) {
            System.err.println("Error al eliminar detalle del prestamo:"+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public int cantidadPaginas(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        int cantidadDeBloques = 0;
        try {
            ResultSet rs = st.executeQuery("SELECT CEIL(COUNT(idDetallePres)/"+this.paginacion+") AS cantidad FROM "
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
