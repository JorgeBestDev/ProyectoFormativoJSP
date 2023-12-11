/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author gutie
 */
public class Usuario {

    private BigInteger idUsu;
    private String nombreUsu;
    private String tipoDocUsu;
    private BigInteger noDocUsu;
    private BigInteger celUsu;
    private String correoUsu;
    private Rol idRolF;
    private String usuario;
    private String contraseña;
    int paginacion = 10;

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

    public BigInteger getIdUsu() {
        return idUsu;
    }

    public void setIdUsu(BigInteger idUsu) {
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

    public BigInteger getNoDocUsu() {
        return noDocUsu;
    }

    public void setNoDocUsu(BigInteger noDocUsu) {
        this.noDocUsu = noDocUsu;
    }

    public BigInteger getCelUsu() {
        return celUsu;
    }

    public void setCelUsu(BigInteger celUsu) {
        this.celUsu = celUsu;
    }

    public String getCorreoUsu() {
        return correoUsu;
    }

    public void setCorreoUsu(String correoUsu) {
        this.correoUsu = correoUsu;
    }

    public Rol getIdRolF() {
        return idRolF;
    }

    public void setIdRolF(Rol idRolF) {
        this.idRolF = idRolF;
    }

    public ArrayList listar(int pagina) {
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        ArrayList listaUsu = new ArrayList();
        Usuario elUsu;
        String listado = "SELECT Usuario.*, Rol.nombreRol FROM Usuario "
                + "JOIN Rol ON Usuario.idRolF = Rol.idRol";

        if (pagina > 0) {
            int paginacionMax = pagina * this.paginacion;
            int paginacionMin = paginacionMax - this.paginacion;
            listado = "SELECT * FROM " + this.getClass().getSimpleName()
                    + " ORDER BY idUsu LIMIT " + paginacionMin + "," + paginacionMax;
        }

        try {
            ResultSet rs = st.executeQuery(listado);
            while (rs.next()) {
                elUsu = new Usuario();
                elUsu.setIdUsu(BigInteger.valueOf(rs.getLong("idUsu")));
                elUsu.setNombreUsu(rs.getString("nombreUsu"));
                elUsu.setTipoDocUsu(rs.getString("tipoDocUsu"));
                elUsu.setNoDocUsu(BigInteger.valueOf(rs.getLong("noDocUsu")));
                elUsu.setCelUsu(BigInteger.valueOf(rs.getLong("celUsu")));
                elUsu.setCorreoUsu(rs.getString("correoUsu"));

                Rol rol = new Rol();
                rol.setIdRol(BigInteger.valueOf(rs.getLong("idRolF")));
                rol.setNombreRol(rs.getString("nombreRol"));
                elUsu.setIdRolF(rol);
                elUsu.setUsuario(rs.getString("usuario"));
                elUsu.setContraseña(rs.getString("contraseña"));
                listaUsu.add(elUsu);
            }
        } catch (SQLException ex) {
            System.err.println("Error al listar usuario:" + ex.getLocalizedMessage());
        }
        conexion.desconectar();
        return listaUsu;
    }

    @Override
    public String toString() {
        return "Usuario{"
                + "idUsu=" + idUsu
                + ", nombreUsu='" + nombreUsu + '\''
                + ", tipoDocUsu='" + tipoDocUsu + '\''
                + ", noDocUsu=" + noDocUsu
                + ", celUsu=" + celUsu
                + ", correoUsu='" + correoUsu + '\''
                + ", idRolF=" + idRolF
                + ", usuario='" + usuario + '\''
                + ", contraseña='" + contraseña + '\''
                + '}';
    }

    public void insertar() {
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try {
            st.executeUpdate("INSERT INTO Usuario(idUsu,nombreUsu,tipoDocUsu,"
                    + "noDocUsu,noFichaUsu,nombreFichaUsu,celUsu,correoUsu,idRolF)"
                    + "VALUES(" + getIdUsu() + ",'" + getNombreUsu() + "','" + getTipoDocUsu() + "',"
                    + getNoDocUsu() + "," + getCelUsu()
                    + ",'" + getCorreoUsu() + "'," + getIdRolF() + ")");
        } catch (SQLException ex) {
            System.err.println("Error al insertar usuario:" + ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }

    public void modificar() {
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try {
            st.executeUpdate("UPDATE Usuario SET nombreUsu='" + getNombreUsu() + "',tipoDocUsu='"
                    + getTipoDocUsu() + "',noDocUsu='" + getNoDocUsu() + "',celUsu='" + getCelUsu() + "'"
                    + ",correoUsu='" + getCorreoUsu() + "',idRolF='" + getIdRolF() + "' WHERE idUsu=" + getIdUsu());
        } catch (SQLException ex) {
            System.err.println("Error al modificar usuario:" + ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }

    public void eliminar() {
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try {
            st.executeUpdate("DELETE FROM Usuario WHERE idUsu=" + getIdUsu());
            st.execute("ALTER TABLE Usuario AUTO_INCREMENT =0");

        } catch (SQLException ex) {
            System.err.println("Error al eliminar usuario:" + ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }

    public int cantidadPaginas() {
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        int cantidadDeBloques = 0;
        try {
            ResultSet rs = st.executeQuery("SELECT CEIL(COUNT(idUsu)/" + this.paginacion + ") AS cantidad FROM "
                    + this.getClass().getSimpleName());
            if (rs.next()) {
                cantidadDeBloques = rs.getInt("cantidad");
            }
        } catch (SQLException ex) {
            System.err.println("Error al obtener la cantidad de paginas " + ex.getLocalizedMessage());
        }
        return cantidadDeBloques;
    }

    public Boolean validar() {
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try {

            String nombreRol = null; // Inicializa la variable donde se guardará el resultado de la consulta
            String nombreUsuario = null; // Inicializa la variable donde se guardará el resultado de la consulta
            String correoUsuario = null; // Inicializa la variable donde se guardará el resultado de la consulta

            String consulta = "SELECT * FROM Usuario "
                    + "JOIN Rol ON Usuario.idRolF = Rol.idRol WHERE usuario.usuario='" + getUsuario() + "' "
                    + "AND Usuario.contraseña = '" + getContraseña() + "'";

            System.out.println(consulta + "antas de ejecutar");

            ResultSet rs = st.executeQuery(consulta);

            // Verifica si existe un rol Administrador o EncargadoAlmacen en el resultado de la consulta
            while (rs.next()) {
                nombreUsuario = rs.getString("nombreUsu");
                correoUsuario = rs.getString("correoUsu");
                nombreRol = rs.getString("nombreRol");
                System.out.println("nombre rol en el modelo usuario " + nombreRol);
                System.out.println("nombre usuario en el modelo usuario " + nombreUsuario);
                System.out.println("correo en el modelo usuario " + correoUsuario);
                if ("Administrador".equals(nombreRol)) {
                    // Rol válido encontrado, puedes realizar las acciones correspondientes
                    System.out.println("Usuario tiene el rol: " + nombreRol);
                    return true;
                } else if ("EncargadoAlmacen".equals(nombreRol)) {
                    System.out.println("Usuario tiene el rol: " + nombreRol);
                    return false;
                }
            }

            if (nombreRol == null || (!"Administrador".equals(nombreRol) && !"EncargadoAlmacen".equals(nombreRol))) {
                // Usuario no tiene el rol necesario, toma las acciones correspondientes
                System.out.println("Usuario no tiene el rol necesario.");
            }

        } catch (SQLException e) {
            System.err.println("Error en funcion validar" + e.getMessage());  // Maneja las excepciones según tus necesidades
        } finally {
            conexion.desconectar(); // Cierra la conexión y los recursos (Statement, ResultSet) aquí si es necesario
        }
        return null;
    }

    public void obtenerUsuarioPorCredenciales(String usuario, String contraseña) {
        System.out.println("usuario y contraseña obtenerUsuarioPorCredenciales " + getUsuario() + getContraseña());
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        Conexion conexionBD = new Conexion();

        try {
            String consulta = "SELECT * FROM usuario JOIN Rol ON Usuario.idRolF = Rol.idRol WHERE Usuario.usuario = '" + getUsuario() + "' AND contraseña ='" + getContraseña() + "'  ";
            ResultSet rs = st.executeQuery(consulta);

            if (rs.next()) {
                // Crear un objeto Usuario y establecer sus atributos
                setIdUsu(BigInteger.valueOf(rs.getLong("idUsu")));
                setNombreUsu(rs.getString("nombreUsu"));
                setTipoDocUsu(rs.getString("tipoDocUsu"));
                setCelUsu(BigInteger.valueOf(rs.getLong("celUsu")));
                setCorreoUsu(rs.getString("correoUsu"));

                Rol rol = new Rol();
                rol.setIdRol(BigInteger.valueOf(rs.getLong("idRolF")));
                rol.setNombreRol(rs.getString("nombreRol"));
                setIdRolF(rol);

                setUsuario(rs.getString("usuario"));
                setContraseña(rs.getString("contraseña"));

            } else {
                System.out.println("No se encuentra usuario por credenciales");
            }
        } catch (SQLException e) {
            System.err.println("Error en obtener usuario por credenciales " + e.getLocalizedMessage());
        } finally {
            // Cerrar recursos (ResultSet, PreparedStatement, Connection, etc.)
            conexionBD.desconectar();
        }

    }

    private BigInteger BigInteger(long aLong) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
