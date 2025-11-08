package Taller.Modelo;

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

    public OrdenDeTrabajo(int idTurno, EstadoTurno estado, FechaServicio fechaServicio, Vehiculo vehiculo, Mecanico mecanicoAsignado, String informeTecnico, String diagnostico, List<ItemRepuesto> repuestosUtilizados) {
        this.idTurno = idTurno;
        this.estado = estado;
        this.fechaServicio = fechaServicio;
        this.vehiculo = vehiculo;
        this.mecanicoAsignado = mecanicoAsignado;
        this.informeTecnico = informeTecnico;
        this.diagnostico = diagnostico;
        this.repuestosUtilizados = repuestosUtilizados;
    }

    public int getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(int idTurno) {
        this.idTurno = idTurno;
    }

    public EstadoTurno getEstado() {
        return estado;
    }

    public void setEstado(EstadoTurno estado) {
        this.estado = estado;
    }

    public FechaServicio getFechaServicio() {
        return fechaServicio;
    }

    public void setFechaServicio(FechaServicio fechaServicio) {
        this.fechaServicio = fechaServicio;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Mecanico getMecanicoAsignado() {
        return mecanicoAsignado;
    }

    public void setMecanicoAsignado(Mecanico mecanicoAsignado) {
        this.mecanicoAsignado = mecanicoAsignado;
    }

    public String getInformeTecnico() {
        return informeTecnico;
    }

    public void setInformeTecnico(String informeTecnico) {
        this.informeTecnico = informeTecnico;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public List<ItemRepuesto> getRepuestosUtilizados() {
        return repuestosUtilizados;
    }

    public void setRepuestosUtilizados(List<ItemRepuesto> repuestosUtilizados) {
        this.repuestosUtilizados = repuestosUtilizados;
    }
}
