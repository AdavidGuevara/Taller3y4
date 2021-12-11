package test;

import datos.Conexion;
import datos.PersonaDAO;
import datos.PersonaDaoJDBC;
import domain.Persona;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TestManejoPersona {
    public static void main(String[] args) {

        Connection conexion = null;
        try {
            conexion = Conexion.getConnection();
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            PersonaDAO personaDao = new PersonaDaoJDBC(conexion);

            Persona persona1 = new Persona("Andres");
            List<Persona> personas = personaDao.buscarNombre(persona1);

            // Buscando todos las personas con el nombre "Andres".
            System.out.println("Buscando todos las personas con el nombre \"Andres\":");
            for (Persona persona: personas) {
                System.out.println(persona);
            }
            System.out.println(" ");

            // Listando toda base de datos solo con los nombres y apellidos.
            List<Persona> personas1 = personaDao.seleccionarNombre();
            System.out.println("Listando toda base de datos solo con los nombres y apellidos");
            for (Persona persona: personas1) {
                System.out.println(persona);
            }
            System.out.println(" ");

            // Buscando la persona con idpersona = 43.
            System.out.println("Buscando la persona con idpersona = 43:");
            Persona persona2 = new Persona(43);
            Persona personaId = personaDao.buscarId(persona2);
            System.out.println(personaId);
            System.out.println(" ");

            // Buscando las personas con puntaje mayor o igual a 50.
            System.out.println("Buscando las personas con puntaje mayor o igual a 50:");
            List<Persona> personasMayor = personaDao.buscarPuntosMayor(50);
            for (Persona persona: personasMayor) {
                System.out.println(persona);
            }
            System.out.println(" ");

            // Buscando las personas con puntaje menor o igual a 49.
            System.out.println("Buscando las personas con puntaje menor o igual a 49");
            List<Persona> personasMenor = personaDao.buscarPuntosMenor(49);
            for (Persona persona: personasMenor) {
                System.out.println(persona);
            }
            System.out.println(" ");

            // Buscando las personas que viven en la ciudad de barranquilla.
            System.out.println("Buscando las personas que viven en la ciudad de Barranquilla:");
            List<Persona> personasCiudad = personaDao.buscarCiudad("Barranquilla");
            for (Persona persona: personasCiudad) {
                System.out.println(persona);
            }
            System.out.println(" ");

            conexion.commit();
            System.out.println("Se ha hecho commit de la transacion");
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            System.out.println(" ");
            System.out.println("Entramos en rollback");
            try {
                assert conexion != null;
                conexion.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
    }
}
