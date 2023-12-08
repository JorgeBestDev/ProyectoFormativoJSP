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
public class DetallePres {
    
    private BigInteger idDetallePres;
    private Producto idProductoF;
    private Prestamo idPrestamoF;
    int paginacion = 10;

    public BigInteger getIdDetallePres() {
        return idDetallePres;
    }

    public void setIdDetallePres(BigInteger idDetallePres) {
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
        ArrayList listaDetalles = new ArrayList();
        DetallePres detallePre;
        String listado = "SELECT * FROM detallepres inner join producto on idProductoF = idProducto inner join prestamo on idPrestamoF = idPrestamo;";
        System.out.println("listado   "+listado);
        if (pagina>0) {
            int paginacionMax = pagina * this.paginacion;
            int paginacionMin = paginacionMax - this.paginacion;
            listado = "SELECT * FROM "+this.getClass().getSimpleName()+
                    " ORDER BY idDetallePres LIMIT "+paginacionMin+","+paginacionMax;
        }
        
        try {
            ResultSet rs = st.executeQuery(listado);
            while (rs.next()) {
                detallePre = new DetallePres();
                detallePre.setIdDetallePres(BigInteger.valueOf(rs.getLong("idDetallePres")));
                
                Producto pro = new Producto();
                pro.setIdProducto(BigInteger.valueOf(rs.getLong("idProducto")));
                pro.setNombreProducto(rs.getString("nombreProducto"));
                detallePre.setIdProductoF(pro);
                
                Prestamo pre = new Prestamo();
                pre.setIdPrestamo(BigInteger.valueOf(rs.getLong("idPrestamo")));
                detallePre.setIdPrestamoF(pre);
                
                listaDetalles.add(detallePre);
            }
        } catch (SQLException ex) {
            System.err.println("Error al listar detalle del prestamo:"+ex.getLocalizedMessage());
        } 
        conexion.desconectar();
        return listaDetalles;
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
            String sql="DELETE FROM DetallePres WHERE DetallePres.idDetallePres="+getIdDetallePres();
            st.execute(sql);
            st.execute("ALTER TABLE DetallePres AUTO_INCREMENT =0");
            System.out.println("consulta eliminar detalle"+sql);
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
