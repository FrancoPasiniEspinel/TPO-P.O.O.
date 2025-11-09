package Taller.Vistas;

import Taller.Controlador.ControladorOrdenes;
import Taller.Modelo.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class VistaMecanico extends JFrame {

    private final ControladorOrdenes controladorOrdenes;
    private final OrdenDeTrabajo ordenAsignada;

    private JTextArea txtInfoOrden;
    private JTextArea txtDiagnostico;
    private JTextArea txtDetalleTecnico;
    private JTextField txtHoras;
    private JTable tblRepuestos;
    private JTextField txtNombreRepuesto;
    private JTextField txtCantidad;
    private DefaultTableModel modeloRepuestos;
    private JLabel lblHorasTotales; // NUEVO

    // Botones para deshabilitar si no hay orden
    private JButton btnRegistrarHoras;
    private JButton btnGuardarDetalle;
    private JButton btnAgregarRepuesto;
    private JButton btnFinalizar;

    public VistaMecanico(Empleado mecanico, ControladorOrdenes controladorOrdenes) {
        super("Módulo Mecánico - Orden Asignada");
        this.controladorOrdenes = controladorOrdenes;
        this.ordenAsignada = controladorOrdenes.obtenerOrdenMecanico(mecanico.getLegajo());

        inicializarComponentes();
        mostrarDatosOrden();

        this.setSize(950, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    // -------------------------------------------------------------------------------------
    // ESTRUCTURA PRINCIPAL
    // -------------------------------------------------------------------------------------
    private void inicializarComponentes() {
        JPanel contenido = new JPanel(new BorderLayout(10, 10));
        contenido.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel superior: información general de la orden
        txtInfoOrden = new JTextArea(6, 60);
        txtInfoOrden.setEditable(false);
        txtInfoOrden.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtInfoOrden.setBorder(BorderFactory.createTitledBorder("Información de la Orden"));
        contenido.add(new JScrollPane(txtInfoOrden), BorderLayout.NORTH);

        // Panel central: pestañas
        JTabbedPane pestañas = new JTabbedPane();
        pestañas.addTab("Diagnóstico, Horas y Detalles", crearPanelDiagnosticoHorasDetalles());
        pestañas.addTab("Repuestos Usados", crearPanelRepuestos());
        contenido.add(pestañas, BorderLayout.CENTER);

        // Panel inferior: acciones
        contenido.add(crearPanelAccionesFinales(), BorderLayout.SOUTH);

        this.add(contenido);

        // Si no hay orden, deshabilitar funciones
        if (ordenAsignada == null) {
            deshabilitarFuncionesPorFaltaDeOrden();
        }
    }

    // -------------------------------------------------------------------------------------
    // PESTAÑA 1: Diagnóstico, Horas y Detalle Técnico
    // -------------------------------------------------------------------------------------
    private JPanel crearPanelDiagnosticoHorasDetalles() {
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));

        // --- Diagnóstico ---
        JPanel pnlDiag = new JPanel(new BorderLayout());
        pnlDiag.setBorder(BorderFactory.createTitledBorder("Diagnóstico General"));
        txtDiagnostico = new JTextArea(5, 60);
        txtDiagnostico.setEditable(false);
        pnlDiag.add(new JScrollPane(txtDiagnostico), BorderLayout.CENTER);
        panel.add(pnlDiag);

        // --- Horas trabajadas ---
        JPanel pnlHoras = new JPanel();
        pnlHoras.setLayout(new BoxLayout(pnlHoras, BoxLayout.Y_AXIS));
        pnlHoras.setBorder(BorderFactory.createTitledBorder("Registro de Horas de Trabajo"));

        JPanel filaHoras = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        txtHoras = new JTextField(5);
        btnRegistrarHoras = new JButton("Registrar Horas");
        btnRegistrarHoras.addActionListener(this::accionRegistrarHoras);
        filaHoras.add(new JLabel("Horas trabajadas:"));
        filaHoras.add(txtHoras);
        filaHoras.add(btnRegistrarHoras);
        pnlHoras.add(filaHoras);

        // NUEVO: mostrar total de horas acumuladas
        lblHorasTotales = new JLabel();
        actualizarHorasTotales();
        JPanel filaTotal = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        filaTotal.add(lblHorasTotales);
        pnlHoras.add(filaTotal);

        panel.add(pnlHoras);

        // --- Detalle Técnico ---
        JPanel pnlDetalle = new JPanel(new BorderLayout());
        pnlDetalle.setBorder(BorderFactory.createTitledBorder("Detalles Técnicos / Observaciones"));
        txtDetalleTecnico = new JTextArea(4, 60);
        pnlDetalle.add(new JScrollPane(txtDetalleTecnico), BorderLayout.CENTER);
        btnGuardarDetalle = new JButton("Guardar Detalle Técnico");
        btnGuardarDetalle.addActionListener(this::accionGuardarDetalleTecnico);
        pnlDetalle.add(btnGuardarDetalle, BorderLayout.SOUTH);
        panel.add(pnlDetalle);

        return panel;
    }

    // -------------------------------------------------------------------------------------
    // NUEVO MÉTODO: actualizar la etiqueta de horas
    // -------------------------------------------------------------------------------------
    private void actualizarHorasTotales() {
        if (ordenAsignada != null) {
            lblHorasTotales.setText("Total acumulado: " + ordenAsignada.getHorasTrabajo() + " horas");
        } else {
            lblHorasTotales.setText("Total acumulado: 0 horas");
        }
    }

    // -------------------------------------------------------------------------------------
    // PESTAÑA 2: Registro de Repuestos
    // -------------------------------------------------------------------------------------
    private JPanel crearPanelRepuestos() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        String[] columnas = {"Nombre Repuesto", "Cantidad"};
        modeloRepuestos = new DefaultTableModel(columnas, 0);
        tblRepuestos = new JTable(modeloRepuestos);
        panel.add(new JScrollPane(tblRepuestos), BorderLayout.CENTER);

        JPanel pnlCarga = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        pnlCarga.setBorder(BorderFactory.createTitledBorder("Agregar Repuesto Usado"));
        txtNombreRepuesto = new JTextField(15);
        txtCantidad = new JTextField(5);
        btnAgregarRepuesto = new JButton("Agregar");
        btnAgregarRepuesto.addActionListener(this::accionAgregarRepuesto);
        pnlCarga.add(new JLabel("Nombre:"));
        pnlCarga.add(txtNombreRepuesto);
        pnlCarga.add(new JLabel("Cantidad:"));
        pnlCarga.add(txtCantidad);
        pnlCarga.add(btnAgregarRepuesto);
        panel.add(pnlCarga, BorderLayout.NORTH);

        return panel;
    }

    // -------------------------------------------------------------------------------------
    // PANEL DE ACCIONES INFERIOR
    // -------------------------------------------------------------------------------------
    private JPanel crearPanelAccionesFinales() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnFinalizar = new JButton("Finalizar Reparación");
        btnFinalizar.setBackground(new Color(60, 170, 60));
        btnFinalizar.addActionListener(this::accionFinalizar);

        JButton btnCerrar = new JButton("Cerrar Ventana");
        btnCerrar.addActionListener(e -> dispose());

        panel.add(btnFinalizar);
        panel.add(btnCerrar);
        return panel;
    }

    // -------------------------------------------------------------------------------------
    // ACCIONES
    // -------------------------------------------------------------------------------------

    private void accionRegistrarHoras(ActionEvent e) {
        try {
            int horas = Integer.parseInt(txtHoras.getText());
            ordenAsignada.setHorasTrabajo(ordenAsignada.getHorasTrabajo() + horas);
            boolean respuesta = controladorOrdenes.actualizarHorasOrden(
                    ordenAsignada.getIdOrdenDeTrabajo(),
                    ordenAsignada.getHorasTrabajo()
            );
            if (!respuesta) {
                JOptionPane.showMessageDialog(this,
                        "Actualización de horas fallida.",
                        "Error",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            actualizarHorasTotales(); // 🔁 actualiza el label
            JOptionPane.showMessageDialog(this, "Horas registradas correctamente.");
            txtHoras.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un número válido para las horas trabajadas.");
        }
    }

    private void accionGuardarDetalleTecnico(ActionEvent e) {
        String detalle = txtDetalleTecnico.getText().trim();
        if (detalle.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Debe ingresar algún detalle técnico antes de guardar.",
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean respuesta = controladorOrdenes.modificarInformeTecnico(ordenAsignada.getIdOrdenDeTrabajo(), detalle);
        if (!respuesta){
            JOptionPane.showMessageDialog(this,
                    "Guardado de detalle técnico fallido.",
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
        } else{
            JOptionPane.showMessageDialog(this, "Detalle técnico guardado correctamente.");
        }
    }

    private void accionAgregarRepuesto(ActionEvent e) {
        String nombre = txtNombreRepuesto.getText().trim();
        String cantidadStr = txtCantidad.getText().trim();

        if (nombre.isEmpty() || cantidadStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete ambos campos para agregar un repuesto.");
            return;
        }

        try {
            int cantidad = Integer.parseInt(cantidadStr);
            modeloRepuestos.addRow(new Object[]{nombre, cantidad});
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "La cantidad debe ser numérica.");
        }
    }

    private void accionFinalizar(ActionEvent e) {
        ordenAsignada.setEstado("Reparado");
        JOptionPane.showMessageDialog(this, "La reparación ha sido marcada como FINALIZADA.");
        mostrarDatosOrden();
    }

    // MÉTODO: deshabilitar si no hay orden asignada
    private void deshabilitarFuncionesPorFaltaDeOrden() {
        JOptionPane.showMessageDialog(this, "No hay ninguna orden de trabajo asignada actualmente.");
        txtDiagnostico.setText("(sin orden asignada)");
        txtDetalleTecnico.setText("(sin orden asignada)");
        txtHoras.setEnabled(false);
        txtNombreRepuesto.setEnabled(false);
        txtCantidad.setEnabled(false);

        btnRegistrarHoras.setEnabled(false);
        btnGuardarDetalle.setEnabled(false);
        btnAgregarRepuesto.setEnabled(false);
        btnFinalizar.setEnabled(false);
    }

    // -------------------------------------------------------------------------------------
    // MOSTRAR DATOS DE LA ORDEN
    // -------------------------------------------------------------------------------------
    private void mostrarDatosOrden() {
        if (ordenAsignada == null) {
            txtInfoOrden.setText("No hay ninguna orden de trabajo asignada.");
            return;
        }

        Cliente c = ordenAsignada.getClienteAsignado();
        Vehiculo v = ordenAsignada.getVehiculo();

        StringBuilder sb = new StringBuilder();
        sb.append("ORDEN DE TRABAJO #").append(ordenAsignada.getIdOrdenDeTrabajo()).append("\n");
        sb.append("Estado: ").append(ordenAsignada.getEstado()).append("\n");
        sb.append("Fecha creación: ").append(ordenAsignada.getFechaCreacion()).append("\n\n");

        sb.append("CLIENTE:\n");
        sb.append("DNI: ").append(c.dni()).append("\n");
        sb.append("Nombre: ").append(c.nombre()).append("\n");
        sb.append("Teléfono: ").append(c.telefono()).append("\n\n");

        sb.append("VEHÍCULO:\n");
        sb.append("Patente: ").append(v.getPatente()).append("\n");
        sb.append("Marca: ").append(v.getMarca()).append("\n");
        sb.append("Modelo: ").append(v.getModelo()).append("\n");
        sb.append("Año: ").append(v.getAñoFabricacion());

        txtInfoOrden.setText(sb.toString());

        // mostrar diagnóstico y detalle técnico en sus cuadros
        txtDiagnostico.setText(ordenAsignada.getDiagnostico() == null ? "" : ordenAsignada.getDiagnostico());
        txtDetalleTecnico.setText(ordenAsignada.getInformeTecnico() == null ? "" : ordenAsignada.getInformeTecnico());
        actualizarHorasTotales(); // actualiza también al cargar
    }
}
