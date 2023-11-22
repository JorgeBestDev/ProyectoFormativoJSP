/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class Conexion {
    private Connection conexion = null;

    public Conexion() {
        System.out.println("entra al constructor");
    }
    
    
    public Statement conectar(){
        Statement st = null;
        try{
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jdbc/BDProyectoJSP");
            conexion = ds.getConnection("ADMIN","ADMIN123._.");
            st = conexion.createStatement();
        }catch(NamingException ex){
            System.err.println("Error al iniciar contexto: "+ex.getLocalizedMessage());
        }catch(SQLException | RuntimeException ex){
            System.err.println("Error al conectarse con la base de datis: "+ex.getLocalizedMessage());
        }
        return st;
    }
    
    public void desconectar(){
        try{
        conexion.close();
    }catch(SQLException ex){
            System.err.println("Error al cerrar la BD: "+ex.getLocalizedMessage());
    }
    }
}
