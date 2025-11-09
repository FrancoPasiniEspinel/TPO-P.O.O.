package Taller.Controlador;

import Taller.Modelo.Empleado;
import Taller.Vistas.VistaAdministrativo;
import Taller.Vistas.VistaLogin;
import Taller.Vistas.VistaMecanico;
import Taller.Vistas.VistaRecepcionista;

import javax.swing.*;

public class ControladorMaestro {
    // Vistas
    private JFrame vistaAdministrativo;
    private JFrame vistaMecanico;
    private JFrame vistaRecepcionista;
    private JFrame vistaLogin;

    // Controladores
    private ControladorLogin controladorLogin;
    private ControladorOrdenes controladorOrdenes;

    public void iniciar() {
        controladorLogin = new ControladorLogin(this);
        this.vistaLogin = new VistaLogin(controladorLogin);
        vistaLogin.setVisible(true);
    }

    public void cambiarVentana(Empleado empleado) {
        vistaLogin.setVisible(false);

        switch (empleado.getTipo()) {
            case "Mecanico":
                controladorOrdenes = new ControladorOrdenes(this);
                vistaMecanico = new VistaMecanico(empleado, controladorOrdenes);
                vistaMecanico.setVisible(true);
                break;
            case "Administrativo":
                vistaAdministrativo = new VistaAdministrativo();
                vistaAdministrativo.setVisible(true);
                break;
            case "Recepcionista":
                controladorOrdenes = new ControladorOrdenes(this);
                vistaRecepcionista = new VistaRecepcionista(controladorOrdenes);
                vistaRecepcionista.setVisible(true);
                break;
        }
    }
}
