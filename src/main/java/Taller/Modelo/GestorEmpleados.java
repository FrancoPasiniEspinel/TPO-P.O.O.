package Taller.Modelo;

import java.sql.*;

public class GestorEmpleados {

    private static final String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=TallerMecanico;encrypt=true;trustServerCertificate=true;";
    private static final String user = "taller_user";
    private static final String password = "12345678";

    public GestorEmpleados() {
    }

    public Empleado validarCredenciales(String legajo, String contraseña) {
        try (Connection connection = DriverManager.getConnection(connectionUrl, user, password)) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Empleado WHERE legajo = ? AND contraseña = ?");
            ps.setString(1, legajo);
            ps.setString(2, contraseña);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Empleado(
                            rs.getInt("legajo"),
                            rs.getString("contraseña"),
                            rs.getString("tipo"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getDouble("costoHora")
                    );
                } else {
                    return null;
                }
            } catch (SQLException ex) {
                System.err.println("Error en ejecucion de la sentencia SQL: " + ex.getMessage());
                return null;
            }

        } catch (SQLException e) {
            System.err.println("Error conectando a la BDD: " + e.getMessage());
            return null;
        }
    }
}
