package Taller.Vistas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Optional;

// Importar los Gestores necesarios
// import Taller.Gestor.GestorInventario;
// import Taller.Gestor.GestorFacturacion;

public class VistaAdministrativo extends JFrame {

    // DEPENDENCIAS (Comentadas para solo diseño)
    // private final GestorInventario gestorInventario;
    // private final GestorFacturacion gestorFacturacion;

    // Componentes de la GUI
    private JTabbedPane tabbedPane;

    // Componentes Pestaña 1: Inventario
    private JTable tblRepuestosFaltantes;
    private DefaultTableModel modeloTablaRepuestos; // Modelo para manipular la tabla
    private JButton btnSolicitarPedido;
    private JButton btnRegistrarRecepcion;
    private JTextField txtCodRepuestoRecibido;
    private JTextField txtCantRecibida;

    // Componentes Pestaña 2: Pagos
    private JTextField txtIdOrdenPago;
    private JTextField txtMontoRecibido;
    private JComboBox<String> cmbMetodoPago;
    private JButton btnConfirmarPago;

    /**
     * Constructor enfocado solo en el diseño.
     */
    public VistaAdministrativo(/* GestorInventario gi, GestorFacturacion gf */) {
        super("Módulo Administrativo - Finanzas e Inventario");

        inicializarComponentes();

        this.setSize(900, 650);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("1. Gestión de Inventario/Pedidos", crearPanelInventario());
        tabbedPane.addTab("2. Procesamiento de Pagos/Facturación", crearPanelPagos());
        this.add(tabbedPane);
    }

    // =================================================================================
    // PESTAÑA 1: INVENTARIO (PEDIDOS Y RECEPCIÓN)
    // =================================================================================

    private JPanel crearPanelInventario() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel pnlFaltantes = crearPanelRepuestosFaltantes();
        panel.add(pnlFaltantes, BorderLayout.CENTER);

        JPanel pnlRecepcion = crearPanelRecepcionRepuestos();
        panel.add(pnlRecepcion, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel crearPanelRepuestosFaltantes() {
        JPanel pnlFaltantes = new JPanel(new BorderLayout());
        pnlFaltantes.setBorder(BorderFactory.createTitledBorder("Repuestos a Encargar (Lista de Pedido Activo)"));

        String[] columnas = {"Código", "Nombre", "Cantidad Necesaria", "Precio Unitario", "Total"};
        modeloTablaRepuestos = new DefaultTableModel(columnas, 0);
        tblRepuestosFaltantes = new JTable(modeloTablaRepuestos);

        // Simulación: Cargar filas para probar el borrado
        modeloTablaRepuestos.addRow(new Object[]{"Filt-01", "Filtro Aceite", (Object) 5, (Object) 45.50, (Object) 227.50});
        modeloTablaRepuestos.addRow(new Object[]{"Filt-02", "Filtro Aire", (Object) 10, (Object) 30.00, (Object) 300.00});

        btnSolicitarPedido = new JButton("CONFIRMAR PEDIDO DE REPUESTOS");
        btnSolicitarPedido.setBackground(new Color(50, 150, 250));
        btnSolicitarPedido.setForeground(Color.WHITE);

        pnlFaltantes.add(new JScrollPane(tblRepuestosFaltantes), BorderLayout.CENTER);
        pnlFaltantes.add(btnSolicitarPedido, BorderLayout.SOUTH);

        return pnlFaltantes;
    }

    private JPanel crearPanelRecepcionRepuestos() {
        JPanel pnlRecepcion = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        pnlRecepcion.setBorder(BorderFactory.createTitledBorder("Carga de Repuestos Recibidos"));

        pnlRecepcion.add(new JLabel("Código Repuesto:"));
        txtCodRepuestoRecibido = new JTextField(10);
        pnlRecepcion.add(txtCodRepuestoRecibido);

        pnlRecepcion.add(new JLabel("Cantidad Recibida:"));
        txtCantRecibida = new JTextField(5);
        pnlRecepcion.add(txtCantRecibida);

        btnRegistrarRecepcion = new JButton("Registrar Recepción");
        btnRegistrarRecepcion.setBackground(new Color(40, 150, 40));
        btnRegistrarRecepcion.setForeground(Color.WHITE);

        btnRegistrarRecepcion.addActionListener(this::registrarRecepcionDeRepuestos);

        pnlRecepcion.add(btnRegistrarRecepcion);

        return pnlRecepcion;
    }

    private void registrarRecepcionDeRepuestos(ActionEvent e) {
        String codigo = txtCodRepuestoRecibido.getText().trim();
        String cantidadStr = txtCantRecibida.getText().trim();

        if (codigo.isEmpty() || cantidadStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar Código y Cantidad recibida.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int cantidad;
        try {
            cantidad = Integer.parseInt(cantidadStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "La Cantidad debe ser un número entero.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // SIMULACIÓN VISUAL: Asumimos que la delegación fue exitosa para actualizar la tabla.
        actualizarTablaTrasRecepcion(codigo, cantidad);
    }

    private void actualizarTablaTrasRecepcion(String codigoRecibido, int cantidadRecibida) {
        DefaultTableModel modelo = (DefaultTableModel) tblRepuestosFaltantes.getModel();

        for (int i = modelo.getRowCount() - 1; i >= 0; i--) {
            String codigoTabla = (String) modelo.getValueAt(i, 0);

            if (codigoTabla.equals(codigoRecibido)) {

                Object cantidadObj = modelo.getValueAt(i, 2);
                int cantidadNecesaria = 0;

                try {
                    cantidadNecesaria = ((Number) cantidadObj).intValue();
                } catch (Exception e) {
                    cantidadNecesaria = (int) modelo.getValueAt(i, 2);
                }

                if (cantidadRecibida >= cantidadNecesaria) {
                    // Si se recibió la cantidad completa o más: BORRAMOS LA FILA
                    modelo.removeRow(i);
                    JOptionPane.showMessageDialog(this, "¡Recepción Completa! Repuesto " + codigoRecibido + " borrado de la lista de pendientes.", "Actualización", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Si se recibió una cantidad PARCIAL: ACTUALIZAMOS LA FILA
                    int nuevoFaltante = cantidadNecesaria - cantidadRecibida;

                    double precioUnitario = (double) modelo.getValueAt(i, 3);
                    double nuevoTotal = nuevoFaltante * precioUnitario;

                    modelo.setValueAt(Optional.of(nuevoFaltante), i, 2);
                    modelo.setValueAt(Optional.of(nuevoTotal), i, 4);
                    JOptionPane.showMessageDialog(this, "Cantidad de repuesto " + codigoRecibido + " actualizada en la tabla. Faltan: " + nuevoFaltante, "Actualización Parcial", JOptionPane.INFORMATION_MESSAGE);
                }

                txtCodRepuestoRecibido.setText("");
                txtCantRecibida.setText("");
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "No se encontró ningún pedido pendiente para el código " + codigoRecibido + ".", "Búsqueda Fallida", JOptionPane.WARNING_MESSAGE);
    }

    // =================================================================================
    // PESTAÑA 2: PROCESAMIENTO DE PAGOS Y FACTURACIÓN (AGREGADO NUEVAMENTE)
    // =================================================================================

    private JPanel crearPanelPagos() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // --- 1. ID Orden de Trabajo ---
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("ID Orden de Pago:"), gbc);

        gbc.gridx = 1; gbc.weightx = 1.0;
        txtIdOrdenPago = new JTextField(15);
        panel.add(txtIdOrdenPago, gbc);

        // --- 2. Método de Pago ---
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.0;
        panel.add(new JLabel("Método de Pago:"), gbc);

        gbc.gridx = 1; gbc.weightx = 1.0;
        cmbMetodoPago = new JComboBox<>(new String[]{"Efectivo", "Tarjeta/Transferencia"});
        panel.add(cmbMetodoPago, gbc);

        // --- 3. Monto Recibido ---
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0.0;
        panel.add(new JLabel("Monto Recibido:"), gbc);

        gbc.gridx = 1; gbc.weightx = 1.0;
        txtMontoRecibido = new JTextField(15);
        panel.add(txtMontoRecibido, gbc);

        // --- 4. Botón de Confirmación ---
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER; gbc.fill = GridBagConstraints.NONE;

        btnConfirmarPago = new JButton("CONFIRMAR PAGO Y HABILITAR RETIRO");
        btnConfirmarPago.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnConfirmarPago.setBackground(new Color(250, 100, 100));
        btnConfirmarPago.setForeground(Color.WHITE);

        // CONEXIÓN CLAVE: Añadir el ActionListener para la facturación
        btnConfirmarPago.addActionListener(this::confirmarPago);

        panel.add(btnConfirmarPago, gbc);

        // Etiqueta de nota
        JLabel lblNota = new JLabel("<html><i>Al confirmar, se registra el pago y se notifica al Recepcionista para el retiro.</i></html>", SwingConstants.CENTER);
        gbc.gridy = 4;
        panel.add(lblNota, gbc);

        return panel;
    }

    /**
     * Manejador de evento para el botón de Confirmar Pago.
     * Realiza validación de formato y delega la lógica de facturación.
     */
    private void confirmarPago(ActionEvent e) {
        String idOrdenStr = txtIdOrdenPago.getText().trim();
        String montoStr = txtMontoRecibido.getText().trim();
        String metodoPago = (String) cmbMetodoPago.getSelectedItem();

        // 1. VALIDACIÓN DE FORMATO
        if (idOrdenStr.isEmpty() || montoStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar el ID de la Orden y el Monto.", "Faltan Datos", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int idOrden;
        double monto;
        try {
            idOrden = Integer.parseInt(idOrdenStr);
            monto = Double.parseDouble(montoStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El ID de Orden y el Monto deben ser numéricos.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 2. LLAMADA AL GESTOR (Delegación de la Lógica a tu gestorFacturacion)

        /*
        boolean exito = gestorFacturacion.registrarPagoYFacturar(idOrden, monto, metodoPago);

        if (exito) {
            JOptionPane.showMessageDialog(this,
                "Pago de $ " + monto + " registrado y Orden #" + idOrden + " habilitada para retiro.",
                "Pago Confirmado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                "Error al procesar el pago o la orden no está lista para facturar.",
                "Error de Facturación", JOptionPane.ERROR_MESSAGE);
        }
        */

        // SIMULACIÓN VISUAL
        JOptionPane.showMessageDialog(this,
                "SIMULACIÓN EXITOSA: La lógica de registro de pago de $ " + monto + " para la Orden #" + idOrden + " ha sido DELEGADA al GestorFacturacion.",
                "Delegación Completa", JOptionPane.INFORMATION_MESSAGE);

        // Limpiar campos después de la simulación
        txtIdOrdenPago.setText("");
        txtMontoRecibido.setText("");
    }

    // =================================================================================
    // MAIN TEMPORAL PARA VER EL DISEÑO
    // =================================================================================
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VistaAdministrativo().setVisible(true);
        });
    }
}