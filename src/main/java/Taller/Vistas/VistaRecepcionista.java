package Taller.Vistas;

import Taller.Modelo.Cliente;
import Taller.Modelo.Vehiculo;
import Taller.Modelo.OrdenDeTrabajo;
import javax.swing.*;
import java.awt.*;
import java.util.List;

// import Taller.Gestor.GestorOrdenes;

public class VistaRecepcionista extends JFrame {

    private final GestorOrdenes gestorOrdenes;

    // Componentes de la GUI
    private JTabbedPane tabbedPane;

    // Componentes de Búsqueda y Creación
    private JTextField txtDniBusqueda;
    private JTextField txtNombreCliente;
    private JTextField txtTelefonoCliente;
    private JTextField txtPatenteVehiculo;
    private JTextArea txtDescripcionFalla;
    private JButton btnBuscarCliente;
    private JButton btnCrearOrden;

    // Componentes de Consulta y Finalización
    private JTextField txtIdOrdenConsulta;
    private JButton btnConsultarEstado;
    private JButton btnRetiro;
    private JButton btnEntrega;
    private JButton btnVerHistorial;

    /**
     * Constructor enfocado solo en el diseño (sin inyección de dependencia para la visualización).
     */
    public VistaRecepcionista(/* Gestores deberían ir aquí */) {
        super("Módulo Recepcionista - Taller Mecánico");

        inicializarComponentes();

        this.setSize(900, 650);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        mostrarMenu();
    }

    private void inicializarComponentes() {
        tabbedPane = new JTabbedPane();

        // 1. Pestaña: Carga de Órdenes (Funcionalidades principales)
        tabbedPane.addTab("1. Carga de Cliente y Órdenes", crearPanelCargaOrden());

        // 2. Pestaña: Consultas (Estado, Historial, Retiro/Entrega)
        tabbedPane.addTab("2. Consultas y Finalización", crearPanelConsultas());

        this.add(tabbedPane);
    }

    /**
     * Implementa la funcionalidad mostrarMenu(). En una GUI, es el inicio de la ventana.
     */
    public void mostrarMenu() {
        System.out.println("Recepcionista: Menú principal activo (Ventana de Pestañas)");
    }

    // =================================================================================
    // PESTAÑA 1: CARGA DE ÓRDENES
    // =================================================================================

    private JPanel crearPanelCargaOrden() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel Norte: Búsqueda y Carga de Cliente
        JPanel pnlCliente = crearPanelCliente();
        panel.add(pnlCliente, BorderLayout.NORTH);

        // Panel Central: Datos del Vehículo y Orden
        JPanel pnlOrden = crearPanelDatosOrden();
        panel.add(pnlOrden, BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearPanelCliente() {
        JPanel pnlCliente = new JPanel(new GridBagLayout());
        pnlCliente.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY),
                "Carga y Búsqueda de Cliente"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Fila 1: Búsqueda por DNI (buscarClientePorDni)
        gbc.gridx = 0; gbc.gridy = 0;
        pnlCliente.add(new JLabel("DNI Cliente:"), gbc);

        gbc.gridx = 1; gbc.weightx = 0.5; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtDniBusqueda = new JTextField(15);
        pnlCliente.add(txtDniBusqueda, gbc);

        gbc.gridx = 2; gbc.weightx = 0.0; gbc.fill = GridBagConstraints.NONE;
        btnBuscarCliente = new JButton("Buscar Cliente"); // Mapea a buscarClientePorDni()
        // btnBuscarCliente.addActionListener(e -> buscarClientePorDni());
        pnlCliente.add(btnBuscarCliente, gbc);

        // Fila 2: Nombre
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.0; gbc.fill = GridBagConstraints.NONE;
        pnlCliente.add(new JLabel("Nombre:"), gbc);

        gbc.gridx = 1; gbc.weightx = 0.5; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtNombreCliente = new JTextField(20);
        pnlCliente.add(txtNombreCliente, gbc);

        // Fila 2: Teléfono
        gbc.gridx = 2; gbc.weightx = 0.0; gbc.fill = GridBagConstraints.NONE;
        pnlCliente.add(new JLabel("Teléfono:"), gbc);

        gbc.gridx = 3; gbc.weightx = 0.5; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtTelefonoCliente = new JTextField(15);
        pnlCliente.add(txtTelefonoCliente, gbc);

        // Botón de Carga/Modificación (mapea a cargarCliente / modificarOrdenReparacion)
        JButton btnCargarModificar = new JButton("Cargar cliente (cargarCliente)");
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 4; gbc.anchor = GridBagConstraints.CENTER;
        pnlCliente.add(btnCargarModificar, gbc);

        return pnlCliente;
    }

    private JPanel crearPanelDatosOrden() {
        JPanel pnlOrden = new JPanel(new GridBagLayout());
        pnlOrden.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY),
                "Datos del Vehículo y Orden (crearOrdenReparacion)"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fila 1: Patente y Tipo de Vehículo
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.0;
        pnlOrden.add(new JLabel("Patente Vehículo:"), gbc);

        gbc.gridx = 1; gbc.weightx = 0.5;
        txtPatenteVehiculo = new JTextField(15);
        pnlOrden.add(txtPatenteVehiculo, gbc);

        // Fila 2: Descripción de la Falla
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.0; gbc.anchor = GridBagConstraints.NORTHWEST;
        pnlOrden.add(new JLabel("Descripción Falla:"), gbc);

        gbc.gridx = 1; gbc.gridy = 1; gbc.gridwidth = 3; gbc.weightx = 1.0; gbc.gridheight = 2; gbc.fill = GridBagConstraints.BOTH;
        txtDescripcionFalla = new JTextArea(5, 20);
        JScrollPane scroll = new JScrollPane(txtDescripcionFalla);
        pnlOrden.add(scroll, gbc);

        // Fila 3: Botón de Crear Orden
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 4; gbc.gridheight = 1; gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.NONE; gbc.anchor = GridBagConstraints.CENTER;

        btnCrearOrden = new JButton("GENERAR NUEVA ORDEN DE TRABAJO"); // Mapea a crearOrdenReparacion
        btnCrearOrden.setBackground(new Color(40, 150, 40));
        btnCrearOrden.setForeground(Color.BLACK);
        // btnCrearOrden.addActionListener(e -> crearOrdenReparacion());
        pnlOrden.add(btnCrearOrden, gbc);

        return pnlOrden;
    }

    // =================================================================================
    // PESTAÑA 2: CONSULTAS Y FINALIZACIÓN
    // =================================================================================

    private JPanel crearPanelConsultas() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));

        // Panel Norte: Consultas y Acciones (Estado, Retiro, Entrega)
        JPanel pnlAcciones = crearPanelAccionesConsulta();
        panel.add(pnlAcciones, BorderLayout.NORTH);

        // Panel Central: Historial (JTable)
        JPanel pnlHistorial = crearPanelHistorial();
        panel.add(pnlHistorial, BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearPanelAccionesConsulta() {
        JPanel pnlAcciones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        pnlAcciones.setBorder(BorderFactory.createTitledBorder("Consultas y Finalización"));

        txtIdOrdenConsulta = new JTextField(8);

        // Botón 1: Consultar Estado
        btnConsultarEstado = new JButton("Consultar Estado (consultarEstadoOrden)");
        // btnConsultarEstado.addActionListener(e -> consultarEstadoOrden());

        // Botón 2: Registrar Retiro
        btnRetiro = new JButton("Registrar Retiro (registrarRetiroVehiculo)");
        btnRetiro.setBackground(new Color(250, 100, 100));
        // btnRetiro.addActionListener(e -> registrarRetiroVehiculo());

        // Botón 3: Registrar Entrega
        btnEntrega = new JButton("Registrar Entrega (registrarEntregaVehiculo)");
        btnEntrega.setBackground(new Color(100, 100, 250));
        // btnEntrega.addActionListener(e -> registrarEntregaVehiculo());


        pnlAcciones.add(new JLabel("ID Orden:"));
        pnlAcciones.add(txtIdOrdenConsulta);
        pnlAcciones.add(btnConsultarEstado);
        pnlAcciones.add(new JSeparator(SwingConstants.VERTICAL));
        pnlAcciones.add(btnRetiro);
        pnlAcciones.add(btnEntrega);

        return pnlAcciones;
    }

    private JPanel crearPanelHistorial() {
        JPanel pnlHistorial = new JPanel(new BorderLayout());
        pnlHistorial.setBorder(BorderFactory.createTitledBorder("Historial de Órdenes por Cliente (verHistorialCliente)"));

        // JTable: Componente esencial para mostrar listas de datos
        String[] columnas = {"ID Orden", "Vehículo", "Fecha Servicio", "Estado"};
        JTable tablaHistorial = new JTable(new Object[0][0], columnas);

        btnVerHistorial = new JButton("Mostrar Historial del Cliente Cargado");
        btnVerHistorial.setBackground(new Color(150, 150, 250));
        // btnVerHistorial.addActionListener(e -> verHistorialCliente());

        pnlHistorial.add(new JScrollPane(tablaHistorial), BorderLayout.CENTER);
        pnlHistorial.add(btnVerHistorial, BorderLayout.SOUTH);

        return pnlHistorial;
    }

    // =================================================================================
    // MÉTODOS DEL DIAGRAMA (Se implementan vacíos para compatibilidad)
    // =================================================================================

    // Todos estos métodos deben ser llamados por los ActionListeners de los botones.

    public boolean cargarCliente(Cliente cliente) { return true; } // Mapea a cargarCliente()
    public boolean modificarOrdenReparacion(int idOrden, String nuevosDatos) { return true; } // Mapea a botón de modificación
    public OrdenDeTrabajo crearOrdenReparacion(Cliente cliente, Vehiculo vehiculo, String descripcion) { return null; } // Mapea a btnCrearOrden
    public String consultarEstadoOrden(int idOrden) { return null; } // Mapea a btnConsultarEstado
    public boolean registrarRetiroVehiculo(int idOrden) { return true; } // Mapea a btnRetiro
    public Cliente buscarClientePorDni(String dni) { return null; } // Mapea a btnBuscarCliente
    public boolean registrarEntregaVehiculo(int idOrden) { return true; } // Mapea a btnEntrega
    public List<OrdenDeTrabajo> verHistorialCliente(int idCliente) { return null; } // Mapea a btnVerHistorial
}