package views;

import exceptions.LoginException;
import exceptions.LoginInvalidLoginException;
import exceptions.LoginInvalidPasswordException;
import service.UsuarioService;
import views.components.Header;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Login extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JLabel labelExit;
    private UsuarioService usuarioService;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Login() {

        usuarioService = new UsuarioService();
        setResizable(false);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 788, 527);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        contentPane.add(new Header(this, Header.Tipo.DARK));

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 788, 527);
        panel.setBackground(Color.WHITE);
        contentPane.add(panel);
        panel.setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(12, 138, 199));
        panel_1.setBounds(484, 0, 304, 527);
        panel.add(panel_1);
        panel_1.setLayout(null);

        JLabel imgHotel = new JLabel("");
        imgHotel.setBounds(0, 0, 304, 538);
        panel_1.add(imgHotel);
        imgHotel.setIcon(new ImageIcon(Login.class.getResource("/imagenes/img-hotel-login-.png")));

        labelExit = new JLabel("X");
        labelExit.setBounds(0, 0, 53, 36);
        labelExit.setForeground(SystemColor.text);
        labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
        labelExit.setHorizontalAlignment(SwingConstants.CENTER);

        txtUsuario = new JTextField();
        txtUsuario.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtUsuario.getText().equals("Digite seu nome de usuario")) {
                    txtUsuario.setText("");
                    txtUsuario.setForeground(Color.black);
                }
                if (String.valueOf(txtSenha.getPassword()).isEmpty()) {
                    txtSenha.setText("********");
                    txtSenha.setForeground(Color.gray);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });
        txtUsuario.setFont(new Font("Roboto", Font.PLAIN, 16));
        txtUsuario.setText("Digite seu nome de usuario");
        txtUsuario.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        txtUsuario.setForeground(SystemColor.activeCaptionBorder);
        txtUsuario.setBounds(65, 256, 324, 32);
        panel.add(txtUsuario);
        txtUsuario.setColumns(10);

        JSeparator separator = new JSeparator();
        separator.setBackground(new Color(0, 120, 215));
        separator.setBounds(65, 292, 324, 2);
        panel.add(separator);

        JLabel labelTitulo = new JLabel("LOGIN");
        labelTitulo.setForeground(SystemColor.textHighlight);
        labelTitulo.setFont(new Font("Roboto Black", Font.PLAIN, 26));
        labelTitulo.setBounds(196, 150, 89, 26);
        panel.add(labelTitulo);

        JSeparator separator_1 = new JSeparator();
        separator_1.setBackground(SystemColor.textHighlight);
        separator_1.setBounds(65, 393, 324, 2);
        panel.add(separator_1);

        txtSenha = new JPasswordField();
        txtSenha.setText("********");
        txtSenha.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(txtSenha.getPassword()).equals("********")) {
                    txtSenha.setText("");
                    txtSenha.setForeground(Color.black);
                }
                if (txtUsuario.getText().isEmpty()) {
                    txtUsuario.setText("Digite seu nome de usuario");
                    txtUsuario.setForeground(Color.gray);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });
        txtSenha.setForeground(SystemColor.activeCaptionBorder);
        txtSenha.setFont(new Font("Roboto", Font.PLAIN, 16));
        txtSenha.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        txtSenha.setBounds(65, 353, 324, 32);
        panel.add(txtSenha);

        JLabel LabelUsuario = new JLabel("USUARIO");
        LabelUsuario.setForeground(SystemColor.textInactiveText);
        LabelUsuario.setFont(new Font("Roboto Black", Font.PLAIN, 20));
        LabelUsuario.setBounds(65, 219, 107, 26);
        panel.add(LabelUsuario);

        JLabel lblSenha = new JLabel("SENHA");
        lblSenha.setForeground(SystemColor.textInactiveText);
        lblSenha.setFont(new Font("Roboto Black", Font.PLAIN, 20));
        lblSenha.setBounds(65, 316, 140, 26);
        panel.add(lblSenha);

        JButton btnLogin = new JButton();
        btnLogin.addActionListener(e -> {
            login();
        });
        btnLogin.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                btnLogin.setBackground(new Color(0, 156, 223));
            }

            @Override
            public void focusLost(FocusEvent e) {
                btnLogin.setBackground(SystemColor.textHighlight);
            }
        });
        btnLogin.setOpaque(true);
        btnLogin.setBackground(SystemColor.textHighlight);
        btnLogin.setBounds(65, 431, 122, 44);
        panel.add(btnLogin);
        btnLogin.setLayout(null);
        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        JLabel lblEntrar = new JLabel("ENTRAR");
        lblEntrar.setBounds(0, 0, 122, 44);
        btnLogin.add(lblEntrar);
        lblEntrar.setForeground(SystemColor.controlLtHighlight);
        lblEntrar.setHorizontalAlignment(SwingConstants.CENTER);
        lblEntrar.setFont(new Font("Roboto", Font.PLAIN, 18));

        JLabel logo = new JLabel("");
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        logo.setIcon(new ImageIcon(Login.class.getResource("/imagenes/lOGO-50PX.png")));
        logo.setBounds(65, 65, 48, 59);
        panel.add(logo);
    }

    private void login() {
        String senha = new String(txtSenha.getPassword());
        String login = txtUsuario.getText();
        if (!validar(login, senha)) return;
        try {
            usuarioService.autenticar(login, senha);
            MenuUsuario menu = new MenuUsuario();
            menu.setVisible(true);
            dispose();
        } catch (LoginException e) {
            if (e instanceof LoginInvalidPasswordException) {
                txtSenha.requestFocus();
            } else if (e instanceof LoginInvalidLoginException) {
                txtUsuario.requestFocus();
            }
            throw new LoginException(e.getMessage());
        }
    }

    public boolean validar(String login, String senha) {
        if (login.isBlank()) {
            InterfaceUtil.showMessageDialog(this, "O campo usuário é requirido.");
            txtUsuario.requestFocus();
            return false;
        } else if (senha.isBlank()) {
            InterfaceUtil.showMessageDialog(this, "O campo senha é requirido.");
            txtSenha.requestFocus();
            return false;
        }
        return true;
    }

}
