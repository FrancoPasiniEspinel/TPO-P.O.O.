package Taller.Modelo;

import java.util.Date;

public abstract class Vehiculo {
    private String patente;
    private String marca;
    private String modelo;
    private int añoFabricacion;
    private Date ultimoServio;
    private TipoCaja tipoCaja;
    private int numeroMotor;
    private int kilometraje;
    private int numeroChasis;
    private TipoCombustible tipoCombustible;

    public Vehiculo(String patente, String marca, String modelo, int añoFabricacion, Date ultimoServio, TipoCaja tipoCaja, int numeroMotor, int kilometraje, int numeroChasis, TipoCombustible tipoCombustible) {
        this.patente = patente;
        this.marca = marca;
        this.modelo = modelo;
        this.añoFabricacion = añoFabricacion;
        this.ultimoServio = ultimoServio;
        this.tipoCaja = tipoCaja;
        this.numeroMotor = numeroMotor;
        this.kilometraje = kilometraje;
        this.numeroChasis = numeroChasis;
        this.tipoCombustible = tipoCombustible;
    }

    public Vehiculo() {
    }
}
