package views.hospede;

import model.Hospede;
import service.HospedeService;
import views.Buscar;
import views.WindowAlert;

import java.awt.event.MouseEvent;

public class HospedeViewEdicao extends HospedeView {

    HospedeService hospedeService;

    public HospedeViewEdicao(Hospede hospede) {
        super(null);
        lblTitulo.setText("EDITAR HOSPEDE #" + hospede.getId());
        hospedeService = new HospedeService();
        this.hospede = hospede;
        super.iniciarValores();
    }

    @Override
    protected void onClickBtnVoltar_(MouseEvent e) {
        new Buscar().setVisible(true);
        dispose();
    }

    @Override
    protected void salvar() {
        if (!super.validar()) return;
        super.salvarNaVariavel();
        hospedeService.update(hospede);
        new WindowAlert("Hospede editado com sucesso!", WindowAlert.Tipos.SUCCESS);
        dispose();
    }

}
