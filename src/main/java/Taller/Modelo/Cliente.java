package Taller.Modelo;

public class Cliente {
    private int idCliente;
    private String nombre;
    private int dni;
    private String correo_electronico;
    private int telefono;

    public Cliente(int idCliente, String nombre, int dni, int telefono, String correo_electronico) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.correo_electronico = correo_electronico;
    }
}
