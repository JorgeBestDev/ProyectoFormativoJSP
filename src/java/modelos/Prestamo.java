/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

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
    
    private int idPrestamo;
    private Date fechaPrestamo;
    private Date fechaEntregaPrestamo;   
    private String observacionPrestamo;
    private EncargadoAlm idEncargadoF;
    private Usuario idUsuF;
    int paginacion;

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
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

    public EncargadoAlm getIdEncargadoF() {
        return idEncargadoF;
    }

    public void setIdEncargadoF(EncargadoAlm idEncargadoF) {
        this.idEncargadoF = idEncargadoF;
    }

    public Usuario getIdUsuF() {
        return idUsuF;
    }

    public void setIdUsuF(Usuario idUsuF) {
        this.idUsuF = idUsuF;
    }
    
    public ArrayList listar (int pagina){
        Conexion conexion = new Conexion();
        Statement st = conexion.conexion();
        ArrayList listaPre = new ArrayList();
        Prestamo elPre;
        String listado = "SELECT * FROM `prestamo` inner join encargadoalm on idEncargadoF = idEncargado inner join usuario on idUsuF = idUsu";
        
        if (pagina>0) {
            int paginacionMax = pagina * this.paginacion;
            int paginacionMin = paginacionMax - this.paginacion;
            listado = "SELECT * FROM "+this.getClass().getSimpleName()+
                    " ORDER BY idPrestamo LIMIT "+paginacionMin+","+paginacionMax;
        }
        
        try {
            ResultSet rs = st.executeQuery(listado);
            while (rs.next()) {
                elPre = new Prestamo();
                elPre.setIdPrestamo(rs.getInt("idPre"));
                elPre.setFechaPrestamo(rs.getDate("fechaPrestamo"));                
                elPre.setFechaEntregaPrestamo(rs.getDate("fechaEntregaPrestamo"));
                elPre.setObservacionPrestamo(rs.getString("observacionPrestamo"));
                EncargadoAlm enc = new EncargadoAlm();
                enc.setIdEncargado(rs.getInt("idEncargado"));
                enc.setNombreEncargado(rs.getString("nombreEncargado"));
                elPre.setIdEncargadoF(enc);
                Usuario usu = new Usuario();
                usu.setIdUsu(rs.getInt("idUsu"));
                usu.setNombreUsu(rs.getString("nombreUsu"));
                elPre.setIdUsuF(usu);
                listaPre.add(elPre);
            }
        } catch (SQLException ex) {
            System.err.println("Error al listar prestamo:"+ex.getLocalizedMessage());
        }
        conexion.desconectar();
        return listaPre;
    }
    
    public void insertar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conexion();
        try {
            st.executeUpdate("INSERT INTO Prestamo(idPrestamo,fechaPrestamo,"
                    + "fechaEntregaPrestamo,observacionPrestamo,idEncargadoF,idUsuF,)"
                    +"VALUES("+getIdPrestamo()+",'"+getFechaPrestamo()+"','"+getFechaEntregaPrestamo()+"','"
                    +getObservacionPrestamo()+"',"+getIdEncargadoF().getIdEncargado()+",'"+getIdUsuF().getIdUsu()+")");
        } catch(SQLException ex) {
            System.err.println("Error al insertar prestamo:"+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public void modificar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conexion();
        try {
            st.executeUpdate("UPDATE Prestamo SET fechaPrestamo='"+getFechaPrestamo()+"',fechaEntregaPrestamo='"
                    +getFechaEntregaPrestamo()+"',observacionPrestamo='"+getObservacionPrestamo()
                    +"',idEncargadoF="+getIdEncargadoF().getIdEncargado()+",idUsuF="
                    +getIdUsuF().getIdUsu()+" WHERE idPrestamo="+getIdPrestamo());
        } catch (SQLException ex) {
            System.err.println("Error al modificar prestamo:"+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public void eliminar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conexion();
        try {
            st.executeUpdate("DELETE FROM Prestamo WHERE idPrestamo="+getIdPrestamo());
        } catch (SQLException ex) {
            System.err.println("Error al eliminar prestamo:"+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public int cantidadPaginas(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conexion();
        int cantidadDeBloques = 0;
        try {
            ResultSet rs = st.executeQuery("SELECT CEIL(COUNT(idPrestamo)/"+this.paginacion+") AS cantidad FROM "
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
