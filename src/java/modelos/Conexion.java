/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.sql.*;
import javax.sql.*;
import javax.naming.*;

/**
 *
 * @author Sena
 */
public class Conexion {
    Connection conexion = null;
    public Statement conexion(){
        Statement st = null;
        try
        {
            Context ctx= new InitialContext();
            DataSource ds = (DataSource)ctx.lookup("jdbc/BDIngresoJSP");
            conexion = ds.getConnection("ADMINISTRADOR","ADMINISTRADOR");
            st = conexion.createStatement();
        } catch (NamingException e)
        {
            System.err.println("Error al iniciar Contexto: "+e.getMessage());
        } catch (SQLException e){
            System.err.println("Error al Conectar:"+e.getLocalizedMessage());
        }
        return st;
    }
    public void desconectar(){
        try
        {
            conexion.close();
        } catch (SQLException e)
        {
            System.err.println("Error al cerrar la BD");
        }
    }
}
