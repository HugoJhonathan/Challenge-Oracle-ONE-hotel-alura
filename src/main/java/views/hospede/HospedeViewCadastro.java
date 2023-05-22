package views.hospede;

import model.Reserva;
import service.ReservaService;
import views.WindowAlert;
import views.reserva.ReservaViewCadastro;

import java.awt.event.MouseEvent;

public class HospedeViewCadastro extends HospedeView {

    @Override
    protected void onClickBtnVoltar_(MouseEvent e) {
        super.salvarNaVariavel();
        new ReservaViewCadastro(reserva).setVisible(true);
        dispose();
    }

    public HospedeViewCadastro(Reserva reserva) {
        super(reserva);
        lblTitulo.setText("CADASTRAR HOSPEDE");
        setVisible(true);
    }

    @Override
    protected void salvar() {
        if (!super.validar()) return;
        super.salvarNaVariavel();
        new ReservaService().save(reserva);
        new WindowAlert("Reserva e Hospede cadastrados com sucesso! Reserva ID: " + reserva.getId() + ", Hospede ID: " + reserva.getHospede().getId(), WindowAlert.Tipos.SUCCESS).setVisible(true);
        dispose();
    }

}