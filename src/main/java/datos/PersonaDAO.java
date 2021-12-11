package datos;

import domain.Persona;

import java.sql.SQLException;
import java.util.List;

public interface PersonaDAO {

    public List<Persona> seleccionar() throws SQLException;

    public List<Persona> buscarNombre(Persona persona) throws SQLException;

    public List<Persona> seleccionarNombre() throws SQLException;

    public Persona buscarId(Persona persona) throws SQLException;

    public List<Persona> buscarPuntosMayor(int puntos) throws SQLException;

    public List<Persona> buscarPuntosMenor(int puntos) throws SQLException;

    public List<Persona> buscarCiudad(String ciudad) throws SQLException;

    public int insertar(Persona persona) throws SQLException;

    public int actualizar(Persona persona) throws SQLException;

    public int eliminar(Persona persona) throws SQLException;
}