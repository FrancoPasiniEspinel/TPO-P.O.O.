package Taller.Modelo;

public class Empleado {
    private final int legajo;
    private final String contraseña;
    private final String tipo;
    private final String nombre;
    private final String apellido;
    private final double costoHora;

    public Empleado(int legajo, String contraseña, String tipo, String nombre, String apellido, double costoHora) {
        this.legajo = legajo;
        this.contraseña = contraseña;
        this.tipo = tipo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.costoHora = costoHora;
    }

    public int getLegajo() {
        return legajo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public double getCostoHora() {
        return costoHora;
    }
}
