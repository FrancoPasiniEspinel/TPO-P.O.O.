package Taller.Vistas;

import java.util.List;
import Taller.Modelo.OrdenDeTrabajo;
import Taller.Modelo.Repuesto;


public class vistaMecanico {

    public class VistaMecanico {


        public void verOrdenAsignada(OrdenDeTrabajo orden) {

            System.out.println("-> VistaMecanico: Mostrando detalles de la orden asignada: " + orden.getIdTurno());
        }

        public boolean registrarDiagnostico(OrdenDeTrabajo orden, String descripcion, List<Repuesto> listaRepuestos) {
            System.out.println("-> VistaMecanico: Registrando diagnóstico para orden " + orden.getIdTurno());
            System.out.println("   - Descripción: " + descripcion);
            return true;
        }

        public boolean marcarOrdenEnEspera(int idOrden) {
            System.out.println("-> VistaMecanico: Marcando orden " + idOrden + " como 'En Espera'.");
            return true;
        }

        public boolean registrarRepuestosUsados(OrdenDeTrabajo orden, List<Repuesto> repuestos) {
            System.out.println("-> VistaMecanico: Registrando repuestos usados en la orden " + orden.getIdTurno());
            return true;
        }

        public boolean finalizarReparacion(int idOrden) {
            System.out.println("-> VistaMecanico: Finalizando la reparación de la orden: " + idOrden);
            return true;
        }

        public boolean registrarHorasTrabajo(int idOrden, double horas) {
            System.out.println("-> VistaMecanico: Registrando " + horas + " horas trabajadas en orden " + idOrden);
            return true;
        }

        public boolean agregarObservaciones(int idOrden, String texto) {
            System.out.println("-> VistaMecanico: Agregando observación a la orden " + idOrden + ": " + texto);
            return true;
        }
    }
}
