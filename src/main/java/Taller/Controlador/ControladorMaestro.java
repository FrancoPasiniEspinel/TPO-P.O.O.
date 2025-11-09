package Taller.Controlador;

import Taller.Modelo.Empleado;
import Taller.Vistas.VistaRecepcionista;
import Taller.Vistas.VistaAdministrativo;
import Taller.Vistas.VistaMecanico;
import Taller.Vistas.VistaLogin;

import javax.swing.*;

public class ControladorMaestro {
    // Vistas
    private JFrame vistaAdministrativo = new VistaAdministrativo();
    private JFrame vistaMecanico = new VistaMecanico();
    private JFrame vistaRecepcionista = new VistaRecepcionista();
    private JFrame vistaLogin;

    // Controladores
    ControladorLogin controladorLogin;

    public void iniciar() {
        controladorLogin = new ControladorLogin(this);
        this.vistaLogin = new VistaLogin(controladorLogin);
        vistaLogin.setVisible(true);
    }

    public void cambiarVentana(Empleado empleado) {
        vistaLogin.setVisible(false);

        switch (empleado.getTipo()){
            case "Mecanico":
                vistaMecanico = new VistaMecanico();
                vistaMecanico.setVisible(true);
                break;
            case "Administrativo":
                vistaAdministrativo = new VistaAdministrativo();
                vistaAdministrativo.setVisible(true);
                break;
            case "Recepcionista":
                vistaRecepcionista = new VistaRecepcionista();
                vistaRecepcionista.setVisible(true);
                break;
        }
    }
}
