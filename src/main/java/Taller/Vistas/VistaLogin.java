package Taller.Vistas;

import Taller.Controlador.ControladorLogin;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaLogin extends JFrame {

    // COMPONENTES DE SWING
    private JTextField txtLegajo;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JCheckBox chkMostrarPass;

    ControladorLogin controlador;

    public VistaLogin(ControladorLogin controlador) {
        super("Ingreso al Sistema | Taller Mecánico");

        this.controlador = controlador;
        inicializarComponentes();

        this.setSize(900, 650);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- Título del Formulario
        JLabel lblTitulo = new JLabel("VALIDACIÓN DE ACCESO", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        // Panel Login (GridBagLayout)
        JPanel pnlLogin = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Legajo
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST;
        pnlLogin.add(new JLabel("Legajo:"), gbc);

        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtLegajo = new JTextField(20);
        ((AbstractDocument) txtLegajo.getDocument()).setDocumentFilter(new DigitFilter());
        pnlLogin.add(txtLegajo, gbc);

        // Contraseña
        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST;
        pnlLogin.add(new JLabel("Contraseña:"), gbc);

        gbc.gridx = 1;
        txtPassword = new JPasswordField(20);
        pnlLogin.add(txtPassword, gbc);

        // Checkbox "Mostrar Contraseña"
        chkMostrarPass = new JCheckBox("Mostrar Contraseña");
        gbc.gridx = 1; gbc.gridy = 2;
        gbc.gridwidth = 1; // Ocupa solo la segunda columna
        gbc.anchor = GridBagConstraints.WEST; // Alineado a la izquierda

        // Funcionamiento Checkbox
        chkMostrarPass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Si la casilla está marcada, muestra los caracteres ('\0' es el carácter nulo)
                if (chkMostrarPass.isSelected()) {
                    txtPassword.setEchoChar((char) 0);
                } else {
                    // Si está desmarcada, oculta con el carácter por defecto (el punto)
                    txtPassword.setEchoChar('*');
                }
            }
        });
        pnlLogin.add(chkMostrarPass, gbc);


        //  Boton de Ingreso
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;

        btnLogin = new JButton("VALIDAR ACCESO");
        btnLogin.setBackground(new Color(50, 150, 250));
        btnLogin.setForeground(Color.BLACK);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Funcionamiento Boton de Ingreso
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validarCredenciales();
            }
        });

        pnlLogin.add(btnLogin, gbc);

        // Ensamblar el Content Pane
        contentPane.add(lblTitulo, BorderLayout.NORTH);
        contentPane.add(pnlLogin, BorderLayout.CENTER);
        this.setContentPane(contentPane);
    }

    private void validarCredenciales() {
        String legajo = txtLegajo.getText();
        String password = new String(txtPassword.getPassword());

        if (legajo.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Debe completar todos los campos.",
                    "Campos incompletos",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean respuesta = controlador.validarCredenciales(legajo, password);

        if (!respuesta) {
            JOptionPane.showMessageDialog(this,
                    "Credenciales de ingreso incorrectas. Ingrese nuevamente.",
                    "Credenciales incorrectas",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
    }

    // CLASE INTERNA DE FILTRO (Necesaria para que compile el código del diseño)
    private class DigitFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (string.matches("\\d+")) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            if (text.matches("\\d+")) {
                super.replace(fb, offset, length, text, attrs);
            }
        }
    }
}