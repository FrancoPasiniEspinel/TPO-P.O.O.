package Taller.Controlador;

import Taller.Modelo.GestorOrdenes;
import Taller.Modelo.OrdenDeTrabajo;

public class ControladorOrdenes {
    private final ControladorMaestro controladorMaestro;
    private final GestorOrdenes gestorOrdenes;

    public ControladorOrdenes(ControladorMaestro controladorMaestro) {
        this.controladorMaestro = controladorMaestro;
        this.gestorOrdenes = new GestorOrdenes();
    }


    public String generarOrden(int dni, String nombre, int telefono, String patente, String marca, String modelo, int añoFabricacion, String descripcion) {
        return gestorOrdenes.generarOrden(dni, nombre, telefono, patente, marca, modelo, añoFabricacion, descripcion);
    }

    public OrdenDeTrabajo buscarOrdenPorPatente(String patente) {
        return gestorOrdenes.buscarOrdenPorPatente(patente);
    }

    public boolean registrarEntregaVehiculo(int idOrdenDeTrabajo) {
        return gestorOrdenes.registrarEntregaVehiculo(idOrdenDeTrabajo);
    }

    public OrdenDeTrabajo obtenerOrdenMecanico(int legajo) {
        OrdenDeTrabajo ordenAsignada = gestorOrdenes.obtenerOrdenMecanico(legajo);
        if (ordenAsignada == null){
            ordenAsignada = gestorOrdenes.asignarOrdenPrioritaria(legajo);
        }
        return ordenAsignada;
    }

    public boolean modificarInformeTecnico(int idOrdenDeTrabajo, String detalle) {
        return gestorOrdenes.modificarInformeTecnico(idOrdenDeTrabajo, detalle);
    }

    public boolean actualizarHorasOrden(int idOrdenDeTrabajo, int horasTrabajo) {
        return gestorOrdenes.actualizarHorasOrden(idOrdenDeTrabajo, horasTrabajo);
    }
}
