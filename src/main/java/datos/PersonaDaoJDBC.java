package datos;

import domain.Persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonaDaoJDBC implements PersonaDAO{
    private Connection conexionTransaccional;
    private static final String SQL_SELECT = "SELECT idpersona, nombre, apellido, puntos, ciudad FROM b65ihh8zrciucldqywtr.persona";
    private static final String SQL_INSERT = "INSERT INTO b65ihh8zrciucldqywtr.persona(idpersona, nombre, apellido, puntos, ciudad) VALUES(?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE b65ihh8zrciucldqywtr.persona SET nombre = ?, apellido = ?, puntos = ?, ciudad = ? WHERE idpersona = ?";
    private static final String SQL_DELETE = "DELETE FROM b65ihh8zrciucldqywtr.persona WHERE idpersona = ?";

    private static final String SQL_BUSQUEDA1 = "SELECT * FROM b65ihh8zrciucldqywtr.persona WHERE nombre = ?";
    private static final String SQL_BUSQUEDA2 = "SELECT nombre, apellido FROM b65ihh8zrciucldqywtr.persona";
    private static final String SQL_BUSQUEDA3 = "SELECT * FROM b65ihh8zrciucldqywtr.persona WHERE idpersona = ?";
    private static final String SQL_BUSQUEDA4 = "SELECT * FROM b65ihh8zrciucldqywtr.persona WHERE puntos >= ?";
    private static final String SQL_BUSQUEDA5 = "SELECT * FROM b65ihh8zrciucldqywtr.persona WHERE puntos <= ?";
    private static final String SQL_BUSQUEDA6 = "SELECT nombre, apellido FROM b65ihh8zrciucldqywtr.persona WHERE ciudad = ?";

    public PersonaDaoJDBC(){

    }

    public PersonaDaoJDBC(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    public List<Persona> seleccionar() throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        Persona persona;
        List<Persona> personas = new ArrayList<>();
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            st = conn.prepareStatement(SQL_SELECT);
            rs = st.executeQuery();

            while (rs.next()){
                int idPersona = rs.getInt("idpersona");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                int puntos = rs.getInt("puntos");
                String ciudad = rs.getString("ciudad");

                persona = new Persona(idPersona, nombre, apellido, puntos, ciudad);
                personas.add(persona);
            }
        }finally {
            try {
                assert rs != null;
                Conexion.close(rs);
                Conexion.close(st);
                if(this.conexionTransaccional == null){
                    Conexion.close(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return personas;
    }

    public int insertar(Persona persona) throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        int registros = 0;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            st = conn.prepareStatement(SQL_INSERT);
            st.setInt(1, persona.getIdPersona());
            st.setString(2, persona.getNombre());
            st.setString(3, persona.getApellido());
            st.setInt(4, persona.getPuntos());
            st.setString(5, persona.getCiudad());
            registros = st.executeUpdate();
        } finally {
            try {
                assert st != null;
                Conexion.close(st);
                if(this.conexionTransaccional == null){
                    Conexion.close(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return registros;
    }

    public int actualizar(Persona persona) throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        int registros = 0;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            st = conn.prepareStatement(SQL_UPDATE);
            st.setString(1, persona.getNombre());
            st.setString(2, persona.getApellido());
            st.setInt(3, persona.getPuntos());
            st.setString(4, persona.getCiudad());
            st.setInt(5, persona.getIdPersona());
            registros = st.executeUpdate();
        }finally {
            try {
                assert st != null;
                Conexion.close(st);
                if(this.conexionTransaccional == null){
                    Conexion.close(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return registros;
    }

    public int eliminar(Persona persona) throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        int registros = 0;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            st = conn.prepareStatement(SQL_DELETE);
            st.setInt(1, persona.getIdPersona());
            registros = st.executeUpdate();
        } finally {
            try {
                assert st != null;
                Conexion.close(st);
                if(this.conexionTransaccional == null){
                    Conexion.close(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return registros;
    }

    public List<Persona> buscarNombre(Persona persona) throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Persona> personas = new ArrayList<>();
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            st = conn.prepareStatement(SQL_BUSQUEDA1);
            st.setString(1, persona.getNombre());
            rs = st.executeQuery();

            while (rs.next()){
                int idPersona = rs.getInt("idpersona");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                int puntos = rs.getInt("puntos");
                String ciudad = rs.getString("ciudad");

                persona = new Persona(idPersona, nombre, apellido, puntos, ciudad);
                personas.add(persona);
            }
        }finally {
            try {
                assert rs != null;
                Conexion.close(rs);
                Conexion.close(st);
                if(this.conexionTransaccional == null){
                    Conexion.close(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return personas;
    }

    public List<Persona> seleccionarNombre() throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        Persona persona;
        List<Persona> personas = new ArrayList<>();
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            st = conn.prepareStatement(SQL_BUSQUEDA2);
            rs = st.executeQuery();

            while (rs.next()){
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");

                persona = new Persona(nombre, apellido);
                personas.add(persona);
            }
        }finally {
            try {
                assert rs != null;
                Conexion.close(rs);
                Conexion.close(st);
                if(this.conexionTransaccional == null){
                    Conexion.close(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return personas;
    }

    public Persona buscarId(Persona persona) throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            st = conn.prepareStatement(SQL_BUSQUEDA3);
            st.setInt(1, persona.getIdPersona());
            rs = st.executeQuery();

            while (rs.next()){
                int idPersona = rs.getInt("idpersona");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                int puntos = rs.getInt("puntos");
                String ciudad = rs.getString("ciudad");

                persona = new Persona(idPersona, nombre, apellido, puntos, ciudad);
            }
        }finally {
            try {
                assert rs != null;
                Conexion.close(rs);
                Conexion.close(st);
                if(this.conexionTransaccional == null){
                    Conexion.close(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return persona;
    }

    public List<Persona> buscarPuntosMayor(int punto) throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        Persona persona;
        List<Persona> personas = new ArrayList<>();
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            st = conn.prepareStatement(SQL_BUSQUEDA4);
            st.setInt(1, punto);
            rs = st.executeQuery();

            while (rs.next()){
                int idPersona = rs.getInt("idpersona");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                int puntos = rs.getInt("puntos");
                String ciudad = rs.getString("ciudad");

                persona = new Persona(idPersona, nombre, apellido, puntos, ciudad);
                personas.add(persona);
            }
        }finally {
            try {
                assert rs != null;
                Conexion.close(rs);
                Conexion.close(st);
                if(this.conexionTransaccional == null){
                    Conexion.close(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return personas;
    }

    public List<Persona> buscarPuntosMenor(int punto) throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        Persona persona;
        List<Persona> personas = new ArrayList<>();
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            st = conn.prepareStatement(SQL_BUSQUEDA5);
            st.setInt(1, punto);
            rs = st.executeQuery();

            while (rs.next()){
                int idPersona = rs.getInt("idpersona");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                int puntos = rs.getInt("puntos");
                String ciudad = rs.getString("ciudad");

                persona = new Persona(idPersona, nombre, apellido, puntos, ciudad);
                personas.add(persona);
            }
        }finally {
            try {
                assert rs != null;
                Conexion.close(rs);
                Conexion.close(st);
                if(this.conexionTransaccional == null){
                    Conexion.close(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return personas;
    }

    public List<Persona> buscarCiudad(String ciudad) throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        Persona persona;
        List<Persona> personas = new ArrayList<>();
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            st = conn.prepareStatement(SQL_BUSQUEDA6);
            st.setString(1, ciudad);
            rs = st.executeQuery();

            while (rs.next()){
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");

                persona = new Persona(nombre, apellido);
                personas.add(persona);
            }
        }finally {
            try {
                assert rs != null;
                Conexion.close(rs);
                Conexion.close(st);
                if(this.conexionTransaccional == null){
                    Conexion.close(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return personas;
    }
}