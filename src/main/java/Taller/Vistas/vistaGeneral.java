package Taller.Vistas;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// La clase ya no necesita el Gestor para solo el diseño, pero se mantiene la estructura
// para la futura conexión funcional.
// public class vistaGeneral extends JFrame {
// public vistaGeneral(gestorEmpleado gestor) { ... }
// }

public class vistaGeneral extends JFrame {

    // COMPONENTES DE SWING
    private JTextField txtLegajo;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JCheckBox chkMostrarPass; // Nuevo componente

    // El constructor se deja sin el gestor para que se pueda ver solo el diseño
    public vistaGeneral() {
        super("Ingreso al Sistema | Taller Mecánico");
        // Si necesitas volver a la versión funcional, recuerda re-añadir el parámetro gestorEmpleado gestor al constructor

        inicializarComponentes();

        this.setSize(450, 290); // Se aumenta el alto para la nueva casilla
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

        // --- Panel Login (GridBagLayout)
        JPanel pnlLogin = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // --- 1. Legajo ---
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST;
        pnlLogin.add(new JLabel("Legajo:"), gbc);

        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtLegajo = new JTextField(20);
        ((AbstractDocument) txtLegajo.getDocument()).setDocumentFilter(new DigitFilter());
        pnlLogin.add(txtLegajo, gbc);

        // --- 2. Contraseña ---
        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST;
        pnlLogin.add(new JLabel("Contraseña:"), gbc);

        gbc.gridx = 1;
        txtPassword = new JPasswordField(20);
        pnlLogin.add(txtPassword, gbc);

        // --- 3. Checkbox "Mostrar Contraseña" (NUEVA FILA) ---
        chkMostrarPass = new JCheckBox("Mostrar Contraseña");
        gbc.gridx = 1; gbc.gridy = 2;
        gbc.gridwidth = 1; // Ocupa solo la segunda columna
        gbc.anchor = GridBagConstraints.WEST; // Alineado a la izquierda

        // ** CÓDIGO CLAVE: Añadir el listener para mostrar/ocultar **
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


        // --- 4. Botón de Ingreso (NUEVA FILA) ---
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;

        btnLogin = new JButton("VALIDAR ACCESO");
        btnLogin.setBackground(new Color(50, 150, 250));
        btnLogin.setForeground(Color.BLACK);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // El ActionListener funcional de validarIngreso debe implementarse después.
        // btnLogin.addActionListener(this::validarIngreso);
        pnlLogin.add(btnLogin, gbc);

        // Ensamblar el Content Pane
        contentPane.add(lblTitulo, BorderLayout.NORTH);
        contentPane.add(pnlLogin, BorderLayout.CENTER);
        this.setContentPane(contentPane);
    }

    // ******************************************************
    // CLASE INTERNA DE FILTRO (Necesaria para que compile el código del diseño)
    // ******************************************************
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

    // ******************************************************
    // MÉTODO MAIN TEMPORAL PARA VER EL DISEÑO
    // ******************************************************
    public static void main(String[] args) {
        // Lanza la aplicación en el hilo de eventos de Swing
        SwingUtilities.invokeLater(() -> {
            // Se crea la vista sin pasarle ningún gestor
            new vistaGeneral().setVisible(true);
        });
    }
}