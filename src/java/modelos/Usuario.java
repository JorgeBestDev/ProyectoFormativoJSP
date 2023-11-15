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
public class Usuario {

    private int idUsu;
    private String nombreUsu;
    private String tipoDocUsu;
    private int noDocUsu;
    private int celUsu;
    private String correoUsu;
    private int idRolF;
    private String usuario;
    private String contraseña;
    int paginacion;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public int getIdUsu() {
        return idUsu;
    }

    public void setIdUsu(int idUsu) {
        this.idUsu = idUsu;
    }

    public String getNombreUsu() {
        return nombreUsu;
    }

    public void setNombreUsu(String nombreUsu) {
        this.nombreUsu = nombreUsu;
    }

    public String getTipoDocUsu() {
        return tipoDocUsu;
    }

    public void setTipoDocUsu(String tipoDocUsu) {
        this.tipoDocUsu = tipoDocUsu;
    }

    public int getNoDocUsu() {
        return noDocUsu;
    }

    public void setNoDocUsu(int noDocUsu) {
        this.noDocUsu = noDocUsu;
    }

    public int getCelUsu() {
        return celUsu;
    }

    public void setCelUsu(int celUsu) {
        this.celUsu = celUsu;
    }

    public String getCorreoUsu() {
        return correoUsu;
    }

    public void setCorreoUsu(String correoUsu) {
        this.correoUsu = correoUsu;
    }

    public int getIdRolF() {
        return idRolF;
    }

    public void setIdRolF(int idRolF) {
        this.idRolF = idRolF;
    }

    public ArrayList listar(int pagina) {
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        ArrayList listaUsu = new ArrayList();
        Usuario elUsu;
        String listado = "SELECT * FROM `usuario` inner join rol on idRolF = idRol";

        if (pagina > 0)
        {
            int paginacionMax = pagina * this.paginacion;
            int paginacionMin = paginacionMax - this.paginacion;
            listado = "SELECT * FROM " + this.getClass().getSimpleName()
                    + " ORDER BY idUsuario LIMIT " + paginacionMin + "," + paginacionMax;
        }

        try
        {
            ResultSet rs = st.executeQuery(listado);
            while (rs.next())
            {
                elUsu = new Usuario();
                elUsu.setIdUsu(rs.getInt("idUsu"));
                elUsu.setNombreUsu(rs.getString("nombreUsu"));
                elUsu.setTipoDocUsu(rs.getString("tipoDocUsu"));
                elUsu.setNoDocUsu(rs.getInt("noDocUsu"));
                elUsu.setCelUsu(rs.getInt("celUsu"));
                elUsu.setCorreoUsu(rs.getString("correoUsu"));
                elUsu.setIdRolF(rs.getInt("idRol"));
                listaUsu.add(elUsu);
            }
        } catch (SQLException ex)
        {
            System.err.println("Error al listar usuario:" + ex.getLocalizedMessage());
        }
        conexion.desconectar();
        return listaUsu;
    }

    public void insertar() {
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try
        {
            st.executeUpdate("INSERT INTO Usuario(idUsu,nombreUsu,tipoDocUsu,"
                    + "noDocUsu,noFichaUsu,nombreFichaUsu,celUsu,correoUsu,idRolF)"
                    + "VALUES(" + getIdUsu() + ",'" + getNombreUsu() + "','" + getTipoDocUsu() + "',"
                    + getNoDocUsu() + "," + getCelUsu()
                    + ",'" + getCorreoUsu() + "'," + getIdRolF() + ")");
        } catch (SQLException ex)
        {
            System.err.println("Error al insertar usuario:" + ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }

    public void modificar() {
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try
        {
            st.executeUpdate("UPDATE Usuario SET nombreUsu='" + getNombreUsu() + "',tipoDocUsu='"
                    + getTipoDocUsu() + "',noDocUsu='" + getNoDocUsu() + "',celUsu='" + getCelUsu() + "'"
                    + ",correoUsu='" + getCorreoUsu() + "',idRolF='" + getIdRolF() + "' WHERE idUsu=" + getIdUsu());
        } catch (SQLException ex)
        {
            System.err.println("Error al modificar usuario:" + ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }

    public void eliminar() {
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try
        {
            st.executeUpdate("DELETE FROM Usuario WHERE idUsu=" + getIdUsu());
        } catch (SQLException ex)
        {
            System.err.println("Error al eliminar usuario:" + ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }

    public int cantidadPaginas() {
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        int cantidadDeBloques = 0;
        try
        {
            ResultSet rs = st.executeQuery("SELECT CEIL(COUNT(idUsu)/" + this.paginacion + ") AS cantidad FROM "
                    + this.getClass().getSimpleName());
            if (rs.next())
            {
                cantidadDeBloques = rs.getInt("cantidad");
            }
        } catch (SQLException ex)
        {
            System.err.println("Error al obtener la cantidad de paginas " + ex.getLocalizedMessage());
        }
        return cantidadDeBloques;
    }


    public Boolean validar() {
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try
        {

            String nombreRol = null; // Inicializa la variable donde se guardará el resultado de la consulta
            String nombreUsuario = null; // Inicializa la variable donde se guardará el resultado de la consulta
            String correoUsuario = null; // Inicializa la variable donde se guardará el resultado de la consulta
            String consulta = "SELECT * FROM Usuario "
                    + "JOIN Rol ON Usuario.idRolF = Rol.idRol WHERE usuario.usuario='" + getUsuario() + "' "
                    + "AND Usuario.contraseña = '" + getContraseña() + "'";
            System.out.println(consulta);
            ResultSet rs = st.executeQuery(consulta);

            // Verifica si existe un rol Administrador o EncargadoAlmacen en el resultado de la consulta
            while (rs.next())
            {
                nombreUsuario = rs.getString("nombreUsu");
                correoUsuario = rs.getString("correoUsu");
                nombreRol = rs.getString("nombreRol");
                System.out.println("nombre rol en el modelo usuario " + nombreRol);
                System.out.println("nombre usuario en el modelo usuario " + nombreUsuario);
                System.out.println("correo en el modelo usuario " + correoUsuario);
                if ("Administrador".equals(nombreRol))
                {
                    // Rol válido encontrado, puedes realizar las acciones correspondientes
                    System.out.println("Usuario tiene el rol: " + nombreRol);
                    return true;
                } else if ("EncargadoAlmacen".equals(nombreRol))
                {
                    System.out.println("Usuario tiene el rol: " + nombreRol);
                    return false;
                }
            }

            if (nombreRol == null || (!"Administrador".equals(nombreRol) && !"EncargadoAlmacen".equals(nombreRol)))
            {
                // Usuario no tiene el rol necesario, toma las acciones correspondientes
                System.out.println("Usuario no tiene el rol necesario.");
            }

        } catch (SQLException e)
        {
            System.err.println("Error en funcion validar" + e.getMessage());  // Maneja las excepciones según tus necesidades
        } finally
        {
            conexion.desconectar(); // Cierra la conexión y los recursos (Statement, ResultSet) aquí si es necesario
        }
        return null;
    }

}
