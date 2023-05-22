package views;

import model.Hospede;
import model.Reserva;
import service.HospedeService;
import service.ReservaService;
import views.components.Header;
import views.hospede.HospedeViewEdicao;
import views.reserva.ReservaViewEdicao;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

@SuppressWarnings("serial")
public class Buscar extends JFrame {

    private JPanel contentPane;
    private JTextField txtBuscar;
    private JTable tbHospedes;
    private JTable tbReservas;
    private DefaultTableModel modelo;
    private DefaultTableModel modeloHospedes;
    private JTabbedPane panel;
    private HospedeService hospedeService;
    private ReservaService reservaService;
    private List<Reserva> listReservas;
    private List<Hospede> listHospedes;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Buscar frame = new Buscar();
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
    public Buscar() {
        reservaService = new ReservaService();
        hospedeService = new HospedeService();
        listReservas = reservaService.findAll();
        listHospedes = hospedeService.findAll();

        setIconImage(Toolkit.getDefaultToolkit().getImage(Buscar.class.getResource("/imagenes/lOGO-50PX.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 910, 571);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        setUndecorated(true);

        txtBuscar = new JTextField();
        txtBuscar.setBounds(536, 127, 193, 31);
        txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());

        txtBuscar.setColumns(10);
        contentPane.add(txtBuscar);

        JLabel lblTitulo = new JLabel("SISTEMA DE BUSCA");
        lblTitulo.setForeground(new Color(12, 138, 199));
        lblTitulo.setFont(new Font("Roboto Black", Font.BOLD, 24));
        lblTitulo.setBounds(331, 62, 280, 42);
        contentPane.add(lblTitulo);

        panel = new JTabbedPane(JTabbedPane.TOP);
        panel.setBackground(new Color(12, 138, 199));
        panel.setForeground(Color.white);

        panel.setFont(new Font("Roboto", Font.PLAIN, 16));
        panel.setBounds(20, 169, 865, 328);
        panel.addChangeListener(cl -> {
            if (tbReservas != null) {
                tbReservas.clearSelection();
            }
            if (tbHospedes != null) {
                tbHospedes.clearSelection();
            }
        });
        contentPane.add(panel);

        tbReservas = new JTable() {
            @Override
            public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend) {
                super.changeSelection(rowIndex, -1, toggle, extend);
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
        modelo = (DefaultTableModel) tbReservas.getModel();
        modelo.addColumn("Numero de Reserva");
        modelo.addColumn("Data Check In");
        modelo.addColumn("Data Check Out");
        modelo.addColumn("Valor");
        modelo.addColumn("Forma de Pago");
        modelo.addColumn("Hospede");
        modelo.addColumn("Hospede ID");
        JScrollPane scroll_table = new JScrollPane(tbReservas);
        panel.addTab("Reservas", new ImageIcon(Buscar.class.getResource("/imagenes/reservado.png")), scroll_table, null);
        scroll_table.setVisible(true);

        tbHospedes = new JTable() {
            @Override
            public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend) {
                super.changeSelection(rowIndex, -1, toggle, extend);
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tbHospedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbHospedes.setFont(new Font("Roboto", Font.PLAIN, 16));
        modeloHospedes = (DefaultTableModel) tbHospedes.getModel();
        modeloHospedes.addColumn("Numero de Hóspede");
        modeloHospedes.addColumn("Nome");
        modeloHospedes.addColumn("Data de Nascimento");
        modeloHospedes.addColumn("Nacionalidade");
        modeloHospedes.addColumn("Telefone");
        JScrollPane scroll_tableHuespedes = new JScrollPane(tbHospedes);
        panel.addTab("Huéspedes", new ImageIcon(Buscar.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
        scroll_tableHuespedes.setVisible(true);
        listHospedes = new HospedeService().findAll();

        preencherTabelaReserva(listReservas);
        preencherTabelaHospede(listHospedes);

        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon(Buscar.class.getResource("/imagenes/Ha-100px.png")));
        lblNewLabel_2.setBounds(56, 51, 104, 107);
        contentPane.add(lblNewLabel_2);

        contentPane.add(new Header(this, Header.Tipo.LIGHT, Header.Tipo.LIGHT) {
            @Override
            public void onClickBtnVoltar(MouseEvent e) {
                new MenuUsuario().setVisible(true);
                dispose();
            }
        });

        JSeparator separator_1_2 = new JSeparator();
        separator_1_2.setForeground(new Color(12, 138, 199));
        separator_1_2.setBackground(new Color(12, 138, 199));
        separator_1_2.setBounds(539, 159, 193, 2);
        contentPane.add(separator_1_2);

        JButton btnbuscar = new JButton();

        btnbuscar.setLayout(null);
        btnbuscar.setBackground(new Color(12, 138, 199));
        btnbuscar.setBounds(748, 125, 122, 35);
        btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnbuscar);

        JLabel lblBuscar = new JLabel("BUSCAR");
        lblBuscar.setBounds(0, 0, 122, 35);
        btnbuscar.add(lblBuscar);
        lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
        lblBuscar.setForeground(Color.WHITE);
        lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

        JButton btnEditar = new JButton();
        btnEditar.setLayout(null);
        btnEditar.setBackground(new Color(12, 138, 199));
        btnEditar.setBounds(635, 508, 122, 35);
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnEditar);

        JLabel lblEditar = new JLabel("EDITAR");
        btnEditar.setOpaque(true);

        lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
        lblEditar.setForeground(Color.WHITE);
        lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblEditar.setBounds(0, 0, 122, 35);
        btnEditar.add(lblEditar);

        JButton btnDeletar = new JButton();
        btnDeletar.setLayout(null);
        btnDeletar.setBackground(new Color(12, 138, 199));
        btnDeletar.setBounds(767, 508, 122, 35);
        btnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        contentPane.add(btnDeletar);
        JLabel lblExcluir = new JLabel("DELETAR");
        lblExcluir.setHorizontalAlignment(SwingConstants.CENTER);
        lblExcluir.setForeground(Color.WHITE);
        lblExcluir.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblExcluir.setBounds(0, 0, 122, 35);
        btnDeletar.add(lblExcluir);
        setResizable(false);

        btnEditar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Long id = getIdInTable();
                if (getActiveTable() == Reserva.class) {
                    Reserva reserva = reservaService.findById(id);
                    new ReservaViewEdicao(reserva);
                } else if (getActiveTable() == Hospede.class) {
                    Hospede hospede = hospedeService.findById(id);
                    new HospedeViewEdicao(hospede).setVisible(true);
                }
                dispose();
            }
        });

        btnDeletar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Long id = getIdInTable();
                if (getActiveTable() == Reserva.class) {
                    reservaService.delete(id);
                    modelo.removeRow(getRowSelected());
                    new WindowAlert("Reserva deletada!", WindowAlert.Tipos.SUCCESS);
                } else if (getActiveTable() == Hospede.class) {
                    hospedeService.delete(id);
                    modeloHospedes.removeRow(getRowSelected());
                    new WindowAlert("Hospede deletado!", WindowAlert.Tipos.SUCCESS);
                }
            }
        });

        txtBuscar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                handleTxtBuscarChange(txtBuscar.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (txtBuscar.getText() != "") {
                    handleTxtBuscarChange(txtBuscar.getText());
                    return;
                }
                if (getActiveTable().equals(Hospede.class)) {
                    preencherTabelaHospede(listHospedes);
                } else {
                    preencherTabelaReserva(listReservas);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                System.out.println("update");
            }
        });

//        btnbuscar.addActionListener(e -> {
//        });
    }

    private int getRowSelected() {
        JScrollPane asd = (JScrollPane) panel.getSelectedComponent();
        JTable sd = (JTable) asd.getViewport().getView();
        return sd.getSelectedRow();
    }

    private void handleTxtBuscarChange(String pesquisa) {
        if (getActiveTable().equals(Hospede.class)) {
            List<Hospede> hospedes = filtrarHospedes(pesquisa);
            preencherTabelaHospede(hospedes);
        } else if (getActiveTable().equals(Reserva.class)) {
            List<Reserva> reservas = filtrarReservas(pesquisa);
            preencherTabelaReserva(reservas);
        }
    }

    private List<Reserva> filtrarReservas(String pesquisa) {
        return listReservas.stream()
                .filter(reserva -> {
                    if (pesquisa.isBlank()) return true;
                    if (reserva.getFormaPagamento().getNome().toLowerCase().contains(pesquisa.toLowerCase()))
                        return true;
                    if (Config.numberFormat.format(reserva.getValor()).contains(pesquisa)) return true;
                    if (Config.simpleDateFormat.format(reserva.getDataEntrada()).contains(pesquisa)) return true;
                    if (Config.simpleDateFormat.format(reserva.getDataSaida()).contains(pesquisa)) return true;
                    if (reserva.getHospede().getNomeCompleto().toLowerCase().contains(pesquisa.toLowerCase()))
                        return true;
                    try {
                        if (reserva.getId().equals(Long.parseLong(pesquisa))) {
                            return true;
                        }
                    } catch (NumberFormatException ex) {
                    }
                    return false;
                })
                .toList();
    }

    private List<Hospede> filtrarHospedes(String pesquisa) {
        return listHospedes.stream()
                .filter(hospede -> {
                    if (pesquisa.isBlank()) return true;
                    String format = Config.simpleDateFormat.format(hospede.getDataNascimento());
                    if (format.contains(pesquisa)) return true;
                    if (hospede.getNomeCompleto().toLowerCase().contains(pesquisa.toLowerCase())) return true;
                    if (hospede.getTelefone().contains(pesquisa)) return true;
                    if (hospede.getNacionalidade().toLowerCase().contains(pesquisa.toLowerCase())) return true;
                    try {
                        if (hospede.getId().equals(Long.parseLong(pesquisa))) return true;
                    } catch (NumberFormatException ex) {
                    }
                    return false;
                })
                .toList();
    }

    private Long getIdInTable() {
        JScrollPane asd = (JScrollPane) panel.getSelectedComponent();
        JTable sd = (JTable) asd.getViewport().getView();
        if (getRowSelected() != -1) {
            Long id = (Long) sd.getValueAt(getRowSelected(), 0);
            return id;
        } else {
            InterfaceUtil.showMessageDialog(this, "Selecione um registro para editar!");
        }
        return null;
    }

    private Object getActiveTable() {
        if (panel.getSelectedIndex() == 0) {
            return Reserva.class;
        } else if (panel.getSelectedIndex() == 1) {
            return Hospede.class;
        }
        return null;
    }

    private void preencherTabelaHospede(List<Hospede> allHospedes) {
        modeloHospedes.setRowCount(0);
        allHospedes.forEach(reserva -> {
            modeloHospedes.addRow(new Object[]{
                    reserva.getId(),
                    reserva.getNomeCompleto(),
                    Config.simpleDateFormat.format(reserva.getDataNascimento()),
                    reserva.getNacionalidade(),
                    Config.formatStringWithPhoneMask(reserva.getTelefone()),
            });
        });
    }

    private void preencherTabelaReserva(List<Reserva> all) {
        modelo.setRowCount(0);
        all.forEach(reserva -> {
            modelo.addRow(new Object[]{
                    reserva.getId(),
                    Config.simpleDateFormat.format(reserva.getDataEntrada()),
                    Config.simpleDateFormat.format(reserva.getDataSaida()),
                    Config.numberFormat.format(reserva.getValor()),
                    reserva.getFormaPagamento(),
                    reserva.getHospede().getNomeCompleto(),
                    reserva.getHospede().getId(),
            });
        });
    }

}