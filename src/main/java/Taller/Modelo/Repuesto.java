package Taller.Modelo;

public class Repuesto {
    private String codigo;
    private String nombre;
    private double precio_unitario;
    private int stock;

    public Repuesto(String codigo, String nombre, double precio_unitario, int stock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio_unitario = precio_unitario;
        this.stock = stock;
    }
}
