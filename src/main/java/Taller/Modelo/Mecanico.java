package Taller.Modelo;

public class Mecanico extends Empleado{
    private int idMecanico;

    public Mecanico(int legajo, String contraseña, String tipo, String nombre, String apellido, double costoHora, int idMecanico) {
        super(legajo, contraseña, tipo, nombre, apellido, costoHora);
        this.idMecanico = idMecanico;
    }

    @Override
    public int getLegajo() {
        return idMecanico;
    }
}
