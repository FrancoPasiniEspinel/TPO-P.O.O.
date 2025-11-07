import java.util.List;
public class OrdenDeTrabajo {

    private int idTurno;
    private EstadoTurno estado;
    private FechaServicio fechaServicio;
    private Vehiculo vehiculo;
    private Mecanico mecanicoAsignado;
    private String informeTecnico;
    private String diagnostico;
    private List<ItemRepuesto> repuestosUtilizados;
}
