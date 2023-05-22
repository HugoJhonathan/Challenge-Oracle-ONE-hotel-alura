package views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class WindowAlert extends JDialog {

    private final JPanel contentPanel = new JPanel();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            WindowAlert dialog = new WindowAlert("adminn", Tipos.SUCCESS);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public WindowAlert(String mensagem, Tipos tipo) {
        setTitle(tipo.getNome());
        setIconImage(Toolkit.getDefaultToolkit().getImage(WindowAlert.class.getResource("/imagenes/aH-40px.png")));
        setBounds(100, 100, 394, 250);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(SystemColor.control);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        contentPanel.setLayout(null);

        JLabel lblNewLabel = new JLabel();
        if (tipo != null) {
            lblNewLabel.setIcon(new ImageIcon(WindowAlert.class.getResource("/imagenes" + tipo.getImageName())));
        }
        lblNewLabel.setBounds(138, 11, 100, 100);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("<html><p style='text-align:center'>" + mensagem + "</p></html>");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setForeground(new Color(79, 83, 80));
        lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 16));
        lblNewLabel_1.setBounds(27, 105, 322, 50);
        contentPanel.add(lblNewLabel_1);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();//serve para fechar a janela atual
                if (tipo == Tipos.SUCCESS) {
                    new MenuUsuario().setVisible(true);
                }
            }
        });
        okButton.setActionCommand("OK");
        getRootPane().setDefaultButton(okButton);
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setActionCommand("Cancel");
        buttonPane.add(okButton);
        buttonPane.add(cancelButton);
        setVisible(true);
    }

    public enum Tipos {
        SUCCESS("/success.png", "Sucesso"),
        ERROR("/error.png", "Error"),
        ALERT("/alert.png", "Alerta");

        private String imageName;
        private String nome;


        Tipos(String imageName, String nome) {
            this.imageName = imageName;
            this.nome = nome;

        }

        public String getImageName() {
            return imageName;
        }

        public String getNome() {
            return nome;
        }
    }

}
