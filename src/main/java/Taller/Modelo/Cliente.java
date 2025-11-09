package Taller.Modelo;

public class Cliente {
    private final int idCliente;
    private final String nombre;
    private final int dni;
    private final String correo_electronico;
    private final int telefono;

    public Cliente(int idCliente, String nombre, int dni, int telefono, String correo_electronico) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.correo_electronico = correo_electronico;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDni() {
        return dni;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public int getTelefono() {
        return telefono;
    }
}
