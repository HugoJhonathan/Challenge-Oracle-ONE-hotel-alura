package views.reserva;

import com.toedter.calendar.JDateChooser;
import exceptions.MinhaException;
import model.MetodoPagamento;
import model.Reserva;
import service.ReservaService;
import views.Config;
import views.MenuPrincipal;
import views.MenuUsuario;
import views.components.Header;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static views.InterfaceUtil.showMessageDialog;


@SuppressWarnings("serial")
public abstract class ReservaView extends JFrame {

    Reserva reserva;
    JLabel lblTitulo;

    JPanel contentPane;
    JTextField txtValor;
    JDateChooser txtDataE;
    JDateChooser txtDataS;
    JComboBox<String> txtFormaPagamento;
    JLabel lblSeguinte = new JLabel("PRÓXIMO");

    JLabel lblValorSimbolo;

    public ReservaView(Reserva reserva) {
        super("Reserva");
        this.reserva = reserva == null ? new Reserva() : reserva;
        setIconImage(Toolkit.getDefaultToolkit().getImage(ReservaView.class.getResource("/imagenes/aH-40px.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 910, 560);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.add(new Header(this, Header.Tipo.LIGHT, Header.Tipo.DARK) {
            @Override
            public void onClickBtnVoltar(MouseEvent e) {
                new MenuUsuario().setVisible(true);
                dispose();
            }

            @Override
            public void onClickBtnExit(MouseEvent e) {
                new MenuPrincipal().setVisible(true);
                dispose();
            }
        });
        contentPane.setBackground(SystemColor.control);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setUndecorated(true);

        JPanel panel = new JPanel();
        panel.setBorder(null);
        panel.setBackground(Color.WHITE);
        panel.setBounds(0, 0, 910, 560);
        contentPane.add(panel);
        panel.setLayout(null);

        JSeparator separator_1_2 = new JSeparator();
        separator_1_2.setForeground(SystemColor.textHighlight);
        separator_1_2.setBounds(68, 195, 289, 2);
        separator_1_2.setBackground(SystemColor.textHighlight);
        panel.add(separator_1_2);

        JSeparator separator_1_3 = new JSeparator();
        separator_1_3.setForeground(SystemColor.textHighlight);
        separator_1_3.setBackground(SystemColor.textHighlight);
        separator_1_3.setBounds(68, 453, 289, 2);
        panel.add(separator_1_3);

        JSeparator separator_1_1 = new JSeparator();
        separator_1_1.setForeground(SystemColor.textHighlight);
        separator_1_1.setBounds(68, 281, 289, 11);
        separator_1_1.setBackground(SystemColor.textHighlight);
        panel.add(separator_1_1);

        txtDataE = new JDateChooser();
        txtDataE.getCalendarButton().setBackground(SystemColor.textHighlight);
        txtDataE.getCalendarButton().setIcon(new ImageIcon(ReservaView.class.getResource("/imagenes/icon-reservas.png")));
        txtDataE.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 12));
        txtDataE.setBounds(68, 161, 289, 35);
        txtDataE.getCalendarButton().setBounds(268, 0, 21, 33);
        txtDataE.setBackground(Color.WHITE);
        txtDataE.setBorder(new LineBorder(SystemColor.window));
        txtDataE.setDateFormatString(Config.simpleDateFormat.toPattern());
        txtDataE.setFont(new Font("Roboto", Font.PLAIN, 18));

        txtDataE.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("date".equals(evt.getPropertyName())) {
                    handleChangeDate();
                }
            }
        });


        panel.add(txtDataE);

        lblValorSimbolo = new JLabel("$");
        lblValorSimbolo.setVisible(false);
        lblValorSimbolo.setBounds(121, 332, 17, 25);
        lblValorSimbolo.setForeground(SystemColor.textHighlight);
        lblValorSimbolo.setFont(new Font("Roboto", Font.BOLD, 17));

        panel.add(lblValorSimbolo);

        JLabel lblCheckIn = new JLabel("DATA DE CHECK IN");
        lblCheckIn.setForeground(SystemColor.textInactiveText);
        lblCheckIn.setBounds(68, 136, 169, 14);
        lblCheckIn.setFont(new Font("Roboto Black", Font.PLAIN, 18));
        panel.add(lblCheckIn);

        JLabel lblCheckOut = new JLabel("DATA DE CHECK OUT");
        lblCheckOut.setForeground(SystemColor.textInactiveText);
        lblCheckOut.setBounds(68, 221, 187, 14);
        lblCheckOut.setFont(new Font("Roboto Black", Font.PLAIN, 18));
        panel.add(lblCheckOut);

        txtDataS = new JDateChooser();
        txtDataS.getCalendarButton().setIcon(new ImageIcon(ReservaView.class.getResource("/imagenes/icon-reservas.png")));
        txtDataS.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 11));
        txtDataS.setBounds(68, 246, 289, 35);
        txtDataS.getCalendarButton().setBounds(267, 1, 21, 31);
        txtDataS.setBackground(Color.WHITE);
        txtDataS.setFont(new Font("Roboto", Font.PLAIN, 18));
//        txtDataS.addPropertyChangeListener(new PropertyChangeListener() {
//            public void propertyChange(PropertyChangeEvent evt) {
//                System.out.println("mudei");
//            }
//        });
        txtDataS.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("date".equals(evt.getPropertyName())) {
                    handleChangeDate();
                }
            }
        });
        txtDataS.setDateFormatString(Config.simpleDateFormat.toPattern());
        txtDataS.getCalendarButton().setBackground(SystemColor.textHighlight);
        txtDataS.setBorder(new LineBorder(new Color(255, 255, 255), 0));
        panel.add(txtDataS);


        txtValor = new JTextField();
        txtValor.setBackground(SystemColor.text);
        txtValor.setHorizontalAlignment(SwingConstants.LEFT);
        txtValor.setForeground(Color.BLACK);
        txtValor.setBounds(78, 328, 300, 33);
        txtValor.setEditable(false);
        txtValor.setFont(new Font("Roboto Black", Font.BOLD, 17));
        txtValor.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        panel.add(txtValor);
        //txtValor.setColumns(10);

        JLabel lblValor = new JLabel("VALOR DA RESERVA");
        lblValor.setForeground(SystemColor.textInactiveText);
        lblValor.setBounds(72, 303, 196, 14);
        lblValor.setFont(new Font("Roboto Black", Font.PLAIN, 18));
        panel.add(lblValor);

        txtFormaPagamento = new JComboBox<>();
        txtFormaPagamento.setBounds(68, 417, 289, 38);
        txtFormaPagamento.setBackground(SystemColor.text);
        txtFormaPagamento.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
        txtFormaPagamento.setFont(new Font("Roboto", Font.PLAIN, 16));


        List<String> lista = new ArrayList<>();
        lista.add("Selecione uma opção");
        lista.addAll(Arrays.stream(MetodoPagamento.values()).map(el -> el.getNome()).toList());
        txtFormaPagamento.setModel(new DefaultComboBoxModel(lista.toArray()));
        panel.add(txtFormaPagamento);

        JLabel lblFormaPago = new JLabel("FORMA DE PAGAMENTO");
        lblFormaPago.setForeground(SystemColor.textInactiveText);
        lblFormaPago.setBounds(68, 382, 213, 24);
        lblFormaPago.setFont(new Font("Roboto Black", Font.PLAIN, 18));
        panel.add(lblFormaPago);

        lblTitulo = new JLabel();
        lblTitulo.setBounds(109, 60, 219, 42);
        lblTitulo.setForeground(new Color(12, 138, 199));
        lblTitulo.setFont(new Font("Roboto", Font.BOLD, 20));
        panel.add(lblTitulo);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(428, 0, 482, 560);
        panel_1.setBackground(new Color(12, 138, 199));
        panel.add(panel_1);
        panel_1.setLayout(null);

        JLabel logo = new JLabel("");
        logo.setBounds(197, 68, 104, 107);
        panel_1.add(logo);
        logo.setIcon(new ImageIcon(ReservaView.class.getResource("/imagenes/Ha-100px.png")));

        JLabel imagenFondo = new JLabel("");
        imagenFondo.setBounds(0, 140, 500, 409);
        panel_1.add(imagenFondo);
        imagenFondo.setBackground(Color.WHITE);
        imagenFondo.setIcon(new ImageIcon(ReservaView.class.getResource("/imagenes/reservas-img-3.png")));

        JSeparator separator_1 = new JSeparator();
        separator_1.setForeground(SystemColor.textHighlight);
        separator_1.setBounds(68, 362, 289, 2);
        separator_1.setBackground(SystemColor.textHighlight);
        panel.add(separator_1);

        JButton btnProximo = new JButton();
        btnProximo.addActionListener(e -> {
            cadastrar();
        });
        btnProximo.setLayout(null);
        btnProximo.setBackground(SystemColor.textHighlight);
        btnProximo.setBounds(238, 493, 122, 35);
        panel.add(btnProximo);
        btnProximo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));


        lblSeguinte.setHorizontalAlignment(SwingConstants.CENTER);
        lblSeguinte.setForeground(Color.WHITE);
        lblSeguinte.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblSeguinte.setBounds(0, 0, 122, 35);
        btnProximo.add(lblSeguinte);
        txtFormaPagamento.addActionListener((e) -> {
            if (txtFormaPagamento.getSelectedIndex() != 0) {
                MetodoPagamento metodoPagamento = MetodoPagamento.findByName((String) txtFormaPagamento.getSelectedItem());
                this.reserva.setFormaPagamento(metodoPagamento);
            }
        });
        preencherDados();
    }

    protected abstract void cadastrar();

    protected boolean validar() {
        if (txtDataE.getDate() == null || txtDataS.getDate() == null) {
            showMessageDialog(this, "Deve preencher todos os campos.");
            return false;
        } else if (txtFormaPagamento.getSelectedIndex() == 0) {
            showMessageDialog(this, "Deve preencher a forma de pagamento!");
            return false;
        }
        return true;
    }

    protected void preencherDados() {
        txtDataE.setDate(reserva.getDataEntrada());
        txtDataS.setDate(reserva.getDataSaida());
        if (reserva.getValor() != null) {
            txtValor.setText(Config.numberFormat.format(reserva.getValor()));
        }
        if (reserva.getFormaPagamento() != null) {
            txtFormaPagamento.setSelectedItem(reserva.getFormaPagamento().getNome());
        }
    }


    private void handleChangeDate() {
        if (!Objects.nonNull(txtDataE.getDate()) || !Objects.nonNull(txtDataS.getDate())) {
            return;
        }
        try {
            BigDecimal valor = ReservaService.calcularValorDiaria(txtDataE.getDate(), txtDataS.getDate());
            String valorString = Config.numberFormat.format(valor);
            txtValor.setText(valorString);
            reserva.setDataEntrada(txtDataE.getDate());
            reserva.setDataSaida(txtDataS.getDate());
            reserva.setValor(valor);
        } catch (MinhaException e) {
            showMessageDialog(this, e.getMessage());
            txtDataS.setDate(null);
            reserva.setDataSaida(null);
        }
    }

}
