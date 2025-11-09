package Taller.Modelo;

public class Repuesto {
    private final String codigo;
    private final String nombre;
    private final double precio_unitario;
    private final int stock;

    public Repuesto(String codigo, String nombre, double precio_unitario, int stock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio_unitario = precio_unitario;
        this.stock = stock;
    }
}
