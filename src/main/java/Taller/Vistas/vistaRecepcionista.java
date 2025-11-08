package Taller.Vistas;

import java.util.List;
import Taller.Modelo.Cliente;
import Taller.Modelo.Vehiculo;
import Taller.Modelo.OrdenDeTrabajo;

public class vistaRecepcionista {



    public class VistaRecepcionista {

        public boolean cargarCliente(Cliente cliente) {
            System.out.println("-> Recepcionista: Cliente cargado.");
            return true;
        }


        public boolean modificarOrdenReparacion(int idOrden, String nuevosDatos) {
            System.out.println("-> Recepcionista: Modificando orden " + idOrden);
            return true;
        }


        public void mostrarMenu() {
            System.out.println("-> Recepcionista: Mostrando menú principal.");
        }


        public OrdenDeTrabajo crearOrdenReparacion(Cliente cliente, Vehiculo vehiculo, String descripcion) {
            System.out.println("-> Recepcionista: Creando nueva orden de trabajo.");
            return null; // Devuelve la OrdenDeTrabajo creada
        }


        public String consultarEstadoOrden(int idOrden) {
            System.out.println("-> Recepcionista: Consultando estado de orden " + idOrden);
            return "Estado simulado";
        }


        public boolean registrarRetiroVehiculo(int idOrden) {
            System.out.println("-> Recepcionista: Registrando retiro de vehículo de la orden " + idOrden);
            return true;
        }


        public Cliente buscarClientePorDni(String dni) {
            System.out.println("-> Recepcionista: Buscando cliente con DNI: " + dni);
            return null;
        }


        public boolean registrarEntregaVehiculo(int idOrden) {
            System.out.println("-> Recepcionista: Registrando ingreso de vehículo para la orden " + idOrden);
            return true;
        }


        public List<OrdenDeTrabajo> verHistorialCliente(int idCliente) {
            System.out.println("-> Recepcionista: Mostrando historial para el cliente " + idCliente);
            return null;
        }
    }


}
