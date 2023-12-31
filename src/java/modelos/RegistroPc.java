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
import java.sql.Date;

/**
 *
 * @author gutie
 */
public class RegistroPc {
    
    private BigInteger idRegistro;
    private String marcaPc;
    private String colorPc;
    private String serialPc;
    private Usuario idUsuF;
    private Persona idPerF;
    private Date entradaPc;
    private Date salidaPc;
    int paginacion;

    public BigInteger getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(BigInteger idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getMarcaPc() {
        return marcaPc;
    }

    public void setMarcaPc(String marcaPc) {
        this.marcaPc = marcaPc;
    }

    public String getColorPc() {
        return colorPc;
    }

    public void setColorPc(String colorPc) {
        this.colorPc = colorPc;
    }

    public String getSerialPc() {
        return serialPc;
    }

    public void setSerialPc(String serialPc) {
        this.serialPc = serialPc;
    }

    public Usuario getIdUsuF() {
        return idUsuF;
    }

    public void setIdUsuF(Usuario idUsuF) {
        this.idUsuF = idUsuF;
    }

    public Persona getIdPerF() {
        return idPerF;
    }

    public void setIdPerF(Persona idPerF) {
        this.idPerF = idPerF;
    }
    
    public Date getEntradaPc() {
        return entradaPc;
    }

    public void setEntradaPc(Date entradaPc) {
        this.entradaPc = entradaPc;
    }

    public Date getSalidaPc() {
        return salidaPc;
    }

    public void setSalidaPc(Date salidaPc) {
        this.salidaPc = salidaPc;
    }
    
    public ArrayList listar (int pagina){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        ArrayList listaReg = new ArrayList();
        RegistroPc elReg;
        String listado = "SELECT * FROM registropc inner join usuario on idUsuF = idUsu inner join persona on idPerF = idPersona";
        
        if (pagina>0) {
            int paginacionMax = pagina * this.paginacion;
            int paginacionMin = paginacionMax - this.paginacion;
            listado = "SELECT * FROM "+this.getClass().getSimpleName()+
                    " ORDER BY idRegistro LIMIT "+paginacionMin+","+paginacionMax;
        }
        
        try {
            ResultSet rs = st.executeQuery(listado);
            while (rs.next()) {
                elReg = new RegistroPc();
                elReg.setIdRegistro(BigInteger.valueOf(rs.getLong("idRegistro")));
                elReg.setMarcaPc(rs.getString("marcaPc"));                
                elReg.setColorPc(rs.getString("colorPc"));
                elReg.setSerialPc(rs.getString("serialPc"));
                
                Usuario usu = new Usuario();
                usu.setIdUsu(BigInteger.valueOf(rs.getLong("idUsu")));
                usu.setNombreUsu(rs.getString("nombreUsu"));
                elReg.setIdUsuF(usu);
                
                Persona per = new Persona();
                per.setIdPersona(BigInteger.valueOf(rs.getLong("idPersona")));
                per.setNombrePersona(rs.getString("nombrePersona"));
                elReg.setIdPerF(per);
                
                elReg.setEntradaPc(rs.getDate("entradaPc"));
                elReg.setSalidaPc(rs.getDate("salidaPc"));
                listaReg.add(elReg);
            }
        } catch (SQLException ex) {
            System.err.println("Error al listar registro del pc:"+ex.getLocalizedMessage());
        }
        conexion.desconectar();
        return listaReg;
    }
    
    public void insertar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try {
            st.executeUpdate("INSERT INTO RegistroPc(idRegistro,marcaPc,colorPc,serialPc,idUsuF,idPerF,entradaPc,salidaPc)"
                    +"VALUES("+getIdRegistro()+",'"+getMarcaPc()+"','"+getColorPc()+"','"
                    +getSerialPc()+"',"+getIdUsuF().getIdUsu()+","+getIdPerF().getIdPersona()+",'"+getEntradaPc()+"',null)");
        } catch(SQLException ex) {
            System.err.println("Error al insertar registro del pc:"+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public void modificar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try {
            st.executeUpdate("UPDATE RegistroPc SET marcaPc='"+getMarcaPc()+"',colorPc='"
                    +getColorPc()+"',serialPc='"+getSerialPc()+"',idUsuF="+getIdUsuF().getIdUsu()
                    +",idPerF="+getIdPerF().getIdPersona()+",entradaPc='"+getEntradaPc()+"',salidaPc='"+getSalidaPc()
                    +"' WHERE idRegistro="+getIdRegistro());
        } catch (SQLException ex) {
            System.err.println("Error al modificar registro del pc:"+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public void eliminar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try {
            st.executeUpdate("DELETE FROM RegistroPc WHERE idRegistro="+getIdRegistro());
            st.execute("ALTER TABLE RegistroPc AUTO_INCREMENT =0");
        } catch (SQLException ex) {
            System.err.println("Error al eliminar registro del pc:"+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public int cantidadPaginas(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        int cantidadDeBloques = 0;
        try {
            ResultSet rs = st.executeQuery("SELECT CEIL(COUNT(idRegistro)/"+this.paginacion+") AS cantidad FROM "
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
