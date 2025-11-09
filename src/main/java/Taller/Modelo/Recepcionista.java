package Taller.Modelo;

public class Recepcionista extends Empleado {
    private final int idRecepcionista;

    public Recepcionista(int legajo, String contraseña, String tipo, String nombre, String apellido, double costoHora, int idRecepcionista) {
        super(legajo, contraseña, tipo, nombre, apellido, costoHora);
        this.idRecepcionista = idRecepcionista;
    }
}
