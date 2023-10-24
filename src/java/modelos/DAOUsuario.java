/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;
import java.sql.*;
/**
 *
 * @author Sena
 */
public class DAOUsuario extends Conexion{
    public Usuario identificar(Usuario user) throws Exception{
        Usuario usu = null;
        Conexion con;
        Connection cn=null;
        Statement st = null;
        ResultSet rs=null;
        String sql ="select u.idUsu, r.nombreRol from usuario u inner join rol r on u.idRolF WHERE u.estado = 1 AND u.usuario = '"+user.getUsuario()+"' and u.contraseña'"+ user.getContraseña() +"'";
        con = new Conexion();
        try
        {
            cn= (Connection) con.conectar();
            st= cn.createStatement();
            rs= st.executeQuery(sql);
            if (rs.next()== true)
            {
                usu = new Usuario();
                usu.setIdUsu(rs.getInt("idUsuario"));
                usu.setUsuario(user.getUsuario());
                usu.setIdRolF(new Rol());
                usu.getIdRolF().getNombreRol(rs.getString("nombreRol"));
                usu.setEstado(true);
            }
        } catch (Exception e)
        {
            System.err.println("Error en DAOUsuario"+e.getMessage());
        } finally{
            if (rs !=null && rs.isClosed() == false)
            {
                rs.close();
            }
            rs=null;
            if (st !=null && st.isClosed() == false)
            {
                st.close();
            }
            st=null;
            if (cn !=null && cn.isClosed() == false)
            {
                cn.close();
            }
            cn=null;
        }
        return usu;
    }
}
