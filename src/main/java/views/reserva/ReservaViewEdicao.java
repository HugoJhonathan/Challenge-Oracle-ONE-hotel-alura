package views.reserva;

import model.Reserva;
import service.ReservaService;
import views.WindowAlert;

import java.awt.*;

public class ReservaViewEdicao extends ReservaView {

    ReservaService reservaService;

    public ReservaViewEdicao(Reserva reserva) {
        super(reserva);
        reservaService = new ReservaService();
        lblSeguinte.setText("SALVAR");
        lblTitulo.setText("EDITAR RESERVA #" + reserva.getId());
        setVisible(true);
    }

    @Override
    protected void cadastrar() {
        if (!super.validar()) return;
        reservaService.update(reserva);
        new WindowAlert("Reserva salva com sucesso!", WindowAlert.Tipos.SUCCESS);
        dispose();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ReservaView frame = new ReservaViewEdicao(null);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
