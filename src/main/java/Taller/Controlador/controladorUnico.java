package Taller.Controlador;

import Taller.Vistas.vistaRecepcionista;
import Taller.Vistas.vistaAdministrativo;
import Taller.Vistas.vistaMecanico;
import Taller.Vistas.vistaGeneral;
import Taller.Gestor.gestorEmpleado;

import javax.swing.*;

public class controladorUnico {

    public static void main(String[] args) {
        // Lanza la aplicación en el hilo de eventos de Swing
        SwingUtilities.invokeLater(() -> {
            // Se crea la vista sin pasarle ningún gestor (null)
            new vistaAdministrativo().setVisible(true);
        });
    }
}
