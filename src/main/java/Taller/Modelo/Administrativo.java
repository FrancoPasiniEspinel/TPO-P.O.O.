package Taller.Modelo;

public class Administrativo extends Empleado {
    private final int idAdministrativo;

    public Administrativo(int legajo, String contraseña, String tipo, String nombre, String apellido, double costoHora, int idAdministrativo) {
        super(legajo, contraseña, tipo, nombre, apellido, costoHora);
        this.idAdministrativo = idAdministrativo;
    }

    @Override
    public int getLegajo() {
        return idAdministrativo;
    }
}
