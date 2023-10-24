/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author Sena
 */
public class Comprobar {
    public static void main (String[] args) throws SQLException, NamingException{
    
        Conexion c = new Conexion();
        if (c.conectar()!=null)
        {
            System.out.println("Conexion Correcta");
        }else{
             System.err.println("Conexion Erronea");
        }
        
    }
}
