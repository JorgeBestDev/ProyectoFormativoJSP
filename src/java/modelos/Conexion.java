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
    
    public Statement conectar(){
        Statement st = null;
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup("BD/PGrupo");
            conexion = ds.getConnection("ADMIN","ADMIN123._.");
            st = conexion.createStatement();
        } catch (NamingException ex) {
            System.err.println("Error al iniciar contexto:"+ex.getMessage());
        } catch (SQLException ex){
            System.err.println("    Error al conectarse a la BD:"+ex.getLocalizedMessage());
        }
        return st;
    }
    
    public void desconectar(){
        try {
            conexion.close();
            
        } catch (SQLException ex) {
            System.err.println("Error al cerrar la BD:"+ex.getLocalizedMessage());
        }
    }
}
