package views.hospede;

import com.toedter.calendar.JDateChooser;
import model.Hospede;
import model.Reserva;
import views.Config;
import views.MenuPrincipal;
import views.components.Header;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.text.Format;

import static views.InterfaceUtil.showMessageDialog;

@SuppressWarnings("serial")
public abstract class HospedeView extends JFrame {

    Reserva reserva;
    Hospede hospede;
    JLabel lblTitulo;

    JPanel contentPane;
    JTextField txtNome;
    JTextField txtSobrenome;
    JFormattedTextField txtTelefone;
    JTextField txtNreserva;
    JDateChooser txtDataN;
    JComboBox<Format> txtNacionalidade;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    HospedeView frame = new HospedeViewCadastro(new Reserva());
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    protected abstract void onClickBtnVoltar_(MouseEvent e);

    /**
     * Create the frame.
     */
    public HospedeView(Reserva reserva) {
        this.reserva = reserva == null ? new Reserva() : reserva;
        this.hospede = this.reserva.getHospede() == null ? new Hospede() : reserva.getHospede();
        this.reserva.setHospede(this.hospede);
        setIconImage(Toolkit.getDefaultToolkit().getImage(HospedeView.class.getResource("/imagenes/lOGO-50PX.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 910, 634);
        contentPane = new JPanel();
        contentPane.add(new Header(this, Header.Tipo.DARK, Header.Tipo.LIGHT) {
            @Override
            public void onClickBtnVoltar(MouseEvent e) {
                onClickBtnVoltar_(e);
            }

            @Override
            public void onClickBtnExit(MouseEvent e) {
                new MenuPrincipal().setVisible(true);
                dispose();
            }
        });
        contentPane.setBackground(SystemColor.text);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        setUndecorated(true);
        contentPane.setLayout(null);


        txtNome = new JTextField();
        txtNome.setFont(new Font("Roboto", Font.PLAIN, 16));
        txtNome.setBounds(560, 135, 285, 33);
        txtNome.setBackground(Color.WHITE);
        txtNome.setColumns(10);
        txtNome.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        contentPane.add(txtNome);

        txtSobrenome = new JTextField();
        txtSobrenome.setFont(new Font("Roboto", Font.PLAIN, 16));
        txtSobrenome.setBounds(560, 204, 285, 33);
        txtSobrenome.setColumns(10);
        txtSobrenome.setBackground(Color.WHITE);
        txtSobrenome.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        contentPane.add(txtSobrenome);

        txtDataN = new JDateChooser();
        txtDataN.getCalendarButton().setBackground(SystemColor.textHighlight);
        txtDataN.getCalendarButton()
                .setIcon(new ImageIcon(HospedeView.class.getResource("/imagenes/icon-reservas.png")));
        txtDataN.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 12));
        txtDataN.setBounds(560, 278, 285, 36);
        txtDataN.setDateFormatString(Config.simpleDateFormat.toPattern());
        txtDataN.getCalendarButton().setBounds(268, 0, 90, 33);
        txtDataN.setBackground(Color.WHITE);
        txtDataN.setBorder(new LineBorder(SystemColor.window));
        txtDataN.setDateFormatString(Config.simpleDateFormat.toPattern());
        txtDataN.setFont(new Font("Roboto", Font.PLAIN, 18));
        contentPane.add(txtDataN);

        txtNacionalidade = new JComboBox();
        txtNacionalidade.setBounds(560, 350, 289, 36);
        txtNacionalidade.setBackground(SystemColor.text);
        txtNacionalidade.setFont(new Font("Roboto", Font.PLAIN, 16));
        txtNacionalidade.setModel(new DefaultComboBoxModel(new String[]{"Selecione uma opção", "alemão", "andorrano",
                "angolano", "antiguano", "saudita", "argelino", "argentino", "armênio", "australiano", "austríaco",
                "azerbaijano", "bahamense", "bangladês, bangladense", "barbadiano", "bahreinita", "belga", "belizenho",
                "beninês", "belarusso", "boliviano", "bósnio", "botsuanês", "brasileiro", "bruneíno", "búlgaro",
                "burkineonse, burkinabé", "burundês", "butanês", "cabo-verdiano", "camerounês", "cambojano",
                "catariano", "canadense", "cazaque", "chadiano", "chileno", "chinês", "cipriota", "colombiano",
                "comoriano", "congolês", "congolês", "sul-coreano", "norte-coreano", "costa-marfinense, marfinense",
                "costa-ricense", "croata", "cubano", "dinamarquês", "djiboutiano", "dominiquense", "egípcio",
                "salvadorenho", "emiradense, emirático", "equatoriano", "eritreu", "eslovaco", "esloveno", "espanhol",
                "estadunidense", "estoniano", "etíope", "fijiano", "filipino", "finlandês",
                "francês", "gabonês", "gambiano", "ganês ou ganense", "georgiano", "granadino", "grego", "guatemalteco",
                "guianês", "guineense", "guineense, bissau-guineense", "equato-guineense", "haitiano", "hondurenho",
                "húngaro", "iemenita", "cookiano", "marshallês", "salomonense", "indiano", "indonésio", "iraniano",
                "iraquiano", "irlandês", "islandês", "34", "jamaicano", "japonês", "jordaniano", "kiribatiano",
                "kuwaitiano", "laosiano", "lesotiano", "letão", "libanês", "liberiano", "líbio", "liechtensteiniano",
                "lituano", "luxemburguês", "macedônio", "madagascarense", "malásio37", "malawiano", "maldivo",
                "maliano", "maltês", "marroquino", "mauriciano", "mauritano", "mexicano", "myanmarense", "micronésio",
                "moçambicano", "moldovo", "monegasco", "mongol", "montenegrino", "namibiano", "nauruano", "nepalês",
                "nicaraguense", "nigerino", "nigeriano", "niuiano", "norueguês", "neozelandês", "omani", "neerlandês",
                "palauano", "palestino", "panamenho", "papua, papuásio", "paquistanês", "paraguaio", "peruano",
                "polonês, polaco", "português", "queniano", "quirguiz", "britânico", "centro-africano", "tcheco",
                "dominicano", "romeno", "ruandês", "russo", "samoano", "santa-lucense", "são-cristovense", "samarinês",
                "santomense", "são-vicentino", "seichelense", "senegalês", "sérvio", "singapurense", "sírio",
                "somaliano, somali", "sri-lankês", "suázi", "sudanês", "sul-sudanês", "sueco", "suíço", "surinamês",
                "tajique", "tailandês", "tanzaniano", "timorense", "togolês", "tonganês", "trinitário", "tunisiano",
                "turcomeno", "turco", "tuvaluano", "ucraniano", "ugandês", "uruguaio", "uzbeque", "vanuatuense",
                "vaticano", "venezuelano", "vietnamita", "zambiano", "zimbabueano"}));
        txtNacionalidade.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
        txtNacionalidade.setSelectedIndex(0);
        contentPane.add(txtNacionalidade);

        JLabel lblNome = new JLabel("NOME");
        lblNome.setBounds(562, 119, 253, 14);
        lblNome.setForeground(SystemColor.textInactiveText);
        lblNome.setFont(new Font("Roboto Black", Font.PLAIN, 18));
        contentPane.add(lblNome);

        JLabel lblSobrenome = new JLabel("SOBRENOME");
        lblSobrenome.setBounds(560, 189, 255, 14);
        lblSobrenome.setForeground(SystemColor.textInactiveText);
        lblSobrenome.setFont(new Font("Roboto Black", Font.PLAIN, 18));
        contentPane.add(lblSobrenome);

        JLabel lblDataN = new JLabel("DATA DE NASCIMENTO");
        lblDataN.setBounds(560, 256, 255, 14);
        lblDataN.setForeground(SystemColor.textInactiveText);
        lblDataN.setFont(new Font("Roboto Black", Font.PLAIN, 18));
        contentPane.add(lblDataN);

        JLabel lblNacionalidade = new JLabel("NACIONALIDADE");
        lblNacionalidade.setBounds(560, 326, 255, 14);
        lblNacionalidade.setForeground(SystemColor.textInactiveText);
        lblNacionalidade.setFont(new Font("Roboto Black", Font.PLAIN, 18));
        contentPane.add(lblNacionalidade);

        JLabel lblTelefone = new JLabel("TELEFONE");
        lblTelefone.setBounds(562, 406, 253, 14);
        lblTelefone.setForeground(SystemColor.textInactiveText);
        lblTelefone.setFont(new Font("Roboto Black", Font.PLAIN, 18));
        contentPane.add(lblTelefone);
        txtTelefone = new JFormattedTextField(Config.getMaskPhone());
        txtTelefone.setFocusLostBehavior(JFormattedTextField.PERSIST);
        txtTelefone.setFont(new Font("Roboto", Font.PLAIN, 16));
        txtTelefone.setBounds(560, 424, 285, 33);
        txtTelefone.setColumns(10);
        txtTelefone.setBackground(Color.WHITE);
        txtTelefone.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        contentPane.add(txtTelefone);

        lblTitulo = new JLabel();
        lblTitulo.setBounds(606, 55, 234, 42);
        lblTitulo.setForeground(new Color(12, 138, 199));
        lblTitulo.setFont(new Font("Roboto Black", Font.PLAIN, 23));
        contentPane.add(lblTitulo);

        JLabel lblNumeroReserva = new JLabel("NÚMERO DE RESERVA");
        lblNumeroReserva.setBounds(560, 474, 253, 14);
        lblNumeroReserva.setForeground(SystemColor.textInactiveText);
        lblNumeroReserva.setFont(new Font("Roboto Black", Font.PLAIN, 18));
        //contentPane.add(lblNumeroReserva);

        txtNreserva = new JTextField();
        txtNreserva.setFont(new Font("Roboto", Font.PLAIN, 16));
        txtNreserva.setBounds(560, 495, 285, 33);
        txtNreserva.setColumns(10);
        txtNreserva.setBackground(Color.WHITE);
        txtNreserva.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        if (reserva != null) {
            txtNreserva.setText(String.valueOf(reserva.getId()));
        }
        //contentPane.add(txtNreserva);

        JSeparator separator_1_2 = new JSeparator();
        separator_1_2.setBounds(560, 170, 289, 2);
        separator_1_2.setForeground(new Color(12, 138, 199));
        separator_1_2.setBackground(new Color(12, 138, 199));
        contentPane.add(separator_1_2);

        JSeparator separator_1_2_1 = new JSeparator();
        separator_1_2_1.setBounds(560, 240, 289, 2);
        separator_1_2_1.setForeground(new Color(12, 138, 199));
        separator_1_2_1.setBackground(new Color(12, 138, 199));
        contentPane.add(separator_1_2_1);

        JSeparator separator_1_2_2 = new JSeparator();
        separator_1_2_2.setBounds(560, 314, 289, 2);
        separator_1_2_2.setForeground(new Color(12, 138, 199));
        separator_1_2_2.setBackground(new Color(12, 138, 199));
        contentPane.add(separator_1_2_2);

        JSeparator separator_1_2_3 = new JSeparator();
        separator_1_2_3.setBounds(560, 386, 289, 2);
        separator_1_2_3.setForeground(new Color(12, 138, 199));
        separator_1_2_3.setBackground(new Color(12, 138, 199));
        contentPane.add(separator_1_2_3);

        JSeparator separator_1_2_4 = new JSeparator();
        separator_1_2_4.setBounds(560, 457, 289, 2);
        separator_1_2_4.setForeground(new Color(12, 138, 199));
        separator_1_2_4.setBackground(new Color(12, 138, 199));
        contentPane.add(separator_1_2_4);

        JSeparator separator_1_2_5 = new JSeparator();
        separator_1_2_5.setBounds(560, 529, 289, 2);
        separator_1_2_5.setForeground(new Color(12, 138, 199));
        separator_1_2_5.setBackground(new Color(12, 138, 199));
        //contentPane.add(separator_1_2_5);

        JButton btnsalvar = new JButton();
        btnsalvar.setBounds(723, 560, 122, 35);
        btnsalvar.addActionListener(e -> {
            salvar();
        });
        btnsalvar.setLayout(null);
        btnsalvar.setBackground(new Color(12, 138, 199));
        contentPane.add(btnsalvar);
        btnsalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        JLabel labelSalvar = new JLabel("SALVAR");
        labelSalvar.setHorizontalAlignment(SwingConstants.CENTER);
        labelSalvar.setForeground(Color.WHITE);
        labelSalvar.setFont(new Font("Roboto", Font.PLAIN, 18));
        labelSalvar.setBounds(0, 0, 122, 35);
        btnsalvar.add(labelSalvar);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 489, 634);
        panel.setBackground(new Color(12, 138, 199));
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel imageFundo = new JLabel("");
        imageFundo.setBounds(0, 121, 479, 502);
        panel.add(imageFundo);
        imageFundo.setIcon(new ImageIcon(HospedeView.class.getResource("/imagenes/registro.png")));

        JLabel logo = new JLabel("");
        logo.setBounds(194, 39, 104, 107);
        panel.add(logo);
        logo.setIcon(new ImageIcon(HospedeView.class.getResource("/imagenes/Ha-100px.png")));
        iniciarValores();
    }

    protected abstract void salvar();

    protected void salvarNaVariavel() {
        hospede.setNome(txtNome.getText());
        hospede.setSobrenome(txtSobrenome.getText());
        hospede.setDataNascimento(txtDataN.getDate());
        hospede.setNacionalidade(txtNacionalidade.getSelectedItem().toString());
        hospede.setTelefone(txtTelefone.getText().replaceAll("[^\\d]", ""));
        reserva.setHospede(hospede);
    }

    protected void iniciarValores() {
        txtNome.setText(hospede.getNome());
        txtSobrenome.setText(hospede.getSobrenome());
        txtDataN.setDate(hospede.getDataNascimento());
        txtTelefone.setText(hospede.getTelefone());
        if (hospede.getNacionalidade() != null) {
            txtNacionalidade.setSelectedItem(hospede.getNacionalidade().toString());
        }
    }

    protected boolean validar() {
        Component comp = null;
        String messagem = null;
        if (txtNome.getText().isBlank()) {
            comp = txtNome;
            messagem = "nome";
        } else if (txtSobrenome.getText().isBlank()) {
            comp = txtSobrenome;
            messagem = "sobrenome";
        } else if (txtTelefone.getText().isBlank() || !Config.validatePhoneMask(txtTelefone.getText())) {
            comp = txtTelefone;
            messagem = "telefone";
        } else if (txtDataN.getDate() == null) {
            comp = txtDataN;
            messagem = "data";
        } else if (txtNacionalidade.getSelectedIndex() == 0) {
            comp = txtNacionalidade;
            messagem = "nacionalidade";
        }
        if (comp != null) {
            comp.requestFocus();
            showMessageDialog(this, "O campo " + messagem + " é requirido!");
            return false;
        }
        return true;
    }

}
