package Taller.Controlador;

import Taller.Modelo.Empleado;
import Taller.Modelo.GestorEmpleados;

public class ControladorLogin {

    private final ControladorMaestro controladorMaestro;
    private final GestorEmpleados gestorEmpleados;

    public ControladorLogin(ControladorMaestro controladorMaestro) {
        this.controladorMaestro = controladorMaestro;
        this.gestorEmpleados = new GestorEmpleados();
    }

    public boolean validarCredenciales(String legajo, String password) {
        Empleado respuesta = gestorEmpleados.validarCredenciales(legajo, password);

        if (!(respuesta == null)) {
            controladorMaestro.cambiarVentana(respuesta);
            return true;
        }
        return false;
    }
}
