package views.reserva;

import model.Reserva;
import service.ReservaService;
import views.hospede.HospedeViewCadastro;

import java.awt.*;

public class ReservaViewCadastro extends ReservaView {

    private ReservaService service;


    public ReservaViewCadastro(Reserva reserva) {
        super(reserva);
        lblTitulo.setText("CADASTRAR RESERVA");

    }

    @Override
    protected void cadastrar() {
        if (!super.validar()) return;
        new HospedeViewCadastro(reserva);
        dispose();
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ReservaView frame = new ReservaViewCadastro(null);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
